/**
 * 
 */
package seg.jUCMNav.model.commands.changeConstraints;

import grl.GRLNode;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import urncore.IURNContainerRef;

/**
 * This command is used to move and resize GrlNode elements
 * 
 * @author Jean-François Roy
 *
 */
public class MoveResizeGrlNodeCommand extends Command implements JUCMNavCommand {

    private GRLNode node;

    IURNContainerRef oldParent, newParent;

    int oldX, oldY, newX, newY;
//    int oldWidth, newWidth;
//    int oldHeight, newHeight;
    /**
     * 
     */
    public MoveResizeGrlNodeCommand(GRLNode node, int x, int y){ 
            this.node = node;
            setNewX(x);
            setNewY(y);

            setLabel(Messages.getString("MoveResizeGrlNodeCommand.moveResizeGrlNode")); //$NON-NLS-1$

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

        if (oldX != newX)
            node.setX(newX);
        if (oldY != newY)
            node.setY(newY);
        if (oldParent != newParent)
            node.setContRef(newParent);

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
    public void setNode(GRLNode node) {
        this.node = node;
    }

    /**
     * Remembers the current and new parent, according to the destination location.
     */
    private void setParents() {
        oldParent = (IURNContainerRef) node.getContRef();
        if (node.getDiagram() != null ) {
            newParent = ParentFinder.findParent(node.getDiagram(), newX, newY);
        }
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert node != null : "pre node"; //$NON-NLS-1$
        assert node.getX() == this.oldX: "pre node X"; //$NON-NLS-1$
        assert node.getY() == this.oldY : "pre node Y"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert node != null : "post node"; //$NON-NLS-1$
        assert node.getX() == this.newX && node.getY() == this.newY : "post node position"; //$NON-NLS-1$

        if (newParent != null)
            assert (new Rectangle(newParent.getX(), newParent.getY(), newParent.getWidth(), newParent.getHeight()))
                    .contains(new Point(node.getX(), node.getY())) : "post node in parent."; //$NON-NLS-1$


    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (oldX != newX)
            node.setX(oldX);
        if (oldY != newY)
            node.setY(oldY);
        if (oldParent != newParent)
            node.setContRef(oldParent);

        testPreConditions();
    }
}
