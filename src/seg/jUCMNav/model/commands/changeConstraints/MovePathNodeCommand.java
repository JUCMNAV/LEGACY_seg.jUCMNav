package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.PathNode;

/**
 * This command is used to resize/move PathNodes.
 * 
 * Package access because we don't want external classes using it directly. Use SetConstraintCommand instead.
 * 
 * @author Etienne Tremblay, jkealey
 * 
 */
class MovePathNodeCommand extends Command implements JUCMNavCommand {
    private PathNode node;

    ComponentRef oldParent, newParent;

    int oldX, oldY, newX, newY;

    /**
     * 
     * @param node
     *            the pathnode to be moved
     * @param x
     *            the new x location
     * @param y
     *            the new y location
     */
    public MovePathNodeCommand(PathNode node, int x, int y) {
        this.node = node;
        this.newX = x;
        this.newY = y;

        setLabel(Messages.getString("SetConstraintCommand.moveNode")); //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldX = node.getX();
        oldY = node.getY();

        setParents();

        redo();
    }

    /**
     * @return Returns the newX.
     */
    public int getNewX() {
        return newX;
    }

    /**
     * @return Returns the newY.
     */
    public int getNewY() {
        return newY;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        node.setX(newX);
        node.setY(newY);
        node.setCompRef(newParent);
        testPostConditions();
    }

    /**
     * @param newX
     *            The newX to set.
     */
    public void setNewX(int newX) {
        this.newX = newX;
    }

    /**
     * @param newY
     *            The newY to set.
     */
    public void setNewY(int newY) {
        this.newY = newY;
    }

    /**
     * @param node
     *            The node to set.
     */
    public void setNode(PathNode node) {
        this.node = node;
    }

    /**
     * Remembers the current and new parent, according to the destination location.
     */
    private void setParents() {
        oldParent = node.getCompRef();
        if (!(node instanceof Connect)) {
            if (node.getPathGraph() != null && node.getPathGraph().getMap() != null) {
                newParent = ParentFinder.findParent(node.getPathGraph().getMap(), newX, newY);
            }
        }
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert node != null : "post node"; //$NON-NLS-1$
        assert node.getX() == newX && node.getY() == newY : "post node position"; //$NON-NLS-1$

        if (newParent != null)
            assert (new Rectangle(newParent.getX(), newParent.getY(), newParent.getWidth(), newParent.getHeight()))
                    .contains(new Point(node.getX(), node.getY())) : "post node in parent."; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert node != null : "pre node"; //$NON-NLS-1$
        assert node.getX() == oldX && node.getY() == oldY : "pre node position"; //$NON-NLS-1$

        // this is not true because in our compound command the parent might already have been moved.
        // if (oldParent!=null)
        // assert (new Rectangle(oldParent.getX(), oldParent.getY(), oldParent.getWidth(), oldParent.getHeight())).contains(new
        // Point(node.getX(), node.getY()))
        // : "pre node in parent.";
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        node.setX(oldX);
        node.setY(oldY);
        node.setCompRef(oldParent);
        testPreConditions();
    }
}