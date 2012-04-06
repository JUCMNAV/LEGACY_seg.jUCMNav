/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ContributionContext;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Removes a contribution context's included context
 * 
 * @author jkealey
 * 
 */
public class DeleteIncludedContributionContextCommand extends Command implements JUCMNavCommand {

    private ContributionContext parent, child;
    private int index;
    private boolean aborted; // can happen if deleting multiple contexts at a time

    /**
     * Remove child from the parent's included strategy list.
     */
    public DeleteIncludedContributionContextCommand(ContributionContext parent, ContributionContext child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("DeleteIncludedContributionContextCommand.RemoveIncludedContributionContext")); //$NON-NLS-1$

    }

    /**
     * Only if no strategy/scenario/context in it
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && parent.getIncludedContexts().contains(child);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (!parent.getIncludedContexts().contains(child)) {
            aborted = true;
        }

        index = parent.getIncludedContexts().indexOf(child);

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

        testPreConditions();

        parent.getIncludedContexts().remove(child);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre something is null"; //$NON-NLS-1$
        assert parent.getIncludedContexts().contains(child) : "pre context included"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post something is null"; //$NON-NLS-1$
        assert !parent.getIncludedContexts().contains(child) : "post context included"; //$NON-NLS-1$	
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        parent.getIncludedContexts().add(index, child);

        testPreConditions();
    }
}
