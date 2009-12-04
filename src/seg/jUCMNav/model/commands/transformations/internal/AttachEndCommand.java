package seg.jUCMNav.model.commands.transformations.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * This command represents the action of a user dragging an EndPoint in a Stub/Join. This action will erase the EndPoint and add the path as a predecessor of
 * the Stub/Join.
 * 
 * @author Etienne Tremblay
 */
public class AttachEndCommand extends Command implements JUCMNavCommand {

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
     * end point's parent componentref.
     */
    private ComponentRef oldParent;

    /**
     * <code>ncOldEnd</code>: The connection going from the end point initialy.
     */
    private NodeConnection ncOldEnd;

    /**
     * The pathgraph containing the nodes and connections
     */
    private UCMmap pg;

    /**
     * @param oldEndPoint
     *            The end point beeing dragged to the stub/join.
     * @param stubOrJoin
     *            The stub/join where the end point will get merged.
     */
    public AttachEndCommand(EndPoint oldEndPoint, PathNode stubOrJoin) {
        super();
        this.oldEndPoint = oldEndPoint;
        this.stubOrJoin = stubOrJoin;
    }

    /**
     * Disable the default constructor.
     */
    private AttachEndCommand() {
        super();
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (oldEndPoint != null && stubOrJoin != null)
            return true;
        else
            return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldX = oldEndPoint.getX();
        oldY = oldEndPoint.getY();

        pg = (UCMmap) oldEndPoint.getDiagram();
        ncOldEnd = (NodeConnection) oldEndPoint.getPred().get(0);
        oldParent = (ComponentRef) oldEndPoint.getContRef();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        ncOldEnd.setTarget(stubOrJoin);
        pg.getNodes().remove(oldEndPoint);
        oldEndPoint.setContRef(null);

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        super.undo();

        pg.getNodes().add(oldEndPoint);

        ncOldEnd.setTarget(oldEndPoint);

        oldEndPoint.setX(oldX);
        oldEndPoint.setY(oldY);
        oldEndPoint.setContRef(oldParent);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldEndPoint != null : "pre old end point"; //$NON-NLS-1$
        assert stubOrJoin != null : "pre stub"; //$NON-NLS-1$
        assert ncOldEnd != null : "pre old node connection"; //$NON-NLS-1$
        assert pg != null : "pre pathgraph"; //$NON-NLS-1$

        assert oldEndPoint.getX() == oldX && oldEndPoint.getY() == oldY : "pre old end position"; //$NON-NLS-1$
        assert ncOldEnd.getTarget() == oldEndPoint : "pre connection source is the end point"; //$NON-NLS-1$
        assert pg.getNodes().contains(oldEndPoint) : "pre pathgraph contains the end point"; //$NON-NLS-1$
        assert pg.getConnections().contains(ncOldEnd) : "pre pathgraph contains the connection"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldEndPoint != null : "post old end point"; //$NON-NLS-1$
        assert stubOrJoin != null : "post stub"; //$NON-NLS-1$
        assert ncOldEnd != null : "post old node connection"; //$NON-NLS-1$
        assert pg != null : "post pathgraph"; //$NON-NLS-1$

        assert ncOldEnd.getTarget() == stubOrJoin : "post connection source is the stub"; //$NON-NLS-1$
        assert !pg.getNodes().contains(oldEndPoint) : "post pathgraph doesn't contain the end point"; //$NON-NLS-1$
        assert pg.getConnections().contains(ncOldEnd) : "post pathgraph contains the connection"; //$NON-NLS-1$
    }

}