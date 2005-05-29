/*
 * Created on 8-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddForkOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddForkOnEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.ForkPathsCommand;
import ucm.map.AndFork;

/**
 * @author jpdaigle
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class AddAndForkAction extends SelectionAction {

    public static final String ADDANDFORK = "AddAndFork";

    /**
     * @param part
     */
    public AddAndForkAction(IWorkbenchPart part) {
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
        case SelectionHelper.NODECONNECTION:
        case SelectionHelper.EMPTYPOINT:
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            return true;
        default:
            return false;
        }
    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        AndFork newAndFork = (AndFork) ModelCreationFactory.getNewObject(sel.getUrnspec(), AndFork.class);
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            comm = new ForkPathsCommand(sel.getEmptypoint(), sel.getStartpoint(), newAndFork);
            return comm;
        case SelectionHelper.EMPTYPOINT:
            comm = new AddForkOnEmptyPointCommand(newAndFork, sel.getPathgraph(), sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new AddForkOnConnectionCommand(newAndFork, sel.getPathgraph(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
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
        return ADDANDFORK;
    }

}