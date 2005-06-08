/*
 * Created on May 25, 2005
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddJoinOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddJoinOnEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.DividePathOnNodeConnectionCompoundCommand;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import ucm.map.AndJoin;

/**
 * @author jpdaigle
 */
public class AddAndJoinAction extends SelectionAction {
    public static final String ADDANDJOIN = "AddAndJoin";

    /**
     * @param part
     */
    public AddAndJoinAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndJoin16.gif"));
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
        case SelectionHelper.ENDPOINT_NODECONNECTION:
        case SelectionHelper.NODECONNECTION:
        case SelectionHelper.EMPTYPOINT:
            return true;
        default:
            return false;
        }
    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        AndJoin newAndJoin = (AndJoin) ModelCreationFactory.getNewObject(sel.getUrnspec(), AndJoin.class);
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
            comm = new JoinPathsCommand(sel.getEmptypoint(), sel.getEndpoint(), newAndJoin);
            return comm;
        case SelectionHelper.ENDPOINT_NODECONNECTION:
            return new DividePathOnNodeConnectionCompoundCommand(sel.getEndpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
                    .getNodeconnectionMiddle().y, false);
        case SelectionHelper.EMPTYPOINT:
            comm = new AddJoinOnEmptyPointCommand(newAndJoin, sel.getPathgraph(), sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new AddJoinOnConnectionCommand(newAndJoin, sel.getPathgraph(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
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
        return ADDANDJOIN;
    }

}