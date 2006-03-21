package seg.jUCMNav.editors;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.ui.internal.PartSite;

import seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider;
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
    
    private PaletteRoot paletteRoot;
   
    /** Create a new GrlEditor instance. This is called by the Workspace. 
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
        //Root editpart that include a zoom manager
        GrlConnectionOnBottomRootEditPart root = new GrlConnectionOnBottomRootEditPart(getParent());

        // zoom management is delegated to us from our parent.
        List zoomLevels = new ArrayList(3);
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        viewer.setRootEditPart(root);
        
        ContextMenuProvider provider = new UrnContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(provider);


        // Bug 381: 3.1: remove extra items from contextual menus
        // getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider", provider, viewer); //$NON-NLS-1$
        ArrayList menuExtenders = new ArrayList(1);
        PartSite.registerContextMenu("seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider", provider, viewer, true, //$NON-NLS-1$
                this, menuExtenders);
        if (menuExtenders.get(0)!=null)
        	provider.removeMenuListener((IMenuListener)menuExtenders.get(0));

        
        viewer.setEditPartFactory(new GrlGraphicalEditPartFactory((GRLGraph)getModel()));
        viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler()));
    }
    
    /**
     * Allows dragging from the palette into the editor.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
     */
    protected PaletteViewerProvider createPaletteViewerProvider() {
        return new PaletteViewerProvider(getEditDomain()) {
            protected void configurePaletteViewer(PaletteViewer viewer) {
                super.configurePaletteViewer(viewer);
                // create a drag source listener for this palette viewer
                // together with an appropriate transfer drop target listener, this will enable
                // model element creation by dragging a CombinatedTemplateCreationEntries
                // from the palette into the editor
                // @see ShapesEditor#createTransferDropTargetListener()
                viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
            }
        };
    }
    
    /**
     * Create a transfer drop target listener. When using a CombinedTemplateCreationEntry tool in the palette, this will enable model element creation by
     * dragging from the palette.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
     */
    private TransferDropTargetListener createTransferDropTargetListener() {
        return new TemplateTransferDropTargetListener(getGraphicalViewer()) {
            protected CreationFactory getFactory(Object template) {
                return new SimpleFactory((Class) template);
            }
        };
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
    public IURNDiagram getModel(){
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
        if (null == paletteRoot) {
            // create root
            paletteRoot = new GrlPaletteRoot(parent);
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
        graphicalViewer.addDropTargetListener(createTransferDropTargetListener());
    }
    
    /**
     * Set the model of this editor
     * 
     * @param model
     */
    public void setModel(IURNDiagram model){
        graphModel = (GRLGraph)model;
    }
}
