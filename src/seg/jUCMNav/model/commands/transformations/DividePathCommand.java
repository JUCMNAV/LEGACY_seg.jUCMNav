package seg.jUCMNav.model.commands.transformations;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import seg.jUCMNav.model.commands.create.AddBranchOnStubCommand;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Condition;

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

    private PathNode newNode;

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
        add(new SplitLinkCommand((UCMmap) startOrEnd.getDiagram(), empty, ncTarget, x, y));

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
        this.newNode = newForkJoin;
        add(new SplitLinkCommand((UCMmap) nc.getDiagram(), newForkJoin, nc, x, y));

        if (isNormalForkJoin(newForkJoin))
            add(new AddBranchCommand(newForkJoin, true));
    }

    private boolean isNormalForkJoin(PathNode newForkJoin) {
        return !(newForkJoin instanceof Stub) && !(newForkJoin instanceof Timer);
    }

    /**
     * add a new passed fork/join on a node connection, attaching an existing branch.
     * 
     * @param newForkJoin
     *            the fork/join to add.
     * @param nc
     *            the node connection on which it should be added
     * @param x
     *            its x coordinate
     * @param y
     *            its y coordinate
     * @param startOrEnd
     *            the existing branch
     */
    public DividePathCommand(PathNode newForkJoin, NodeConnection nc, int x, int y, PathNode startOrEnd) {
        this.newNode = newForkJoin;
        add(new SplitLinkCommand((UCMmap) nc.getDiagram(), newForkJoin, nc, x, y));
        if (isNormalForkJoin(newForkJoin))
            add(new AttachBranchCommand(startOrEnd, newForkJoin));
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
        this.newNode = newForkJoin;
        add(new ReplaceEmptyPointCommand(ep, newForkJoin));
        if (isNormalForkJoin(newForkJoin))
            add(new AddBranchCommand(newForkJoin, true));
    }

    /**
     * add a new passed fork/join on an emptypoint/direction arrow, attaching an existing branch
     * 
     * @param newForkJoin
     *            the fork/join to add.
     * @param ep
     *            the empty point / direction arrow to be replaced
     * @param startOrEnd
     *            the existing branch
     */
    public DividePathCommand(PathNode newForkJoin, PathNode ep, PathNode startOrEnd) {
        this.newNode = newForkJoin;
        add(new ReplaceEmptyPointCommand(ep, newForkJoin));
        if (isNormalForkJoin(newForkJoin))
            add(new AttachBranchCommand(startOrEnd, newForkJoin));
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
        newNode = null;

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
        if (isNormalForkJoin(toInsert))
            add(new AttachBranchCommand(startOrEnd, toInsert));
    }

    public PathNode getNewNode() {
        return newNode;
    }

    /**
     * Will clone the branches of the old element to the new one.
     * 
     * @param cloneFromForkJoin
     */
    public void cloneBranchesFrom(PathNode cloneFromForkJoin, UCMmap diagram) {
        if (newNode != null) {
            if (cloneFromForkJoin instanceof AndFork || cloneFromForkJoin instanceof OrFork || cloneFromForkJoin instanceof Stub
                    || cloneFromForkJoin instanceof Timer) {
                for (int i = 0; i < cloneFromForkJoin.getSucc().size(); i++) {
                    NodeConnection nc = (NodeConnection) cloneFromForkJoin.getSucc().get(i);
                    if (newNode instanceof Stub) {
                        if (i > 0)
                            add(new AddBranchOnStubCommand((Stub) newNode, false, diagram));
                    } else
                        copyNodeConnection(i, nc);
                }
            }

            if (cloneFromForkJoin instanceof AndJoin || cloneFromForkJoin instanceof OrJoin || cloneFromForkJoin instanceof Stub) {
                for (int i = 0; i < cloneFromForkJoin.getPred().size(); i++) {
                    NodeConnection nc = (NodeConnection) cloneFromForkJoin.getPred().get(i);
                    if (newNode instanceof Stub) {
                        if (i > 0)
                            add(new AddBranchOnStubCommand((Stub) newNode, true, diagram));
                    } else
                        copyNodeConnection(i, nc);
                }
            }
        }
    }

    private void copyNodeConnection(int i, NodeConnection nc) {

        Condition cond = null;
        if (nc.getCondition() != null)
            cond = (Condition) EcoreUtil.copy(nc.getCondition());

        if (newNode instanceof Timer && i > 0) {
            add(new AddBranchCommand(newNode, true, cond));
            return;
        }

        if (i == 0) {
            if (cond != null) {
                for (int j = 0; j < getCommands().size(); j++) {
                    if (getCommands().get(j) instanceof SplitLinkCommand) {
                        SplitLinkCommand command = (SplitLinkCommand) getCommands().get(j);
                        command.setOutgoingCondition(cond);
                    } else if (getCommands().get(j) instanceof ReplaceEmptyPointCommand) {
                        ReplaceEmptyPointCommand command = (ReplaceEmptyPointCommand) getCommands().get(j);
                        command.setOutgoingCondition(cond);
                    }
                    break; // causes dead code above - but haven't investigated properly if we should just look at first node, or move this break into each if branch. 
                }
            }
        } else if (i == 1) {
            if (cond != null) {
                for (int j = 0; j < getCommands().size(); j++) {
                    if (getCommands().get(j) instanceof AddBranchCommand) {
                        AddBranchCommand command = (AddBranchCommand) getCommands().get(j);
                        command.setNewCondition(cond);
                        break;
                    }
                }

            }
        } else if (i >= 2) { // not added by other inserts.
            add(new AddBranchCommand(newNode, true, cond));
        }
    }
}