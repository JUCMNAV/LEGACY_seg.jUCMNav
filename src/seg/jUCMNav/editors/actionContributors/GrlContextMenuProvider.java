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

import seg.jUCMNav.actions.AddGrlGraphAction;
import seg.jUCMNav.actions.AddMapAction;

/**
 * This class create the context menu used by the GRLEditor
 * @author Jean-François Roy
 *
 */
public class GrlContextMenuProvider extends ContextMenuProvider {

    private ActionRegistry actionRegistry;

    /**
     * @param viewer
     * @param registry
     *            has to be passed in case we don't want to use the action registry used in the viewer. [is this bad coding?]

     */
    public GrlContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
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

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_IN);
        menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT);
        menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

        action = getActionRegistry().getAction(AddMapAction.ADDMAP);
        if (action.isEnabled())
            menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddGrlGraphAction.ADDGRLGRAPH);
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
