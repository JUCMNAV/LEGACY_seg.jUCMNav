package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectDefaultPaletteToolAction;
import seg.jUCMNav.editors.actionContributors.UcmContextMenuProvider;
import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.editparts.ConnectionOnBottomRootEditPart;
import seg.jUCMNav.editparts.GraphicalEditPartFactory;
import seg.jUCMNav.views.outline.UcmOutlinePage;
import ucm.map.Map;

/**
 * This is the main class for editing a single Map in our model.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class UcmEditor extends GraphicalEditorWithFlyoutPalette {

    /** one editor per map. */
    private Map mapModel;

    /** The palette root used to display the palette. */
    private PaletteRoot paletteRoot;

    /** the parent containing the action registry */
    private UCMNavMultiPageEditor parent;

    /** Cache save-request status. */
    private boolean saveAlreadyRequested;

    /** KeyHandler with common bindings for both the Outline View and the Editor. */
    private KeyHandler sharedKeyHandler;

    // our outline page.
    private UcmOutlinePage outline;

    /** Create a new UcmEditor instance. This is called by the Workspace. */
    public UcmEditor(UCMNavMultiPageEditor parent) {
        this.parent = parent;
        setEditDomain(new DefaultEditDomain(this));
    }

    /**
     * Handle events to know when a command was executed. So we can know when we can or cannot save the file.
     */
    public void commandStackChanged(EventObject event) {
        // Note: some actions go directly to this command stack and others go to our parents.

        super.commandStackChanged(event);
        if (isDirty() && !saveAlreadyRequested) {
            saveAlreadyRequested = true;
            firePropertyChange(IEditorPart.PROP_DIRTY);
        } else {
            saveAlreadyRequested = false;
            firePropertyChange(IEditorPart.PROP_DIRTY);
        }
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
        ConnectionOnBottomRootEditPart root = new ConnectionOnBottomRootEditPart(getParent());

        // zoom management is delegated to us from our parent.
        List zoomLevels = new ArrayList(3);
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        viewer.setRootEditPart(root);

        ContextMenuProvider provider = new UcmContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(provider);
        getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.UcmContextMenuProvider", provider, viewer); //$NON-NLS-1$

        viewer.setEditPartFactory(new GraphicalEditPartFactory(getModel()));
        viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler()));
    }

    /**
     * Create all the actions in the action registry.
     * 
     * Now done in multipage.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    protected void createActions() {
        // now done in MultiPage
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
     * @see #createPaletteViewerProvider()
     */
    private TransferDropTargetListener createTransferDropTargetListener() {
        return new TemplateTransferDropTargetListener(getGraphicalViewer()) {
            protected CreationFactory getFactory(Object template) {
                return new SimpleFactory((Class) template);
            }
        };
    }

    /**
     * Used when UcmEditor was the root model element.
     * 
     * @deprecated
     */
    public void doSave(IProgressMonitor monitor) {
        System.out.println(Messages.getString("UcmEditor.oldSave")); //$NON-NLS-1$
    }

    /**
     * Used when UcmEditor was the root model element.
     * 
     * @deprecated
     */
    public void doSaveAs() {
        System.out.println(Messages.getString("UcmEditor.oldSaveAs")); //$NON-NLS-1$
    }

    /**
     * Execute a command in the parent's command stack.
     * 
     * @param cmd
     */
    public void execute(Command cmd) {
        parent.getDelegatingCommandStack().execute(cmd);
    }

    /**
     * Returns a reference to the multi page action registry.
     */
    protected ActionRegistry getActionRegistry() {
        // one action registry for all editors
        return parent.getActionRegistry();
    }

    /**
     * Returns the adapter for the specified key. Such as the property sheet, the outline view etc.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class type) {
        if (type == ZoomManager.class)
            return getGraphicalViewer().getProperty(ZoomManager.class.toString());
        else if (type == ActionRegistry.class)
            return getActionRegistry();
        else if (type == IContentOutlinePage.class)
            return getOutlinePage();

        return super.getAdapter(type);
    }

    /**
     * @return the outline associated with this editor
     */
    private UcmOutlinePage getOutlinePage() {
        if (outline == null)
            outline = new UcmOutlinePage(getParent(), new TreeViewer());
        return outline;
    }

    /**
     * Overridden to change to public visibility.
     */
    public CommandStack getCommandStack() {
        return getEditDomain().getCommandStack();

    }

    /**
     * Returns the KeyHandler with common bindings for both the Outline and Graphical Views. For example, delete is a common action.
     */
    KeyHandler getCommonKeyHandler() {
        if (sharedKeyHandler == null) {
            sharedKeyHandler = new KeyHandler();

            // Add key and action pairs to sharedKeyHandler
            sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));

            sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));

            sharedKeyHandler.put(KeyStroke.getPressed((char) 1, (int) 'a', SWT.CTRL), getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));

            sharedKeyHandler.put(KeyStroke.getReleased(SWT.ESC, SWT.ESC, 0), getActionRegistry()
                    .getAction(SelectDefaultPaletteToolAction.SETDEFAULTPALETTETOOL));

        }
        return sharedKeyHandler;
    }

    /**
     * Overriden to allow external access.
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#getEditDomain()
     */
    public DefaultEditDomain getEditDomain() {
        return super.getEditDomain();
    }

    /**
     * Overriden to allow external access.
     */
    public GraphicalViewer getGraphicalViewer() {
        return super.getGraphicalViewer();
    }

    /**
     * Return the root model of this editor.
     * 
     * @return The model we are editing.
     */
    public Map getModel() {
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
     * Returns this editor's container.
     * 
     * @return parent multi-page editor
     */
    public UCMNavMultiPageEditor getParent() {
        return parent;
    }

    /**
     * Returns the selection syncronizer object. The synchronizer can be used to sync the selection of 2 or more EditPartViewers.
     * 
     * @return the syncrhonizer
     */
    protected SelectionSynchronizer getSelectionSynchronizer() {
        return parent.getSelectionSynchronizer();
    }

    /**
     * Set up the editor's inital content (after creation).
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
     */
    protected void initializeGraphicalViewer() {
        GraphicalViewer graphicalViewer = getGraphicalViewer();
        graphicalViewer.setContents(getModel()); // set the contents of this editor
        //		 listen for dropped parts
        graphicalViewer.addDropTargetListener(createTransferDropTargetListener());
    }

    /**
     * Return true if the editor contains any changes.
     * 
     * @see org.eclipse.ui.ISaveablePart#isDirty()
     */
    public boolean isDirty() {
        return getCommandStack().isDirty();
    }

    /**
     * Save is always allowed, even though it won't happen here.
     * 
     * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * Redo's the command at the top of the parent's redo stack.
     *  
     */
    public void redo() {
        parent.getDelegatingCommandStack().redo();
    }

    /**
     * Sets the map to be manipulated by this editor
     * 
     * @param m
     */
    public void setModel(Map m) {
        mapModel = m;
    }

    /**
     * Undo's the command at the top of the parent's undo stack.
     *  
     */
    public void undo() {
        parent.getDelegatingCommandStack().undo();
    }
}