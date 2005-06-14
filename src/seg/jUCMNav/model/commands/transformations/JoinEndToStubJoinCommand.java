package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * Created 27-05-2005
 * 
 * This command represents the action of a user dragging an EndPoint in a Stub/Join. This action will erase the EndPoint and add the path as
 * a predecessor of the Stub/Join.
 * 
 * @author Etienne Tremblay
 */
public class JoinEndToStubJoinCommand extends Command implements JUCMNavCommand {

    /**
     * <code>oldEndPoint</code>: The end point beeing dragged to the stub.
     */
    private EndPoint oldEndPoint;

    /**
     * <code>stubOrJoin</code>: The stub/join where the end point will get merged.
     */
    private PathNode stubOrJoin;

    /**
     * <code>oldX</code>: The old coordinates of the end point.
     */
    private int oldX, oldY;

    /**
     * <code>ncOldEnd</code>: The connection going from the end point initialy.
     */
    private NodeConnection ncOldEnd;

    private PathGraph pg;

    /**
     * @param oldEndPoint
     *            The end point beeing dragged to the stub/join.
     * @param stubOrJoin
     *            The stub/join where the end point will get merged.
     */
    public JoinEndToStubJoinCommand(EndPoint oldEndPoint, PathNode stubOrJoin) {
        super();
        this.oldEndPoint = oldEndPoint;
        this.stubOrJoin = stubOrJoin;
    }

    /**
     * Disable the default constructor.
     */
    private JoinEndToStubJoinCommand() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (oldEndPoint != null && stubOrJoin != null)
            return true;
        else
            return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldX = oldEndPoint.getX();
        oldY = oldEndPoint.getY();

        pg = oldEndPoint.getPathGraph();
        ncOldEnd = (NodeConnection) oldEndPoint.getPred().get(0);

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        ncOldEnd.setTarget(stubOrJoin);
        pg.getPathNodes().remove(oldEndPoint);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        pg.getPathNodes().add(oldEndPoint);

        ncOldEnd.setTarget(oldEndPoint);

        oldEndPoint.setX(oldX);
        oldEndPoint.setY(oldY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldEndPoint != null : "pre old end point"; //$NON-NLS-1$
        assert stubOrJoin != null : "pre stub"; //$NON-NLS-1$
        assert ncOldEnd != null : "pre old node connection"; //$NON-NLS-1$
        assert pg != null : "pre pathgraph"; //$NON-NLS-1$

        assert oldEndPoint.getX() == oldX && oldEndPoint.getY() == oldY : "pre old end position"; //$NON-NLS-1$
        assert ncOldEnd.getSource() == oldEndPoint : "pre connection source is the end point"; //$NON-NLS-1$
        assert pg.getPathNodes().contains(oldEndPoint) : "pre pathgraph contains the end point"; //$NON-NLS-1$
        assert pg.getNodeConnections().contains(ncOldEnd) : "pre pathgraph contains the connection"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldEndPoint != null : "post old end point"; //$NON-NLS-1$
        assert stubOrJoin != null : "post stub"; //$NON-NLS-1$
        assert ncOldEnd != null : "post old node connection"; //$NON-NLS-1$
        assert pg != null : "post pathgraph"; //$NON-NLS-1$

        assert ncOldEnd.getSource() == stubOrJoin : "post connection source is the stub"; //$NON-NLS-1$
        assert !pg.getPathNodes().contains(oldEndPoint) : "post pathgraph doesn't contain the end point"; //$NON-NLS-1$
        assert pg.getNodeConnections().contains(ncOldEnd) : "post pathgraph contains the connection"; //$NON-NLS-1$
    }

}