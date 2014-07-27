package seg.jUCMNav.editors;

import grl.GrlPackage;

import java.net.URI;




import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.INavigationLocationProvider;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.services.ISourceProviderService;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.actionContributors.ActionRegistryManager;
import seg.jUCMNav.editors.resourceManagement.MultiPageFileManager;
import seg.jUCMNav.editors.resourceManagement.ResourceTracker;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.sourceProviders.AlignStateSourceProvider;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.OpenEditorQuickFix;
import seg.jUCMNav.views.QuickFixer;
import seg.jUCMNav.views.UCMPerspectiveFactory;
import seg.jUCMNav.views.outline.UrnOutlinePage;
import seg.jUCMNav.views.outline.UrnTreeViewer;
import seg.jUCMNav.views.property.tabbed.GEFTabbedPropertySheetPage;
import ucm.UcmPackage;
import ucm.map.MapPackage;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

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
public class UCMNavMultiPageEditor extends MultiPageEditorPart implements Adapter, INavigationLocationProvider, IGotoMarker, IPropertySourceProvider,
        ITabbedPropertySheetPageContributor {
    private final class ActionRegistrySelectionListener implements ISelectionListener {
        protected ActionRegistryManager manager;

        public ActionRegistrySelectionListener(ActionRegistryManager manager) {
            this.manager = manager;
        }

        public void selectionChanged(IWorkbenchPart part, ISelection selection) {
            if (manager != null)
                manager.updateEditPartActions();
            
            ISourceProviderService service = (ISourceProviderService) PlatformUI.getWorkbench().
            		getActiveWorkbenchWindow().getService(ISourceProviderService.class); 
            AlignStateSourceProvider alignStateSourceProvider = (AlignStateSourceProvider) service.getSourceProvider(AlignStateSourceProvider.SELECTION_STATE); 
            alignStateSourceProvider.updateStateSelectionChanged();
        }
    }

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
    private ISelectionListener selectionListener;

    /** the selection synchronizer for the edit part viewer */
    private SelectionSynchronizer synchronizer;

    /** To be notified of changes to the map names */
    private Notifier target;

    private UrnOutlinePage soleUrnOutlinePage = null;
    
    /**
     * We always want to be editing a URNspec
     */
    public UCMNavMultiPageEditor() {
        setModel(ModelCreationFactory.getNewURNspec());
        ColorManager.refresh();
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

    protected IEditorSite createSite(IEditorPart editor) {
        return new UrnMultiPageEditorSite(this, editor);
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
     * @param listener
     *            The listener to add
     */
    public void addPageChangeListener(IPageChangeListener listener) {
        getMultiPageTabManager().addPageChangeListener(listener);
    }

    /**
     * Remove a page change listener.
     * 
     * @param listener
     *            The listener to remove
     */
    public void removePageChangeListener(IPageChangeListener listener) {
        getMultiPageTabManager().removePageChangeListener(listener);
    }

    /**
     * Releases all listeners
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#dispose()
     */
    public void dispose() {
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(getSelectionListener());
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(getMultiPageTabManager().getSelectionListener());

        // remove delegating CommandStackListener
        getDelegatingCommandStack().removeCommandStackListener(getMultiPageCommandStackListener());

        getDelegatingCommandStack().setCurrentCommandStack(null);
        // dispose multi page command stack listener
        getMultiPageCommandStackListener().dispose();

        // dispose the ActionRegistry (will dispose all actions)
        getActionRegistryManager().dispose();
        getActionRegistry().dispose();

        // dispose the ResourceChangeListener
        IFile file = null;
        if (getEditorInput() instanceof FileEditorInput) {
            file = ((FileEditorInput) getEditorInput()).getFile();
            file.getWorkspace().removeResourceChangeListener(getResourceTracker());
        }

        if (model != null) {
            model.getUrndef().eAdapters().remove(this);
            // stop listening to all maps for name changes
            for (int i = 0; i < model.getUrndef().getSpecDiagrams().size(); i++)
                ((IURNDiagram) model.getUrndef().getSpecDiagrams().get(i)).eAdapters().remove(this);
        }
        // clear memory cache
        ScenarioUtils.releaseEnvironment(model);
        EvaluationStrategyManager.releaseEnvironment(this);

        if (file != null) {

            // clear markers
            try {

                IMarker[] existingMarkers = file.findMarkers(IMarker.PROBLEM, true, 3);
                for (int i = 0; i < existingMarkers.length; i++) {
                    IMarker marker = existingMarkers[i];
                    marker.delete();
                }
            } catch (CoreException ex) {
            }
        }

        // bug 531.
        getSite().setSelectionProvider(null);
        actionRegistry = null;
        actionRegistryManager = null;
        delegatingCommandStack = null;
        if (getDelegatingZoomManager() != null)
            getDelegatingZoomManager().setEditor(null);
        delegatingZoomManager = null;
        fileManager = null;
        model = null;
        multiPageCommandStackListener = null;
        if (getMultiPageTabManager() != null)
            getMultiPageTabManager().setEditor(null);
        multiPageTabManager = null;
        resourceTracker = null;
        selectionListener = null;
        target = null;
        deactivateSite(true, false);
        // important: always call super implementation of dispose
        super.dispose();

        // System.out.println(getSelectionSynchronizer());
        synchronizer = null;

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
    public ActionRegistryManager getActionRegistryManager() {
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
//        else if (getPageCount() == 0 && adapter == IContentOutlinePage.class) {
//        	return new UrnOutlinePage(this, new UrnTreeViewer());
//        }
        else if( adapter == IContentOutlinePage.class ) {
        	if( soleUrnOutlinePage == null ) {
        		soleUrnOutlinePage = new UrnOutlinePage(this, new UrnTreeViewer());
        	}
        	return soleUrnOutlinePage;
        }
        // delegate to open editor if possible
        if (getPageCount() > 0) {
            if (adapter == org.eclipse.ui.views.properties.IPropertySheetPage.class)
                return new GEFTabbedPropertySheetPage(this);
            else
                return getActiveEditor().getAdapter(adapter);
        } else
            return super.getAdapter(adapter);
    }

    public IEditorPart getActiveEditor() {
        return super.getActiveEditor();
    }

    /**
     * Returns the current active page.
     * 
     * Public because used as utility method elsewhere.
     * 
     * @return the current active page or <code>null</code>
     */
    public UrnEditor getCurrentPage() {
        if (getActivePage() == -1)
            return null;

        return (UrnEditor) getActiveEditor();
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
            delegatingZoomManager = new DelegatingZoomManager(this);
            if (null != getCurrentPage() && null != getCurrentPage().getGraphicalViewer())
                delegatingZoomManager.setCurrentZoomManager(getZoomManager(getCurrentPage().getGraphicalViewer()));
        }

        return delegatingZoomManager;
    }

    /**
     * Changed visibility to simplify coding.
     */
    public IEditorPart getEditor(int pageIndex) {
        if (getPageCount() == 0)
            return null;
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
            // already there
            // multiPageCommandStackListener.addCommandStack(getDelegatingCommandStack());
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

    public Composite getContainer() {
        return super.getContainer();
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
     * Return the resource tracker of this editor.
     * 
     * @return The resource tracker of this editor.
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
        if (selectionListener == null)
            selectionListener = new ActionRegistrySelectionListener(getActionRegistryManager());
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
        // read URNspec from input
        try {

            // doing this here (even though done in super.init() because we want the following invocation to be able to popup an error message should anything
            // fail.
            setSite(site);

            // we expect IFileEditorInput here,
            // ClassCassException is catched to force PartInitException

            if (!(input instanceof IFileEditorInput)) {
                // File -> Open gives us a JavaFileEditorInput
                // we don't want to open it because our scenario warnings are associated to the IFile...
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        Messages.getString("UCMNavMultiPageEditor.Error"), Messages.getString("UCMNavMultiPageEditor.CannotUseFileOpen")); //$NON-NLS-1$ //$NON-NLS-2$
                try {
                    this.closeEditor(false);
                } catch (Exception ex) {

                }

                return;

            } else {
                IFile file = ((IFileEditorInput) input).getFile();
                setModel(getFileManager().create(file));
            }

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

        // Creates actions and registers them to the ActionRegistry. Because the action registry is shared, we must created them here.
        // delegated to the registry manager.
        getActionRegistryManager().createActions(this, getSite().getKeyBindingService(), getDelegatingZoomManager());

        try {
            // IDE.openEditor(page, file, true);
            PlatformUI.getWorkbench().showPerspective(UCMPerspectiveFactory.JUCMNAV_PERSPECTIVE_ID, PlatformUI.getWorkbench().getActiveWorkbenchWindow());

        } catch (PartInitException e) {
            // ignore
        } catch (WorkbenchException e) {
            // ignore
        } catch (AssertionFailedException e) {
            // ignore
        }
        
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
     * TODO Modify this method to refresh name related to GRLGraph name
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {

        int type = notification.getEventType();
        switch (type) {
        case Notification.SET:
            int featureIdUcm = notification.getFeatureID(UcmPackage.class);
            int featureIdGrl = notification.getFeatureID(GrlPackage.class);
            if ((featureIdUcm == MapPackage.UC_MMAP__NAME) || (featureIdGrl == GrlPackage.GRL_GRAPH__NAME)) {
                getMultiPageTabManager().refreshPageNames();
            }
            break;
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

    public URI getInputRawLocationUri(){
    	IFile iFile = ((FileEditorInput) getEditorInput()).getFile();
    	return iFile.getRawLocationURI();
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
            // Set the listener to listen only for changes in the workspace
            file.getWorkspace().addResourceChangeListener(getResourceTracker()); // , IResourceChangeEvent.POST_CHANGE);
            setPartName(file.getName());
        }
    }

    public void setInput(IFile newFile, URNspec spec) {
        setInput(new FileEditorInput(newFile));
        try {
            getFileManager().create(newFile);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        getFileManager().resetFile(newFile, spec);
        setModel(spec);
        recreatePages();
    }

    /**
     * @param model
     *            The model to set.
     */
    public void setModel(URNspec model) {

        // remove old listeners
        if (this.model != null) {
            this.model.getUrndef().eAdapters().remove(this);
            for (int i = 0; i < this.model.getUrndef().getSpecDiagrams().size(); i++) {
                ((IURNDiagram) this.model.getUrndef().getSpecDiagrams().get(i)).eAdapters().remove(this);
            }
        }

        this.model = model;

        model.getUrndef().eAdapters().add(this);
        // we must register ourselves to be able to change the tabs when the names change.
        for (int i = 0; model != null && i < model.getUrndef().getSpecDiagrams().size(); i++) {
            ((IURNDiagram) model.getUrndef().getSpecDiagrams().get(i)).eAdapters().add(this);
        }
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

    /**
     * Creates a {@link MultiPageEditorLocation}
     * 
     * @see org.eclipse.ui.INavigationLocationProvider#createEmptyNavigationLocation()
     */
    public INavigationLocation createEmptyNavigationLocation() {
        return new MultiPageEditorLocation(this);
    }

    /**
     * Creates a {@link MultiPageEditorLocation}
     * 
     * @see org.eclipse.ui.INavigationLocationProvider#createNavigationLocation()
     */
    public INavigationLocation createNavigationLocation() {
        return new MultiPageEditorLocation(this);
    }

    /**
     * Change the active page in the editor from the Graph object.
     * 
     * @param diagram
     */
    public void setActivePage(IURNDiagram diagram) {
        int pageIndex = getModel().getUrndef().getSpecDiagrams().indexOf(diagram);
        setActivePage(pageIndex);
    }

    /**
     * If the marker has an EObject attribute, the value is its {@link URNmodelElement} ID. We try to find it in the {@link URNspec} and select it in the UI.
     * 
     * Otherwise, we look at its resolutions and if a code editor must be opened to fix it (via a {@link OpenEditorQuickFix}), we open the code editor.
     */
    public void gotoMarker(IMarker marker) {
        try {
            if (!marker.exists())
                return;
            Object o = marker.getAttribute("EObject"); //$NON-NLS-1$
            if (o != null) {

                UrnOutlinePage outline;
                //if (getPageCount() == 0)
                    outline = (UrnOutlinePage) getAdapter(IContentOutlinePage.class);
                //else
                    //outline = (UrnOutlinePage) getEditor(0).getAdapter(IContentOutlinePage.class);

                Object element = URNElementFinder.find(getModel(), o.toString());
                if (element != null) {

                    EditPart part = (EditPart) outline.getViewer().getEditPartRegistry().get(element);

                    selectInOutline(part);
                }

                // if found nothing, see if we have a quick fix.

                QuickFixer fixer = new QuickFixer();
                IMarkerResolution[] resolutions = fixer.getResolutions(marker);

                if (resolutions.length > 0) {
                    // we don't want to automatically run any other quick fix than this one.
                    if (resolutions[0] instanceof OpenEditorQuickFix) {
                        resolutions[0].run(marker);
                    }
                }

            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }
    
    public void selectInOutline(EditPart part) {
        UrnOutlinePage outline = (UrnOutlinePage) getAdapter(IContentOutlinePage.class);
        if (part != null && outline != null) {
            getMultiPageTabManager().getSelectionListener().selectionChanged(this, new StructuredSelection(part));
            outline.getViewer().select(part);
        }
    }
    
    public void selectInDiagram(EObject model, IURNDiagram diagram) {
        setActivePage(diagram);
        EditPart part = (EditPart)getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(model);
        getMultiPageTabManager().getSelectionListener().selectionChanged(this, new StructuredSelection(part));
        
        getCurrentPage().getGraphicalViewer().select(part);
    }

    /**
     * Return's the outline's property source.
     */
    public IPropertySource getPropertySource(Object object) {
        UrnOutlinePage outline;
        if (getPageCount() == 0)
            outline = (UrnOutlinePage) getAdapter(IContentOutlinePage.class);
        else
            outline = (UrnOutlinePage) getEditor(0).getAdapter(IContentOutlinePage.class);

        if (outline != null) {
            EditPart part = (EditPart) outline.getViewer().getEditPartRegistry().get(object);
            if (part != null)
                return (IPropertySource) part.getAdapter(IPropertySource.class);
        }
        return null;
    }

    public String getContributorId() {
        return getSite().getId();
    }

    protected void initializePageSwitching() {
        super.initializePageSwitching();

        getMultiPageTabManager().setupDragDropPage();
    }
}