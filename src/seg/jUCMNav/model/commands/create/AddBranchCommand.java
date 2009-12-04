package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.Condition;

/**
 * This command adds a branch on a fork/join. Currently, the new branch is always positioned in the same location. This behaviour could be improved to place
 * items in the direction of the path. When adding multiple branches, the distance from the PathNode increases so as to avoid cluttering the diagram.
 * 
 * Also supports timers that don't already have timeout paths.
 * 
 * @author jkealey
 * 
 */
public class AddBranchCommand extends Command implements JUCMNavCommand {

    // where to insert
    private PathNode insertionNode;
    private UCMmap pg;
    private URNspec urn;

    // new elements
    private EmptyPoint newEmpty;
    private NodeConnection newConn, newConn2;
    private PathNode newStartOrEnd;
    private Condition newCondition;

    // if true, relax pre/post condition checking.
    private boolean inCompoundCommand = false;

    /**
     * @param insertionNode
     *            the fork/join/timer on which to add a branch
     * @param inCompoundCommand
     *            if true, relax pre/post condition checking.
     * @param newCondition
     *            if not null, it will be the condition set to the new node connection.
     */
    public AddBranchCommand(PathNode insertionNode, boolean inCompoundCommand, Condition newCondition) {
        this.insertionNode = insertionNode;
        this.inCompoundCommand = inCompoundCommand;
        this.newCondition = newCondition;
        setLabel(Messages.getString("AddBranchCommand.addBranch")); //$NON-NLS-1$
    }

    public Condition getNewCondition() {
        return newCondition;
    }

    public void setNewCondition(Condition newCondition) {
        this.newCondition = newCondition;
    }

    /**
     * @param insertionNode
     *            the fork/join/timer on which to add a branch
     * @param inCompoundCommand
     *            if true, relax pre/post condition checking.
     */
    public AddBranchCommand(PathNode insertionNode, boolean inCompoundCommand) {
        this.insertionNode = insertionNode;
        this.inCompoundCommand = inCompoundCommand;
        this.newCondition = null;
        setLabel(Messages.getString("AddBranchCommand.addBranch")); //$NON-NLS-1$
    }

    /**
     * @param insertionNode
     *            the fork/join/stub on which to add a branch
     */
    public AddBranchCommand(PathNode insertionNode) {
        this.insertionNode = insertionNode;
        this.newCondition = null;
        setLabel(Messages.getString("AddBranchCommand.addBranch")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (this.insertionNode instanceof OrFork || this.insertionNode instanceof OrJoin || this.insertionNode instanceof AndFork
                || this.insertionNode instanceof AndJoin || (this.insertionNode instanceof Timer && ((inCompoundCommand && this.insertionNode.getSucc().size() == 0) || this.insertionNode
                .getSucc().size() == 1)))
                && (this.insertionNode.getDiagram() != null || inCompoundCommand);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // generate new instances.
        pg = (UCMmap) this.insertionNode.getDiagram();
        urn = pg.getUrndefinition().getUrnspec();
        newEmpty = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer) {
            newStartOrEnd = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);
            newEmpty.setX(insertionNode.getX() + 50);
            newStartOrEnd.setX(insertionNode.getX() + 100);
        } else {
            newStartOrEnd = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);
            newEmpty.setX(insertionNode.getX() - 50);
            newStartOrEnd.setX(insertionNode.getX() - 100);
        }

        // increase distance from insertionNode as number of branches increases.
        int i = insertionNode.getSucc().size() + insertionNode.getPred().size() - 1;
        newEmpty.setY(insertionNode.getY() - i * 30 * (Math.abs((i % 2) * 2 - 1) / ((i % 2) * 2 - 1)));
        newStartOrEnd.setY(insertionNode.getY() - i * 30 * (Math.abs((i % 2) * 2 - 1) / ((i % 2) * 2 - 1)));

        newConn = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);
        newConn2 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer) {
            newConn.setSource(insertionNode);
            newConn.setTarget(newEmpty);
            newConn2.setSource(newEmpty);
            newConn2.setTarget(newStartOrEnd);
        } else {
            newConn.setTarget(insertionNode);
            newConn.setSource(newEmpty);
            newConn2.setTarget(newEmpty);
            newConn2.setSource(newStartOrEnd);
        }

        // branches following OrForks always have conditions.

        if (newCondition == null && (insertionNode instanceof OrFork || insertionNode instanceof WaitingPlace || insertionNode instanceof Timer)) {
            newCondition = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            // blocking path.
            if (insertionNode instanceof Timer)
                newCondition.setExpression("false"); //$NON-NLS-1$
        }

        if (newCondition != null)
            newConn.setCondition(newCondition);

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        pg.getNodes().add(newEmpty);
        pg.getNodes().add(newStartOrEnd);
        pg.getConnections().add(newConn);
        pg.getConnections().add(newConn2);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer)
            insertionNode.getSucc().add(newConn);
        else
            insertionNode.getPred().add(newConn);

        newEmpty.setContRef(ParentFinder.getPossibleParent(newEmpty));
        newStartOrEnd.setContRef(ParentFinder.getPossibleParent(newStartOrEnd));

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer)
            insertionNode.getSucc().remove(newConn);
        else
            insertionNode.getPred().remove(newConn);

        pg.getNodes().remove(newEmpty);
        pg.getNodes().remove(newStartOrEnd);
        pg.getConnections().remove(newConn);
        pg.getConnections().remove(newConn2);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer)
            insertionNode.getSucc().remove(newConn);
        else
            insertionNode.getPred().remove(newConn);

        newEmpty.setContRef(null);
        newStartOrEnd.setContRef(null);

        testPreConditions();

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert insertionNode != null && newEmpty != null && newConn != null && newConn2 != null && newStartOrEnd != null : "pre something null"; //$NON-NLS-1$
        assert inCompoundCommand || (pg != null && urn != null);

        assert inCompoundCommand || pg.getNodes().contains(insertionNode) : "pre node in model"; //$NON-NLS-1$
        assert !pg.getNodes().contains(newEmpty) && !pg.getNodes().contains(newStartOrEnd) : "pre nodes not in model"; //$NON-NLS-1$
        assert !pg.getConnections().contains(newConn) && !pg.getConnections().contains(newConn) : "pre connections not in model"; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert insertionNode != null && newEmpty != null && newConn != null && newConn2 != null && newStartOrEnd != null && pg != null && urn != null : "post something null"; //$NON-NLS-1$
        assert pg.getNodes().contains(insertionNode) : "post node in model"; //$NON-NLS-1$
        assert pg.getNodes().contains(newEmpty) && pg.getNodes().contains(newStartOrEnd) : "post nodes in model"; //$NON-NLS-1$
        assert pg.getConnections().contains(newConn) && pg.getConnections().contains(newConn) : "post connections in model"; //$NON-NLS-1$

    }

}