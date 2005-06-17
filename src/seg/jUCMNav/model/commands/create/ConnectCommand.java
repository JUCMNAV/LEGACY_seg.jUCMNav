package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

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

/**
 * Created on 16-Jun-2005
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

    public ConnectCommand(PathNode first, PathNode second) {
        if (first instanceof EmptyPoint || first instanceof EndPoint) {
            this.left = first;
            this.right = second;
        } else {
            this.left = second;
            this.right = first;
        }

        if (left != null) {
            urn = left.getPathGraph().getMap().getUcmspec().getUrnspec();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (left instanceof EmptyPoint || left instanceof EndPoint) {
            return (right instanceof StartPoint || right instanceof WaitingPlace || right instanceof Timer);
        } else
            return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        connect = (Connect) ModelCreationFactory.getNewObject(urn, Connect.class);
        ncLeft = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);
        ncRight = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        oldLeftX = left.getX();
        oldLeftY = left.getY();
        oldLeftParent = left.getCompRef();

        connect.getPred().add(ncLeft);
        connect.getSucc().add(ncRight);
        connect.setX(right.getX());
        connect.setY(right.getY());

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        ncLeft.setSource(left);
        ncRight.setTarget(right);

        left.getPathGraph().getNodeConnections().add(ncLeft);
        left.getPathGraph().getNodeConnections().add(ncRight);
        left.getPathGraph().getPathNodes().add(connect);

        left.setX(right.getX());
        left.setY(right.getY());

        left.setCompRef(ParentFinder.getPossibleParent(left));

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        ncLeft.setSource(null);
        ncRight.setTarget(null);

        left.getPathGraph().getNodeConnections().remove(ncLeft);
        left.getPathGraph().getNodeConnections().remove(ncRight);
        left.getPathGraph().getPathNodes().remove(connect);

        left.setX(oldLeftX);
        left.setY(oldLeftY);

        left.setCompRef(oldLeftParent);

        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // TODO Auto-generated method stub

    }

}