package seg.jUCMNav.editors;

import fm.FeatureDiagram;
import grl.GRLGraph;

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

import seg.jUCMNav.editors.palette.FmdPaletteRoot;
import seg.jUCMNav.editors.palette.GrlPaletteRoot;
import seg.jUCMNav.editparts.GrlConnectionOnBottomRootEditPart;
import seg.jUCMNav.editparts.GrlGraphicalEditPartFactory;
import urncore.IURNDiagram;

/**
 * This is the main class for editing a single GRLGraph in our model.
 * 
 * @author Jean-François Roy
 */

public class GrlEditor extends UrnEditor {

    private GRLGraph graphModel;
    
    /**
     * Create a new GrlEditor instance. This is called by the Workspace.
     * 
     */
    public GrlEditor(UCMNavMultiPageEditor parent) {
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
        // Root editpart that include a zoom manager
        root = new GrlConnectionOnBottomRootEditPart(getParent());

        // zoom management is delegated to us from our parent.
        List zoomLevels = new ArrayList(3);
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        viewer.setRootEditPart(root);

        registerContextMenuProvider(viewer);

        viewer.setEditPartFactory(new GrlGraphicalEditPartFactory((GRLGraph) getModel()));
        KeyHandler handler = new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler());
        viewer.setKeyHandler(handler);
        getEditDomain().getPaletteViewer().setKeyHandler(handler);

    }

    /**
     * Overiden to change the visibility
     */
    public CommandStack getCommandStack() {
        return getEditDomain().getCommandStack();
    }

    /**
     * Return the model of this editor
     * 
     * @return The model of this editor
     */
    public IURNDiagram getModel() {
        return graphModel;
    }

    /**
     * Returns the palette's preferences.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
     */
    protected FlyoutPreferences getPalettePreferences() {
        return GrlPaletteRoot.createPalettePreferences();
    }

    /**
     * Returns the default <code>PaletteRoot</code> for this editor and all its pages.
     * 
     * @return the default <code>PaletteRoot</code>
     */
    public PaletteRoot getPaletteRoot() {
    	if (graphModel == null) return paletteRoot;
        if (null == paletteRoot) {
        	if (graphModel instanceof FeatureDiagram) {
                paletteRoot = new FmdPaletteRoot(parent);
        	} else {
                paletteRoot = new GrlPaletteRoot(parent);
        	}
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
     * Set the model of this editor
     * 
     * @param model
     *            a {@link GRLGraph}
     */
    public void setModel(IURNDiagram model) {
        graphModel = (GRLGraph) model;
    	getEditDomain().setPaletteRoot(getPaletteRoot());
    }

    public void dispose() {
        if (getGraphicalViewer() != null) {
            getGraphicalViewer().removeDropTargetListener(getTransferDropTargetListener());
            getGraphicalViewer().removeDropTargetListener(getUrnTransferDropTargetListener());
        }

        graphModel = null;
        super.dispose();
    }

}
