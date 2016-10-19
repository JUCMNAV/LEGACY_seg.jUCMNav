package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import grl.ContributionContext;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.dyncontext.DynamicContext;

/**
 * Command to delete ContributionContext of the selected DynamicContext
 * 
 * @author aprajita
 * 
 */
public class DeleteDynamicContextContributionContextCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext parent;
	private ContributionContext child;
    private boolean aborted;

    /**
     * Removes contribution context from the parent DynamicContext
     */
    public DeleteDynamicContextContributionContextCommand(DynamicContext parent, ContributionContext child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("DeleteDynamicContextContributionContextCommand.DeleteDynamicContextContributionContext")); //$NON-NLS-1$

    }

    /**
     * Only if the child is the included Contribution Context of the parent
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && parent.getContributionContext() == child;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (!(parent.getContributionContext() == child)) {
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
        
        parent.setContributionContext(null);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre something is null"; //$NON-NLS-1$
        assert parent.getContributionContext() == child : "pre contribution context included"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post something is null"; //$NON-NLS-1$
        assert !(parent.getContributionContext() == child) : "post contribution context included"; //$NON-NLS-1$	
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        parent.setContributionContext(child);

        testPreConditions();
    }

}
