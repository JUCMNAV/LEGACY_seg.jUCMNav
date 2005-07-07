package seg.jUCMNav.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.INavigationLocationProvider;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.actionContributors.ActionRegistryManager;
import seg.jUCMNav.editors.resourceManagement.MultiPageFileManager;
import seg.jUCMNav.editors.resourceManagement.ResourceTracker;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.views.outline.UcmOutlinePage;
import ucm.UcmPackage;
import ucm.map.Map;
import ucm.map.MapPackage;
import urn.URNspec;

/**
 * This class is the central location for our editor. Its main responsibilities are as follows. 
 * 
 * 1) The editor associated with .jucm files. See seg.jUCMNav.editors.resourceManagement.MultiPageFileManager.
 * 
 * 2) An editor itself, has an outline when no maps are available. Its model is the root URNspec. See UcmOutlinePage.
 * 
 * 3) A container for UcmEditors, which edit only one map. Some aspects are delegated to the children but some are handled here.
 * 
 * 3.1) For tab management, see MultiPageTabManager.
 * 
 * 3.2) For command stack management (delegated to children), see MultiPageCommandStackListener, DelegatingCommandStack.
 * 
 * 3.3) For zoom management (delegated to children) see DelegatingZoomManager
 * 
 * @author jkealey
 *  
 */
public class UCMNavMultiPageEditor extends MultiPageEditorPart implements Adapter, INavigationLocationProvider {
    /** the actionregistry shared between all editors */
    private ActionRegistry actionRegistry;

    /** keeps track of different types of actions, that sometimes need to be accessed later */
    private ActionRegistryManager actionRegistryManager;

    /** the delegating CommandStack */
    private DelegatingCommandStack delegatingCommandStack;

    /** the delegating ZoomManager */
    private DelegatingZoomManager delegatingZoomManager;

    /** the file manager */
    private MultiPageFileManager fileManager;

    /** this boolean is used to persist the fact that we might have performed stack actions elsewhere than in the individual editor stacks. */
    private boolean isDirty;

    /** This is the root of the editor's model. */
    private URNspec model;

    /** the <code>CommandStackListener</code> */
    private MultiPageCommandStackListener multiPageCommandStackListener;

    /** to simplify the code in this class, delegate tab management to MultiPageTabManager */
    private MultiPageTabManager multiPageTabManager;

    /** the resource tracker instance */
    private ResourceTracker resourceTracker;

    /** the selection listener */
    private ISelectionListener selectionListener = new ISelectionListener() {
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {
            getActionRegistryManager().updateEditPartActions();
        }
    };

    /** the selection synchronizer for the edit part viewer */
    private SelectionSynchronizer synchronizer;

    /** To be notified of changes to the map names */
    private Notifier target;

    /**
     * We always want to be editing a URNspec
     */
    public UCMNavMultiPageEditor() {
        setModel((URNspec) ModelCreationFactory.getNewURNspec());
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
     * Delegates tab creation to MultiPageTabManager.
     */
    protected void createPages() {
        getMultiPageTabManager().createPages();
    }

    /**
     * Delegates tab creation to MultiPageTabManager.
     */
    protected void currentPageChanged() {
        getMultiPageTabManager().currentPageChanged();

    }
    
    /**
     * The listener will get notifications when the current page is changed.
     * 
     * @param listener The listener to add
     */
    public void addPageChangeListener(IPageChangeListener listener) {
    	multiPageTabManager.addPageChangeListener(listener);
    }
    
    /**
     * Remove a page change listener.
     * 
     * @param listener The listener to remove
     */
    public void removePageChangeListener(IPageChangeListener listener) {
    	multiPageTabManager.removePageChangeListener(listener);
    }

    /**
     * Releases all listeners
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#dispose()
     */
    public void dispose() {
        // remove delegating CommandStackListener
        getDelegatingCommandStack().removeCommandStackListener(getMultiPageCommandStackListener());

        // dispose multi page command stack listener
        getMultiPageCommandStackListener().dispose();

        // dispose the ActionRegistry (will dispose all actions)
        getActionRegistry().dispose();

        // stop listening to all maps for name changes
        for (int i = 0; i < model.getUcmspec().getMaps().size(); i++)
            ((Map) model.getUcmspec().getMaps().get(i)).eAdapters().remove(this);

        // important: always call super implementation of dispose
        super.dispose();
    }

    /**
     * Perform a save on the file we are editing.
     * 
     * Delegates to MultiPageFileManager.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor) {
        getFileManager().doSave(monitor);
    }

    /**
     * Perform a save as action on the file we are editing.
     * 
     * Delegates to MultiPageFileManager.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs() {
        getFileManager().doSaveAs();
    }

    /**
     * Returns the action registry of this editor.
     * 
     * Public to give the outline access. 
     * 
     * @return the action registry
     */
    public ActionRegistry getActionRegistry() {
        if (actionRegistry == null)
            actionRegistry = new ActionRegistry();

        return actionRegistry;
    }

    /**
     * Returns the action registry manager. We delegate action registry management to this class.
     * 
     * @return the action registry manager
     */
    protected ActionRegistryManager getActionRegistryManager() {
        if (actionRegistryManager == null) {
            actionRegistryManager = new ActionRegistryManager(getActionRegistry());
        }
        return actionRegistryManager;
    }

    /**
     * Changed visibility to simplify coding.
     */
    public int getActivePage() {
        return super.getActivePage();
    }

    /**
     * Returns adapters for our editor.
     * 
     * To note: If we don't have any maps in the file (actually, opened tabs), we will show a default outline page.
     * 
     * If we don't have an adapter defined here and there are opened tabs, we will delegate to the active editor instead of our superclass.
     *  
     */
    public Object getAdapter(Class adapter) {
        if (adapter == ActionRegistry.class)
            return getActionRegistry();
        else if (adapter == ZoomManager.class)
            return getDelegatingZoomManager();
        else if (adapter == CommandStack.class)
            return getDelegatingCommandStack();
        else if (getPageCount() == 0 && adapter == IContentOutlinePage.class)
            return new UcmOutlinePage(this, new TreeViewer());

        // delegate to open editor if possible
        if (getPageCount() > 0) {
            return getActiveEditor().getAdapter(adapter);
        } else
            return super.getAdapter(adapter);
    }

    /**
     * Returns the current active page.
     * 
     * Public because used as utility method elsewhere.
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
    public DelegatingCommandStack getDelegatingCommandStack() {
        if (null == delegatingCommandStack) {
            delegatingCommandStack = new DelegatingCommandStack();
            if (null != getCurrentPage())
                delegatingCommandStack.setCurrentCommandStack(getCurrentPage().getCommandStack());
        }

        return delegatingCommandStack;
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
     * Changed visibility to simplify coding.
     */
    public IEditorPart getEditor(int pageIndex) {
        return super.getEditor(pageIndex);
    }

    /**
     * Returns our file manager.
     * 
     * @return our file manager
     */
    public MultiPageFileManager getFileManager() {
        if (fileManager == null)
            fileManager = new MultiPageFileManager(this);
        return fileManager;
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
    public MultiPageCommandStackListener getMultiPageCommandStackListener() {
        if (null == multiPageCommandStackListener) {
            multiPageCommandStackListener = new MultiPageCommandStackListener(this);
            multiPageCommandStackListener.addCommandStack(getDelegatingCommandStack());
        }
        return multiPageCommandStackListener;
    }

    /**
     * Returns our tab manager to which we delegate the tab related work.
     * 
     * @return our tab manager
     */
    protected MultiPageTabManager getMultiPageTabManager() {

        if (multiPageTabManager == null) {
            multiPageTabManager = new MultiPageTabManager(this);
        }

        return multiPageTabManager;
    }

    /**
     * Overridden to allow delegation to MultiPageTabManager
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#getPageCount()
     */
    public int getPageCount() {
        return super.getPageCount();
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
     * Public to give the outline access. 
     * 
     * @return the syncrhonizer
     */
    public SelectionSynchronizer getSelectionSynchronizer() {
        if (synchronizer == null)
            synchronizer = new SelectionSynchronizer();
        return synchronizer;
    }

    /**
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
    protected ZoomManager getZoomManager(GraphicalViewer viewer) {
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

    /**
     * Initializes the multipage editor.
     * 
     * @see org.eclipse.ui.IEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
     */
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        //		 read URNspec from input
        try {
            // we expect IFileEditorInput here,
            // ClassCassException is catched to force PartInitException
            IFile file = ((IFileEditorInput) input).getFile();

            // doing this here (even though done in super.init() because we want the following invocation to be able to popup an error message should anything
            // fail.
            setSite(site);
            setModel(getFileManager().create(file));

            // validate URNspec
            if (null == getModel())
                throw new PartInitException(Messages.getString("UCMNavMultiPageEditor.inputNotValidURN")); //$NON-NLS-1$

        } catch (CoreException e) {
            throw new PartInitException(e.getStatus());
        } catch (ClassCastException e) {
            throw new PartInitException(Messages.getString("UCMNavMultiPageEditor.inputNotValidURN"), e); //$NON-NLS-1$
        }

        // URNspec is ok
        super.init(site, input);

        // add delegating CommandStackListener
        getDelegatingCommandStack().addCommandStackListener(getMultiPageCommandStackListener());

        // add selection change listener
        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(getSelectionListener());
        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(getMultiPageTabManager().getSelectionListener());

        //  Creates actions and registers them to the ActionRegistry. Because the action registry is shared, we must created them here.
        // delegated to the registry manager.
        getActionRegistryManager().createActions(this, getSite().getKeyBindingService(), getDelegatingZoomManager());

    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
    }

    /**
     * Is this editor dirty? Through the MultiPageCommandStackListener and the DelegatingCommandStack behaviour, this means that either one of the editor stacks
     * is dirty or the URNspec stack has been modified.
     */
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * Always allow saving.
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {

        int type = notification.getEventType();
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (type) {
        case Notification.SET:
            switch (featureId) {
            case MapPackage.MAP__NAME:
                getMultiPageTabManager().refreshPageNames();
                break;
            }
        }
    }

    /**
     * This method is called when the user clicks on a tab.
     * 
     * Delegates to parent and MultiPageTabManager.
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
     */
    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);

        getMultiPageTabManager().pageChange(newPageIndex);

    }

    /**
     * Delegates to MultiPageTabManager; removes-recreates all tabs.
     */
    public void recreatePages() {
        getMultiPageTabManager().recreatePages();
    }

    /**
     * To allow us to programatically set the active page.
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
    protected void setDirty(boolean dirty) {
        if (isDirty != dirty) {
            isDirty = dirty;
            firePropertyChange(IEditorPart.PROP_DIRTY);
        }
    }

    /**
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

        // remove old listeners
        if (this.model != null) {
            for (int i = 0; i < this.model.getUcmspec().getMaps().size(); i++)
                ((Map) this.model.getUcmspec().getMaps().get(i)).eAdapters().remove(this);
        }

        this.model = model;

        // we must register ourselves to be able to change the tabs when the names change.
        for (int i = 0; model != null && i < model.getUcmspec().getMaps().size(); i++)
            ((Map) model.getUcmspec().getMaps().get(i)).eAdapters().add(this);

    }

    /**
     * Sets the command stack listener.
     * 
     * @param multiPageCommandStackListener
     */
    public void setMultiPageCommandStackListener(MultiPageCommandStackListener multiPageCommandStackListener) {
        this.multiPageCommandStackListener = multiPageCommandStackListener;
    }

    /**
     * Overridden to allow delegation to MultiPageTabManager
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#setPageText(int, java.lang.String)
     */
    protected void setPageText(int pageIndex, String text) {
        super.setPageText(pageIndex, text);
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocationProvider#createEmptyNavigationLocation()
     */
    public INavigationLocation createEmptyNavigationLocation() {
        return new MultiPageEditorLocation(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocationProvider#createNavigationLocation()
     */
    public INavigationLocation createNavigationLocation() {
        return new MultiPageEditorLocation(this);
    }

    /**
     * Change the active page in the editor from the Map object.
     * 
     * @param map
     */
    public void setActivePage(Map map) {
        int pageIndex = getModel().getUcmspec().getMaps().indexOf(map);
        setActivePage(pageIndex);
    }
}