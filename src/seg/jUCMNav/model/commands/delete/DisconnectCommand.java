package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Created on 16-Jun-2005
 * 
 * @author jkealey
 *  
 */
public class DisconnectCommand extends Command implements JUCMNavCommand {

    private PathNode left, right;
    private NodeConnection ncLeft, ncRight;
    private Connect connect;
    private URNspec urn;

    private int oldLeftX, oldLeftY;
    private ComponentRef oldLeftParent;

    public DisconnectCommand(PathNode pn) {
        // pn to the left of a connect?
        for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            if (nc.getTarget() instanceof Connect) {
                this.left = pn;
                this.ncLeft = nc;
                this.connect = (Connect) nc.getTarget();
                this.ncRight = (NodeConnection) this.connect.getSucc().get(0);
                this.right = this.ncRight.getTarget();
                break;
            }

        }

        // no? pn to the right of a connect?
        if (left == null) {
            for (Iterator iter = pn.getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                if (nc.getSource() instanceof Connect) {
                    this.right = pn;
                    this.ncRight = nc;
                    this.connect = (Connect) nc.getSource();
                    this.ncLeft = (NodeConnection) this.connect.getPred().get(0);
                    this.left = this.ncLeft.getSource();
                    break;
                }

            }
        }

        if (left != null && left.getPathGraph() != null) {
            this.urn = this.left.getPathGraph().getMap().getUcmspec().getUrnspec();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return left != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.oldLeftX = this.left.getX();
        this.oldLeftY = this.left.getY();
        this.oldLeftParent = this.left.getCompRef();

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void undo() {
        if (connect.getPathGraph() != null)
            return; // already re-connected

        testPostConditions();

        ncLeft.setSource(left);
        ncRight.setTarget(right);

        left.getPathGraph().getNodeConnections().add(ncLeft);
        left.getPathGraph().getNodeConnections().add(ncRight);
        left.getPathGraph().getPathNodes().add(connect);

        left.setX(oldLeftX);
        left.setY(oldLeftY);

        left.setCompRef(oldLeftParent);

        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void redo() {
        if (connect.getPathGraph() == null)
            return; // already disconnected

        testPreConditions();

        ncLeft.setSource(null);
        ncRight.setTarget(null);

        left.getPathGraph().getNodeConnections().remove(ncLeft);
        left.getPathGraph().getNodeConnections().remove(ncRight);
        left.getPathGraph().getPathNodes().remove(connect);

        left.setX(right.getX());
        left.setY(right.getY() - 50);
        left.setCompRef(ParentFinder.getPossibleParent(left));

        testPostConditions();
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