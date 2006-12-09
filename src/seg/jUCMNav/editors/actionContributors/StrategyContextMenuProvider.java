/**
 * 
 */
package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.metadata.EditMetadataAction;
import seg.jUCMNav.actions.scenarios.AddEvaluationStrategyAction;
import seg.jUCMNav.actions.scenarios.AddPrePostConditionAction;
import seg.jUCMNav.actions.scenarios.AddScenarioAction;
import seg.jUCMNav.actions.scenarios.AddScenarioGroupAction;
import seg.jUCMNav.actions.scenarios.AddStartEndPointAction;
import seg.jUCMNav.actions.scenarios.AddStrategiesGroupAction;
import seg.jUCMNav.actions.scenarios.AddVariableAction;
import seg.jUCMNav.actions.scenarios.DuplicateAction;
import seg.jUCMNav.actions.scenarios.EditCodeAction;
import seg.jUCMNav.actions.scenarios.IncludeScenarioAction;
import seg.jUCMNav.actions.scenarios.MoveAction;
import seg.jUCMNav.actions.scenarios.VariableInitializationsAction;

/**
 * This class build the context menu for the Strategy view
 * 
 * @author Jean-François Roy, jkealey
 *
 */
public class StrategyContextMenuProvider extends ContextMenuProvider {

    private ActionRegistry actionRegistry;
    
    /**
     * @param viewer
     * @param registry
     *            has to be passed
     */
    public StrategyContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer);
        setActionRegistry(registry);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void buildContextMenu(IMenuManager menu) {
        GEFActionConstants.addStandardActionGroups(menu);
        IAction action;

        action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.REDO.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(AddStrategiesGroupAction.ADDSTRATEGIESGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddEvaluationStrategyAction.ADDEVALUATIONSTRATEGY);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(AddScenarioGroupAction.ADDSCENARIOGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddScenarioAction.ADDSCENARIO);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);     
        
        action = getActionRegistry().getAction(AddVariableAction.ADDBOOLVARIABLE);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);            

        action = getActionRegistry().getAction(AddVariableAction.ADDINTVARIABLE);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);            
        
        action = getActionRegistry().getAction(AddVariableAction.ADDVARIABLEWIZARD);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);    
        
        action = getActionRegistry().getAction(AddVariableAction.ADDENUMERATIONVARIABLE);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);    
                
        action = getActionRegistry().getAction(EditCodeAction.EDITCODEACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);              

        action = getActionRegistry().getAction(DuplicateAction.DUPLICATEACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action); 
        
        action = getActionRegistry().getAction(MoveAction.MOVEUPACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action); 

        action = getActionRegistry().getAction(MoveAction.MOVEDOWNACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action); 
        
        action = getActionRegistry().getAction(VariableInitializationsAction.VARIABLEINITIALIZATIONS);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action); 
        
        action = getActionRegistry().getAction(IncludeScenarioAction.INCLUDESCENARIO);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);    
        
        action = getActionRegistry().getAction(AddStartEndPointAction.ADDSTARTPOINT);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);    
        
        action = getActionRegistry().getAction(AddStartEndPointAction.ADDENDPOINT);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);    
                
        action = getActionRegistry().getAction(AddPrePostConditionAction.ADDPRECONDITIONACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);            

        action = getActionRegistry().getAction(AddPrePostConditionAction.ADDPOSTCONDITIONACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(EditMetadataAction.EDITMETADATAACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
    }

    /**
     * 
     * @return the action registry used by the context menu provider.
     */
    private ActionRegistry getActionRegistry() {
        return actionRegistry;
    }

    /**
     * 
     * @param registry
     *            the action registry used by the context menu provider.
     */
    private void setActionRegistry(ActionRegistry registry) {
        actionRegistry = registry;
    }
}
