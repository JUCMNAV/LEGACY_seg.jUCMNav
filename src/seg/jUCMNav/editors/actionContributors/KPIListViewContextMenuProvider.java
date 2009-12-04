package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.kpi.AddIndicatorGroupAction;
import seg.jUCMNav.actions.kpi.EditIndicatorGroupsAction;
import seg.jUCMNav.actions.metadata.EditMetadataAction;

/**
 * @author pchen
 * 
 */
public class KPIListViewContextMenuProvider extends ContextMenuProvider {

    private ActionRegistry actionRegistry;

    /**
     * @param viewer
     * @param registry
     *            has to be passed
     */
    public KPIListViewContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer);
        setActionRegistry(registry);
    }

    /*
     * (non-Javadoc)
     * 
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

        action = getActionRegistry().getAction(AddIndicatorGroupAction.ADDINDICATORGROUP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditIndicatorGroupsAction.EDITINDICATORGROUPSACTION);
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

    public void dispose() {
        setActionRegistry(null);
        super.dispose();
    }
}
