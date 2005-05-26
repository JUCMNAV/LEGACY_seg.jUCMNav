/*
 * Created on May 25, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.PathGraph;
import urn.URNspec;

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
        /*
         * Conditions for enabling: selection contains exactly 1 endpoint and 1 emptypoint.
         */
        List parts = getSelectedObjects();
        boolean bEnd, bEmpty;
        if (parts.size() == 2 && parts.get(0) instanceof EditPart && parts.get(1) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);
            EditPart part2 = (EditPart) parts.get(1);
            bEnd = (part.getModel() instanceof EndPoint) || (part2.getModel() instanceof EndPoint);
            bEmpty = (part.getModel() instanceof EmptyPoint) || (part2.getModel() instanceof EmptyPoint);
            return (bEnd && bEmpty);
        }

        return false;
    }

    private Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part1 = (EditPart) parts.get(0);
        EditPart part2 = (EditPart) parts.get(1);
        EmptyPoint emPoint;
        EndPoint endPoint;

        // part1 and part2 are an EmptyPoint and an EndPoint, need to figure out which is which
        if (part1.getModel() instanceof EmptyPoint) {
            emPoint = (EmptyPoint) part1.getModel();
            endPoint = (EndPoint) part2.getModel();
        } else {
            emPoint = (EmptyPoint) part2.getModel();
            endPoint = (EndPoint) part1.getModel();
        }

        PathGraph pg = (PathGraph) emPoint.eContainer();
        AndJoin newAndJoin = (AndJoin) ModelCreationFactory.getNewObject((URNspec) pg.eContainer().eContainer()
                .eContainer(), AndJoin.class);
        JoinPathsCommand comm = new JoinPathsCommand(emPoint, endPoint, newAndJoin);
        return comm;
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