package seg.jUCMNav.views.strategies;

import grl.EvaluationStrategy;

import java.util.Iterator;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
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
import org.eclipse.ui.part.ViewPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editors.actionContributors.StrategyContextMenuProvider;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.EvaluationStategyTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioDefTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioGroupTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyRootEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyTreeEditPartFactory;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.UCMspec;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

/**
 * 
 * The strategy/scenario design/execution view. 
 * 
 * @author Jean-François Roy, jkealey
 *
 */
public class StrategiesView extends ViewPart implements IPartListener2, ISelectionChangedListener{
	private TreeViewer viewer;

    static final int ID_DESIGN = 0;
    static final int ID_STRATEGY = 1;

	private UCMNavMultiPageEditor multieditor;
	private EvaluationStrategy currentStrategy;
    private EvaluationStategyTreeEditPart currentSelection;
	private ScenarioDef currentScenario;
    private ScenarioDefTreeEditPart currentScenarioSelection;
    
    private IAction showDesignView, showStrategiesView, refreshTreeView; 
    
    private int currentView;
	/**
	 * The constructor.
	 */
	public StrategiesView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
        viewer = new TreeViewer();
        viewer.addSelectionChangedListener(this);
        
        //viewer.setContents(null);
        //viewer.setEditPartFactory(new StrategyTreeEditPartFactory(null));
        
        viewer.setEditDomain(new DefaultEditDomain(null));
        
        getSite().getPage().addPartListener(this);
        
        viewer.createControl(parent);
        getSite().setSelectionProvider(viewer); 
        
        showDesignView = new Action() {
            public void run() {
                // tree view
                showPage(ID_DESIGN);
            }
        };
        showDesignView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/urnstratscenoff16.gif")); //$NON-NLS-1$
        showDesignView.setToolTipText(Messages.getString("StrategiesView.switchDesign"));  //$NON-NLS-1$
        showDesignView.setText(Messages.getString("StrategiesView.switchDesign"));  //$NON-NLS-1$

        showStrategiesView = new Action() {
            public void run() {
                // tree view
                showPage(ID_STRATEGY);
            }
        };
        showStrategiesView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/urnstratscenon16.gif")); //$NON-NLS-1$
        showStrategiesView.setToolTipText(Messages.getString("StrategiesView.switchStrategies"));  //$NON-NLS-1$
        showStrategiesView.setText(Messages.getString("StrategiesView.switchStrategies"));  //$NON-NLS-1$

        refreshTreeView = new Action() {
            public void run() {
                // tree view
            	if (viewer!=null && viewer.getRootEditPart().getChildren().size()>0) {
            		StrategyRootEditPart root = ((StrategyRootEditPart) viewer.getRootEditPart().getChildren().get(0));
            		if (root.getModel()!=null && ((UCMNavMultiPageEditor)root.getModel()).getModel()!=null)
            			root.refreshScenarioTreeView(((UCMNavMultiPageEditor)root.getModel()).getModel().getUcmspec());
            	}
            	
            	showPage(currentView);
            }
        };
        refreshTreeView.setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/refresh.gif")); //$NON-NLS-1$
        refreshTreeView.setToolTipText(Messages.getString("StrategiesView.Refresh")); //$NON-NLS-1$
        refreshTreeView.setText(Messages.getString("StrategiesView.Refresh"));  //$NON-NLS-1$

        
        IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
        tbm.add(showStrategiesView);
        tbm.add(showDesignView);
        tbm.add(refreshTreeView);

        IMenuManager manager= getViewSite().getActionBars().getMenuManager();
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
        if (multieditor != null){
            // unhook outline viewer
            multieditor.getSelectionSynchronizer().removeViewer(viewer);
        }
        getSite().getPage().removePartListener(this);
        
        // dispose
        super.dispose();
    }
    
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partActivated(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) == this || partRef.getPart(false) instanceof UCMNavMultiPageEditor) {
            setEditor(partRef);
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
            viewer.setContents(null);
        }
        showPage(ID_DESIGN);
        currentStrategy = null;
        currentSelection = null;
        
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
        if(partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this)
            setEditor(partRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partHidden(IWorkbenchPartReference partRef) {
        //viewer.setContents(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partVisible(IWorkbenchPartReference partRef) {
        if(partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this){
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
        if (partRef.getPage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            setEditor((UCMNavMultiPageEditor) partRef.getPage().getActiveEditor());
        }
    }
    
    /**
     * @param editor
     */
    private void setEditor(UCMNavMultiPageEditor editor) {
        if (multieditor != editor){
            multieditor = editor;
            EvaluationStrategyManager.getInstance().setMultieditor(editor);
            if (multieditor==null)return;
            //getActionRegistryManager().createActions(this, multieditor, getSite().getKeyBindingService());
            
            if (multieditor.getCurrentPage() != null)
            	multieditor.getCurrentPage().getGraphicalViewer().addSelectionChangedListener(this);
            
            viewer.setEditDomain(new DefaultEditDomain(multieditor));
            viewer.setEditPartFactory(new StrategyTreeEditPartFactory(multieditor.getModel()));
    
            // register them. other ways failed to add undo/redo, only added delete.  
            IActionBars bars = getViewSite().getActionBars();
            String id = ActionFactory.UNDO.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.REDO.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));
            id = ActionFactory.DELETE.getId();
            bars.setGlobalActionHandler(id, multieditor.getActionRegistry().getAction(id));

           
            //Hook context menu

            ContextMenuProvider cmProvider = new StrategyContextMenuProvider(viewer, multieditor.getActionRegistry());
            viewer.setContextMenu(cmProvider);
            getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.StrategyContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$
 

            // hook viewer
            editor.getSelectionSynchronizer().removeViewer(viewer);
            multieditor.getSelectionSynchronizer().addViewer(viewer);
            viewer.setContents(multieditor);
                        
            Tree tree = (Tree) viewer.getControl();
            if (tree.getTopItem() != null) { //fix for crash on linux!
                Object[] items = tree.getTopItem().getItems();
                for (int i = 0; i < items.length; i++) {
                    ((TreeItem) items[i]).setExpanded(true);
                }
                tree.getTopItem().setExpanded(true);
            }
        } 
    }
    
    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        if (viewer.getContents() != null){
            viewer.getControl().setFocus();
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        StructuredSelection sel = (StructuredSelection)event.getSelection();
        
        if ((event.getSource() instanceof TreeViewer) && (multieditor != null)){
            for (Iterator j = sel.iterator(); j.hasNext();) {
                Object obj = j.next();
                if (obj instanceof EvaluationStategyTreeEditPart){
                    if (currentSelection != null){
                        currentSelection.setSelected(false);
                    }
                    currentSelection = (EvaluationStategyTreeEditPart)obj;
                    if (currentView == ID_STRATEGY){
                        currentSelection.setSelected(true);
                    }
                    EvaluationStrategy scen = ((EvaluationStategyTreeEditPart)obj).getEvaluationStrategy();                    
                    currentStrategy = scen;
                    if (currentView == ID_STRATEGY){
                        (EvaluationStrategyManager.getInstance()).setStrategy(scen);
                        
                        if (ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables() && ScenarioUtils.getActiveScenario(multieditor.getModel())!=null) {
                            // refresh scenario too. 
                            if (ScenarioUtils.getActiveScenario(multieditor.getModel()) instanceof ScenarioDef)
                                ScenarioUtils.setActiveScenario((ScenarioDef) ScenarioUtils.getActiveScenario(multieditor.getModel()));
                            else if (ScenarioUtils.getActiveScenario(multieditor.getModel()) instanceof ScenarioGroup)
                                ScenarioUtils.setActiveScenario((ScenarioGroup) ScenarioUtils.getActiveScenario(multieditor.getModel()));
                            else if (ScenarioUtils.getActiveScenario(multieditor.getModel()) instanceof UCMspec)
                                ScenarioUtils.setActiveScenario((UCMspec) ScenarioUtils.getActiveScenario(multieditor.getModel()));
                            
                        }
                        
                        for (int i=0; i< multieditor.getPageCount(); i++){
                            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(true);
                            
                            if (ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables() && ScenarioUtils.getActiveScenario(multieditor.getModel())!=null) { 
                                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);
                            }
                        }
                        
 
                    }
                } else if (obj instanceof ScenarioDefTreeEditPart && ((ScenarioDefTreeEditPart)obj).getParent() instanceof ScenarioGroupTreeEditPart ){
                    if (currentScenarioSelection != null){
                    	currentScenarioSelection.setSelected(false);
                    }
                    currentScenarioSelection = (ScenarioDefTreeEditPart)obj;
                    if (currentView == ID_STRATEGY){
                    	currentScenarioSelection.setSelected(true);
                    }
                    ScenarioDef scen = ((ScenarioDefTreeEditPart)obj).getScenarioDef();                    
                    currentScenario = scen;
                    if (currentView == ID_STRATEGY){
                    	ScenarioUtils.setActiveScenario(currentScenario);
                        for (int i=0; i< multieditor.getPageCount(); i++){
                            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);         
                        }
                    }
                }

            }
        }
    }
    
    /**
     * Change the view of the model
     * 
     * @param id
     *            parameter indicating which view to display, passed when an appropriate button is pressed in the workbench
     */
    protected void showPage(int id) {
        if (id == ID_DESIGN) {
            showDesignView.setChecked(true);
            showStrategiesView.setChecked(false);
            
            if (currentSelection != null){
                currentSelection.setSelected(false);
            }
            
            if (currentScenarioSelection != null){
            	currentScenarioSelection.setSelected(false);
            }            
            currentView = ID_DESIGN;
            if (currentStrategy != null){
                EvaluationStrategyManager.getInstance().setStrategy(null);
                for (int i=0; i< multieditor.getPageCount(); i++){
                    UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                    ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(false);         
                }
            }
            if (currentScenario != null){
            	ScenarioUtils.clearActiveScenario(multieditor.getModel());
                for (int i=0; i< multieditor.getPageCount(); i++){
                    UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                    ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(false);         
                }
            }
            
// this is weird, windows move  
//			try {
//				PlatformUI.getWorkbench().showPerspective(UCMPerspectiveFactory.JUCMNAV_PERSPECTIVE_ID,
//						PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//
//			} catch (PartInitException e) {
//			} catch (WorkbenchException e) {
//			}
        } else if (id == ID_STRATEGY) {
            showDesignView.setChecked(false);
            showStrategiesView.setChecked(true);
            
            if (currentSelection != null){
                currentSelection.setSelected(true);
            }
            
            if (currentScenarioSelection != null){
            	currentScenarioSelection.setSelected(true);
            }              
            
            currentView = ID_STRATEGY;
            if (currentStrategy != null && (currentStrategy.getGroup()==null || currentStrategy.getGroup().getGrlspec()==null)){
            	// was deleted
            	currentStrategy=null;
            }

            if (currentStrategy != null){
                EvaluationStrategyManager.getInstance().setStrategy(currentStrategy);
                for (int i=0; i< multieditor.getPageCount(); i++){
                    UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                    ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(true);         
                }         
            }
             
            if (currentScenario != null && (currentScenario.getGroup()==null || currentScenario.getGroup().getUcmspec()==null)){
            	// was deleted
            	currentScenario=null;
            }
            if (currentScenario != null){
            	ScenarioUtils.setActiveScenario(currentScenario);
                for (int i=0; i< multieditor.getPageCount(); i++){
                    UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                    ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);         
                }
            }
            
//            this is weird, windows move             
//			try {
//				PlatformUI.getWorkbench().showPerspective(UCMPerspectiveFactoryExecution.JUCMNAV_PERSPECTIVE_ID,
//						PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//
//			} catch (PartInitException e) {
//			} catch (WorkbenchException e) {
//			}            
        }
    }
    
	public Object getAdapter(Class adapter) {
		if (adapter == org.eclipse.ui.views.properties.IPropertySheetPage.class) 
		{
			//PropertySheetPage page = new PropertySheetPage();
            
            //page.setPropertySourceProvider(multieditor);
            ////page.setRootEntry(new UndoablePropertySheetEntry(multieditor.getDelegatingCommandStack()));
            //return page;
			
			if (multieditor!=null)
				return multieditor.getAdapter(org.eclipse.ui.views.properties.IPropertySheetPage.class);
			else
				return super.getAdapter(adapter);
					

		}
		else
			return super.getAdapter(adapter);
	}
}