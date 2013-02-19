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

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.EditURNLinksAction;
import seg.jUCMNav.actions.TagElementAction;
import seg.jUCMNav.actions.kpi.RunAllGRLEvaluationAction;
import seg.jUCMNav.actions.metadata.EditMetadataAction;
import seg.jUCMNav.actions.performance.ManageDemandAction;
import seg.jUCMNav.actions.performance.ManageResourcesAction;
import seg.jUCMNav.actions.scenarios.AddContributionContextAction;
import seg.jUCMNav.actions.scenarios.AddContributionContextGroupAction;
import seg.jUCMNav.actions.scenarios.AddEvaluationStrategyAction;
import seg.jUCMNav.actions.scenarios.AddKPIConversionAction;
import seg.jUCMNav.actions.scenarios.AddPrePostConditionAction;
import seg.jUCMNav.actions.scenarios.AddQualitativeMappingAction;
import seg.jUCMNav.actions.scenarios.AddScenarioAction;
import seg.jUCMNav.actions.scenarios.AddScenarioGroupAction;
import seg.jUCMNav.actions.scenarios.AddStartEndPointAction;
import seg.jUCMNav.actions.scenarios.AddStrategiesGroupAction;
import seg.jUCMNav.actions.scenarios.AddVariableAction;
import seg.jUCMNav.actions.scenarios.DuplicateAction;
import seg.jUCMNav.actions.scenarios.EditCodeAction;
import seg.jUCMNav.actions.scenarios.EditEvaluationRangeAction;
import seg.jUCMNav.actions.scenarios.IncludeContributionContextAction;
import seg.jUCMNav.actions.scenarios.IncludeScenarioAction;
import seg.jUCMNav.actions.scenarios.IncludeStrategyAction;
import seg.jUCMNav.actions.scenarios.MoveContributionContextAction;
import seg.jUCMNav.actions.scenarios.MoveScenarioAction;
import seg.jUCMNav.actions.scenarios.MoveStrategyAction;
import seg.jUCMNav.actions.scenarios.RunAllScenariosAction;
import seg.jUCMNav.actions.scenarios.SetComparisonStrategyAction;
import seg.jUCMNav.actions.scenarios.StartStrategyDifferenceModeAction;
import seg.jUCMNav.actions.scenarios.StopStrategyDifferenceModeAction;
import seg.jUCMNav.actions.scenarios.VariableInitializationsAction;

/**
 * This class builds the context menu for the Strategy/scenario view
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

    /**
     * Looks up a set of actions in the action registry. If they are enabled, adds them to the correct groups.
     */
    public void buildContextMenu(IMenuManager menu) {
        GEFActionConstants.addStandardActionGroups(menu);
        IAction action;

        action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.REDO.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.CUT.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.COPY.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.PASTE.getId());
        menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(AddStrategiesGroupAction.ADDSTRATEGIESGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddContributionContextGroupAction.ADDCONTRIBUTIONCONTEXTGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddContributionContextAction.ADDCONTRIBUTIONCONTEXT);
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

        action = getActionRegistry().getAction(AddKPIConversionAction.ADDKPICONVERSION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddQualitativeMappingAction.ADDQUALITATIVEMAPPING);
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

        action = getActionRegistry().getAction(MoveScenarioAction.MOVEUPACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MoveScenarioAction.MOVEDOWNACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(MoveStrategyAction.MOVEUPACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MoveStrategyAction.MOVEDOWNACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(MoveContributionContextAction.MOVEUPACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MoveContributionContextAction.MOVEDOWNACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(VariableInitializationsAction.VARIABLEINITIALIZATIONS);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(IncludeScenarioAction.INCLUDESCENARIO);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(IncludeStrategyAction.INCLUDESTRATEGY);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(IncludeContributionContextAction.INCLUDECONTRIBUTIONCONTEXT);
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

        action = getActionRegistry().getAction(RunAllScenariosAction.RUNALLSCENARIOS);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(RunAllGRLEvaluationAction.RUNALLGRLEVALUATIONS);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        // _js_
        action = getActionRegistry().getAction(ManageDemandAction.MANAGEDEMANDACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        // _js_
        action = getActionRegistry().getAction(ManageResourcesAction.MANAGERESOURCESACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditMetadataAction.EDITMETADATAACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(EditEvaluationRangeAction.EDITEVALUATIONRANGEACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);        
        
        action = getActionRegistry().getAction(EditURNLinksAction.EDITURNLINKS);
        if (action.isEnabled()) {
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
            action.setToolTipText( Messages.getString("StrategyContextMenuProvider.OpenMenuToEditURNLinks") ); //$NON-NLS-1$
        }
        
        action = getActionRegistry().getAction(TagElementAction.TAG_ELEMENT_ACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(SetComparisonStrategyAction.SET_COMPARISON_STRATEGY);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(StartStrategyDifferenceModeAction.START_STRATEGY_DIFF_MODE);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(StopStrategyDifferenceModeAction.STOP_STRATEGY_DIFF_MODE);
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

    public void dispose() {
        setViewer(null);
        setActionRegistry(null);
        super.dispose();
    }
}
