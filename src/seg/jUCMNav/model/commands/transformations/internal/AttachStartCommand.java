package seg.jUCMNav.model.commands.transformations.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urncore.Condition;

/**
 * This command represents the action of a user dragging a StartPoint in a Stub/Fork. This action will erase the StartPoint and add the path as a successor of
 * the stub/fork.
 * 
 * @author Etienne Tremblay
 */
public class AttachStartCommand extends Command implements JUCMNavCommand {

    /**
     * <code>oldStartPoint</code>: The start point beeing dragged to the stub.
     */
    private StartPoint oldStartPoint;

    /**
     * <code>stubOrFork</code>: The stub where the start point will get merged.
     */
    private PathNode stubOrFork;

    /**
     * <code>oldX, oldY</code>: The old coordinates of the start point.
     */
    private int oldX, oldY;

    /**
     * <code>ncOldStart</code>: The connection going from the start point initialy.
     */
    private NodeConnection ncOldStart;

    /**
     * If connecting to OrFork, add the condition.
     */
    private Condition newCondition;

    /**
     * end point's parent componentref.
     */
    private ComponentRef oldParent;

    /**
     * The pathgraph containing the nodes and connections
     */
    private UCMmap pg;

    /**
     * @param oldStartPoint
     *            The start point beeing dragged to the stub/fork.
     * @param stubOrFork
     *            The stub/fork where the start point will get merged.
     */
    public AttachStartCommand(StartPoint oldStartPoint, PathNode stubOrFork) {
        super();
        this.oldStartPoint = oldStartPoint;
        this.stubOrFork = stubOrFork;
    }

    /**
     * Disable the default constructor.
     */
    private AttachStartCommand() {
        super();
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (stubOrFork != null && oldStartPoint != null)
            return true;
        else
            return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // Remember the last position of the startpoint.
        oldX = oldStartPoint.getX();
        oldY = oldStartPoint.getY();

        pg = (UCMmap) oldStartPoint.getDiagram();

        ncOldStart = (NodeConnection) oldStartPoint.getSucc().get(0);

        if (stubOrFork instanceof OrFork) {
            newCondition = (Condition) ModelCreationFactory.getNewObject(pg.getUrndefinition().getUrnspec(), Condition.class);
        }

        oldParent = (ComponentRef) oldStartPoint.getContRef();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        ncOldStart.setSource(stubOrFork);
        pg.getNodes().remove(oldStartPoint);

        if (newCondition != null)
            ncOldStart.setCondition(newCondition);

        oldStartPoint.setContRef(null);

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        super.undo();

        pg.getNodes().add(oldStartPoint);
        ncOldStart.setSource(oldStartPoint);

        oldStartPoint.setX(oldX);
        oldStartPoint.setY(oldY);

        if (newCondition != null)
            ncOldStart.setCondition(null);

        oldStartPoint.setContRef(oldParent);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldStartPoint != null : "pre old start point"; //$NON-NLS-1$
        assert stubOrFork != null : "pre stub"; //$NON-NLS-1$
        assert ncOldStart != null : "pre old node connection"; //$NON-NLS-1$
        assert pg != null : "pre pathgraph"; //$NON-NLS-1$

        assert oldStartPoint.getX() == oldX && oldStartPoint.getY() == oldY : "pre old start position"; //$NON-NLS-1$
        assert ncOldStart.getSource() == oldStartPoint : "pre connection source is the start point"; //$NON-NLS-1$
        assert pg.getNodes().contains(oldStartPoint) : "pre pathgraph contains the start point"; //$NON-NLS-1$
        assert pg.getConnections().contains(ncOldStart) : "pre pathgraph contains the connection"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldStartPoint != null : "post old start point"; //$NON-NLS-1$
        assert stubOrFork != null : "post stub"; //$NON-NLS-1$
        assert ncOldStart != null : "post old node connection"; //$NON-NLS-1$
        assert pg != null : "post pathgraph"; //$NON-NLS-1$

        assert ncOldStart.getSource() == stubOrFork : "post connection source is the stub"; //$NON-NLS-1$
        assert !pg.getNodes().contains(oldStartPoint) : "post pathgraph doesn't contain the start point"; //$NON-NLS-1$
        assert pg.getConnections().contains(ncOldStart) : "post pathgraph contains the connection"; //$NON-NLS-1$
    }

}