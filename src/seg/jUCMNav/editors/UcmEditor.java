package seg.jUCMNav.editors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.AddLabelAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.BindChildren;
import seg.jUCMNav.actions.BindWithParent;
import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.UnbindChildren;
import seg.jUCMNav.actions.UnbindFromParent;
import seg.jUCMNav.editors.actionContributors.UcmContextMenuProvider;
import seg.jUCMNav.editors.palette.UcmPaletteListener;
import seg.jUCMNav.editors.palette.UcmPaletteRoot;
import seg.jUCMNav.editors.resourceManagement.ResourceTracker;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.editparts.ConnectionOnBottomRootEditPart;
import seg.jUCMNav.editparts.GraphicalEditPartFactory;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.Map;
import urn.URNspec;

/**
 * This is the main class for editing a Map in our model.
 * 
 * @author Etienne Tremblay
 */
public class UcmEditor extends GraphicalEditorWithFlyoutPalette {

    /**
     * The palette root used to display the palette.
     */
    private PaletteRoot paletteRoot;

    /** KeyHandler with common bindings for both the Outline View and the Editor. */
    private KeyHandler sharedKeyHandler;

    /** Cache save-request status. */
    private boolean saveAlreadyRequested;

    /** This is the root of the editor's model. */
    //private Map ucm;
    private URNspec model;

    /** the model manager */
    private UrnModelManager modelManager;

    /** the resource tracker instance */
    private ResourceTracker resourceTracker;

    /** Create a new UcmEditor instance. This is called by the Workspace. */
    public UcmEditor() {
        model = (URNspec) ModelCreationFactory.getNewObject(URNspec.class);

        setEditDomain(new DefaultEditDomain(this));
    }

    public Map getMap(int index) {
        return (Map) model.getUcmspec().getMaps().get(index);
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
        ConnectionOnBottomRootEditPart root = new ConnectionOnBottomRootEditPart();

        List zoomLevels = new ArrayList(3);
        zoomLevels.add(ZoomManager.FIT_ALL);
        zoomLevels.add(ZoomManager.FIT_WIDTH);
        zoomLevels.add(ZoomManager.FIT_HEIGHT);
        root.getZoomManager().setZoomLevelContributions(zoomLevels);

        IAction zoomIn = new ZoomInAction(root.getZoomManager());
        IAction zoomOut = new ZoomOutAction(root.getZoomManager());
        getActionRegistry().registerAction(zoomIn);
        getActionRegistry().registerAction(zoomOut);
        getSite().getKeyBindingService().registerAction(zoomIn);
        getSite().getKeyBindingService().registerAction(zoomOut);

        viewer.setRootEditPart(root);

        ContextMenuProvider provider = new UcmContextMenuProvider(viewer, getActionRegistry());
        viewer.setContextMenu(provider);
        getSite().registerContextMenu("seg.jUCMNav.editors.UcmContextMenuProvider", provider, viewer);

        viewer.setEditPartFactory(new GraphicalEditPartFactory(getMap(0)));
        viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler()));
    }

    /**
     * Create all the actions in the action registry.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    protected void createActions() {
        super.createActions();

        ActionRegistry registry = getActionRegistry();
        IAction action;

        action = new CutPathAction(this);
        action.setText("Cut path");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AddLabelAction(this);
        action.setText("Add label");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AddOrForkAction(this);
        action.setText("Add OR-Fork");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new BindWithParent(this);
        action.setText("Bind with parent component");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new UnbindFromParent(this);
        action.setText("Unbind from parent component");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new UnbindChildren(this);
        action.setText("Unbind all enclosed elements");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new BindChildren(this);
        action.setText("Bind all enclosed elements");
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

    }

    /**
     * Handle events to know when a command was executed. So we can know when we can or cannot save the file.
     */
    public void commandStackChanged(EventObject event) {
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
     * Closes this editor.
     * 
     * @param save
     */
    public void closeEditor(final boolean save) {
        getSite().getShell().getDisplay().syncExec(new Runnable() {
            public void run() {
                getSite().getPage().closeEditor(UcmEditor.this, save);
            }
        });
    }

    /**
     * Perform a save on the file we are editing.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor) {
        try {
            IFile file = ((IFileEditorInput) getEditorInput()).getFile();
            if (file.exists()
                    || MessageDialogWithToggle.openConfirm(getSite().getShell(), "Create File", "The file '" + file.getName()
                            + "' doesn't exist. Click OK to create it.")) {
                save(file, monitor);
                getCommandStack().markSaveLocation();
            }
        } catch (CoreException e) {
            ErrorDialog.openError(getSite().getShell(), "Error During Save", "The current UCM model could not be saved.", e.getStatus());
        }
    }

    /**
     * Perform a save as action on the file we are editing.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs() {
        // get the new path
        SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
        dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
        dialog.open();
        IPath path = dialog.getResult();

        // if the user presses cancel or refuses to overwrite an existing file, null will be returned.
        if (path == null)
            return;
        try {
            // because our modelmanager is tightly coupled with the resource.
            modelManager = new UrnModelManager();

            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IFile file = workspace.getRoot().getFile(path);

            // we're overwriting the file so get rid of it
            if (file.exists()) {
                file.delete(true, false, new NullProgressMonitor());
            }

            // creates a non existing resource and assigns it our model
            modelManager.createURNspec(path, model);

            // save the new file
            modelManager.save(path);
            getCommandStack().markSaveLocation();

            // reinit everything
            init(getEditorSite(), new FileEditorInput(file));

            // without this code, we can't edit the new file or we can edit the old URNspec.
        	configureGraphicalViewer();
        	hookGraphicalViewer();
        	initializeGraphicalViewer();

        } catch (Exception e) {
            ErrorDialog.openError(getSite().getShell(), "Error During Save", "The current UCM model could not be saved.", new Status(IStatus.ERROR,
                    "seg.jUCMNav", IStatus.ERROR, "", e));
        }

    }

    /**
     * Returns the adapter for the specified key. Such as the property sheet, the outline view etc.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class type) {
        if (type == ZoomManager.class)
            return getGraphicalViewer().getProperty(ZoomManager.class.toString());
        return super.getAdapter(type);
    }

    /**
     * Returns the KeyHandler with common bindings for both the Outline and Graphical Views. For example, delete is a common action.
     */
    KeyHandler getCommonKeyHandler() {
        if (sharedKeyHandler == null) {
            sharedKeyHandler = new KeyHandler();

            // Add key and action pairs to sharedKeyHandler
            sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
        }
        return sharedKeyHandler;
    }

    /**
     * Return the root model of this editor.
     * 
     * @return The model we are editing.
     */
    public URNspec getModel() {
        return model;
    }

    /**
     * Set up the editor's inital content (after creation).
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
     */
    protected void initializeGraphicalViewer() {
        GraphicalViewer graphicalViewer = getGraphicalViewer();
        graphicalViewer.setContents(getMap(0)); // set the contents of this editor
        //		 listen for dropped parts
        graphicalViewer.addDropTargetListener(createTransferDropTargetListener());
    }

    /**
     * Return true if the editor contains any changes.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#isDirty()
     */
    public boolean isDirty() {
        return getCommandStack().isDirty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * Returns the default <code>PaletteRoot</code> for this editor and all its pages.
     * 
     * @return the default <code>PaletteRoot</code>
     */
    protected PaletteRoot getPaletteRoot() {
        if (null == paletteRoot) {
            // create root
            paletteRoot = new UcmPaletteRoot();
        }
        return paletteRoot;
    }

    /*
     * (non-Javadoc)
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
                viewer.addPaletteListener(new UcmPaletteListener());
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
     * Return the ressource tracker of this editor.
     * 
     * @return The ressource tracker of this editor.
     */
    private ResourceTracker getResourceTracker() {
        if (resourceTracker == null)
            resourceTracker = new ResourceTracker(this);

        return resourceTracker;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    public void setInput(IEditorInput input) {
        if (getEditorInput() != null) {
            IFile file = ((FileEditorInput) getEditorInput()).getFile();
            file.getWorkspace().removeResourceChangeListener(getResourceTracker());
        }

        super.setInput(input);

        if (getEditorInput() != null) {
            IFile file = ((FileEditorInput) getEditorInput()).getFile();
            file.getWorkspace().addResourceChangeListener(getResourceTracker());
            setPartName(file.getName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
     */
    protected FlyoutPreferences getPalettePreferences() {
        return UcmPaletteRoot.createPalettePreferences();
    }

    /**
     * Returns the URNspec object from the specified file.
     * 
     * @param file
     * @return the ucm object from the specified file
     */
    private URNspec create(IFile file) throws CoreException {
        URNspec urn = null;
        modelManager = new UrnModelManager();

        if (file.exists()) {
            try {
                modelManager.load(file.getFullPath());
            } catch (Exception e) {
                modelManager.createURNspec(file.getFullPath());
            }

            urn = modelManager.getModel();
            if (null == urn) {
                throw new CoreException(new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "Error loading the UCM.", null));
            }
        }
        return urn;
    }

    /**
     * Saves the ucm under the specified file.
     * 
     * @param file
     *            is the file where we want to save this UCM.
     * @param progressMonitor
     *            is the object used to show the progress of the save.
     * @throws CoreException
     */
    private void save(IFile file, IProgressMonitor progressMonitor) throws CoreException {

        if (null == progressMonitor)
            progressMonitor = new NullProgressMonitor();

        progressMonitor.beginTask("Saving " + file, 2);

        if (null == modelManager) {
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "No model manager found for saving the file.", null);
            throw new CoreException(status);
        }

        // save URNspec to file
        try {
            modelManager.save(file.getFullPath());

            progressMonitor.worked(1);
            file.refreshLocal(IResource.DEPTH_ZERO, new SubProgressMonitor(progressMonitor, 1));
            progressMonitor.done();
        } catch (FileNotFoundException e) {
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "Error writing file.", e);
            throw new CoreException(status);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "Error writing file.", e);
            throw new CoreException(status);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
     */
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        //		 read URNspec from input
        try {
            // we expect IFileEditorInput here,
            // ClassCassException is catched to force PartInitException
            IFile file = ((IFileEditorInput) input).getFile();
            model = create(file);

            // validate URNspec
            if (null == getModel())
                throw new PartInitException("The specified input is not a valid URN file.");
        } catch (CoreException e) {
            throw new PartInitException(e.getStatus());
        } catch (ClassCastException e) {
            throw new PartInitException("The specified input is not a valid URN file.", e);
        }

        // URNspec is ok
        super.init(site, input);
    }

    /**
     * Execute a command in the appropriate command stack.
     * 
     * @param cmd
     * @param map
     */
    public void execute(Command cmd, int map) {
        getEditDomain().getCommandStack().execute(cmd);
    }
}