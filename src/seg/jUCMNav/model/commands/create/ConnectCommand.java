package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.IURNContainerRef;

/**
 * Creates a synchronous connection (EndPoint with StartPoint/WaitingPlace/Timer) or asynchronous connection (EmptyPoint with StartPoint/WaitingPlace/Timer)
 * 
 * @author jkealey
 * 
 */
public class ConnectCommand extends Command implements JUCMNavCommand {

    private PathNode left, right;
    private NodeConnection ncLeft, ncRight;
    private Connect connect;
    private URNspec urn;

    private int oldLeftX, oldLeftY;
    private ComponentRef oldLeftParent;

    /**
     * Joins first with second by adding a Connect element in between.
     * 
     * @param first
     * @param second
     */
    public ConnectCommand(PathNode first, PathNode second) {
        if (first instanceof EmptyPoint || first instanceof EndPoint) {
            this.left = first;
            this.right = second;
        } else {
            this.left = second;
            this.right = first;
        }

        if (left != null) {
            urn = left.getDiagram().getUrndefinition().getUrnspec();
        }

        setLabel(Messages.getString("ConnectCommand.connect")); //$NON-NLS-1$
    }

    /**
     * Creates a synchronous connection (EndPoint with StartPoint/WaitingPlace/Timer) or asynchronous connection (EmptyPoint with StartPoint/WaitingPlace/Timer)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (left instanceof EmptyPoint || left instanceof EndPoint) {
            return (right instanceof StartPoint || right instanceof WaitingPlace || right instanceof Timer);
        } else
            return false;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        connect = (Connect) ModelCreationFactory.getNewObject(urn, Connect.class);
        ncLeft = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);
        ncRight = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        oldLeftX = left.getX();
        oldLeftY = left.getY();
        oldLeftParent = (ComponentRef) left.getContRef();

        connect.getPred().add(ncLeft);
        connect.getSucc().add(ncRight);
        connect.setX(right.getX());
        connect.setY(right.getY());

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        ncLeft.setSource(left);
        ncRight.setTarget(right);

        left.getDiagram().getConnections().add(ncLeft);
        left.getDiagram().getConnections().add(ncRight);
        left.getDiagram().getNodes().add(connect);

        left.setX(right.getX());
        left.setY(right.getY());

        IURNContainerRef parent = ParentFinder.getPossibleParent(left);
        left.setContRef(parent);
        connect.setContRef(parent);

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        ncLeft.setSource(null);
        ncRight.setTarget(null);

        left.getDiagram().getConnections().remove(ncLeft);
        left.getDiagram().getConnections().remove(ncRight);
        left.getDiagram().getNodes().remove(connect);

        left.setX(oldLeftX);
        left.setY(oldLeftY);

        left.setContRef(oldLeftParent);
        connect.setContRef(null);

        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert left != null && right != null && ncLeft != null && ncRight != null && connect != null && urn != null : "pre something is null"; //$NON-NLS-1$

        if (left instanceof EndPoint)
            assert left.getSucc().size() == 0 : "pre left already connected"; //$NON-NLS-1$
        else
            assert left.getSucc().size() == 1 : "pre left already connected"; //$NON-NLS-1$

        if (right instanceof StartPoint)
            assert right.getPred().size() == 0 : "pre right already connected"; //$NON-NLS-1$
        else
            assert right.getPred().size() == 1 : "pre right already connected"; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert left != null && right != null && ncLeft != null && ncRight != null && connect != null && urn != null : "post something is null"; //$NON-NLS-1$

        if (left instanceof EndPoint)
            assert left.getSucc().size() == 1 : "post left not connected"; //$NON-NLS-1$
        else
            assert left.getSucc().size() == 2 : "post left not connected"; //$NON-NLS-1$

        if (right instanceof StartPoint)
            assert right.getPred().size() == 1 : "post right not connected"; //$NON-NLS-1$
        else
            assert right.getPred().size() == 2 : "post right not connected"; //$NON-NLS-1$

    }

    public Connect getConnect() {
        return connect;
    }

}