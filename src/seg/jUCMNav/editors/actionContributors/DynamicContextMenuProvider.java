package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.dynamicContexts.AddDynamicContextAction;
import seg.jUCMNav.actions.dynamicContexts.AddDynamicContextContributionContextAction;
import seg.jUCMNav.actions.dynamicContexts.AddDynamicContextScenarioAction;
import seg.jUCMNav.actions.dynamicContexts.AddDynamicContextStrategyAction;
import seg.jUCMNav.actions.dynamicContexts.AddDynamicContextsGroupAction;
import seg.jUCMNav.actions.dynamicContexts.AddTimepointAction;
import seg.jUCMNav.actions.dynamicContexts.AddTimepointsGroupAction;
import seg.jUCMNav.actions.dynamicContexts.IncludeDynamicContextAction;
import seg.jUCMNav.actions.dynamicContexts.ManageChangeAction;
import seg.jUCMNav.actions.dynamicContexts.MoveDynamicContextAction;

/**
 * This class builds the context menu for the Dynamic Context view
 * 
 * @author aprajita
 * 
 */
public class DynamicContextMenuProvider extends ContextMenuProvider {
	
	private ActionRegistry actionRegistry;

    /**
     * @param viewer
     * @param registry
     *            has to be passed
     */
    public DynamicContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
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

        action = getActionRegistry().getAction(AddDynamicContextsGroupAction.ADDDYNAMICCONTEXTSGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddDynamicContextAction.ADDDYNAMICCONTEXT);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(IncludeDynamicContextAction.INCLUDEDYNAMICCONTEXT);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(AddDynamicContextStrategyAction.ADDCONTEXTSTRATEGY);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddDynamicContextScenarioAction.ADDCONTEXTSCENARIO);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(AddDynamicContextContributionContextAction.ADDCONTEXTCONTRIBUTIONCONTEXT);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MoveDynamicContextAction.MOVEUPACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MoveDynamicContextAction.MOVEDOWNACTION);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(AddTimepointsGroupAction.ADDTIMEPOINTSGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddTimepointAction.ADDTIMEPOINT);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(ManageChangeAction.MANAGECHANGEACTION);
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
