package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * This command can:
 * 
 * 1) add a new passed fork/join on a node connection, creating a new branch
 * 
 * 2) add a new passed fork/join on an emptypoint/direction arrow, creating a new branch
 * 
 * 3) add a new internally created fork/join on a node connection, attaching a passed branch
 * 
 * 4) add a new internally created fork/join on an emptypoint/direction arrow, attaching a passed branch
 * 
 * 
 * @author jkealey
 * 
 */
public class DividePathCommand extends CompoundCommand {

    /**
     * add a new internally created fork/join on a node connection, attaching a passed branch
     * 
     * @param startOrEnd
     *            the StartPoint or EndPoint to merge with ncTarget
     * @param ncTarget
     *            on which NodeConnection the fork/join should be added.
     * @param x
     *            where the fork/join should be added
     * @param y
     *            where the fork/join should be added
     * @param isOrDivision
     *            true for or fork/join, false for and fork/join.
     * 
     */
    public DividePathCommand(PathNode startOrEnd, NodeConnection ncTarget, int x, int y, boolean isOrDivision) {
        URNspec urn = startOrEnd.getDiagram().getUrndefinition().getUrnspec();
        EmptyPoint empty = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
        add(new SplitLinkCommand((UCMmap)startOrEnd.getDiagram(), empty, ncTarget, x, y));

        createAndInsert(startOrEnd, empty, isOrDivision);
        setLabel(Messages.getString("DividePathCommand.dividePath")); //$NON-NLS-1$
    }

    /**
     * add a new internally created fork/join on an emptypoint/direction arrow, attaching a passed branch
     * 
     * @param startOrEnd
     *            the StartPoint or EndPoint to merge with ncTarget
     * @param empty
     *            which emptyPoint/direction arrow should be replaced with a new fork/join
     * @param isOrDivision
     *            true for or fork/join, false for and fork/join.
     * 
     */
    public DividePathCommand(PathNode startOrEnd, PathNode empty, boolean isOrDivision) {
        createAndInsert(startOrEnd, empty, isOrDivision);
    }

    /**
     * add a new passed fork/join on a node connection, creating a new branch
     * 
     * @param newForkJoin
     *            the fork/join to add.
     * @param nc
     *            the node connection on which it should be added
     * @param x
     *            its x coordinate
     * @param y
     *            its y coordinate
     */
    public DividePathCommand(PathNode newForkJoin, NodeConnection nc, int x, int y) {
        add(new SplitLinkCommand((UCMmap)nc.getDiagram(), newForkJoin, nc, x, y));
        add(new AddBranchCommand(newForkJoin, true));
    }

    /**
     * add a new passed fork/join on an emptypoint/direction arrow, creating a new branch
     * 
     * @param newForkJoin
     *            the fork/join to add.
     * @param ep
     *            the empty point / direction arrow to be replaced
     */
    public DividePathCommand(PathNode newForkJoin, PathNode ep) {
        add(new ReplaceEmptyPointCommand(ep, newForkJoin));
        add(new AddBranchCommand(newForkJoin, true));
    }

    /**
     * Create the new fork/join and call replaceAndAddBranch()
     * 
     * @param startOrEnd
     *            the StartPoint or EndPoint to merge with ncTarget
     * @param empty
     *            which emptyPoint/direction arrow should be replaced with a new fork/join
     * @param isOrDivision
     *            true for or fork/join, false for and fork/join.
     * 
     */
    private void createAndInsert(PathNode startOrEnd, PathNode empty, boolean isOrDivision) {
        URNspec urn = startOrEnd.getDiagram().getUrndefinition().getUrnspec();
        PathNode newNode=null;

        if (startOrEnd instanceof StartPoint) {

            if (isOrDivision)
                newNode = (PathNode) ModelCreationFactory.getNewObject(urn, OrFork.class);
            else
                newNode = (PathNode) ModelCreationFactory.getNewObject(urn, AndFork.class);

        } else if (startOrEnd instanceof EndPoint) {
            if (isOrDivision)
                newNode = (PathNode) ModelCreationFactory.getNewObject(urn, OrJoin.class);
            else
                newNode = (PathNode) ModelCreationFactory.getNewObject(urn, AndJoin.class);
        }

        replaceAndAddBranch(startOrEnd, empty, newNode);
    }

    /**
     * Replaces empty with toInsert and attach startOrEnd to it.
     * 
     * @param startOrEnd
     *            the StartPoint or EndPoint to merge with ncTarget
     * @param empty
     *            which emptyPoint/direction arrow should be replaced with a new fork/join
     * @param toInsert
     *            fork/join to be inserted
     * 
     */
    private void replaceAndAddBranch(PathNode startOrEnd, PathNode empty, PathNode toInsert) {
        add(new ReplaceEmptyPointCommand(empty, toInsert));
        add(new AttachBranchCommand(startOrEnd, toInsert));
    }

}