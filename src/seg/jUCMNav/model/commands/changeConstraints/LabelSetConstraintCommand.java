package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Label;

/**
 * Changes a label's offset from its reference element.
 * 
 * @author Jordan
 */
public class LabelSetConstraintCommand extends Command implements JUCMNavCommand {

    private int newDeltaX;
    private int newDeltaY;
    private int oldDeltaX;
    private int oldDeltaY;
    private Label label;

    public LabelSetConstraintCommand() {
        setLabel(Messages.getString("LabelSetConstraintCommand.resizeCommand"));//$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldDeltaX = label.getDeltaX();
        oldDeltaY = label.getDeltaY();
        redo();

    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        label.setDeltaX(newDeltaX);
        label.setDeltaY(newDeltaY);

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        label.setDeltaX(oldDeltaX);
        label.setDeltaY(oldDeltaY);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert label != null : "post Label"; //$NON-NLS-1$
        assert label.getDeltaX() == newDeltaX && label.getDeltaY() == newDeltaY : "post Label position"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "post Label"; //$NON-NLS-1$
        assert label.getDeltaX() == oldDeltaX && label.getDeltaY() == oldDeltaY : "pre Label position"; //$NON-NLS-1$
    }

    /**
     * @param label
     *            The label to set.
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @param newDeltaX
     *            The newPosition X to set.
     * @param newDeltaY
     *            The newPosition Y to set.
     */
    public void setNewPosition(int newDeltaX, int newDeltaY) {
        this.newDeltaX = newDeltaX;
        this.newDeltaY = newDeltaY;
    }
}