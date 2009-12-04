package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

/**
 * @author pchen
 * 
 */
public class KPIViewContextMenuProvider extends ContextMenuProvider {

    private ActionRegistry actionRegistry;

    /**
     * @param viewer
     * @param registry
     *            has to be passed
     */
    public KPIViewContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
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

        // Add actions here
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
