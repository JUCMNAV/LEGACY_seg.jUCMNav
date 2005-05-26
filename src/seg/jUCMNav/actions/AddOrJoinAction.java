/*
 * Created on May 20, 2005
 */
package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddJoinOnEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import urn.URNspec;

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
        } else if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            // we can add a join with an AddJoinOnEmptyPointCommand
            EditPart part = (EditPart) parts.get(0);
            return (part.getModel() instanceof EmptyPoint);
        }

        return false;
    }

    private Command getCommand() {
        List parts = getSelectedObjects();
        if (parts.size() == 2) {
            // joining an endpoint and emptypoint
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
            OrJoin newOrJoin = (OrJoin) ModelCreationFactory.getNewObject((URNspec) pg.eContainer().eContainer()
                    .eContainer(), OrJoin.class);
            JoinPathsCommand comm = new JoinPathsCommand(emPoint, endPoint, newOrJoin);
            return comm;
        } else if (parts.size() == 1) {
            EditPart part1 = (EditPart) parts.get(0);
            EmptyPoint emPoint;
            emPoint = (EmptyPoint) part1.getModel();

            PathGraph pg = (PathGraph) emPoint.eContainer();
            OrJoin newOrJoin = (OrJoin) ModelCreationFactory.getNewObject((URNspec) pg.eContainer().eContainer()
                    .eContainer(), OrJoin.class);
            AddJoinOnEmptyPointCommand comm = new AddJoinOnEmptyPointCommand(newOrJoin, pg, emPoint);
            return comm;
            
        } else
            return null;

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