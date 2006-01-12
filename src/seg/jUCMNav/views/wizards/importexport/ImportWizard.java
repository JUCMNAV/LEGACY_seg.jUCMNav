package seg.jUCMNav.views.wizards.importexport;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.importexport.ImportUCEd;
import urn.URNspec;

/**
 * *
 * 
 * @author jkealey
 * 
 */
public class ImportWizard extends Wizard implements IImportWizard {

    protected static final String PAGE0 = "Import File";
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
        addPage(new ImportWizardFileSelectionPage(PAGE0));
    }

    /**
     * Saves all images and closes opened editors.
     */
    private boolean doFinish(IProgressMonitor monitor) throws InvocationTargetException {
        boolean b = ((ImportWizardFileSelectionPage) getPage(PAGE0)).finish();

        if (b) {
            ImportUCEd importer = new ImportUCEd();
            newurn = importer.importURN(ImportPreferenceHelper.getPath());
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
        // editor = (UCMNavMultiPageEditor) page.getActiveEditor();
        setWindowTitle("Import File");
    }

    /**
     * Closes opened editors.
     */
    public boolean performCancel() {
        return true;
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {

        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
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

            if (success) {
                jUCMNavLoader loader = new jUCMNavLoader(page, getShell());
                loader.createAndOpenFile(ImportPreferenceHelper.getPath(), ImportPreferenceHelper.getProject(), newurn, ImportPreferenceHelper.getAutoLayout(),
                        ((ImportWizardFileSelectionPage) getPage(PAGE0)).overwrite);
            }

        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, realException.toString(), realException);
            ErrorDialog.openError(getShell(), "An error has occurred", e.getMessage(), error);
            return false;
        } catch (Exception e) {
            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, e.toString(), e);
            ErrorDialog.openError(getShell(), "An error has occurred", e.getMessage(), error);
            return false;
        }
        return success;
    }

}