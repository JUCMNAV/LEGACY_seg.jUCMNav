package seg.jUCMNav.editors;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
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
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.editparts.GraphicalEditPartFactory;
import seg.jUCMNav.emf.UcmModelManager;
import ucm.map.Map;
import ucm.map.MapFactory;
import ucm.map.PathGraph;

/**
 * 
 */
public class UcmEditor extends GraphicalEditorWithFlyoutPalette {
	
	private PaletteRoot paletteRoot;
	
	/** KeyHandler with common bindings for both the Outline View and the Editor. */
	private KeyHandler sharedKeyHandler;	

	/** Cache save-request status. */
	private boolean saveAlreadyRequested;

	/**
	 * This is the root of the editor's model.
	 * 
	 */
	private Map ucm;
	
	/** the model manager */
    private UcmModelManager modelManager;

	/** Create a new ShapesEditor instance. This is called by the Workspace. */
	public UcmEditor() {
		MapFactory factory = MapFactory.eINSTANCE;
		ucm = factory.createMap();
		ucm.setPathGraph(factory.createPathGraph());
		setEditDomain(new DefaultEditDomain(this));
	}

	/**
	 * Configure the graphical viewer before it receives contents.
	 * <p>This is the place to choose an appropriate RootEditPart and EditPartFactory
	 * for your editor. The RootEditPart determines the behavior of the editor's "work-area".
	 * For example, GEF includes zoomable and scrollable root edit parts. The EditPartFactory
	 * maps model elements to edit parts (controllers).</p>
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer)getGraphicalViewer();
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
		getSite().registerContextMenu("org.eclipse.gef.examples.logic.editor.contextmenu", //$NON-NLS-1$
				provider, viewer);
		
		viewer.setEditPartFactory(new GraphicalEditPartFactory(ucm));
		viewer.setKeyHandler(
				new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler()));
	}
	
	/* (non-Javadoc)
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
	}
	/**
     * This class listens to changes to the file system in the workspace, and
     * makes changes accordingly.
     * 1) An open, saved file gets deleted -> close the editor
     * 2) An open file gets renamed or moved -> change the editor's input accordingly
     * 
     * @author Gunnar Wagenknecht
     */
    private class ResourceTracker
        implements IResourceChangeListener, IResourceDeltaVisitor
    {
        /* (non-Javadoc)
         * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
         */
        public void resourceChanged(IResourceChangeEvent event)
        {
            IResourceDelta delta = event.getDelta();
            try
            {
                if (delta != null)
                    delta.accept(this);
            }
            catch (CoreException exception)
            {
                JUCMNavPlugin.getDefault().getLog().log(exception.getStatus());
                exception.printStackTrace();
            }
        }

        /* (non-Javadoc)
         * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
         */
        public boolean visit(IResourceDelta delta)
        {
            if (delta == null
                || !delta.getResource().equals(
                    ((IFileEditorInput) getEditorInput()).getFile()))
                return true;

            if (delta.getKind() == IResourceDelta.REMOVED)
            {
                if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0)
                {
                    // if the file was deleted
                    // NOTE: The case where an open, unsaved file is deleted is being handled by the 
                    // PartListener added to the Workbench in the initialize() method.
                    if (!isDirty())
                        closeEditor(false);
                }
                else
                {
                    // else if it was moved or renamed
                    final IFile newFile =
                        ResourcesPlugin.getWorkspace().getRoot().getFile(
                            delta.getMovedToPath());
                    Display display = getSite().getShell().getDisplay();
                    display.asyncExec(new Runnable()
                    {
                        public void run()
                        {
                            setInput(new FileEditorInput(newFile));
                        }
                    });
                }
            }
            return false;
        }
    }
    
    public void commandStackChanged(EventObject event) {
    	super.commandStackChanged(event);
    	if (isDirty() && !saveAlreadyRequested) {
    		saveAlreadyRequested = true;
    		firePropertyChange(IEditorPart.PROP_DIRTY);
    	}
    	else {
    		saveAlreadyRequested = false;
    		firePropertyChange(IEditorPart.PROP_DIRTY);
    	}
    }
    
    /**
     * Closes this editor.
     * @param save
     */
    void closeEditor(final boolean save)
    {
        getSite().getShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                getSite().getPage().closeEditor(UcmEditor.this, save);
            }
        });
    }

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		try
        {
            IFile file = ((IFileEditorInput) getEditorInput()).getFile();
            if (file.exists()
                || MessageDialogWithToggle.openConfirm(
                    getSite().getShell(),
                    "Create File",
                    "The file '"
                        + file.getName()
                        + "' doesn't exist. Click OK to create it."))
            {
                save(file, monitor);
                getCommandStack().markSaveLocation();
            }
        }
        catch (CoreException e)
        {
            ErrorDialog.openError(
                getSite().getShell(),
                "Error During Save",
                "The current network model could not be saved.",
                e.getStatus());
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
		SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
        dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
        dialog.open();
        IPath path = dialog.getResult();

        if (path == null)
            return;

        ProgressMonitorDialog progressMonitorDialog =
            new ProgressMonitorDialog(getSite().getShell());
        IProgressMonitor progressMonitor =
            progressMonitorDialog.getProgressMonitor();

        try
        {
            save(
                ResourcesPlugin.getWorkspace().getRoot().getFile(path),
                progressMonitor);
            getCommandStack().markSaveLocation();
        }
        catch (CoreException e)
        {
            ErrorDialog.openError(
                getSite().getShell(),
                "Error During Save",
                "The current UCM model could not be saved.",
                e.getStatus());
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class)
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		return super.getAdapter(type);
	}
	
	/**
	 * Returns the KeyHandler with common bindings for both the Outline and Graphical Views.
	 * For example, delete is a common action.
	 */
	KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();

			// Add key and action pairs to sharedKeyHandler
			sharedKeyHandler.put(
					KeyStroke.getPressed(SWT.DEL, 127, 0),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		}
		return sharedKeyHandler;
	}

	private Map getModel() {
		return ucm;
	}

	/**
	 * Set up the editor's inital content (after creation).
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
	 */
	protected void initializeGraphicalViewer() {
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.setContents(getModel()); // set the contents of this editor
//		 listen for dropped parts
		graphicalViewer.addDropTargetListener(createTransferDropTargetListener());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	
	/**
     * Returns the default <code>PaletteRoot</code> for this editor and all
     * its pages.
     * @return the default <code>PaletteRoot</code>
     */
    protected PaletteRoot getPaletteRoot()
    {
        if (null == paletteRoot)
        {
            // create root
            paletteRoot = new UcmPaletteRoot();
        }
        return paletteRoot;
    }
    
    /* (non-Javadoc)
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
     * Create a transfer drop target listener. When using a CombinedTemplateCreationEntry
     * tool in the palette, this will enable model element creation by dragging from the palette.
     * @see #createPaletteViewerProvider()
     */
    private TransferDropTargetListener createTransferDropTargetListener() {
    	return new TemplateTransferDropTargetListener(getGraphicalViewer()) {
    		protected CreationFactory getFactory(Object template) {
    			return new SimpleFactory((Class) template);
    		}
    	};
    }
    
    /** the resource tracker instance */
    private ResourceTracker resourceTracker;
    
    private ResourceTracker getResourceTracker()
    {
        if (resourceTracker == null)
        {
            resourceTracker = new ResourceTracker();

        }

        return resourceTracker;
    }

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	protected void setInput(IEditorInput input) {
		if (getEditorInput() != null)
        {
            IFile file = ((FileEditorInput) getEditorInput()).getFile();
            file.getWorkspace().removeResourceChangeListener(
                getResourceTracker());
        }

        super.setInput(input);

        if (getEditorInput() != null)
        {
            IFile file = ((FileEditorInput) getEditorInput()).getFile();
            file.getWorkspace().addResourceChangeListener(getResourceTracker());
            setPartName(file.getName());
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
	 */
	protected FlyoutPreferences getPalettePreferences() {
		return UcmPaletteRoot.createPalettePreferences();
	}

    /**
     * Returns the network object from the specified file.
     * 
     * @param file
     * @return the network object from the specified file
     */
    private ucm.map.Map create(IFile file) throws CoreException
    {
        ucm.map.Map ucm = null;
        modelManager = new UcmModelManager();

        if (file.exists())
        {
            try
            {
                modelManager.load(file.getFullPath());
            }
            catch (Exception e)
            {
                modelManager.createDiagram(file.getFullPath());
            }

            ucm = modelManager.getModel();
            if (null == ucm)
            {
                throw new CoreException(
                    new Status(
                        IStatus.ERROR,
                        JUCMNavPlugin.PLUGIN_ID,
                        0,
                        "Error loading the network.",
                        null));
            }
        }
        return ucm;
    }

    /**
     * Saves the network under the specified path.
     * 
     * @param network
     * @param path workspace relative path
     * @param progressMonitor
     */
    private void save(IFile file, IProgressMonitor progressMonitor)
        throws CoreException
    {

        if (null == progressMonitor)
            progressMonitor = new NullProgressMonitor();

        progressMonitor.beginTask("Saving " + file, 2);

        if (null == modelManager)
        {
            IStatus status =
                new Status(
                    IStatus.ERROR,
                    JUCMNavPlugin.PLUGIN_ID,
                    0,
                    "No model manager found for saving the file.",
                    null);
            throw new CoreException(status);
        }

        // save network to file
        try
        {
            modelManager.save(file.getFullPath());

            progressMonitor.worked(1);
            file.refreshLocal(
                IResource.DEPTH_ZERO,
                new SubProgressMonitor(progressMonitor, 1));
            progressMonitor.done();
        }
        catch (FileNotFoundException e)
        {
            IStatus status =
                new Status(
                    IStatus.ERROR,
                    JUCMNavPlugin.PLUGIN_ID,
                    0,
                    "Error writing file.",
                    e);
            throw new CoreException(status);
        }
        catch (IOException e)
        {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
            IStatus status =
                new Status(
                    IStatus.ERROR,
                    JUCMNavPlugin.PLUGIN_ID,
                    0,
                    "Error writing file.",
                    e);
            throw new CoreException(status);
        }
    }
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
//		 read network from input
        try
        {
            // we expect IFileEditorInput here, 
            // ClassCassException is catched to force PartInitException
            IFile file = ((IFileEditorInput) input).getFile();
            ucm = create(file);

            // validate network
            if (null == getModel())
                throw new PartInitException("The specified input is not a valid network.");
        }
        catch (CoreException e)
        {
            throw new PartInitException(e.getStatus());
        }
        catch (ClassCastException e)
        {
            throw new PartInitException(
                "The specified input is not a valid network.",
                e);
        }

        // network is ok         
        super.init(site, input);
	}
}
