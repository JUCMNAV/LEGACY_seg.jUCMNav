package seg.jUCMNav.views.wizards.importexport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.importexport.URNImportExtensionPointHelper;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * *
 * 
 * @author jkealey, Jean-Fran�ois Roy
 * 
 */
public class ImportWizard extends Wizard implements IImportWizard {

    protected static final String PAGE0 = Messages.getString("ImportWizard.ImportFile"); //$NON-NLS-1$
    protected URNspec newurn;
    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage page;

    /**
     * The selection we have in this workbench page (might be resources, might be URNspec/maps in outline).
     */
    protected IStructuredSelection selection;

    protected boolean success = false;

    protected boolean hasBeenOpened = false;

    /**
     * Editor open by the wizard.
     */
    protected UCMNavMultiPageEditor openedEditor;

    /**
     * URNspec coming from the selection
     */
    private URNspec urn;

    /**
     * Vector that contains diagram ids to use autolayout
     */
    private Vector autolayoutDiagrams;

    private static String filename;  // the filename of the imported file
    
    public static String getFilename() {
    	return filename;
    }
    
    /**
     * Initialize preferences.
     */
    public ImportWizard() {
        ImportPreferenceHelper.createPreferences();
    }

    /**
     * Add both pages
     */
    public void addPages() {
        addPage(new ImportWizardFileSelectionPage(PAGE0, selection));
    }

    /**
     * Closes the editor opened during the wizard's work.
     */
    private void closedOpenedEditor() {
        if (openedEditor != null && hasBeenOpened) {
            openedEditor.closeEditor(true);
        }
    }

    /**
     * Saves all images and closes opened editors.
     */
    private boolean doFinish(IProgressMonitor monitor) throws InvocationTargetException {
        boolean b = ((ImportWizardFileSelectionPage) getPage(PAGE0)).finish();

        if (b) {
            // If we need didn't import in the current file, set the path
            if (urn == null || ImportPreferenceHelper.getImportType() == ImportPreferenceHelper.IMPORT_NEWFILE) {
                ImportPreferenceHelper.setSavePath(ImportPreferenceHelper.getPath());
            }
            // given the import type, get the exporter id
            int importtype = ImportPreferenceHelper.getType();
            String id = URNImportExtensionPointHelper.getExporterFromLabelIndex(importtype);

            // generate the path.
            String path = ImportPreferenceHelper.getPath();

            IURNImport importer = URNImportExtensionPointHelper.getImporter(id);

            autolayoutDiagrams = new Vector();
            if (URNImportExtensionPointHelper.isUseStream(id)) {
                FileInputStream fis;
                try {
                    fis = new FileInputStream(new File(path));
                    filename = path;
                    // If import type is URN, import the file in this urn
                    if (ImportPreferenceHelper.getImportType() == ImportPreferenceHelper.IMPORT_URN && urn != null) {
                        newurn = importer.importURN(fis, urn, autolayoutDiagrams);
                    } else {
                        newurn = importer.importURN(fis, autolayoutDiagrams);
                    }
                    // found by inforce
                    fis.close();
                } catch (IOException e) {
                    throw new InvocationTargetException(e, Messages.getString("ImportWizard.UnableFindSource")); //$NON-NLS-1$
                }

            } else {
                if (ImportPreferenceHelper.getImportType() == ImportPreferenceHelper.IMPORT_URN && urn != null) {
                    newurn = importer.importURN(path, urn, autolayoutDiagrams);
                } else {
                    newurn = importer.importURN(path, autolayoutDiagrams);
                }
            }

            // newurn = importer.importURN(ImportPreferenceHelper.getPath());
        }
        return b;
    }

    public IWorkbenchPage getPage() {
        return page;
    }

    /**
     * Initializes the pages using the selection
     * 
     * @param workbench
     * @param currentSelection
     *            a collection of .jucm IFiles, Maps or URNspecs
     */
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        this.page = workbench.getActiveWorkbenchWindow().getActivePage();
        this.hasBeenOpened = false;
        // ImportPreferenceHelper.setImportType(ImportPreferenceHelper.IMPORT_NEWFILE);
        ImportPreferenceHelper.setSavePath(ImportPreferenceHelper.getPath());
        // editor = (UCMNavMultiPageEditor) page.getActiveEditor();
        setWindowTitle(Messages.getString("ImportWizard.ImportFile")); //$NON-NLS-1$

        // Set the import type
        // given the import type, get the exporter id
        int importtype = ImportPreferenceHelper.getType();
        String id = URNImportExtensionPointHelper.getExporterFromLabelIndex(importtype);

        if (URNImportExtensionPointHelper.isImportInSelectedFile(id)) {
            ImportPreferenceHelper.setImportType(ImportPreferenceHelper.IMPORT_URN);
        } else {
            ImportPreferenceHelper.setImportType(ImportPreferenceHelper.IMPORT_NEWFILE);
        }

        // Opened the selected editor and get the urnspec if option choose in plugin.xml
        if (ImportPreferenceHelper.getImportType() == ImportPreferenceHelper.IMPORT_URN) {
            // filter ifile resources
            this.selection = currentSelection;
            List selectedResources = IDE.computeSelectedResources(currentSelection);
            if (!selectedResources.isEmpty()) {
                this.selection = new StructuredSelection(selectedResources);
            }

            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            for (Iterator iter = this.selection.iterator(); iter.hasNext();) {
                Object obj = iter.next();

                // if is an IFile, open the editor and extract the URNspec
                if (obj instanceof IFile) {
                    IFile element = (IFile) obj;
                    IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(element.getName());
                    openedEditor = null;
                    FileEditorInput input = new FileEditorInput(element);
                    try {
                        openedEditor = (UCMNavMultiPageEditor) page.findEditor(input);
                        // if editor isn't opened, open it.
                        if (openedEditor == null) {
                            openedEditor = (UCMNavMultiPageEditor) page.openEditor(input, desc.getId(), false);
                            this.hasBeenOpened = true;
                        }
                        urn = openedEditor.getModel();
                        ImportPreferenceHelper.setSavePath(((FileEditorInput) openedEditor.getEditorInput()).getPath().toOSString());
                        // ImportPreferenceHelper.setImportType(ImportPreferenceHelper.IMPORT_URN);
                    } catch (ClassCastException e) {
                        // if default editor isn't UCMNavMultiPageEditor
                        e.printStackTrace();
                    } catch (PartInitException e) {
                        e.printStackTrace();
                    }
                } else {
                    // is a Diagram or URNSpec; extract diagram from those.
                    IEditorPart editorpart = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                    if (editorpart instanceof UCMNavMultiPageEditor) {
                        UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) editorpart;
                        if (obj instanceof IURNDiagram) {
                            IURNDiagram diagram = (IURNDiagram) obj;
                            urn = diagram.getUrndefinition().getUrnspec();
                            openedEditor = (UCMNavMultiPageEditor) editorpart;
                            ImportPreferenceHelper.setSavePath(((FileEditorInput) openedEditor.getEditorInput()).getPath().toOSString());
                            // ImportPreferenceHelper.setImportType(ImportPreferenceHelper.IMPORT_URN);
                        } else if (obj instanceof URNspec) {
                            urn = (URNspec) obj;
                            openedEditor = (UCMNavMultiPageEditor) editorpart;
                            ImportPreferenceHelper.setSavePath(((FileEditorInput) openedEditor.getEditorInput()).getPath().toOSString());
                            // ImportPreferenceHelper.setImportType(ImportPreferenceHelper.IMPORT_URN);
                        }
                    }
                }
            }
        } else
            this.selection = currentSelection;
    }

    /**
     * Closes opened editors.
     */
    public boolean performCancel() {
        closedOpenedEditor();
        return true;
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {

        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    monitor.beginTask(Messages.getString("ImportWizard.Importing"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
                    success = false;
                    success = doFinish(monitor);
                } finally {
                    monitor.done();
                }
            }

        };
        try {
            ((ImportWizardFileSelectionPage) getPage(PAGE0)).preFinish();
            getContainer().run(true, false, op);

            // Close the editor
            closedOpenedEditor();
            jUCMNavLoader loader = new jUCMNavLoader(page, getShell());
            if ((success) && urn != null && ImportPreferenceHelper.getImportType() == ImportPreferenceHelper.IMPORT_URN) {
                if (ImportPreferenceHelper.getAutoLayout()) {
                    loader.createAndOpenFile(ImportPreferenceHelper.getSavePath(), ImportPreferenceHelper.getProject(), newurn, true, true, autolayoutDiagrams);
                } else {
                    loader.createAndOpenFile(ImportPreferenceHelper.getSavePath(), ImportPreferenceHelper.getProject(), newurn, true, true);
                }
            } else if ((success)) {
                if (ImportPreferenceHelper.getAutoLayout()) {
                    loader.createAndOpenFile(ImportPreferenceHelper.getSavePath(), ImportPreferenceHelper.getProject(), newurn, true,
                            ((ImportWizardFileSelectionPage) getPage(PAGE0)).overwrite, autolayoutDiagrams);
                } else {
                    loader.createAndOpenFile(ImportPreferenceHelper.getSavePath(), ImportPreferenceHelper.getProject(), newurn, true,
                            ((ImportWizardFileSelectionPage) getPage(PAGE0)).overwrite);
                }
            }

        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, realException.toString(), realException);
            ErrorDialog.openError(getShell(), Messages.getString("ImportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
            return false;
        } catch (Exception e) {
            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, e.toString(), e);
            ErrorDialog.openError(getShell(), Messages.getString("ImportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
            return false;
        }
        return success;
    }

}