package seg.jUCMNav.model.commands.changeConstraints;

import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import urncore.GRLmodelElement;
import urncore.IURNContainerRef;
import urncore.UCMmodelElement;

/**
 * This command is used to resize/move ComponentRefs. Doesn't move anything inside it although it remembers its children for undo/redo purposes.
 * 
 * Would put package access because we don't want the external framework using it; we want them to use
 * seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand. However, don't want to modify a bunch of test cases that depend
 * on this command's behaviour.
 * 
 * @author jkealey
 * @see seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand
 */
public class SetConstraintContainerRefCommand extends Command implements JUCMNavCommand {
    private Vector children;

    // the ComponentRef
    private IURNContainerRef compRef;

    private IURNContainerRef oldParent, newParent;

    // constraints
    private int oldWidth, oldHeight, newWidth, newHeight;

    private int oldX, oldY, newX, newY;

    public SetConstraintContainerRefCommand(IURNContainerRef cr, int x, int y, int width, int height) {
        setCompRef(cr);
        setNewX(x);
        setNewY(y);
        setNewWidth(width);
        setNewHeight(height);

        setLabel(Messages.getString("SetConstraintBoundContainerRefCompoundCommand.changeCompConstraints")); //$NON-NLS-1$

    }

    /**
     * We can't resize or move fixed components.
     * 
     * @return if the command can execute
     */
    public boolean canExecute() {
        return compRef != null && !compRef.isFixed();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
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
        newParent = ParentFinder.findParent(compRef.getDiagram(), compRef, newX, newY, newWidth, newHeight);
    }

    /**
     * @return Returns the compRef.
     */
    public IURNContainerRef getCompRef() {
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
    public void setCompRef(IURNContainerRef compRef) {
        this.compRef = compRef;
        children = new Vector();
        for (int i = 0; i < compRef.getChildren().size(); i++)
            children.add(compRef.getChildren().get(i));
        for (int i = 0; i < compRef.getNodes().size(); i++)
            children.add(compRef.getNodes().get(i));
    }

    /**
     * @param newHeight
     *            The newHeight to set. If <=0, sets the default value.
     */
    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
        if (newHeight <= 0) {
            if (getCompRef() instanceof UCMmodelElement)
                this.newHeight = ModelCreationFactory.DEFAULT_UCM_COMPONENT_HEIGHT;
            else if (getCompRef() instanceof GRLmodelElement)
                this.newHeight = ModelCreationFactory.DEFAULT_GRL_COMPONENT_HEIGHT;
        }
    }

    /**
     * @param newWidth
     *            The newWidth to set. If <=0, sets the default value.
     */
    public void setNewWidth(int newWidth) {

        this.newWidth = newWidth;
        if (newWidth <= 0) {
            if (getCompRef() instanceof UCMmodelElement)
                this.newWidth = ModelCreationFactory.DEFAULT_UCM_COMPONENT_WIDTH;
            else if (getCompRef() instanceof GRLmodelElement)
                this.newWidth = ModelCreationFactory.DEFAULT_GRL_COMPONENT_WIDTH;
        }
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
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
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

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert canExecute() : "pre canExecute"; //$NON-NLS-1$
        assert compRef.getWidth() == this.oldWidth : "pre width"; //$NON-NLS-1$
        assert compRef.getHeight() == this.oldHeight : "pre height"; //$NON-NLS-1$
        assert compRef.getX() == this.oldX : "pre x"; //$NON-NLS-1$
        assert compRef.getY() == this.oldY : "pre y"; //$NON-NLS-1$

        // this is not true because in our compound command the parent might already have been moved.
        // if (newParent!=null)
        // assert (new Rectangle(newParent.getX(), newParent.getY(), newParent.getWidth(), newParent.getHeight())).contains(new
        // Rectangle(compRef.getX(),
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