package seg.jUCMNav.views.strategies;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.EvaluationStrategy;

import java.util.Iterator;
import java.util.Vector;

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
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.ViewPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditDomain;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editors.actionContributors.StrategyContextMenuProvider;
import seg.jUCMNav.editors.palette.UrnDropTargetListener;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ContributionContextTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.EvaluationStrategyTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioDefTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioGroupTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyTreeEditPartFactory;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.JUCMNavRefreshableView;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDragSourceListener;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDropTargetListener;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.UCMspec;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * 
 * The strategy/scenario design/execution view.
 * 
 * @author Jean-Fran�ois Roy, jkealey
 * 
 */
public class StrategiesView extends ViewPart implements IPartListener2, ISelectionChangedListener, JUCMNavRefreshableView {
    private TreeViewer viewer;

    public static final int ID_DESIGN = 0;
    public static final int ID_STRATEGY = 1;

    private UCMNavMultiPageEditor multieditor;
    private EvaluationStrategy currentStrategy;
    private EvaluationStrategyTreeEditPart currentSelection;
    private ScenarioDef currentScenario;
    private ScenarioDefTreeEditPart currentScenarioSelection;
    
    private ContributionContext currentContributionContext;
    private ContributionContextTreeEditPart currentContributionContextSelection; 

    private IAction showDesignView, showStrategiesView, refreshTreeView, showId, enableGlobalFilter;

    private int currentView;

/*    private UrnDropTargetListener dropListener;*/
    private UrnTemplateTransferDropTargetListener urnDropListener;

    /**
     * The constructor.
     */
    public StrategiesView() {
    }

    protected TransferDropTargetListener getTransferDropTargetListener() {
        // if we cached it, we'd share it amongst models. 
        return new UrnDropTargetListener(viewer, ((URNspec)((UCMNavMultiPageEditor)viewer.getContents()).getModel()));
    }

    protected UrnTemplateTransferDropTargetListener getUrnTransferDropTargetListener() {
        /*if (urnDropListener == null)
            urnDropListener = new UrnTemplateTransferDropTargetListener(this);*/
        return urnDropListener;
    }
    
    /**
     * This is a callback that will allow us to create the viewer and initialize it.
     */
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer();

        // viewer.setContents(null);
        // viewer.setEditPartFactory(new StrategyTreeEditPartFactory(null));

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
        showDesignView.setToolTipText(Messages.getString("StrategiesView.switchDesign")); //$NON-NLS-1$
        showDesignView.setText(Messages.getString("StrategiesView.switchDesign")); //$NON-NLS-1$

        showStrategiesView = new Action() {
            public void run() {
                // tree view
                showPage(ID_STRATEGY);
            }
        };
        showStrategiesView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/urnstratscenon16.gif")); //$NON-NLS-1$
        showStrategiesView.setToolTipText(Messages.getString("StrategiesView.switchStrategies")); //$NON-NLS-1$
        showStrategiesView.setText(Messages.getString("StrategiesView.switchStrategies")); //$NON-NLS-1$

        refreshTreeView = new Action() {
            public void run() {
                // tree view
                /*if (viewer != null && viewer.getRootEditPart().getChildren().size() > 0) {
                    StrategyRootEditPart root = ((StrategyRootEditPart) viewer.getRootEditPart().getChildren().get(0));
                    if (root.getModel() != null && ((UCMNavMultiPageEditor) root.getModel()).getModel() != null)
                        root.refreshScenarioTreeView(((UCMNavMultiPageEditor) root.getModel()).getModel().getUcmspec());
                }*/
                refreshView(); // will force rebuilding of children, not just visual refresh of elements.  

                showPage(currentView);
            }
        };
        refreshTreeView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/refresh.gif")); //$NON-NLS-1$
        refreshTreeView.setToolTipText(Messages.getString("StrategiesView.Refresh")); //$NON-NLS-1$
        refreshTreeView.setText(Messages.getString("StrategiesView.Refresh")); //$NON-NLS-1$

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
        tbm.add(new Separator());
        tbm.add(enableGlobalFilter);
        // tbm.add(showId);

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
                // exceptions
                // viewer.setContextMenu(null);
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
                // multieditor=null;
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
        showPage(ID_DESIGN);
        
        currentScenario = null;
        currentScenarioSelection = null;
        currentStrategy = null;
        currentSelection = null;
        currentContributionContext = null;
        currentContributionContextSelection = null;
    }

    public void setStrategy( EvaluationStrategy strategy ) {
    	currentStrategy = strategy;
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
            viewer.setEditPartFactory(new StrategyTreeEditPartFactory(multieditor.getModel()));

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

            ContextMenuProvider cmProvider = new StrategyContextMenuProvider(viewer, multieditor.getActionRegistry());
            viewer.setContextMenu(cmProvider);
            getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.StrategyContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$

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
            // bug 760; refresh selection after tab change.
            for (int i = 0; i < multieditor.getPageCount(); i++) {
                UrnEditor u = (UrnEditor) multieditor.getEditor(i);
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
                if (obj instanceof EvaluationStrategyTreeEditPart) {
                    if (currentSelection != null) {
                        currentSelection.setSelected(false);
                    }
                    currentSelection = (EvaluationStrategyTreeEditPart) obj;
                    if (currentView == ID_STRATEGY) {
                        currentSelection.setSelected(true);
                    }
                    EvaluationStrategy scen = ((EvaluationStrategyTreeEditPart) obj).getEvaluationStrategy();
                    currentStrategy = scen;
                    if (currentView == ID_STRATEGY) {
                        (EvaluationStrategyManager.getInstance(multieditor)).setStrategy(scen);
                        refreshScenarioIfNeeded();
                    }
                } else if (obj instanceof ContributionContextTreeEditPart) {
                    if (currentContributionContextSelection != null) {
                        currentContributionContextSelection.setSelected(false);
                    }
                    currentContributionContextSelection = (ContributionContextTreeEditPart) obj;
                    if (currentView == ID_STRATEGY) {
                        currentContributionContextSelection.setSelected(true);
                    }
                    ContributionContext context = ((ContributionContextTreeEditPart) obj).getContributionContext();
                    currentContributionContext = context;
                    if (currentView == ID_STRATEGY) {
                        (EvaluationStrategyManager.getInstance(multieditor)).setContributionContext(context);
                        refreshScenarioIfNeeded();
                    }
                } else if (obj instanceof ScenarioDefTreeEditPart && ((ScenarioDefTreeEditPart) obj).getParent() instanceof ScenarioGroupTreeEditPart) {
                    if (currentScenarioSelection != null) {
                        currentScenarioSelection.setSelected(false);
                    }
                    currentScenarioSelection = (ScenarioDefTreeEditPart) obj;
                    if (currentView == ID_STRATEGY) {
                        currentScenarioSelection.setSelected(true);
                    }
                    ScenarioDef scen = ((ScenarioDefTreeEditPart) obj).getScenarioDef();
                    currentScenario = scen;
                    if (currentView == ID_STRATEGY) {
                        ScenarioUtils.setActiveScenario(currentScenario);
                        for (int i = 0; i < multieditor.getPageCount(); i++) {
                            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);
                        }
                    }
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

            if (currentScenarioSelection != null) {
                currentScenarioSelection.setSelected(false);
                currentScenarioSelection = null; // forget it so that we can start anew
            }
            if (currentContributionContextSelection != null) {
                currentContributionContextSelection.setSelected(false);
                currentContributionContextSelection = null; // forget it so that we can start anew
            }
            
            currentView = ID_DESIGN;
            if (currentContributionContext != null) {
                EvaluationStrategyManager.getInstance(multieditor).setContributionContext(null);
                enableStrategyView(); // refresh issue. 
                disableStrategyView();
                currentContributionContext  = null; // forget it so that we can start anew
            }
            if (currentStrategy != null) {
                EvaluationStrategyManager.getInstance(multieditor).setStrategy(null);
                disableStrategyView();
                currentStrategy = null; // forget it so that we can start anew
            }
            if (currentScenario != null && multieditor != null) {
                ScenarioUtils.clearActiveScenario(multieditor.getModel());
                currentScenario = null; // forget it so that we can start anew
                disableStrategyView(); 
            }

            // Removes the metadata for GRL evaluations and UCM executions
            if (multieditor != null)
                MetadataHelper.cleanRunTimeMetadata(multieditor.getModel());

        } else if (id == ID_STRATEGY) {
            showDesignView.setChecked(false);
            showStrategiesView.setChecked(true);

            if (currentSelection != null) {
                currentSelection.setSelected(true);
            }
            if (currentScenarioSelection != null) {
                currentScenarioSelection.setSelected(true);
            }
            if (currentContributionContextSelection != null) {
                currentContributionContextSelection.setSelected(true);
            }

            currentView = ID_STRATEGY;
            
            if (currentContributionContext != null && (currentContributionContext.getGroups().size()==0 || ((ContributionContextGroup)currentContributionContext.getGroups().get(0)).getGrlspec() == null)) {
                // was deleted
                currentContributionContext = null;
            }
            if (currentContributionContext != null) {
                EvaluationStrategyManager.getInstance(multieditor).setContributionContext(currentContributionContext);
            }
            
            if (currentStrategy != null && (currentStrategy.getGroup() == null || currentStrategy.getGroup().getGrlspec() == null)) {
                // was deleted
                currentStrategy = null;
            }
            
            if (currentStrategy != null) {
                EvaluationStrategyManager.getInstance(multieditor).setStrategy(currentStrategy);
                enableStrategyView();
            }

            if (currentScenario != null && (currentScenario.getGroup() == null || currentScenario.getGroup().getUcmspec() == null)) {
                // was deleted
                currentScenario = null;
            }
            if (currentScenario != null) {
                ScenarioUtils.setActiveScenario(currentScenario);
                enableStrategyView();
            }
        }
    }

    private void enableStrategyView() {
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
    
    public boolean isStrategyView() {
    	return( currentView == ID_STRATEGY );
    }
    
    public void highlightStrategies( EvaluationStrategy strategy1, EvaluationStrategy strategy2 ) {
    	EditPart sep1 = null, sep2 = null;
    	
    	if( viewer == null) return;
    	
    	sep1 = (EditPart) viewer.getEditPartRegistry().get( strategy1 );
    	sep2 = (EditPart) viewer.getEditPartRegistry().get( strategy2 );
    	
    	if( sep1 == null | sep2 == null ) return;
    	
    	Vector<EditPart> v = new Vector<EditPart>();
        v.add( sep1 );
        v.add( sep2 );
        StructuredSelection sel = new StructuredSelection(v);
        viewer.setSelection(sel);
        
//    	if( JUCMNavPlugin.isInDebug() ){
//    		System.out.println( "strategies highlighted " ); //$NON-NLS-1$
//    	}

    }
}