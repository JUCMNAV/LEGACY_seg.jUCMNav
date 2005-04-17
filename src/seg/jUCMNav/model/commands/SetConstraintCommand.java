package seg.jUCMNav.model.commands;

import ucm.map.PathNode;

/**
 * This command is used to resize/move PathNodes.
 * 
 * @author Etienne Tremblay
 *  
 */
public class SetConstraintCommand extends JUCMNavCommand {

    int oldX, oldY, newX, newY;
    private PathNode node;

    public SetConstraintCommand(PathNode node, int x, int y) {
        this.node = node;
        this.newX = x;
        this.newY = y;

        setLabel("Move Node");

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldX = node.getX();
        oldY = node.getY();
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        node.setX(newX);
        node.setY(newY);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        node.setX(oldX);
        node.setY(oldY);
        testPreConditions();
    }

    /**
     * @param node
     *            The node to set.
     */
    public void setNode(PathNode node) {
        this.node = node;
    }

    /**
     * @return Returns the newX.
     */
    public int getNewX() {
        return newX;
    }

    /**
     * @param newX
     *            The newX to set.
     */
    public void setNewX(int newX) {
        this.newX = newX;
    }

    /**
     * @return Returns the newY.
     */
    public int getNewY() {
        return newY;
    }

    /**
     * @param newY
     *            The newY to set.
     */
    public void setNewY(int newY) {
        this.newY = newY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert node != null : "pre node";
        assert node.getX() == oldX && node.getY() == oldY : "pre node position";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert node != null : "post node";
        assert node.getX() == newX && node.getY() == newY : "post node position";

    }
}