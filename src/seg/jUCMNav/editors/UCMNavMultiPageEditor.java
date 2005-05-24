package seg.jUCMNav.editors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.actions.StackAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.AddAndForkAction;
import seg.jUCMNav.actions.AddLabelAction;
import seg.jUCMNav.actions.AddMapAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.BindChildren;
import seg.jUCMNav.actions.BindWithParent;
import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.UnbindChildren;
import seg.jUCMNav.actions.UnbindFromParent;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.editparts.MapAndPathGraphEditPart;
import seg.jUCMNav.editparts.treeEditparts.UcmModelElementTreeEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.UcmPackage;
import ucm.map.Map;
import ucm.map.MapPackage;
import urn.URNspec;

/**
 * Created on 17-Apr-2005
 * 
 * @author jkealey
 *  
 */
public class UCMNavMultiPageEditor extends MultiPageEditorPart implements Adapter {

    /**
     * This class listens for command stack changes of the pages contained in this editor and decides if the editor is dirty or not.
     * 
     * @author Gunnar Wagenknecht
     */
    private class MultiPageCommandStackListener implements CommandStackListener {

        /** the observed command stacks */
        private List commandStacks = new ArrayList(2);

        /**
         * Adds a <code>CommandStack</code> to observe.
         * 
         * @param commandStack
         */
        public void addCommandStack(CommandStack commandStack) {
            commandStacks.add(commandStack);
            commandStack.addCommandStackListener(this);
        }

        /**
         * Removes a <code>CommandStack</code> that was observed.
         * 
         * @param commandStack
         */
        public void removeCommandStack(CommandStack commandStack) {
            commandStacks.remove(commandStack);
            commandStack.removeCommandStackListener(this);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
         */
        public void commandStackChanged(EventObject event) {
            if (((CommandStack) event.getSource()).isDirty()) {
                // at least one command stack is dirty,
                // so the multi page editor is dirty too
                setDirty(true);
            } else {
                // probably a save, we have to check all command stacks
                boolean oneIsDirty = false;
                for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
                    CommandStack stack = (CommandStack) stacks.next();
                    if (stack.isDirty()) {
                        oneIsDirty = true;
                        break;
                    }
                }
                setDirty(oneIsDirty);
            }
            if (!(event.getSource() instanceof DelegatingCommandStack)) {
                getDelegatingCommandStack().flushURNspecStack();
            }
        }

        /**
         * Disposed the listener
         */
        public void dispose() {
            for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
                ((CommandStack) stacks.next()).removeCommandStackListener(this);
            }
            commandStacks.clear();
        }

        /**
         * Marks every observed command stack beeing saved. This method should be called whenever the editor/model was saved.
         */
        public void markSaveLocations() {
            for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
                CommandStack stack = (CommandStack) stacks.next();
                stack.markSaveLocation();
            }
        }
    }

    /**
     * This class listens to changes to the file system in the workspace, and makes changes accordingly. 1) An open, saved file gets deleted -> close the editor
     * 2) An open file gets renamed or moved -> change the editor's input accordingly
     * 
     * @author Gunnar Wagenknecht
     */
    private class ResourceTracker implements IResourceChangeListener, IResourceDeltaVisitor {
        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
         */
        public void resourceChanged(IResourceChangeEvent event) {
            IResourceDelta delta = event.getDelta();
            try {
                if (delta != null)
                    delta.accept(this);
            } catch (CoreException exception) {
                JUCMNavPlugin.getDefault().getLog().log(exception.getStatus());
                exception.printStackTrace();
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
         */
        public boolean visit(IResourceDelta delta) {
            if (delta == null || !delta.getResource().equals(((IFileEditorInput) getEditorInput()).getFile()))
                return true;

            if (delta.getKind() == IResourceDelta.REMOVED) {
                if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {
                    // if the file was deleted
                    // NOTE: The case where an open, unsaved file is deleted is being handled by the
                    // PartListener added to the Workbench in the initialize() method.
                    if (!isDirty())
                        closeEditor(false);
                } else {
                    // else if it was moved or renamed
                    final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getMovedToPath());
                    Display display = getSite().getShell().getDisplay();
                    display.asyncExec(new Runnable() {
                        public void run() {
                            setInput(new FileEditorInput(newFile));
                        }
                    });
                }
            }
            return false;
        }
    }

    // the shared actionregistry
    private ActionRegistry actionRegistry;

    /** the delegating CommandStack */
    private DelegatingCommandStack delegatingCommandStack;

    /**
     * The <code>CommandStackListener</code> that listens for changes of the <code>DelegatingCommandStack</code>.
     */
    private CommandStackListener delegatingCommandStackListener = new CommandStackListener() {
        public void commandStackChanged(EventObject event) {
            updateActions(stackActionIDs);
            commandStackVerifyPages(event);
        }
    };

    /** the delegating ZoomManager */
    private DelegatingZoomManager delegatingZoomManager;

    /** the list of action ids that are editor actions */
    private List editorActionIDs = new ArrayList();

    /** the list of action ids that are to EditPart actions */
    private List editPartActionIDs = new ArrayList();

    private boolean isDirty;

    /** This is the root of the editor's model. */
    private URNspec model;

    /** the model manager */
    private UrnModelManager modelManager;

    /** the <code>CommandStackListener</code> */
    private MultiPageCommandStackListener multiPageCommandStackListener;

    /** the resource tracker instance */
    private ResourceTracker resourceTracker;

    /** the selection listener */
    private ISelectionListener selectionListener = new ISelectionListener() {
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {

            if (selection instanceof StructuredSelection) {
                if (((StructuredSelection) selection).getFirstElement() instanceof UcmModelElementTreeEditPart) {
                    UcmModelElementTreeEditPart selectedPart = (UcmModelElementTreeEditPart) ((StructuredSelection) selection).getFirstElement();

                    Map selectedMap = selectedPart.getContainingMap();
                    for (int i = 0; i < getModel().getUcmspec().getMaps().size(); i++) {
                        if (getMap(i).equals(selectedMap)) {
                            if (getActivePage() != i) {
                                setActivePage(i);
                                break;
                            }
                        }
                    }
                }
            }
            updateActions(editPartActionIDs);
        }
    };

    /** the list of action ids that are to CommandStack actions */
    private List stackActionIDs = new ArrayList();

    /** the selection synchronizer for the edit part viewer */
    private SelectionSynchronizer synchronizer;

    /** To be notified of changes to the map names */
    private Notifier target;

    public UCMNavMultiPageEditor() {
        setModel((URNspec) ModelCreationFactory.getNewURNspec());

    }

    /**
     * Adds a general action to the action registry.
     * 
     * @param action
     */
    private void addAction(IAction action) {
        getActionRegistry().registerAction(action);
    }

    /**
     * Adds an editor action to this editor.
     * 
     * <p>
     * Editor actions are actions that depend and work on the editor.
     * 
     * @param action
     *            the editor action
     */
    protected void addEditorAction(EditorPartAction action) {
        getActionRegistry().registerAction(action);
        editorActionIDs.add(action.getId());
    }

    /**
     * Adds an <code>EditPart</code> action to this editor.
     * 
     * <p>
     * <code>EditPart</code> actions are actions that depend and work on the selected <code>EditPart</code>s.
     * 
     * @param action
     *            the <code>EditPart</code> action
     */
    protected void addEditPartAction(SelectionAction action) {
        getActionRegistry().registerAction(action);
        editPartActionIDs.add(action.getId());
    }

    /**
     * Adds an <code>CommandStack</code> action to this editor.
     * 
     * <p>
     * <code>CommandStack</code> actions are actions that depend and work on the <code>CommandStack</code>.
     * 
     * @param action
     *            the <code>CommandStack</code> action
     */
    protected void addStackAction(StackAction action) {
        getActionRegistry().registerAction(action);
        stackActionIDs.add(action.getId());
    }

    /**
     * Closes this editor.
     * 
     * @param save
     */
    public void closeEditor(final boolean save) {
        getSite().getShell().getDisplay().syncExec(new Runnable() {
            public void run() {
                getSite().getPage().closeEditor(UCMNavMultiPageEditor.this, save);
            }
        });
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
     * Creates actions and registers them to the ActionRegistry. Because the action registry is shared, we must created them here.
     *  
     */
    protected void createActions() {
        addStackAction(new UndoAction(this));
        addStackAction(new RedoAction(this));

        IAction zoomIn = new ZoomInAction(getDelegatingZoomManager());
        IAction zoomOut = new ZoomOutAction(getDelegatingZoomManager());
        addAction(zoomIn);
        addAction(zoomOut);
        getSite().getKeyBindingService().registerAction(zoomIn);
        getSite().getKeyBindingService().registerAction(zoomOut);

        ActionRegistry registry = getActionRegistry();
        IAction action;

        action = new SelectAllAction(this);
        addAction(action);

        // Notice the following are calls to addEditPartAction().
        // They need to know the current selection to work.
        // If you write addAction instead, you'll get empty selections
        action = new DeleteAction((IWorkbenchPart) this);
        addEditPartAction((SelectionAction) action);

        action = new CutPathAction(this);
        action.setText("Cut path");
        addEditPartAction((SelectionAction) action);

        action = new AddLabelAction(this);
        action.setText("Add label");
        addEditPartAction((SelectionAction) action);

        action = new AddOrForkAction(this);
        action.setText("Add OR-Fork");
        addEditPartAction((SelectionAction) action);

        action = new AddAndForkAction(this);
        action.setText("Add AND-Fork");
        addEditPartAction((SelectionAction) action);

        action = new BindWithParent(this);
        action.setText("Bind with parent component");
        addEditPartAction((SelectionAction) action);

        action = new UnbindFromParent(this);
        action.setText("Unbind from parent component");
        addEditPartAction((SelectionAction) action);

        action = new UnbindChildren(this);
        action.setText("Unbind all enclosed elements");
        addEditPartAction((SelectionAction) action);

        action = new BindChildren(this);
        action.setText("Bind all enclosed elements");
        addEditPartAction((SelectionAction) action);

        action = new AddMapAction(this);
        action.setText("Add Use Case Map");
        addEditPartAction((SelectionAction) action);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
     */
    protected void createPages() {

        for (int i = 0; i < model.getUcmspec().getMaps().size(); i++) {

            UcmEditor u = new UcmEditor(this);
            u.setModel((Map) model.getUcmspec().getMaps().get(i));

            try {
                addPage(u, getEditorInput());

                // add command stacks
                getMultiPageCommandStackListener().addCommandStack(u.getCommandStack());

                setPageText(getPageCount() - 1, "Map: " + ((Map) model.getUcmspec().getMaps().get(i)).getName());

            } catch (PartInitException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //setActivePage(0);
    }

    /**
     * Indicates that the current page has changed.
     * <p>
     * We update the DelegatingCommandStack, OutlineViewer and other things here.
     */
    protected void currentPageChanged() {
        if (getCurrentPage() != null) {
            // update delegating command stack
            getDelegatingCommandStack().setCurrentCommandStack(getCurrentPage().getCommandStack());

            // update zoom actions
            getDelegatingZoomManager().setCurrentZoomManager(getZoomManager(getCurrentPage().getGraphicalViewer()));
            
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#dispose()
     */
    public void dispose() {
        // dispose multi page command stack listener
        getMultiPageCommandStackListener().dispose();

        // remove delegating CommandStackListener
        getDelegatingCommandStack().removeCommandStackListener(getDelegatingCommandStackListener());
        // disposy the ActionRegistry (will dispose all actions)
        getActionRegistry().dispose();

        for (int i = 0; i < model.getUcmspec().getMaps().size(); i++)
            ((Map) model.getUcmspec().getMaps().get(i)).eAdapters().remove(this);

        // important: always call super implementation of dispose
        super.dispose();
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
                getMultiPageCommandStackListener().markSaveLocations();
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
            getDelegatingCommandStack().markSaveLocation();

            // reinit everything
            init(getEditorSite(), new FileEditorInput(file));

            multiPageCommandStackListener = null;
            while (getPageCount() > 0)
                removePage(0);

            createPages();

            // set the active page (page 0 by default), unless it has already been done
            if (getActivePage() == -1)
                setActivePage(0);

        } catch (Exception e) {
            ErrorDialog.openError(getSite().getShell(), "Error During Save", "The current UCM model could not be saved.", new Status(IStatus.ERROR,
                    "seg.jUCMNav", IStatus.ERROR, "", e));
        }

    }

    /**
     * Returns the action registry of this editor.
     * 
     * @return the action registry
     */
    protected ActionRegistry getActionRegistry() {
        if (actionRegistry == null)
            actionRegistry = new ActionRegistry();

        return actionRegistry;
    }

    public Object getAdapter(Class adapter) {
        //        System.out.println(adapter.toString() + " on editor " + getActivePage());
        if (adapter == ActionRegistry.class)
            return getActionRegistry();
        else if (adapter == ZoomManager.class)
            return getDelegatingZoomManager();
        else if (adapter == CommandStack.class)
            return getDelegatingCommandStack();
        if (getPageCount() > 0) {
            return getActiveEditor().getAdapter(adapter);
        } else
            return super.getAdapter(adapter);
    }

    /**
     * Returns the current active page.
     * 
     * @return the current active page or <code>null</code>
     */
    public UcmEditor getCurrentPage() {
        if (getActivePage() == -1)
            return null;

        return (UcmEditor) getActiveEditor();
    }

    /**
     * Returns the <code>CommandStack</code> for this editor.
     * 
     * @return the <code>CommandStack</code>
     */
    protected DelegatingCommandStack getDelegatingCommandStack() {
        if (null == delegatingCommandStack) {
            delegatingCommandStack = new DelegatingCommandStack();
            if (null != getCurrentPage())
                delegatingCommandStack.setCurrentCommandStack(getCurrentPage().getCommandStack());
        }

        return delegatingCommandStack;
    }

    /**
     * Returns the <code>CommandStackListener</code> for the <code>DelegatingCommandStack</code>.
     * 
     * @return the <code>CommandStackListener</code>
     */
    protected CommandStackListener getDelegatingCommandStackListener() {
        return delegatingCommandStackListener;
    }

    /**
     * Returns the <code>DelegatingZoomManager</code> for this editor.
     * 
     * @return the <code>DelegatingZoomManager</code>
     */
    protected DelegatingZoomManager getDelegatingZoomManager() {
        if (null == delegatingZoomManager) {
            delegatingZoomManager = new DelegatingZoomManager();
            if (null != getCurrentPage() && null != getCurrentPage().getGraphicalViewer())
                delegatingZoomManager.setCurrentZoomManager(getZoomManager(getCurrentPage().getGraphicalViewer()));
        }

        return delegatingZoomManager;
    }

    /**
     * Return a certain map in the model
     * 
     * @param i
     *            the map's index.
     * @return
     */
    public Map getMap(int i) {
        return (Map) model.getUcmspec().getMaps().get(i);
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
     * Returns the global command stack listener.
     * 
     * @return the <code>CommandStackListener</code>
     */
    protected MultiPageCommandStackListener getMultiPageCommandStackListener() {
        if (null == multiPageCommandStackListener) {
            multiPageCommandStackListener = new MultiPageCommandStackListener();
            multiPageCommandStackListener.addCommandStack(getDelegatingCommandStack());
        }
        return multiPageCommandStackListener;
    }

    /**
     * Because the getActionRegistry() doesn't have the appropriate visibility and because we want to make it clear that the action registry is shared
     * 
     * @return
     */
    public ActionRegistry getParentActionRegistry() {
        return getActionRegistry();
    }

    /**
     * Return the ressource tracker of this editor.
     * 
     * @return The ressource tracker of this editor.
     */
    private ResourceTracker getResourceTracker() {
        if (resourceTracker == null)
            resourceTracker = new ResourceTracker();

        return resourceTracker;
    }

    /**
     * Returns the selection listener.
     * 
     * @return the <code>ISelectionListener</code>
     */
    protected ISelectionListener getSelectionListener() {
        return selectionListener;
    }

    /**
     * Returns the selection syncronizer object. The synchronizer can be used to sync the selection of 2 or more EditPartViewers.
     * 
     * @return the syncrhonizer
     */
    protected SelectionSynchronizer getSelectionSynchronizer() {
        if (synchronizer == null)
            synchronizer = new SelectionSynchronizer();
        return synchronizer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /**
     * Returns the zoom manager of the specified viewer.
     * 
     * @param viewer
     *            the viewer to get the zoom manager from
     * @return the zoom manager
     */
    private ZoomManager getZoomManager(GraphicalViewer viewer) {
        // get zoom manager from root edit part
        RootEditPart rootEditPart = viewer.getRootEditPart();
        ZoomManager zoomManager = null;
        if (rootEditPart instanceof ScalableFreeformRootEditPart) {
            zoomManager = ((ScalableFreeformRootEditPart) rootEditPart).getZoomManager();
        } else if (rootEditPart instanceof ScalableRootEditPart) {
            zoomManager = ((ScalableRootEditPart) rootEditPart).getZoomManager();
        }
        return zoomManager;
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
            setModel(create(file));

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

        // add delegating CommandStackListener
        getDelegatingCommandStack().addCommandStackListener(getDelegatingCommandStackListener());

        // add selection change listener
        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(getSelectionListener());

        createActions();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
    }

    public boolean isDirty() {
        return isDirty;
    }

    public boolean isSaveAsAllowed() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {

        int type = notification.getEventType();
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (type) {
        case Notification.SET:
            switch (featureId) {
            case MapPackage.MAP__NAME:
                refreshPageNames();
                break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
     */
    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);

        // we want the outline to know that we've selected another map.
        MapAndPathGraphEditPart mappart = (MapAndPathGraphEditPart) getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(getCurrentPage().getModel());
        getCurrentPage().getGraphicalViewer().flush();
        getCurrentPage().getGraphicalViewer().select(mappart);
        
        // refresh content depending on current page
        currentPageChanged();
    }

    public void refreshPageNames() {
        for (int i = 0; i < model.getUcmspec().getMaps().size(); i++)
            setPageText(i, "Map: " + ((Map) model.getUcmspec().getMaps().get(i)).getName());

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
     * @see org.eclipse.ui.part.MultiPageEditorPart#setActivePage(int)
     */
    protected void setActivePage(int pageIndex) {
        if (getPageCount() > 0) {
            super.setActivePage(pageIndex);

            // refresh content depending on current page
            currentPageChanged();
        }
    }

    /**
     * Changes the dirty state.
     * 
     * @param dirty
     */
    private void setDirty(boolean dirty) {
        if (isDirty != dirty) {
            isDirty = dirty;
            firePropertyChange(IEditorPart.PROP_DIRTY);
        }
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

    /**
     * @param model
     *            The model to set.
     */
    public void setModel(URNspec model) {

        if (this.model != null) {
            for (int i = 0; i < this.model.getUcmspec().getMaps().size(); i++)
                ((Map) this.model.getUcmspec().getMaps().get(i)).eAdapters().remove(this);
        }

        this.model = model;

        // we must register ourselves to be able to change the tabs when the names change.
        for (int i = 0; i < model.getUcmspec().getMaps().size(); i++)
            ((Map) model.getUcmspec().getMaps().get(i)).eAdapters().add(this);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /**
     * Updates the specified actions.
     * 
     * @param actionIds
     *            the list of ids of actions to update
     */
    private void updateActions(List actionIds) {
        for (Iterator ids = actionIds.iterator(); ids.hasNext();) {
            IAction action = getActionRegistry().getAction(ids.next());
            if (null != action && action instanceof UpdateAction)
                ((UpdateAction) action).update();

        }

        //  getCurrentPage().updateActions();
    }

    /**
     * @param event
     */
    private void commandStackVerifyPages(EventObject event) {
        if (getPageCount() != getModel().getUcmspec().getMaps().size() && event.getSource() instanceof DelegatingCommandStack) {
            Map mapChanged = ((DelegatingCommandStack) event.getSource()).getLastAffectedMap();

            // was added
            if (getModel().getUcmspec().getMaps().contains(mapChanged)) {
                UcmEditor u = new UcmEditor(UCMNavMultiPageEditor.this);
                u.setModel(mapChanged);

                try {
                    addPage(u, getEditorInput());
                } catch (PartInitException e) {
                    e.printStackTrace();
                }

                // add command stacks
                getMultiPageCommandStackListener().addCommandStack(u.getCommandStack());

                refreshPageNames();
                setActivePage(getModel().getUcmspec().getMaps().indexOf(mapChanged));

            } else // was deleted
            {
                int i;
                for (i = 0; i < getPageCount(); i++) {
                    if (((UcmEditor) getEditor(i)).getModel().equals(mapChanged)) {
                        break;
                    }
                }

                // remove command stacks
                getMultiPageCommandStackListener().removeCommandStack(((UcmEditor) getEditor(i)).getCommandStack());

                removePage(i);

                currentPageChanged();
            }
        } else {
            if (!(event.getSource() instanceof DelegatingCommandStack))
                getDelegatingCommandStack().flushURNspecStack();

        }
    }
}