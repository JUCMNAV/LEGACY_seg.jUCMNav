package seg.jUCMNav.views.wizards;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
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
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.editparts.ConnectionOnBottomRootEditPart;
import ucm.map.Map;
import urn.URNspec;

/**
 * Created on 3-Jun-2005
 * 
 * Assumption: the default editor for .jucm files is UCMNavMultiPageEditor. Otherwise, a bunch of stuff won't work properly.
 * 
 * Will look into its IStructuredSelection and find all applicable maps.
 * 
 * @author jkealey
 *  
 */
public class ExportImageWizard extends Wizard implements IExportWizard {
    public final static int DEFAULTIMAGETYPE = 1;

    public final static String DEFAULTPATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();

    protected static final String PAGE1 = "Export Image";
    public final static String PREF_IMAGETYPE = "seg.jUCMNav.ExportImage.ImageType";
    public final static String PREF_PATH = "seg.jUCMNav.ExportImage.Path";

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

    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }
    protected HashMap mapsToEditor;
    protected Vector mapsToExport;
    protected Collection openedEditors;
    protected IStructuredSelection selection;
    protected IWorkbench workbench;

    /**
     *  
     */
    public ExportImageWizard() {
        createPreferences();
    }

    public void addPages() {
        addPage(new ExportImageWizardPage(PAGE1, mapsToExport, mapsToEditor));
    }

    /**
     * Closes the editors opened during the wizard's work.
     */
    private void closedOpenedEditors() {
        IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
        for (Iterator iter = openedEditors.iterator(); iter.hasNext();) {
            IEditorPart element = (IEditorPart) iter.next();
            page.closeEditor(element, false);
        }
    }

    /**
     * Sets the default values for the export wizard.
     */
    private void createPreferences() {
        getPreferenceStore().setDefault(ExportImageWizard.PREF_PATH, ExportImageWizard.DEFAULTPATH);
        getPreferenceStore().setDefault(ExportImageWizard.PREF_IMAGETYPE, ExportImageWizard.DEFAULTIMAGETYPE);
    }

    /**
     * Exports a map to a file. Uses mapsToExport to find the editor and the preference store to build the file name.
     * 
     * @param map
     */
    private void ExportMap(Map map) {
        FileOutputStream fos = null;
        try {
            // get the multi editor
            UCMNavMultiPageEditor multieditor = (UCMNavMultiPageEditor) mapsToEditor.get(map);
            // find the index of the given map
            int i = multieditor.getModel().getUcmspec().getMaps().indexOf(map);
            // get the simple editor
            UcmEditor editor = (UcmEditor) (multieditor).getEditor(i);

            // get the high level IFigure to be saved.
            IFigure pane = ((ConnectionOnBottomRootEditPart) (editor.getGraphicalViewer().getRootEditPart())).getScaledLayers();

            // generate image
            Image image = new Image(Display.getCurrent(), pane.getSize().width, pane.getSize().height);
            GC gc = new GC(image);
            SWTGraphics graphics = new SWTGraphics(gc);
            pane.paint(graphics);

            // generate the path.
            Path genericPath = new Path(ExportImageWizard.getPreferenceStore().getString(ExportImageWizard.PREF_PATH));
            genericPath = (Path) genericPath.append("/" + getMapName(map));
            int type = ExportImageWizard.getPreferenceStore().getInt(ExportImageWizard.PREF_IMAGETYPE);
            switch (type) {
            case 0:
                genericPath = (Path) genericPath.addFileExtension("bmp");
                break;
            case 1:
                genericPath = (Path) genericPath.addFileExtension("jpg");
                break;
            }

            // save it.
            fos = new FileOutputStream(genericPath.toOSString());
            ImageLoader loader = new ImageLoader();
            loader.data = new ImageData[] { image.getImageData() };
            switch (type) {
            case 0:
                loader.save(fos, SWT.IMAGE_BMP_RLE);
                break;
            case 1:
                loader.save(fos, SWT.IMAGE_JPEG);
                break;
            }

        } catch (IOException e) {
            System.out.println(e);
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
     * Returns a name for the map, build from the filename it is contained, its id and its map's name, without extension; uses mapsToEditor
     * 
     * To be used to save to disk.
     * 
     * @param map
     * @return map name
     */
    public String getMapName(Map map) {
        UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) mapsToEditor.get(map);
        String filename = ((FileEditorInput) editor.getEditorInput()).getName();
        // remove the .jucm extension
        filename = filename.substring(0, filename.length() - 5);

        String name = map.getName();
        name = cleanFileName(name);
        return filename + "-" + "Map" + map.getId() + "-" + name;
    }

    /**
     * Initializes the pages using the selection
     * 
     * @param workbench
     * @param currentSelection
     *            a collection of .jucm IFiles, Maps or URNspecs
     */
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        this.workbench = workbench;
        this.mapsToExport = new Vector();
        this.openedEditors = new Vector();
        this.mapsToEditor = new HashMap();

        setWindowTitle("Export Image Wizard");

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
                } catch (ClassCastException e) {
                    // if default editor isn't UCMNavMultiPageEditor
                    e.printStackTrace();
                } catch (PartInitException e) {
                    e.printStackTrace();
                }

                // add all maps.
                this.mapsToExport.addAll(editor.getModel().getUcmspec().getMaps());
                for (Iterator iterator = editor.getModel().getUcmspec().getMaps().iterator(); iterator.hasNext();) {
                    Map map = (Map) iterator.next();
                    mapsToEditor.put(map, editor);
                }
            } else {
                // is a Map or URNSpec; extract maps from those.
                IEditorPart editor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                if (obj instanceof Map) {
                    this.mapsToExport.add(obj);
                    this.mapsToEditor.put(obj, editor);
                } else if (obj instanceof URNspec) {
                    this.mapsToExport.addAll(((URNspec) obj).getUcmspec().getMaps());
                    for (Iterator iterator = ((URNspec) obj).getUcmspec().getMaps().iterator(); iterator.hasNext();) {
                        Map map = (Map) iterator.next();
                        this.mapsToEditor.put(map, editor);
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
     * Saves all images and closes opened editors.
     */
    public boolean performFinish() {
        boolean b = ((ExportImageWizardPage) getPage(PAGE1)).finish();

        if (b) {
            for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
                ExportMap((Map) iter.next());
            }

            closedOpenedEditors();
        }
        return b;
    }

}