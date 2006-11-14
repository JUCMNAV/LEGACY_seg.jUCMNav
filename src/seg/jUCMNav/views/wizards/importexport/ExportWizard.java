package seg.jUCMNav.views.wizards.importexport;

import grl.GRLGraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.extensionpoints.IUseCaseMapExport;
import seg.jUCMNav.importexport.HTMLMenuItem;
import seg.jUCMNav.importexport.HTMLMenuParser;
import seg.jUCMNav.importexport.UCMExportExtensionPointHelper;
import seg.jUCMNav.importexport.URNExportExtensionPointHelper;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * Assumption: the default editor for .jucm files is UCMNavMultiPageEditor. Otherwise, a bunch of stuff won't work properly.
 * 
 * Will look into its IStructuredSelection and find all applicable maps.
 * 
 * @author jkealey
 * 
 */
public class ExportWizard extends Wizard implements IExportWizard {

    protected static final String PAGE0 = Messages.getString("ExportWizard.ExportURNorUCM"); //$NON-NLS-1$
    protected static final String PAGE1 = Messages.getString("ExportImageWizard.exportImage"); //$NON-NLS-1$

    /**
     * Removes illegal characters from filenames. Not sure if the complete list is here.
     * 
     * @param name
     * @return cleaned up filename
     */
    public static String cleanFileName(String name) {
        name = name.replace('\\', '_');
        name = name.replace('/', '_');
        name = name.replace(':', '_');
        name = name.replace('*', '_');
        name = name.replace('?', '_');
        name = name.replace('"', '_');
        name = name.replace('<', '_');
        name = name.replace('>', '_');
        name = name.replace('|', '_');
        return name;
    }

    /**
     * mapsToEditor: f(map) |-> UCMNavMultiPageEditor
     * 
     * mapsToSpecificEditor: f(map) |-> UcmEditor
     */
    protected HashMap mapsToEditor, mapsToSpecificEditor;

    /**
     * List of maps to export, first determined by selection when invoking wizard, narrowed down by page 1.
     */
    protected Vector mapsToExport;

    /**
     * List of editors opened by the wizard, which must be closed when done.
     */
    protected Collection openedEditors;

    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage page;

    /**
     * The selection we have in this workbench page (might be resources, might be URNspec/maps in outline).
     */
    protected IStructuredSelection selection;

    /**
     * The diagrams that should be selected by default.
     */
    protected Vector selectedDiagrams;

    /**
     * Initialize preferences.
     */
    public ExportWizard() {
        ExportPreferenceHelper.createPreferences();
    }

    /**
     * Add both pages
     */
    public void addPages() {
        addPage(new ExportWizardTypeSelectionPage(PAGE0));
        addPage(new ExportWizardMapSelectionPage(PAGE1, mapsToExport, mapsToEditor));
    }

    /**
     * Closes the editors opened during the wizard's work.
     */
    private void closedOpenedEditors() {
        for (Iterator iter = openedEditors.iterator(); iter.hasNext();) {
            IEditorPart element = (IEditorPart) iter.next();
            page.closeEditor(element, false);
        }
    }

    /**
     * Adds an entry in both mapsToEditor and mapsToSpecificEditor
     * 
     * @param editor
     * @param diagram
     */
    private void defineMapping(UCMNavMultiPageEditor editor, IURNDiagram diagram) {
        mapsToEditor.put(diagram, editor);
        mapsToSpecificEditor.put(diagram, editor.getEditor(editor.getModel().getUrndef().getSpecDiagrams().indexOf(diagram)));
    }

    /**
     * Saves all images and closes opened editors.
     */
    private boolean doFinish(IProgressMonitor monitor) throws Exception {
        boolean b = ((ExportWizardMapSelectionPage) getPage(PAGE1)).finish();

        if (b) {
            // vector which will be filled with already exported URNspecs
            Vector v = new Vector();

            for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
                if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM) {
                    ExportDiagram((IURNDiagram) iter.next(), !iter.hasNext());
                }
                else
                    ExportURN((IURNDiagram) iter.next(), v);

                monitor.worked(1);
            }
        }
        return b;
    }

    /**
     * Exports a diagram to a file. Uses mapsToExport to find the editor and the preference store to build the file name.
     * 
     * @param diagram
     */
    private void ExportDiagram(IURNDiagram diagram, boolean isLast) throws Exception  {
        FileOutputStream fos = null;

        try {

            // given the export type, get the exporter id
            int imgtype = ExportPreferenceHelper.getPreferenceStore().getInt(ExportPreferenceHelper.PREF_IMAGETYPE);
            String id = UCMExportExtensionPointHelper.getExporterFromLabelIndex(imgtype);

            // generate the path.
            String diagramName = getDiagramName(diagram);
            Path genericPath = new Path(ExportPreferenceHelper.getPreferenceStore().getString(ExportPreferenceHelper.PREF_PATH));
            genericPath = (Path) genericPath.append("/" + diagramName); //$NON-NLS-1$
            genericPath = (Path) genericPath.addFileExtension(UCMExportExtensionPointHelper.getFilenameExtension(id));

            // get the simple editor
            UrnEditor editor = (UrnEditor) mapsToSpecificEditor.get(diagram);

            if (UCMExportExtensionPointHelper.isUseStream(id)) {
                // prepare file stream
                fos = new FileOutputStream(genericPath.toOSString());
            }

            // get exporter
            IUseCaseMapExport exporter = (IUseCaseMapExport) UCMExportExtensionPointHelper.getExporter(id);

            // save it
            if (UCMExportExtensionPointHelper.getMode(id).equals("image")) { //$NON-NLS-1$
                // get the high level IFigure to be saved.
                LayeredPane pane = ((URNRootEditPart) (editor.getGraphicalViewer().getRootEditPart())).getScaledLayers();
                if (UCMExportExtensionPointHelper.isUseStream(id)) {
                    exporter.export(pane, fos);
                } else {
                    exporter.export(pane, genericPath.toOSString());
                }
            } else if (UCMExportExtensionPointHelper.getMode(id).equals("html")) {
            	// Export image files
            	FileOutputStream imgFos = null;
            	String imgPath = createImgPath(genericPath.toOSString());
                
            	// get the high level IFigure to be saved.
                LayeredPane pane = ((URNRootEditPart) (editor.getGraphicalViewer().getRootEditPart())).getScaledLayers();
            	try {
            		imgFos = new FileOutputStream(imgPath);
            		
                    
                    if (UCMExportExtensionPointHelper.isUseStream(id)) {
                    	exporter.export(pane, imgFos);
                    } else {
                        exporter.export(pane, imgPath);
                    }
            	} finally {
                    // close the stream
                    if (imgFos != null) {
                        try {
                        	imgFos.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                
				// export the index pages 
                String htmlPath = getPath(genericPath.toOSString());
                
                if (isLast) {
                	createIndexPages(htmlPath);
                }
                
                // prepare the html menu item
                HTMLMenuItem htmlMenuItem = new HTMLMenuItem();
                htmlMenuItem.reset();
                
                htmlMenuItem.setDiagramName(diagramName);
                if (diagramName.indexOf("GRLGraph") > -1) {
                    htmlMenuItem.setType(HTMLMenuItem.TYPE_GRL);
                } else {
                    htmlMenuItem.setType(HTMLMenuItem.TYPE_UCM);
                }
                htmlMenuItem.setLeafText(diagramName.substring(diagramName.lastIndexOf("-") + 1));
                htmlMenuItem.setLink(diagramName + ".html");
                htmlMenuItem.setBaseX(-pane.getBounds().x);
                htmlMenuItem.setBaseY(-pane.getBounds().y);
                htmlMenuItem.setDiagram(diagram);
                
                // export html files for each map
                if (UCMExportExtensionPointHelper.isUseStream(id)) {
                    exporter.export((IURNDiagram) editor.getModel(), fos);
                } else {
                    exporter.export((IURNDiagram) editor.getModel(), genericPath.toOSString());
                }
                
                // create the XML menu content
                HTMLMenuParser htmlMenuParser = HTMLMenuParser.getParser(htmlPath);
                htmlMenuParser.addMenu(htmlMenuItem);
                
                // write the content of menu to XML file
                if (isLast) {
                    htmlMenuParser.writeToFile();
                    htmlMenuParser.resetDocument();
                }
            } else {
                // model instance
                if (UCMExportExtensionPointHelper.isUseStream(id)) {
                    exporter.export((IURNDiagram) editor.getModel(), fos);
                } else {
                    exporter.export((IURNDiagram) editor.getModel(), genericPath.toOSString());
                }
            }

//        } catch (InvocationTargetException e) {
//            Throwable realException = e.getTargetException();
//            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, realException.toString(), realException);
//            ErrorDialog.openError(getShell(), Messages.getString("ExportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
//            return;
//        } catch (Exception e) {
//            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, e.toString(), e);
//            ErrorDialog.openError(getShell(), Messages.getString("ExportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
//            return;
        } finally {
            // close the stream
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
    
    /**
     * Create index html pages used in exporting UCM/GRL maps to html pages.
     * 
     * @param htmlPath
     */
    private void createIndexPages(String htmlPath) {
        try {
            // Generate the index page
            String srcFile = "htmltemplates/index.html";
            String desFile = htmlPath + "index.html";
            copy(srcFile, desFile);

            // Generate the main page
            srcFile = "htmltemplates/main.html";
            desFile = htmlPath + "main.html";
            copy(srcFile, desFile);

            // Generate the menu page
            srcFile = "htmltemplates/menu.html";
            desFile = htmlPath + "menu.html";
            copy(srcFile, desFile);

            // Generate the xml tree css file
            srcFile = "htmltemplates/xmlTree.css";
            desFile = htmlPath + "xmlTree.css";
            copy(srcFile, desFile);

            // Generate the report css file
            srcFile = "htmltemplates/report.css";
            desFile = htmlPath + "report.css";
            copy(srcFile, desFile);
            
            // Generate the menu css file
            srcFile = "htmltemplates/menu.css";
            desFile = htmlPath + "menu.css";
            copy(srcFile, desFile);

            // Generate the tree dtd file
            // merged dtd into xml due to path issue when parsing
            /*
            srcFile = "htmltemplates/tree.dtd";
            desFile = htmlPath + "tree.dtd";
            copy(srcFile, desFile);
            */

            // Generate the xml tree java script file
            srcFile = "htmltemplates/xmlTree.js";
            desFile = htmlPath + "xmlTree.js";
            copy(srcFile, desFile);
            
            // Generate the menu java script file
            srcFile = "htmltemplates/menu.js";
            desFile = htmlPath + "menu.js";
            copy(srcFile, desFile);

            // Generate the tree xsl file
            srcFile = "htmltemplates/xmlTree.xsl";
            desFile = htmlPath + "xmlTree.xsl";
            copy(srcFile, desFile);

            // Generate the closed.gif file
            srcFile = "htmltemplates/closed.gif";
            desFile = htmlPath + "closed.gif";
            copy(srcFile, desFile);

            // Generate the doc.gif file
            srcFile = "htmltemplates/doc.gif";
            desFile = htmlPath + "doc.gif";
            copy(srcFile, desFile);

            // Generate the open.gif file
            srcFile = "htmltemplates/open.gif";
            desFile = htmlPath + "open.gif";
            copy(srcFile, desFile);
            
            // Generate the open.gif file
            srcFile = "htmltemplates/LogoFinal.gif";
            desFile = htmlPath + "LogoFinal.gif";
            copy(srcFile, desFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Copies src file to dst file. If the dst file does not exist, it is created.
     * 
     * @param srcPath
     * @param dstPath
     * @throws IOException
     */
    private void copy(String srcPath, String dstPath) throws IOException {
        Class location = JUCMNavPlugin.class;

        InputStream in = location.getResourceAsStream(srcPath);
        OutputStream out = new FileOutputStream(new File(dstPath));

        // Transfer bytes from in to out
        int bufLen = 1024 * 8;
        byte[] buf = new byte[bufLen];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    /**
     * Create path of exported image files when exporting UCM/GRL maps to html pages.
     * 
     * @param imgPath
     * @return the path of exported image files
     */
    private String createImgPath(String imgPath) {
        String newPath = "";
        String imgDirectoryPath = "";

        // Add "\img" into the path
        int indexOfLastSlash = imgPath.lastIndexOf("\\");
        imgDirectoryPath = imgPath.substring(0, indexOfLastSlash) + "\\img";
        newPath = imgDirectoryPath + imgPath.substring(indexOfLastSlash);

        // Replace the extension ".html" to be ".jpg"
        int indexOfExt = newPath.lastIndexOf(".");
        newPath = newPath.substring(0, indexOfExt) + ".jpg";

        // Create img directory
        File imgDirectory = new File(imgDirectoryPath);
        if (!imgDirectory.exists()) {
            imgDirectory.mkdirs();
        }

        return newPath;
    }

    /**
     * Get the directory path from a full path url
     * 
     * @param fullPath
     * @return the directory path
     */
    private String getPath(String fullPath) {
        String directoryPath = "";
        
        int indexOfLastSlash = fullPath.lastIndexOf("\\");
        directoryPath = fullPath.substring(0, indexOfLastSlash) + "\\";

        return directoryPath;
    }

	/**
     * Exports a URNSpec to a file. Uses mapsToExport to find the editor and the preference store to build the file name.
     * 
     * Won't export the URNSpec which contains map if it is already contained in v
     * 
     * @param diagram
     * @param v
     */

    private void ExportURN(IURNDiagram diagram, Vector v) throws Exception {
        // dont output same URN multiple times.
        if (v.contains(diagram.getUrndefinition().getUrnspec()))
            return; // we've already exported this URNSpec
        v.add(diagram.getUrndefinition().getUrnspec());

        FileOutputStream fos = null;
        try {

            // given the export type, get the exporter id
            int imgtype = ExportPreferenceHelper.getPreferenceStore().getInt(ExportPreferenceHelper.PREF_IMAGETYPE);
            String id = URNExportExtensionPointHelper.getExporterFromLabelIndex(imgtype);

            // generate the path.
            Path genericPath = new Path(ExportPreferenceHelper.getPreferenceStore().getString(ExportPreferenceHelper.PREF_PATH));
            genericPath = (Path) genericPath.append("/" + ExportPreferenceHelper.getFilenamePrefix());  //$NON-NLS-1$
            genericPath = (Path) genericPath.addFileExtension(URNExportExtensionPointHelper.getFilenameExtension(id));

            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) mapsToEditor.get(diagram);

            // get exporter
            IURNExport exporter = (IURNExport) URNExportExtensionPointHelper.getExporter(id);

            if (URNExportExtensionPointHelper.isUseStream(id)) {
                // prepare file stream
                fos = new FileOutputStream(genericPath.toOSString());

                // save it
                exporter.export(editor.getModel(), fos);
            } else {
                // save it
                exporter.export(editor.getModel(), genericPath.toOSString());
            }

            // export individual maps if desired
            if (URNExportExtensionPointHelper.getPostExporter(id).length() > 0) {
                int oldSelection = ExportPreferenceHelper.getImageType();

                // go through the individual diagram exporters; find the index of the required one
                for (int i = 0; i < UCMExportExtensionPointHelper.getExportLabels().length; i++) {
                    if (UCMExportExtensionPointHelper.getExporterFromLabelIndex(i).equals(URNExportExtensionPointHelper.getPostExporter(id))) {
                        // set it in our preferences.
                        ExportPreferenceHelper.setImageType(i);
                        break;
                    }
                }
                // export the individual diagrams
                for (Iterator iter = editor.getModel().getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
                    IURNDiagram element = (IURNDiagram) iter.next();
                    ExportDiagram(element, !iter.hasNext());
                }

                // set back the original preference so that the URN export is the same next time.
                ExportPreferenceHelper.setImageType(oldSelection);

            }

//        } catch (InvocationTargetException e) {
//            Throwable realException = e.getTargetException();
//            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, realException.toString(), realException);
//            ErrorDialog.openError(getShell(), Messages.getString("ExportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
//        } catch (Exception e) {
//            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, e.toString(), e);
//            ErrorDialog.openError(getShell(), Messages.getString("ExportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
        } finally {
            // close the stream
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 
     * @param diagram
     * @return the prefix of the file containing the diagram; assumes .jucm extension
     */
    public String getFilePrefix(IURNDiagram diagram) {
        
        String filename = ExportPreferenceHelper.getFilenamePrefix();
        //((FileEditorInput) editor.getEditorInput()).getName();
        // remove the .jucm extension
        if (filename.length()>5 && filename.substring(filename.length() - 5).equals(".jucm")){ //$NON-NLS-1$
            filename = filename.substring(0, filename.length() - 5);
        }
        return filename;
    }

    /**
     * Returns a name for the diagram, build from the filename it is contained, its id and its map's name, without extension; uses mapsToEditor
     * 
     * To be used to save to disk.
     * 
     * @param diagram
     * @return map name
     */
    public String getDiagramName(IURNDiagram diagram) {
        String filename = getFilePrefix(diagram);

        String name = ((URNmodelElement) diagram).getName();
        name = cleanFileName(name);

        String result = ""; //$NON-NLS-1$
        if (diagram instanceof UCMmap) {
            result = filename + "-" + "Map" + ((UCMmap) diagram).getId() + "-" + name; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else if (diagram instanceof GRLGraph) {
            result = filename + "-" + "GRLGraph" + ((GRLGraph) diagram).getId() + "-" + name; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
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
        this.mapsToExport = new Vector();
        this.openedEditors = new Vector();
        this.mapsToEditor = new HashMap();
        this.mapsToSpecificEditor = new HashMap();

        setWindowTitle(Messages.getString("ExportImageWizard.exportImageWizard")); //$NON-NLS-1$

        // filter ifile resources
        this.selection = currentSelection;
        List selectedResources = IDE.computeSelectedResources(currentSelection);
        if (!selectedResources.isEmpty()) {
            this.selection = new StructuredSelection(selectedResources);
        }

        IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
        for (Iterator iter = this.selection.iterator(); iter.hasNext();) {
            Object obj = iter.next();

            // if is an IFile, extract all contained maps.
            if (obj instanceof IFile) {
                IFile element = (IFile) obj;
                IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(element.getName());
                UCMNavMultiPageEditor editor = null;
                FileEditorInput input = new FileEditorInput(element);
                try {
                    editor = (UCMNavMultiPageEditor) page.findEditor(input);
                    // if editor isn't opened, open it.
                    if (editor == null) {
                        editor = (UCMNavMultiPageEditor) page.openEditor(input, desc.getId(), false);
                        // to be able to close them later.
                        openedEditors.add(editor);
                    }
                    //Set the filename prefix
                    ExportPreferenceHelper.setFilenamePrefix(editor.getEditorInput().getName());
                } catch (ClassCastException e) {
                    // if default editor isn't UCMNavMultiPageEditor
                    e.printStackTrace();
                } catch (PartInitException e) {
                    e.printStackTrace();
                }

                // add all maps.
                this.mapsToExport.addAll(editor.getModel().getUrndef().getSpecDiagrams());
                for (Iterator iterator = editor.getModel().getUrndef().getSpecDiagrams().iterator(); iterator.hasNext();) {
                    IURNDiagram diagram = (IURNDiagram) iterator.next();
                    defineMapping(editor, diagram);
                }
            } else {
                // is a Diagram or URNSpec; extract diagram from those.
                IEditorPart editorpart = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                if (editorpart instanceof UCMNavMultiPageEditor) {
                    UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) editorpart;
                    // Set the filename prefix
                    ExportPreferenceHelper.setFilenamePrefix(editor.getEditorInput().getName());
                    
                    if (obj instanceof IURNDiagram) {
                        IURNDiagram diagram = (IURNDiagram) obj;
                        addSelectedDiagram(diagram);
                        // this.mapsToExport.add(obj);
                        // defineMapping(editor, diagram);
                        // define all mappings because they are used by some exports
                        for (Iterator iterator = diagram.getUrndefinition().getSpecDiagrams().iterator(); iterator.hasNext();) {
                            IURNDiagram diagram2 = (IURNDiagram) iterator.next();
                            this.mapsToExport.add(diagram2);
                            defineMapping(editor, diagram2);
                        }
                    } else if (obj instanceof URNspec) {
                        this.mapsToExport.addAll(((URNspec) obj).getUrndef().getSpecDiagrams());
                        for (Iterator iterator = ((URNspec) obj).getUrndef().getSpecDiagrams().iterator(); iterator.hasNext();) {
                            IURNDiagram diagram = (IURNDiagram) iterator.next();
                            defineMapping(editor, diagram);
                        }
                    }
                }
            }
          }

    }

    /**
     * Closes opened editors.
     */
    public boolean performCancel() {
        closedOpenedEditors();
        return true;
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    monitor.beginTask(Messages.getString("ExportWizard.Exporting"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
                    doFinish(monitor);
                } catch (Exception ex) {
                	if (ex instanceof InvocationTargetException)
                		throw (InvocationTargetException)ex;
                	else 
                		throw new InvocationTargetException(ex);
                }finally {
                    monitor.done();
                }
            }

        };
        try {
            ((ExportWizardMapSelectionPage) getPage(PAGE1)).preFinish();
            getContainer().run(true, false, op);
            closedOpenedEditors();

        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), Messages.getString("ExportImageWizard.error"), realException.getMessage()); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * When the export type changes, one should refresh page 1 as its contents must change.
     */
    public void refreshPages() {
        ((ExportWizardMapSelectionPage) getPage(PAGE1)).refresh();
    }

    /**
     * 
     * @return the list of selected diagrams
     */
    public Vector getSelectedDiagrams() {
        if (selectedDiagrams == null)
            selectedDiagrams = new Vector();
        return selectedDiagrams;
    }

    /**
     * Add a diagram to the list of selected diagrams
     * 
     * @param d
     *            the diagram to add.
     */
    public void addSelectedDiagram(IURNDiagram d) {
        getSelectedDiagrams().add(d);
    }

}