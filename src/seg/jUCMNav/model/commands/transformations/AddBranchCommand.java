package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

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
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Timer;
import urn.URNspec;
import urncore.Condition;

/**
 * Created on 30-May-2005
 * 
 * @author jkealey
 *  
 */
public class AddBranchCommand extends Command implements JUCMNavCommand {

    private PathNode insertionNode;

    private EmptyPoint newEmpty;

    private NodeConnection newConn, newConn2;

    private PathNode newStartOrEnd;

    private Condition newCondition;

    private PathGraph pg;

    private URNspec urn;

    private boolean inCompoundCommand = false;

    /**
     * @param insertionNode
     *            the fork/join/stub on which to add a branch
     * @param inCompoundCommand
     *            if true, relax pre/post condition checking.
     */
    public AddBranchCommand(PathNode insertionNode, boolean inCompoundCommand) {
        this.insertionNode = insertionNode;
        this.inCompoundCommand = inCompoundCommand;
    }

    /**
     * @param insertionNode
     *            the fork/join/stub on which to add a branch
     */
    public AddBranchCommand(PathNode insertionNode) {
        this.insertionNode = insertionNode;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (this.insertionNode instanceof OrFork || this.insertionNode instanceof OrJoin || this.insertionNode instanceof AndFork
                || this.insertionNode instanceof AndJoin || (this.insertionNode instanceof Timer && this.insertionNode.getSucc().size() == 1))
                && (this.insertionNode.getPathGraph() != null || inCompoundCommand);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // generate new isntances. 
        pg = this.insertionNode.getPathGraph();
        urn = pg.getMap().getUcmspec().getUrnspec();
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

        if (insertionNode instanceof OrFork || insertionNode instanceof Timer) {
            newCondition = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            newConn.setCondition(newCondition);
        }

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        pg.getPathNodes().add(newEmpty);
        pg.getPathNodes().add(newStartOrEnd);
        pg.getNodeConnections().add(newConn);
        pg.getNodeConnections().add(newConn2);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer)
            insertionNode.getSucc().add(newConn);
        else
            insertionNode.getPred().add(newConn);

        newEmpty.setCompRef(ParentFinder.getPossibleParent(newEmpty));
        newStartOrEnd.setCompRef(ParentFinder.getPossibleParent(newStartOrEnd));

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

        pg.getPathNodes().remove(newEmpty);
        pg.getPathNodes().remove(newStartOrEnd);
        pg.getNodeConnections().remove(newConn);
        pg.getNodeConnections().remove(newConn2);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork || insertionNode instanceof Timer)
            insertionNode.getSucc().remove(newConn);
        else
            insertionNode.getPred().remove(newConn);

        newEmpty.setCompRef(null);
        newStartOrEnd.setCompRef(null);

        testPreConditions();

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert insertionNode != null && newEmpty != null && newConn != null && newConn2 != null && newStartOrEnd != null  : "pre something null"; //$NON-NLS-1$
        assert inCompoundCommand || (pg != null && urn != null);
        
        assert inCompoundCommand || pg.getPathNodes().contains(insertionNode) : "pre node in model"; //$NON-NLS-1$
        assert !pg.getPathNodes().contains(newEmpty) && !pg.getPathNodes().contains(newStartOrEnd) : "pre nodes not in model"; //$NON-NLS-1$
        assert !pg.getNodeConnections().contains(newConn) && !pg.getNodeConnections().contains(newConn) : "pre connections not in model"; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert insertionNode != null && newEmpty != null && newConn != null && newConn2 != null && newStartOrEnd != null && pg != null && urn != null : "post something null"; //$NON-NLS-1$
        assert pg.getPathNodes().contains(insertionNode) : "post node in model"; //$NON-NLS-1$
        assert pg.getPathNodes().contains(newEmpty) && pg.getPathNodes().contains(newStartOrEnd) : "post nodes in model"; //$NON-NLS-1$
        assert pg.getNodeConnections().contains(newConn) && pg.getNodeConnections().contains(newConn) : "post connections in model"; //$NON-NLS-1$

    }

}