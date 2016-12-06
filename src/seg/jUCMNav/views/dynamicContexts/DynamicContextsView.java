package seg.jUCMNav.views.dynamicContexts;

import java.util.Iterator;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.ViewPart;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.EvaluationStrategy;
import grl.kpimodel.Indicator;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditDomain;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editors.actionContributors.DynamicContextMenuProvider;
import seg.jUCMNav.editors.palette.UrnDropTargetListener;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts.DynamicContextEvaluationViewObject;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextTreeEditPartFactory;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.TimepointGroupTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.TimepointTreeEditPart;
import seg.jUCMNav.editparts.kpiViewEditparts.KPIViewObject;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.JUCMNavRefreshableView;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDragSourceListener;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDropTargetListener;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import seg.jUCMNav.views.strategies.StrategiesView;
import ucm.UCMspec;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;
import urncore.URNmodelElement;

/**
 * 
 * The dynamic context design/execution view.
 * 
 * @author aprajita
 * 
 */
public class DynamicContextsView extends ViewPart implements IPartListener2, ISelectionChangedListener, JUCMNavRefreshableView {
	
	private TreeViewer viewer;
	
	public static final int ID_DESIGN = 0;
    public static final int ID_STRATEGY = 1;

    private UCMNavMultiPageEditor multieditor;
    private DynamicContext currentContext;
    private DynamicContextTreeEditPart currentSelection;
    
    private Timepoint currentTimepoint;
    private TimepointTreeEditPart currentTimepointSelection;
    private TimepointGroup currentTpGroup;
    private TimepointGroupTreeEditPart currentTpGroupSelection;
    
    private EvaluationStrategy currentStrategy;
    
    private ScenarioDef currentScenario;
    
    private ContributionContext currentContributionContext;

    private IAction showDesignView, showStrategiesView, refreshTreeView, showId, enableGlobalFilter;

    private int currentView;

    private UrnTemplateTransferDropTargetListener urnDropListener;
	
	/**
     * The constructor.
     */
    public DynamicContextsView() {
    }
    protected TransferDropTargetListener getTransferDropTargetListener() {
        // if we cached it, we'd share it amongst models. 
        return new UrnDropTargetListener(viewer, ((URNspec)((UCMNavMultiPageEditor)viewer.getContents()).getModel()));
    }

    protected UrnTemplateTransferDropTargetListener getUrnTransferDropTargetListener() {
       return urnDropListener;
    }
    
    /**
     * This is a callback that will allow us to create the viewer and initialize it.
     */
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer();

        viewer.setEditDomain(new UrnEditDomain(null));

        getSite().getPage().addPartListener(this);

        viewer.createControl(parent);
        getSite().setSelectionProvider(viewer);
        viewer.addDragSourceListener(new UrnTemplateTransferDragSourceListener(viewer));
        viewer.addDropTargetListener(new UrnTemplateTransferDropTargetListener(viewer, null));
        
        showDesignView = new Action() {
            public void run() {
                // tree view
                showPage(ID_DESIGN);
            }
        };
        showDesignView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/urnstratscenoff16.gif")); //$NON-NLS-1$
        showDesignView.setToolTipText(Messages.getString("DynamicContextsView.switchDesign")); //$NON-NLS-1$
        showDesignView.setText(Messages.getString("DynamicContextsView.switchDesign")); //$NON-NLS-1$

        showStrategiesView = new Action() {
            public void run() {
                // tree view
                showPage(ID_STRATEGY);
            }
        };
        showStrategiesView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/urnstratscenon16.gif")); //$NON-NLS-1$
        showStrategiesView.setToolTipText(Messages.getString("DynamicContextsView.switchStrategies")); //$NON-NLS-1$
        showStrategiesView.setText(Messages.getString("DynamicContextsView.switchStrategies")); //$NON-NLS-1$

        refreshTreeView = new Action() {
            public void run() {
                refreshView(); // will force rebuilding of children, not just visual refresh of elements.  

                showPage(currentView);
            }
        };
        refreshTreeView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/refresh.gif")); //$NON-NLS-1$
        refreshTreeView.setToolTipText(Messages.getString("DynamicContextsView.Refresh")); //$NON-NLS-1$
        refreshTreeView.setText(Messages.getString("DynamicContextsView.Refresh")); //$NON-NLS-1$

        // Register the view to DisplayPreferences
        DisplayPreferences.getInstance().registerListener(this);

        // Preferences action
        enableGlobalFilter = new Action() {
            public void run() {
                DisplayPreferences.getInstance().setGlobalFilterEnabled(enableGlobalFilter.isChecked());
                if (enableGlobalFilter.isChecked()) {
                    PreferenceDialog pref = PreferencesUtil.createPreferenceDialogOn(getSite().getShell(),
                            "seg.jUCMNav.views.preferences.DisplayPreferencesPage", new String[] { "seg.jUCMNav.views.preferences.DisplayPreferencesPage" }, //$NON-NLS-1$ //$NON-NLS-2$
                            null);
                    if (pref != null)
                        pref.open();
                }
            }
        };

        enableGlobalFilter.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/filter16.gif")); //$NON-NLS-1$
        enableGlobalFilter.setToolTipText(Messages.getString("StrategiesView.FilterOut")); //$NON-NLS-1$
        enableGlobalFilter.setText(Messages.getString("StrategiesView.FilterOutline")); //$NON-NLS-1$
        enableGlobalFilter.setChecked(DisplayPreferences.getInstance().isGlobalFilterEnabled());

        showId = new Action() {
            public void run() {
                DisplayPreferences.getInstance().setShowNodeNumber(showId.isChecked());
            }
        };
        showId.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/identifiers.png")); //$NON-NLS-1$
        showId.setToolTipText(Messages.getString("UrnOutlinePage.ShowElementsIds")); //$NON-NLS-1$ 
        showId.setText(Messages.getString("UrnOutlinePage.ShowElementsIds")); //$NON-NLS-1$ 
        showId.setChecked(DisplayPreferences.getInstance().getShowNodeNumber());

        IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
        tbm.add(showStrategiesView);
        tbm.add(showDesignView);
        tbm.add(refreshTreeView);

        IMenuManager manager = getViewSite().getActionBars().getMenuManager();
        manager.add(refreshTreeView);
        manager.add(new Separator());
        manager.add(showStrategiesView);
        manager.add(showDesignView);

        showPage(ID_DESIGN);
        
    }

    /**
     * Removes listeners
     * 
     * @see org.eclipse.ui.part.IPage#dispose()
     */
    public void dispose() {

        unregisterElements();

        getSite().getPage().removePartListener(this);
        DisplayPreferences.getInstance().unregisterListener(this);

        // dispose
        super.dispose();
    }

    private void unregisterElements() {
        IActionBars bars = getViewSite().getActionBars();
        String id = ActionFactory.UNDO.getId();
        bars.setGlobalActionHandler(id, null);
        id = ActionFactory.REDO.getId();
        bars.setGlobalActionHandler(id, null);
        id = ActionFactory.DELETE.getId();
        bars.setGlobalActionHandler(id, null);
        id = ActionFactory.PASTE.getId();
        bars.setGlobalActionHandler(id, null);
        id = ActionFactory.COPY.getId();
        bars.setGlobalActionHandler(id, null);
        id = ActionFactory.CUT.getId();
        bars.setGlobalActionHandler(id, null);

        bars.clearGlobalActionHandlers();

        bars.updateActionBars();

        if (viewer != null) {
            if (multieditor != null && multieditor.getSelectionSynchronizer() != null)
                multieditor.getSelectionSynchronizer().removeViewer(viewer);

            RootEditPart p = viewer.getRootEditPart();
            p.setModel(null);
            p.removeNotify();
            p.getChildren().clear();
            if (viewer.getContents() != null) {
                viewer.getContents().setModel(null);
                viewer.setContents(null);
            }
            if (viewer.getControl() != null)
                viewer.getControl().setData(null);

            if (viewer.getContextMenu() != null) {
                viewer.getContextMenu().dispose();
                
            }

            if (viewer.getEditDomain() instanceof UrnEditDomain) {
                UrnEditDomain domain = (UrnEditDomain) viewer.getEditDomain();
                domain.dispose();
                viewer.setEditDomain(new DefaultEditDomain(null));
            }
            viewer.removeSelectionChangedListener(this);
            viewer.setEditPartFactory(null);
            viewer.getEditPartRegistry().clear();

        }

        if (multieditor != null && multieditor.getCurrentPage() != null) {
            multieditor.getCurrentPage().getGraphicalViewer().removeSelectionChangedListener(this);

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partActivated(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) == this || partRef.getPart(false) instanceof UCMNavMultiPageEditor) {
            setEditor(partRef);
        } else {
            // bug 709 - if we are no longer selecting a UCM editor, flush the current selection.
            if (!(partRef.getPage().getActiveEditor() instanceof UCMNavMultiPageEditor)) {
                if (viewer.getContents() != null) {
                    viewer.getContents().setModel(null);
                    viewer.setContents(null);
                }
                setEditor((UCMNavMultiPageEditor) null);

            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partBroughtToTop(IWorkbenchPartReference partRef) {
        // If the part brought to top is this view, then update the input.
        if (partRef.getPart(false) == this) {
            setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partClosed(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor && partRef.getPage().getActiveEditor() == null) {
            if (viewer.getContents() != null) {
                viewer.getContents().setModel(null);
                viewer.setContents(null);
            }
        }
        
        //Setting strategy null before "showPage(ID_DESIGN)" to prevent null pointer exception
        (EvaluationStrategyManager.getInstance(multieditor)).setStrategy(null);
        showPage(ID_DESIGN);
        currentScenario = null;
        currentContext = null;
        currentSelection = null;
        currentContributionContext = null;
        currentTimepoint = null;
        currentTimepointSelection = null;
        currentTpGroup = null;
        currentTpGroupSelection = null;
        currentStrategy = null;
        
    }

    public void setContext( DynamicContext context ) {
    	currentContext = context;
    }
    
    public void setTimepoint( Timepoint tp ) {
    	currentTimepoint = tp;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partDeactivated(IWorkbenchPartReference partRef) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partOpened(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this)
            setEditor(partRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partHidden(IWorkbenchPartReference partRef) {
        // viewer.setContents(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partVisible(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this) {
            setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partInputChanged(IWorkbenchPartReference partRef) {
    }

    private void setEditor(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor) {
            setEditor((UCMNavMultiPageEditor) partRef.getPart(false));
        }
    }

    /**
     * @param editor
     */
    private void setEditor(UCMNavMultiPageEditor editor) {

        if (multieditor != editor) {
            unregisterElements();

            multieditor = editor;
            EvaluationStrategyManager.getInstance(editor).setMultieditor(editor);

            unregisterElements();

            if (multieditor == null)
                return;
            // getActionRegistryManager().createActions(this, multieditor, getSite().getKeyBindingService());

            if (multieditor.getCurrentPage() != null)
                multieditor.getCurrentPage().getGraphicalViewer().addSelectionChangedListener(this);

            if (viewer.getEditDomain() instanceof UrnEditDomain) {
                ((UrnEditDomain) viewer.getEditDomain()).dispose();
            }

            viewer.setEditDomain(new UrnEditDomain(multieditor));
            viewer.getEditDomain().setCommandStack(multieditor.getDelegatingCommandStack());
            viewer.setEditPartFactory(new DynamicContextTreeEditPartFactory(multieditor.getModel()));

            // register them. other ways failed to add undo/redo, only added delete.
            IActionBars bars = getViewSite().getActionBars();
            String id = ActionFactory.UNDO.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.REDO.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.DELETE.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.PASTE.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.COPY.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.CUT.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));

            // Hook context menu

            ContextMenuProvider cmProvider = new DynamicContextMenuProvider(viewer, multieditor.getActionRegistry());
            viewer.setContextMenu(cmProvider);
            getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.DynamicContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$

            // hook viewer
            viewer.addSelectionChangedListener(this);
            editor.getSelectionSynchronizer().removeViewer(viewer);
            multieditor.getSelectionSynchronizer().addViewer(viewer);
            viewer.setContents(multieditor);

            expandTree();
            
            if (EvaluationStrategyManager.getInstance(multieditor).getEvaluationStrategy()!=null)
            {
            	EditPart part = (EditPart) viewer.getEditPartRegistry().get(EvaluationStrategyManager.getInstance(multieditor).getEvaluationStrategy());
            	if (part!=null) {
            	    // causes a loop that invalides the selection - all we want to do is refresh the UI
            		//viewer.select(part);
            	}
            }
        }
        
        // perform even if hasn't changed because our operation gets overridden by the main editor. 
        if (multieditor!=null) { 
        	
        	//The main editor calls the listeners to all the views, and dynamic contexts view here overrides the selection of
        	//ID_STRATEGY or ID_DESIGN from Strategies view, which isn't desirable. In order to avoid that, first the check needs to be done
        	//whether the Strategy view is in strategy mode or not. If it is, then the refresh selection should prioritize its selection over
        	//dyn context view.
        	boolean refreshStView = false;
        	StrategiesView sv = null;
        	if( (sv = EvaluationStrategyManager.getInstance(false).getStrategiesView()) != null ) {
        		//If the currentView in Strategies view is ID_STRATEGY then true
            	refreshStView = sv.isStrategyView();
                   
            }
        	
        	// bug 760; refresh selection after tab change.
            for (int i = 0; i < multieditor.getPageCount(); i++) {
                UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                if (refreshStView)
                	((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(refreshStView);
                else
                	((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(currentView == ID_STRATEGY);
            }  
        }


    }

    private void expandTree() {
        Tree tree = (Tree) viewer.getControl();
        // expand first two levels. 
        if (tree != null && tree.getItems() != null) {
            TreeItem[] items = tree.getItems();
            for (int i = 0; i < items.length; i++) {
                TreeItem[] items2 = items[i].getItems();
                for (int j = 0; j < items2.length; j++) {
                    ((TreeItem) items2[j]).setExpanded(true);
                }
                ((TreeItem) items[i]).setExpanded(true);
            }
        }
    }
    
    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        if (viewer.getContents() != null) {
            viewer.getControl().setFocus();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        StructuredSelection sel = (StructuredSelection) event.getSelection();

        if ((event.getSource() instanceof TreeViewer) && (multieditor != null)) {
            for (Iterator j = sel.iterator(); j.hasNext();) {
                Object obj = j.next();
                if (obj instanceof DynamicContextTreeEditPart) {
                    if (currentSelection != null) {
                        currentSelection.setSelected(false);
                    }
                    currentSelection = (DynamicContextTreeEditPart) obj;
                    if (currentView == ID_STRATEGY) {
                        currentSelection.setSelected(true);
                    }
                    DynamicContext dyn = ((DynamicContextTreeEditPart) obj).getDynamicContext();
                    currentContext = dyn;
                    EvaluationStrategy strat = dyn.getStrategy();
                    currentStrategy = strat;
                    ScenarioDef scen1 = dyn.getScenario();
                    currentScenario = scen1;
                    ContributionContext context = dyn.getContributionContext();
                    currentContributionContext = context;
                    (EvaluationStrategyManager.getInstance(multieditor)).setDynamicContext(dyn);
                    (EvaluationStrategyManager.getInstance(multieditor)).setTimepoint(currentTimepoint);
                    if (currentView == ID_STRATEGY) {
                    	 ScenarioUtils.setActiveScenario(currentScenario);
                         for (int i = 0; i < multieditor.getPageCount(); i++) {
                             UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                             ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);
                         }
                        (EvaluationStrategyManager.getInstance(multieditor)).setStrategy(currentStrategy);
                        (EvaluationStrategyManager.getInstance(multieditor)).setContributionContext(currentContributionContext);
                        refreshScenarioIfNeeded();
                        if (currentStrategy == null)
                        	disableStrategyView();
                        else 
                        	enableStrategyView();
                        
                    }
                } else if (obj instanceof TimepointTreeEditPart) {
                    if (currentTimepointSelection != null) {
                        currentTimepointSelection.setSelected(false);
                    }
                    currentTimepointSelection = (TimepointTreeEditPart) obj;
                    if (currentView == ID_STRATEGY) {
                        currentTimepointSelection.setSelected(true);
                    }
                    Timepoint tp = ((TimepointTreeEditPart) obj).getTimepoint();
                    currentTimepoint = tp;
                    (EvaluationStrategyManager.getInstance(multieditor)).setTimepoint(tp);
                    (EvaluationStrategyManager.getInstance(multieditor)).setDynamicContext(currentContext);
                    (EvaluationStrategyManager.getInstance(multieditor)).setStrategy(currentStrategy);
                    if (currentTpGroupSelection != null) {
                    	currentTpGroupSelection.setSelected(false);
                    }
                    currentTpGroup = null;
                    
                } else if (obj instanceof TimepointGroupTreeEditPart) {
                    if (currentTpGroupSelection != null) {
                    	currentTpGroupSelection.setSelected(false);
                    }
                    currentTpGroupSelection = (TimepointGroupTreeEditPart) obj;
                    if (currentView == ID_STRATEGY) {
                        currentTpGroupSelection.setSelected(true);
                    }
                    TimepointGroup tpGroup = ((TimepointGroupTreeEditPart) obj).getTimepointGroup();
                    currentTpGroup = tpGroup;
                    if (currentTimepointSelection != null) {
                    	currentTimepointSelection.setSelected(false);
                    }
                    currentTimepoint = null;
                    (EvaluationStrategyManager.getInstance(multieditor)).setTimepoint(null);
                } 
                
            }
        }
    }

    public void refreshScenarioIfNeeded() {
        if (ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables()
                && ScenarioUtils.getActiveScenario(multieditor.getModel()) != null) {
            // refresh scenario too.
            if (ScenarioUtils.getActiveScenario(multieditor.getModel()) instanceof ScenarioDef)
                ScenarioUtils.setActiveScenario((ScenarioDef) ScenarioUtils.getActiveScenario(multieditor.getModel()));
            else if (ScenarioUtils.getActiveScenario(multieditor.getModel()) instanceof ScenarioGroup)
                ScenarioUtils.setActiveScenario((ScenarioGroup) ScenarioUtils.getActiveScenario(multieditor.getModel()));
            else if (ScenarioUtils.getActiveScenario(multieditor.getModel()) instanceof UCMspec)
                ScenarioUtils.setActiveScenario((UCMspec) ScenarioUtils.getActiveScenario(multieditor.getModel()));
        }

        for (int i = 0; i < multieditor.getPageCount(); i++) {
            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(true);

            if (ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables()
                    && ScenarioUtils.getActiveScenario(multieditor.getModel()) != null) {
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);
            }
        }        
    }

    public void cancelStrategyMode() {
        for (int i = 0; i < multieditor.getPageCount(); i++) {
            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(false);

            if (ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables()
                    && ScenarioUtils.getActiveScenario(multieditor.getModel()) != null) {
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(false);
            }
        }            	
    }
    
    /**
     * Change the view of the model
     * 
     * @param id
     *            parameter indicating which view to display, passed when an appropriate button is pressed in the workbench
     */
    public void showPage(int id) {
    	
        if (id == ID_DESIGN) {
            showDesignView.setChecked(true);
            showStrategiesView.setChecked(false);
            EvaluationStrategyManager.getInstance().stopDifferenceMode();
            if (currentSelection != null) {
                currentSelection.setSelected(false);
                currentSelection = null; // forget it so that we can start anew
            }

            if (currentTimepointSelection != null) {
            	currentTimepointSelection.setSelected(false);
            	currentTimepointSelection = null; // forget it so that we can start anew
            }
            
            if (currentTpGroupSelection != null) {
            	currentTpGroupSelection.setSelected(false);
            	currentTpGroupSelection = null; // forget it so that we can start anew
            }
            
            currentView = ID_DESIGN;
            if (currentContext != null) {
            	EvaluationStrategyManager.getInstance(multieditor).setDynamicContext(null);
                disableStrategyView();
                currentContext  = null; // forget it so that we can start anew
            }
            if (currentContributionContext != null) {
                EvaluationStrategyManager.getInstance(multieditor).setContributionContext(null);
                enableStrategyView(); // refresh issue. 
                disableStrategyView();
                currentContributionContext  = null; // forget it so that we can start anew
            }
            EvaluationStrategyManager.getInstance(multieditor).setStrategy(null);
            if (currentStrategy != null) {
                disableStrategyView();
                currentStrategy = null; // forget it so that we can start anew
            }
            if (currentScenario != null && multieditor != null) {
                ScenarioUtils.clearActiveScenario(multieditor.getModel());
                currentScenario = null; // forget it so that we can start anew
                disableStrategyView(); 
            }
            if (currentTimepoint != null) {
                EvaluationStrategyManager.getInstance(multieditor).setTimepoint(null);
                disableStrategyView();
                currentTimepoint = null; // forget it so that we can start anew
            }
            if (currentTpGroup != null) {
                disableStrategyView();
                currentTpGroup = null; // forget it so that we can start anew
            }

            // Removes the metadata for GRL evaluations and UCM executions
            if (multieditor != null) {
                MetadataHelper.cleanRunTimeMetadata(multieditor.getModel());
                MetadataHelper.cleanTimedGRLMetadata(multieditor.getModel());
            }


        } else if (id == ID_STRATEGY) {
        	
        	//Switch off the strategy mode of StrategiesView
        	StrategiesView sv = null;
        	if( (sv = EvaluationStrategyManager.getInstance(false).getStrategiesView()) != null ) {
                sv.showPage(StrategiesView.ID_DESIGN);
                   
            }
            showDesignView.setChecked(false);
            showStrategiesView.setChecked(true);

            if (currentSelection != null) {
                currentSelection.setSelected(true);
            }
            if (currentTimepointSelection != null) {
                currentTimepointSelection.setSelected(true);
            }
            if (currentTpGroupSelection != null) {
            	currentTpGroupSelection.setSelected(true);
            }
            
            currentView = ID_STRATEGY;
            
            if (currentContext != null && (currentContext.getGroups().size() == 0 || currentContext.getUrnspec() == null)) {
                // was deleted
                currentContext = null;
            }
            if (currentTimepoint != null && (currentTimepoint.getGroup() == null)) {
                // was deleted
                currentTimepoint = null;
            }
            
            
            //Currently timepoint must be set first
            if (currentTimepoint != null) {
            	EvaluationStrategyManager.getInstance(multieditor).setTimepoint(currentTimepoint);
            }
            
            if (currentContext != null) {
            	EvaluationStrategyManager.getInstance(multieditor).setDynamicContext(currentContext);
            	
            	if (currentContributionContext != null && (currentContributionContext.getGroups().size()==0 || ((ContributionContextGroup)currentContributionContext.getGroups().get(0)).getGrlspec() == null)
                		&& currentContributionContext != currentContext.getContributionContext()) {
                    // was deleted
                    currentContributionContext = null;
                }
                if (currentContributionContext != null) {
                    EvaluationStrategyManager.getInstance(multieditor).setContributionContext(currentContributionContext);
                }
                if ((currentStrategy != null && (currentStrategy.getGroup() == null || currentStrategy.getGroup().getGrlspec() == null)) 
                		|| (currentContext.getStrategy() == null)) {
                    // was deleted or, strategy of current dynamic context is null
                    currentStrategy = null;
                    EvaluationStrategyManager.getInstance(multieditor).setStrategy(null);
                }
                
                if (currentStrategy != null) {
                    EvaluationStrategyManager.getInstance(multieditor).setStrategy(currentStrategy);
                    enableStrategyView();
                } else {
                	EvaluationStrategyManager.getInstance(multieditor).setStrategy(null);
                	disableStrategyView();
                }

                if (currentScenario != null && (currentScenario.getGroup() == null || currentScenario.getGroup().getUcmspec() == null) 
                		&& currentScenario != currentContext.getScenario()) {
                    // was deleted
                    currentScenario = null;
                }
                if (currentScenario != null) {
                    ScenarioUtils.setActiveScenario(currentScenario);
                    enableStrategyView();
                }
            }
            
        }
    }

    public void enableStrategyView() {
        if (multieditor!=null) {
            for (int i = 0; i < multieditor.getPageCount(); i++) {
                UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(true);
            }
        }
        
    }

    private void disableStrategyView() {
        if (multieditor != null) {
            for (int i = 0; i < multieditor.getPageCount(); i++) {
                UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(false);
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);
            }
        }
        viewer.deselectAll();
    }

    public Object getAdapter(Class adapter) {
        if (adapter == org.eclipse.ui.views.properties.IPropertySheetPage.class) {
            if (multieditor != null)
                return multieditor.getAdapter(org.eclipse.ui.views.properties.IPropertySheetPage.class);
            else
                return super.getAdapter(adapter);
        } else
            return super.getAdapter(adapter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.JUCMNavRefreshableView#refreshView()
     */
    public void refreshView() {
        viewer.setContents(viewer.getContents());
        showId.setChecked(DisplayPreferences.getInstance().getShowNodeNumber());
        expandTree();
    }

    public void highlightTreeElement( URNmodelElement element ) {
    	EditPart ep = null;
    	
    	if( viewer == null) return;
    	
    	if( (ep = (EditPart) viewer.getEditPartRegistry().get(element)) != null ) {
    		viewer.select( ep );
    	}
    }
    
    public boolean isDynamicView() {
    	return( currentView == ID_STRATEGY );
    }
    
    public DynamicContext getDynamicContext() {
    	return currentContext;
    }
    
    public TimepointGroup getTimepointGroup() {
    	return currentTpGroup;
    }

}
