/*
 * Created on Jun 1, 2005
 */
package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Condition;

/**
 * @author Jordan
 */
public class ConditionSetConstraintCommand extends Command implements JUCMNavCommand {
    private int newDeltaX;

    private int newDeltaY;

    private int oldDeltaX;

    private int oldDeltaY;

    private Condition condition;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldDeltaX = condition.getDeltaX();
        oldDeltaY = condition.getDeltaY();
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        condition.setDeltaX(newDeltaX);
        condition.setDeltaY(newDeltaY);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        condition.setDeltaX(oldDeltaX);
        condition.setDeltaY(oldDeltaY);

        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert condition != null : "post Condition"; //$NON-NLS-1$
        assert condition.getDeltaX() == oldDeltaX && condition.getDeltaY() == oldDeltaY : "pre Condition position"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert condition != null : "post Condition"; //$NON-NLS-1$
        assert condition.getDeltaX() == newDeltaX && condition.getDeltaY() == newDeltaY : "post Condition position"; //$NON-NLS-1$
    }

    /**
     * @param condition
     *            The condition to set.
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * @param newPosition
     *            The newPosition to set.
     */
    public void setNewPosition(int newDeltaX, int newDeltaY) {
        this.newDeltaX = newDeltaX;
        this.newDeltaY = newDeltaY;
    }
}
