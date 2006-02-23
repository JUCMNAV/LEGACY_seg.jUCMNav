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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editors.actionContributors.StrategyContextMenuProvider;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.EvaluationStategyTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyTreeEditPartFactory;
import seg.jUCMNav.model.util.EvaluationStrategyManager;

public class StrategiesView extends ViewPart implements IPartListener2, ISelectionChangedListener{
	private TreeViewer viewer;

    static final int ID_DESIGN = 0;
    static final int ID_STRATEGY = 1;

	private UCMNavMultiPageEditor multieditor;
	private EvaluationStrategy currentStrategy;
    private EvaluationStategyTreeEditPart currentSelection;
    
    private IAction showDesignView, showStrategiesView; 
    
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
        showDesignView.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/DesignView16.gif")); //$NON-NLS-1$
        showDesignView.setToolTipText("Switch to Design View"); 
        showDesignView.setText("Switch to Design View"); 

        showStrategiesView = new Action() {
            public void run() {
                // tree view
                showPage(ID_STRATEGY);
            }
        };
        showStrategiesView.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/StrategyView16.gif")); //$NON-NLS-1$
        showStrategiesView.setToolTipText("Switch to Strategies View"); 
        showStrategiesView.setText("Switch to Strategies View"); 

        IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
        tbm.add(showStrategiesView);
        tbm.add(showDesignView);

        IMenuManager manager= getViewSite().getActionBars().getMenuManager();
        manager.add(showStrategiesView);
        manager.add(new Separator());
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
            //getActionRegistryManager().createActions(this, multieditor, getSite().getKeyBindingService());
            
            multieditor.getCurrentPage().getGraphicalViewer().addSelectionChangedListener(this);
            
            viewer.setEditDomain(new DefaultEditDomain(multieditor));
            viewer.setEditPartFactory(new StrategyTreeEditPartFactory(multieditor.getModel()));
    
           
            //Hook context menu

            ContextMenuProvider cmProvider = new StrategyContextMenuProvider(viewer, multieditor.getActionRegistry());
            viewer.setContextMenu(cmProvider);
            getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.StrategyContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$
 

            // hook viewer
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
                Object obj = (Object) j.next();
                if (obj instanceof EvaluationStategyTreeEditPart){
                    if (currentSelection != null){
                        currentSelection.setSelected(false);
                    }
                    currentSelection = (EvaluationStategyTreeEditPart)obj;
                    if (currentView == ID_STRATEGY){
                        currentSelection.setSelected(true);
                    }
                    EvaluationStrategy scen = ((EvaluationStategyTreeEditPart)obj).getEvaluationStrategy();                    
                    (EvaluationStrategyManager.getInstance()).setStrategy(scen);
                    currentStrategy = scen;
                    if (currentView == ID_STRATEGY){
                        for (int i=0; i< multieditor.getPageCount(); i++){
                            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(true);         
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
            
            currentView = ID_DESIGN;
            if (currentStrategy != null){
                EvaluationStrategyManager.getInstance().setStrategy(null);
                for (int i=0; i< multieditor.getPageCount(); i++){
                    UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                    ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(false);         
                }
            }
        } else if (id == ID_STRATEGY) {
            showDesignView.setChecked(false);
            showStrategiesView.setChecked(true);
            
            if (currentSelection != null){
                currentSelection.setSelected(true);
            }
            
            currentView = ID_STRATEGY;
            if (currentStrategy != null){
                EvaluationStrategyManager.getInstance().setStrategy(currentStrategy);
                for (int i=0; i< multieditor.getPageCount(); i++){
                    UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                    ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setStrategyView(true);         
                }         
            }
        }
    }
}