package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;

import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.editparts.GraphicalEditPartFactory;
import seg.jUCMNav.editparts.UCMConnectionOnBottomRootEditPart;
import ucm.map.UCMmap;
import urncore.IURNDiagram;

/**
 * This is the main class for editing a single Map in our model.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class UcmEditor extends UrnEditor {

    /** one editor per map. */
    private UCMmap mapModel;

    /** Create a new UcmEditor instance. This is called by the Workspace. */
    public UcmEditor(UCMNavMultiPageEditor parent) {
        super(parent);
    }

    /**
     * Configure the graphical viewer before it receives contents.
     * <p>
     * This is the place to choose an appropriate RootEditPart and EditPartFactory for your editor. The RootEditPart determines the behavior of the editor's
     * "work-area". For example, GEF includes zoomable and scrollable root edit parts. The EditPartFactory maps model elements to edit parts (controllers).
     * </p>
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
     */
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();

        ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();
        root = new UCMConnectionOnBottomRootEditPart(getParent());

        // zoom management is delegated to us from our parent.
        List zoomLevels = new ArrayList(3);
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        viewer.setRootEditPart(root);

        registerContextMenuProvider(viewer);

        viewer.setEditPartFactory(new GraphicalEditPartFactory((UCMmap) getModel()));
        KeyHandler handler = new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler());
        viewer.setKeyHandler(handler);
        getEditDomain().getPaletteViewer().setKeyHandler(handler);
    }

    /**
     * Overridden to change to public visibility.
     */
    public CommandStack getCommandStack() {
        return getEditDomain().getCommandStack();

    }

    /**
     * Return the root model of this editor.
     * 
     * @return The model we are editing.
     */
    public IURNDiagram getModel() {
        return mapModel;
    }

    /**
     * Returns the palette's preferences.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
     */
    protected FlyoutPreferences getPalettePreferences() {
        return UcmPaletteRoot.createPalettePreferences();
    }

    /**
     * Returns the default <code>PaletteRoot</code> for this editor and all its pages.
     * 
     * @return the default <code>PaletteRoot</code>
     */
    public PaletteRoot getPaletteRoot() {
        if (null == paletteRoot) {
            // create root
            paletteRoot = new UcmPaletteRoot(parent);
        }
        return paletteRoot;
    }

    /**
     * Set up the editor's inital content (after creation).
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
     */
    protected void initializeGraphicalViewer() {
        GraphicalViewer graphicalViewer = getGraphicalViewer();
        graphicalViewer.setContents(getModel()); // set the contents of this editor
        // listen for dropped parts
        graphicalViewer.addDropTargetListener(getTransferDropTargetListener());
        graphicalViewer.addDropTargetListener(getUrnTransferDropTargetListener());

    }

    /**
     * Sets the map to be manipulated by this editor
     * 
     * @param m
     *            the {@link UCMmap}
     */
    public void setModel(IURNDiagram m) {
        mapModel = (UCMmap) m;
    }

    public void dispose() {

        if (getGraphicalViewer() != null) {
            getGraphicalViewer().removeDropTargetListener(getTransferDropTargetListener());
            getGraphicalViewer().removeDropTargetListener(getUrnTransferDropTargetListener());

        }
        mapModel = null;
        super.dispose();
    }

}