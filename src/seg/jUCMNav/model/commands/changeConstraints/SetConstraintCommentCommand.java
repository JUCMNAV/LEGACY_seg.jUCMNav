package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Comment;

/**
 * This command is used to resize/move Comments.
 * 
 * @author jkealey
 */
public class SetConstraintCommentCommand extends Command implements JUCMNavCommand {

    private Comment comment;

    // constraints
    private int oldWidth, oldHeight, newWidth, newHeight;

    private int oldX, oldY, newX, newY;

    public SetConstraintCommentCommand(Comment comment, int x, int y, int width, int height) {
        setComment(comment);
        setNewX(x);
        setNewY(y);
        setNewWidth(width);
        setNewHeight(height);

        setLabel(Messages.getString("SetConstraintCommentCommand.MoveResizeComment")); //$NON-NLS-1$

    }

    /**
     * @return if the command can execute
     */
    public boolean canExecute() {
        return comment != null;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldX = comment.getX();
        oldY = comment.getY();
        oldWidth = comment.getWidth();
        oldHeight = comment.getHeight();

        redo();
    }

    /**
     * @return Returns the comment.
     */
    public Comment getComment() {
        return comment;
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
            comment.setX(newX);
        if (oldY != newY)
            comment.setY(newY);
        if (oldWidth != newWidth)
            comment.setWidth(newWidth);
        if (oldHeight != newHeight)
            comment.setHeight(newHeight);

        testPostConditions();
    }

    /**
     * @param comment
     *            The comment to set.
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * @param newHeight
     *            The newHeight to set. If <=0, sets the default value.
     */
    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
        if (newHeight <= 0) {
            this.newHeight = ModelCreationFactory.DEFAULT_UCM_COMPONENT_HEIGHT;
        }
    }

    /**
     * @param newWidth
     *            The newWidth to set. If <=0, sets the default value.
     */
    public void setNewWidth(int newWidth) {

        this.newWidth = newWidth;
        if (newWidth <= 0) {
            this.newWidth = ModelCreationFactory.DEFAULT_UCM_COMPONENT_WIDTH;
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
        assert comment.getWidth() == this.newWidth : "post width"; //$NON-NLS-1$
        assert comment.getHeight() == this.newHeight : "post height"; //$NON-NLS-1$
        assert comment.getX() == this.newX : "post x"; //$NON-NLS-1$
        assert comment.getY() == this.newY : "post y"; //$NON-NLS-1$
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert canExecute() : "pre canExecute"; //$NON-NLS-1$
        assert comment.getWidth() == this.oldWidth : "pre width"; //$NON-NLS-1$
        assert comment.getHeight() == this.oldHeight : "pre height"; //$NON-NLS-1$
        assert comment.getX() == this.oldX : "pre x"; //$NON-NLS-1$
        assert comment.getY() == this.oldY : "pre y"; //$NON-NLS-1$

    }

    /**
     * Will not change model if new and old parameters are the same.
     */
    public void undo() {
        testPostConditions();

        if (oldX != newX)
            comment.setX(oldX);
        if (oldY != newY)
            comment.setY(oldY);
        if (oldWidth != newWidth)
            comment.setWidth(oldWidth);
        if (oldHeight != newHeight)
            comment.setHeight(oldHeight);
        testPreConditions();
    }
}