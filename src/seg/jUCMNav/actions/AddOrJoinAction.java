/*
 * Created on May 20, 2005
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddJoinOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddJoinOnEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import ucm.map.OrJoin;

/**
 * @author jpdaigle
 */
public class AddOrJoinAction extends SelectionAction {
    public static final String ADDORJOIN = "AddOrJoin";

    /**
     * @param part
     */
    public AddOrJoinAction(IWorkbenchPart part) {
        super(part);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return canPerformAction();
    }

    private boolean canPerformAction() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
        case SelectionHelper.NODECONNECTION:
        case SelectionHelper.EMPTYPOINT:
            return true;
        default:
            return false;
        }
    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        OrJoin newOrJoin = (OrJoin) ModelCreationFactory.getNewObject(sel.getUrnspec(), OrJoin.class);
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
            comm = new JoinPathsCommand(sel.getEmptypoint(), sel.getEndpoint(), newOrJoin);
            return comm;
        case SelectionHelper.EMPTYPOINT:
            comm = new AddJoinOnEmptyPointCommand(newOrJoin, sel.getPathgraph(), sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new AddJoinOnConnectionCommand(newOrJoin, sel.getPathgraph(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
                    .getNodeconnectionMiddle().y);
            return comm;

        default:
            return null;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        execute(getCommand());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return ADDORJOIN;
    }

}