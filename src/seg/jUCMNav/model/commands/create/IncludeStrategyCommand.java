/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.EvaluationStrategy;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * This command includes a strategy in another one.
 * 
 * @author jkealey
 * 
 */
public class IncludeStrategyCommand extends Command implements JUCMNavCommand {

    private EvaluationStrategy parent, child;
    boolean aborted = false;
    boolean isInCompoundCommand = false;

    /**
	 * 
	 */
    public IncludeStrategyCommand(EvaluationStrategy parent, EvaluationStrategy child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludeStrategyCommand.IncludeStrategy")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public IncludeStrategyCommand(EvaluationStrategy parent, EvaluationStrategy child, boolean isInCompoundCommand) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludeStrategyCommand.IncludeStrategy")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && (isInCompoundCommand || EvaluationStrategyManager.getPossibleIncludedStrategies(parent).contains(child));
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (!canExecute()) {
            aborted = true; // another command in same compoundcommand invalided our preconditions
            return;
        }
        testPreConditions();
        this.parent.getIncludedStrategies().add(child);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post not null"; //$NON-NLS-1$
        assert parent.getIncludedStrategies().contains(child) : "post child not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre not null"; //$NON-NLS-1$
        assert !parent.getIncludedStrategies().contains(child) : "pre child not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        this.parent.getIncludedStrategies().remove(child);
        testPreConditions();
    }
}
