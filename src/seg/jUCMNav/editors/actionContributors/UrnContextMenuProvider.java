package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.AddAndForkAction;
import seg.jUCMNav.actions.AddAndJoinAction;
import seg.jUCMNav.actions.AddBeliefAction;
import seg.jUCMNav.actions.AddBranchAction;
import seg.jUCMNav.actions.AddBranchOnStubAction;
import seg.jUCMNav.actions.AddConditionLabelAction;
import seg.jUCMNav.actions.AddDirectionArrow;
import seg.jUCMNav.actions.AddEmptyPoint;
import seg.jUCMNav.actions.AddGrlGraphAction;
import seg.jUCMNav.actions.AddLabelAction;
import seg.jUCMNav.actions.AddMapAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.AddOrJoinAction;
import seg.jUCMNav.actions.AddResponsibility;
import seg.jUCMNav.actions.AddTimeoutPathAction;
import seg.jUCMNav.actions.BindChildren;
import seg.jUCMNav.actions.BindWithParent;
import seg.jUCMNav.actions.ConnectAction;
import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.DisconnectAction;
import seg.jUCMNav.actions.DisconnectTimeoutPathAction;
import seg.jUCMNav.actions.DuplicateMapAction;
import seg.jUCMNav.actions.EditStubPluginsAction;
import seg.jUCMNav.actions.EditURNLinksAction;
import seg.jUCMNav.actions.ExportImageAction;
import seg.jUCMNav.actions.ImportAction;
import seg.jUCMNav.actions.MergeStartEndAction;
import seg.jUCMNav.actions.TransmogrifyAndForkOrJoinAction;
import seg.jUCMNav.actions.TransmogrifyOrForkOrJoinAction;
import seg.jUCMNav.actions.UnbindChildren;
import seg.jUCMNav.actions.UnbindFromParent;
import seg.jUCMNav.actions.metadata.EditMetadataAction;
import seg.jUCMNav.actions.scenarios.DeleteEvaluationAction;
import seg.jUCMNav.actions.scenarios.EditCodeAction;

/**
 * This class builds the context menu used in our editor and views.
 * 
 * @author Etienne Tremblay
 */
public class UrnContextMenuProvider extends ContextMenuProvider {

    private ActionRegistry actionRegistry;

    /**
     * Looks up a set of actions in the action registry. If they are enabled, adds them to the correct groups.
     */
    public void buildContextMenu(IMenuManager manager) {
        GEFActionConstants.addStandardActionGroups(manager);

        IAction action;

        action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.REDO.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_IN);
        manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT);
        manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

        action = getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(DeleteEvaluationAction.DELETEEVALUATION);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(CutPathAction.CUTPATH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddLabelAction.ADDLABEL);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddDirectionArrow.ADDDIRECTIONARROW);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(AddEmptyPoint.ADDEMPTYPOINT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(AddResponsibility.ADDRESPONSIBILITY);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddConditionLabelAction.ADDLABEL);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MergeStartEndAction.MERGESTARTEND);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(ConnectAction.CONNECT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(DisconnectAction.DISCONNECT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddOrForkAction.ADDORFORK);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddAndForkAction.ADDANDFORK);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddOrJoinAction.ADDORJOIN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddAndJoinAction.ADDANDJOIN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddBranchAction.ADDBRANCH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddTimeoutPathAction.ADDTIMEOUTPATH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddBranchOnStubAction.ADDBRANCH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddBranchOnStubAction.ADDBRANCH2);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(DisconnectTimeoutPathAction.DISCONNECTTIMEOUTPATH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(TransmogrifyOrForkOrJoinAction.TRANSMOGRIFYFORK);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(TransmogrifyAndForkOrJoinAction.TRANSMOGRIFYJOIN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(BindWithParent.BINDWITHPARENT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(UnbindFromParent.UNBINDFROMPARENT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(UnbindChildren.UNBINDCHILDREN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(BindChildren.BINDCHILDREN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddBeliefAction.ADDBELIEF);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddMapAction.ADDMAP);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddGrlGraphAction.ADDGRLGRAPH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(ImportAction.IMPORT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(ExportImageAction.EXPORTBITMAP);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditStubPluginsAction.EDITSTUBPLUGINS);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditURNLinksAction.EDITURNLINKS);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditCodeAction.EDITCODEACTION);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
        action = getActionRegistry().getAction(DuplicateMapAction.DUPLICATEMAP);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditMetadataAction.EDITMETADATAACTION);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        
   
    }

    /**
     * @param viewer
     * @param registry
     *            has to be passed in case we don't want to use the action registry used in the viewer. [is this bad coding?]
     */
    public UrnContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer);
        setActionRegistry(registry);
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