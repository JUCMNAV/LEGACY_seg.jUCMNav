package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;

import asd.ASDiagram;
import seg.jUCMNav.editors.palette.AsdPaletteRoot;
import seg.jUCMNav.editparts.ASDConnectionOnBottomRootEditPart;
import seg.jUCMNav.editparts.AsdGraphicalEditPartFactory;
import ucm.map.UCMmap;
import urncore.IURNDiagram;


/**
 * This is the main class for editing a single Map in our model.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class AsdEditor extends UrnEditor {

	
    /** one editor per asdiagram. */
    private ASDiagram asdiagram;

    /** Create a new UcmEditor instance. This is called by the Workspace. */
    public AsdEditor(UCMNavMultiPageEditor parent) {
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
        root = new ASDConnectionOnBottomRootEditPart(getParent());

        // zoom management is delegated to us from our parent.
        List zoomLevels = new ArrayList(3);
       zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        /*zoomLevels.add(ZoomManager.ANIMATE_NEVER);*/
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        viewer.setRootEditPart(root);

        registerContextMenuProvider(viewer);

        viewer.setEditPartFactory(new AsdGraphicalEditPartFactory((ASDiagram) getModel()));
        KeyHandler handler = new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler());
        viewer.setKeyHandler(handler);
        getEditDomain().getPaletteViewer().setKeyHandler(handler);
        getGraphicalViewer().addDropTargetListener(new TemplateTransferDropTargetListener(getGraphicalViewer()));
        getEditDomain().getPaletteViewer().addDragSourceListener(
        		new TemplateTransferDragSourceListener(getEditDomain().getPaletteViewer()));
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
        return asdiagram;
    }

    /**
     * Returns the palette's preferences.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
     */
    protected FlyoutPreferences getPalettePreferences() {
        return AsdPaletteRoot.createPalettePreferences();
    	//return null;
    }

    
    public PaletteRoot getPaletteRoot() {
        if (null == paletteRoot) {
            // create root
          //  paletteRoot = new AsdPaletteRoot(parent);
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
        

    public void setModel(IURNDiagram m) {
        asdiagram = (ASDiagram) m;
    }

    public void dispose() {
 
        if (getGraphicalViewer() != null) {
            getGraphicalViewer().removeDropTargetListener(getTransferDropTargetListener());
            getGraphicalViewer().removeDropTargetListener(getUrnTransferDropTargetListener());

        }
        asdiagram = null;
        super.dispose(); 
    }
    
    

}