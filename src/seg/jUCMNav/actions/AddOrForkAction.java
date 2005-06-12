/*
 * Created on Apr 27, 2005
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddForkOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddForkOnEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.DividePathOnNodeConnectionCompoundCommand;
import seg.jUCMNav.model.commands.transformations.ForkPathsCommand;
import ucm.map.OrFork;

/**
 * @author jpdaigle
 */
public class AddOrForkAction extends SelectionAction {
    public static final String ADDORFORK = "AddOrFork"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddOrForkAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif")); //$NON-NLS-1$
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
            return true;
        case SelectionHelper.EMPTYPOINT:
            return true;
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            return SafePathChecker.isSafeJoin(sel.getStartpoint(), sel.getEmptypoint());
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            return SafePathChecker.isSafeJoin(sel.getStartpoint(), sel.getNodeconnection());
        default:
            return false;
        }
    }

    private Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        OrFork newOrFork = (OrFork) ModelCreationFactory.getNewObject(sel.getUrnspec(), OrFork.class);
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            comm = new ForkPathsCommand(sel.getEmptypoint(), sel.getStartpoint(), newOrFork);
            return comm;
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            return new DividePathOnNodeConnectionCompoundCommand(sel.getStartpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
                    .getNodeconnectionMiddle().y, true);
        case SelectionHelper.EMPTYPOINT:
            comm = new AddForkOnEmptyPointCommand(newOrFork, sel.getPathgraph(), sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new AddForkOnConnectionCommand(newOrFork, sel.getPathgraph(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
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
        return ADDORFORK;
    }

}