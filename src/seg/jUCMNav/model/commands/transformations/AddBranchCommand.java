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

    /**
     *  
     */
    public AddBranchCommand(PathNode insertionNode) {
        this.insertionNode = insertionNode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (this.insertionNode instanceof OrFork || this.insertionNode instanceof OrJoin || this.insertionNode instanceof AndFork || this.insertionNode instanceof AndJoin)
                && this.insertionNode.getPathGraph() != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        pg = this.insertionNode.getPathGraph();
        urn = pg.getMap().getUcmspec().getUrnspec();
        newEmpty = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork) {
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

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork) {
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
        
        if (insertionNode instanceof OrFork) {
            newCondition = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            newConn.setCondition(newCondition);
        }

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        pg.getPathNodes().add(newEmpty);
        pg.getPathNodes().add(newStartOrEnd);
        pg.getNodeConnections().add(newConn);
        pg.getNodeConnections().add(newConn2);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork)
            insertionNode.getSucc().add(newConn);
        else
            insertionNode.getPred().add(newConn);

        newEmpty.setCompRef(ParentFinder.getPossibleParent(newEmpty));
        newStartOrEnd.setCompRef(ParentFinder.getPossibleParent(newStartOrEnd));

        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork)
            insertionNode.getSucc().remove(newConn);
        else
            insertionNode.getPred().remove(newConn);

        pg.getPathNodes().remove(newEmpty);
        pg.getPathNodes().remove(newStartOrEnd);
        pg.getNodeConnections().remove(newConn);
        pg.getNodeConnections().remove(newConn2);

        if (insertionNode instanceof OrFork || insertionNode instanceof AndFork)
            insertionNode.getSucc().remove(newConn);
        else
            insertionNode.getPred().remove(newConn);

        newEmpty.setCompRef(null);
        newStartOrEnd.setCompRef(null);

        testPreConditions();

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert insertionNode != null && newEmpty != null && newConn != null && newConn2 != null && newStartOrEnd != null && pg != null && urn != null : "pre something null";
        assert pg.getPathNodes().contains(insertionNode) : "pre node in model";
        assert !pg.getPathNodes().contains(newEmpty) && !pg.getPathNodes().contains(newStartOrEnd) : "pre nodes not in model";
        assert !pg.getNodeConnections().contains(newConn) && !pg.getNodeConnections().contains(newConn) : "pre connections not in model";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert insertionNode != null && newEmpty != null && newConn != null && newConn2 != null && newStartOrEnd != null && pg != null && urn != null : "post something null";
        assert pg.getPathNodes().contains(insertionNode) : "post node in model";
        assert pg.getPathNodes().contains(newEmpty) && pg.getPathNodes().contains(newStartOrEnd) : "post nodes in model";
        assert pg.getNodeConnections().contains(newConn) && pg.getNodeConnections().contains(newConn) : "post connections in model";

    }

}