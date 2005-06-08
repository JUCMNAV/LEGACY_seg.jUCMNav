package seg.jUCMNav.model.commands.changeConstraints;

import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;

/**
 * This command is used to resize/move ComponentRefs
 * 
 * @author jkealey
 *  
 */
public class SetConstraintComponentRefCommand extends Command implements JUCMNavCommand {
    // default size for new components
    public static final int DEFAULT_HEIGHT = 100;
    public static final int DEFAULT_WIDTH = 100;
    private Vector children;

    // the ComponentRef
    private ComponentRef compRef;

    private ComponentRef oldParent, newParent;

    // constraints
    private int oldWidth, oldHeight, newWidth, newHeight;
    private int oldX, oldY, newX, newY;

    public SetConstraintComponentRefCommand(ComponentRef cr, int x, int y, int width, int height) {
        setCompRef(cr);
        setNewX(x);
        setNewY(y);
        setNewWidth(width);
        setNewHeight(height);

        setLabel(Messages.getString("SetConstraintComponentRefCommand.changeCompConst")); //$NON-NLS-1$

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

        setParents();

        redo();
    }

    /**
     * Remembers the current and new parent, according to the destination location.
     */
    private void setParents() {
        oldParent = compRef.getParent();
        newParent = ParentFinder.findParent(compRef.getMap(), compRef, newX, newY, newWidth, newHeight);
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
     * @return Returns the children.
     */
    public List getOriginalChildren() {
        return children;
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
        if (oldParent != newParent)
            compRef.setParent(newParent);

        testPostConditions();
    }

    /**
     * @param compRef
     *            The compRef to set.
     */
    public void setCompRef(ComponentRef compRef) {
        this.compRef = compRef;
        children = new Vector();
        for (int i = 0; i < compRef.getChildren().size(); i++)
            children.add(compRef.getChildren().get(i));
        for (int i = 0; i < compRef.getPathNodes().size(); i++)
            children.add(compRef.getPathNodes().get(i));
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

    public void testPostConditions() {
        assert canExecute() : "post canExecute"; //$NON-NLS-1$
        assert compRef.getWidth() == this.newWidth : "post width"; //$NON-NLS-1$
        assert compRef.getHeight() == this.newHeight : "post height"; //$NON-NLS-1$
        assert compRef.getX() == this.newX : "post x"; //$NON-NLS-1$
        assert compRef.getY() == this.newY : "post y"; //$NON-NLS-1$

        if (newParent != null)
            assert (new Rectangle(newParent.getX(), newParent.getY(), newParent.getWidth(), newParent.getHeight())).contains(new Rectangle(compRef.getX(),
                    compRef.getY(), compRef.getWidth(), compRef.getHeight())) : "post component in parent."; //$NON-NLS-1$

    }

    public void testPreConditions() {
        assert canExecute() : "pre canExecute"; //$NON-NLS-1$
        assert compRef.getWidth() == this.oldWidth : "pre width"; //$NON-NLS-1$
        assert compRef.getHeight() == this.oldHeight : "pre height"; //$NON-NLS-1$
        assert compRef.getX() == this.oldX : "pre x"; //$NON-NLS-1$
        assert compRef.getY() == this.oldY : "pre y"; //$NON-NLS-1$

        // this is not true because in our compound command the parent might already have been moved.
        //        if (newParent!=null)
        //            assert (new Rectangle(newParent.getX(), newParent.getY(), newParent.getWidth(), newParent.getHeight())).contains(new Rectangle(compRef.getX(),
        // compRef.getY(), compRef.getWidth(), compRef.getHeight())) : "post component in parent.";

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
        if (oldParent != newParent)
            compRef.setParent(oldParent);

        testPreConditions();
    }
}