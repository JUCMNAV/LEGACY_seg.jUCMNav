package seg.jUCMNav.model.commands;

import ucm.map.ComponentRef;

/**
 * This command is used to resize/move ComponentRefs
 * 
 * @author jkealey
 *  
 */
public class SetConstraintComponentRefCommand extends JUCMNavCommand {
    // default size for new components
    public static final int DEFAULT_HEIGHT = 100;
    public static final int DEFAULT_WIDTH = 100;

    // the ComponentRef
    private ComponentRef compRef;

    // constraints
    private int oldWidth, oldHeight, newWidth, newHeight;
    private int oldX, oldY, newX, newY;

    public SetConstraintComponentRefCommand(ComponentRef cr, int x, int y, int width, int height) {
        setCompRef(cr);
        setNewX(x);
        setNewY(y);
        setNewWidth(width);
        setNewHeight(height);

        setLabel("Change Component Constraints");
    }

    /**
     * We can't resize or move fixed components.
     * 
     * @return if the command can execute
     */
    public boolean canExecute() {
        return compRef != null && !compRef.isFixed();
    }

    public void execute() {
        oldX = compRef.getX();
        oldY = compRef.getY();
        oldWidth = compRef.getWidth();
        oldHeight = compRef.getHeight();

        redo();
    }

    /**
     * @return Returns the compRef.
     */
    public ComponentRef getCompRef() {
        return compRef;
    }

    /**
     * @return Returns the newHeight.
     */
    public int getNewHeight() {
        return newHeight;
    }

    /**
     * @return Returns the newWidth.
     */
    public int getNewWidth() {
        return newWidth;
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
     * Will not change model if new and old parameters are the same.
     */
    public void redo() {

        testPreConditions();

        if (oldX != newX)
            compRef.setX(newX);
        if (oldY != newY)
            compRef.setY(newY);
        if (oldWidth != newWidth)
            compRef.setWidth(newWidth);
        if (oldHeight != newHeight)
            compRef.setHeight(newHeight);

        testPostConditions();
    }

    /**
     * @param comp
     *            The component to set.
     */
    public void setComponentRef(ComponentRef comp) {
        this.compRef = comp;
    }

    /**
     * @param compRef
     *            The compRef to set.
     */
    public void setCompRef(ComponentRef compRef) {
        this.compRef = compRef;
    }

    /**
     * @param newHeight
     *            The newHeight to set. If <=0, sets the default value.
     */
    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
        if (newHeight <= 0)
            this.newHeight = DEFAULT_HEIGHT;
    }

    /**
     * @param newWidth
     *            The newWidth to set. If <=0, sets the default value.
     */
    public void setNewWidth(int newWidth) {

        this.newWidth = newWidth;
        if (newWidth <= 0)
            this.newWidth = DEFAULT_WIDTH;
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

    public void testPreConditions() {
        assert canExecute() : "pre canExecute";
        assert compRef.getWidth() == this.oldWidth : "pre width";
        assert compRef.getHeight() == this.oldHeight : "pre height";
        assert compRef.getX() == this.oldX : "pre x";
        assert compRef.getY() == this.oldY : "pre y";
    }

    public void testPostConditions() {
        assert canExecute() : "post canExecute";
        assert compRef.getWidth() == this.newWidth : "post width";
        assert compRef.getHeight() == this.newHeight : "post height";
        assert compRef.getX() == this.newX : "post x";
        assert compRef.getY() == this.newY : "post y";
    }

    /**
     * Will not change model if new and old parameters are the same.
     */
    public void undo() {
        testPostConditions();

        if (oldX != newX)
            compRef.setX(oldX);
        if (oldY != newY)
            compRef.setY(oldY);
        if (oldWidth != newWidth)
            compRef.setWidth(oldWidth);
        if (oldHeight != newHeight)
            compRef.setHeight(oldHeight);

        testPreConditions();
    }
}