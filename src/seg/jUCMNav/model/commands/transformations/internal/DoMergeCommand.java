package seg.jUCMNav.model.commands.transformations.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.StartPoint;

/**
 * Will merge a start point and end point into an empty point located at x,y;
 * 
 * @author jkealey
 * 
 */
public class DoMergeCommand extends Command implements JUCMNavCommand {

    private StartPoint startPoint;
    private EndPoint endPoint;
    private EmptyPoint newEmptyPoint;
    private ComponentRef parentStart, parentEnd;
    private Map map;
    private int x, y;
    private NodeConnection prevConn, nextConn;

    /**
     * @param map
     *            the map containing the elements
     * @param sp
     *            the startpoint to merge
     * @param ep
     *            the endpoint to merge
     * @param x
     *            where the new empty point should be created.
     * @param y
     *            where the new empty point should be created
     * 
     */
    public DoMergeCommand(Map map, StartPoint sp, EndPoint ep, int x, int y) {
        this.startPoint = sp;
        this.endPoint = ep;
        this.x = x;
        this.y = y;
        this.map = map;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject(map.getUcmspec().getUrnspec(), EmptyPoint.class);
        newEmptyPoint.setX(x);
        newEmptyPoint.setY(y);
        this.prevConn = (NodeConnection) endPoint.getPred().get(0);
        this.nextConn = (NodeConnection) startPoint.getSucc().get(0);

        parentStart = startPoint.getCompRef();
        parentEnd = endPoint.getCompRef();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return SafePathChecker.isSafeFusion(this.startPoint, this.endPoint);
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        prevConn.setTarget(newEmptyPoint);
        nextConn.setSource(newEmptyPoint);
        map.getPathGraph().getPathNodes().remove(startPoint);
        map.getPathGraph().getPathNodes().remove(endPoint);
        map.getPathGraph().getPathNodes().add(newEmptyPoint);

        startPoint.setCompRef(null);
        endPoint.setCompRef(null);
        newEmptyPoint.setCompRef(ParentFinder.getPossibleParent(newEmptyPoint));

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        super.undo();

        prevConn.setTarget(endPoint);
        nextConn.setSource(startPoint);
        map.getPathGraph().getPathNodes().add(startPoint);
        map.getPathGraph().getPathNodes().add(endPoint);
        map.getPathGraph().getPathNodes().remove(newEmptyPoint);

        startPoint.setCompRef(parentStart);
        endPoint.setCompRef(parentEnd);
        newEmptyPoint.setCompRef(null);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert startPoint != null && endPoint != null && newEmptyPoint != null && map != null : "pre something is null";
        assert map.getPathGraph().getPathNodes().contains(startPoint) && map.getPathGraph().getPathNodes().contains(endPoint)
                && !map.getPathGraph().getPathNodes().contains(newEmptyPoint) : "pre pathnode problem ";
        assert map.getPathGraph().getNodeConnections().contains(prevConn) && map.getPathGraph().getNodeConnections().contains(nextConn) : "pre connection problem ";

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert startPoint != null && endPoint != null && newEmptyPoint != null && map != null : "post something is null";
        assert !(map.getPathGraph().getPathNodes().contains(startPoint) && map.getPathGraph().getPathNodes().contains(endPoint) && !map.getPathGraph()
                .getPathNodes().contains(newEmptyPoint)) : "post pathnode problem ";
        assert map.getPathGraph().getNodeConnections().contains(prevConn) && map.getPathGraph().getNodeConnections().contains(nextConn) : "post connection problem ";

    }

}