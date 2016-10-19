package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import grl.EvaluationStrategy;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.dyncontext.DynamicContext;

/**
 * Command to delete Strategy of the selected DynamicContext
 * 
 * @author aprajita
 * 
 */
public class DeleteDynamicContextStrategyCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext parent;
	private EvaluationStrategy child;
    private boolean aborted;

    /**
     * Removes strategy from the parent DynamicContext
     */
    public DeleteDynamicContextStrategyCommand(DynamicContext parent, EvaluationStrategy child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("DeleteDynamicContextStrategyCommand.DeleteDynamicContextStrategy")); //$NON-NLS-1$

    }

    /**
     * Only if the child is the included Strategy of the parent
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && parent.getStrategy() == child;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (!(parent.getStrategy() == child)) {
            aborted = true;
        }

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

        testPreConditions();

        parent.setStrategy(null);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre something is null"; //$NON-NLS-1$
        assert parent.getStrategy() == child : "pre strategy included"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post something is null"; //$NON-NLS-1$
        assert !(parent.getStrategy() == child) : "post strategy included"; //$NON-NLS-1$	
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        parent.setStrategy(child);

        testPreConditions();
    }

}
