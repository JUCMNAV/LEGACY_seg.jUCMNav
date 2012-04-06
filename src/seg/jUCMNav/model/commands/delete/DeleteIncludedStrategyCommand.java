/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.EvaluationStrategy;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Removes a strategy's included strategy
 * 
 * @author jkealey
 * 
 */
public class DeleteIncludedStrategyCommand extends Command implements JUCMNavCommand {

    private EvaluationStrategy parent, child;
    private int index;
    private boolean aborted; // can happen if deleting multiple strategy at a time

    /**
     * Remove child from the parent's included strategy list.
     */
    public DeleteIncludedStrategyCommand(EvaluationStrategy parent, EvaluationStrategy child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("DeleteIncludedStrategyCommand.RemoveIncludedStrategy")); //$NON-NLS-1$

    }

    /**
     * Only if no strategy/scenario in it
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && parent.getIncludedStrategies().contains(child);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (!parent.getIncludedStrategies().contains(child)) {
            aborted = true;
        }

        index = parent.getIncludedStrategies().indexOf(child);

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

        testPreConditions();

        parent.getIncludedStrategies().remove(child);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre something is null"; //$NON-NLS-1$
        assert parent.getIncludedStrategies().contains(child) : "pre strategy included"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post something is null"; //$NON-NLS-1$
        assert !parent.getIncludedStrategies().contains(child) : "post strategy included"; //$NON-NLS-1$	
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        parent.getIncludedStrategies().add(index, child);

        testPreConditions();
    }
}
