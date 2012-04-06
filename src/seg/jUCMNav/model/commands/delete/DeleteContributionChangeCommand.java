package seg.jUCMNav.model.commands.delete;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Command to delete a ContributionChange.
 * 
 * @author jkealey
 * 
 */
public class DeleteContributionChangeCommand extends Command implements JUCMNavCommand {

    private ContributionChange change;
    private Contribution contrib;
    private ContributionContext context;

    public DeleteContributionChangeCommand(ContributionChange change) {
        this.change = change;
        setLabel(Messages.getString("DeleteContributionChangeCommand.DeleteContributionChange")); //$NON-NLS-1$
    }

    /**
     *  
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return change != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        context = change.getContext();
        contrib = change.getContribution();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        change.setContribution(null);
        change.setContext(null);
        if (contrib!=null) contrib.setContribution(contrib.getContribution()); // force UI refreshes. 

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert change!=null && context!=null && contrib != null : "post something is null"; //$NON-NLS-1$
        assert change.getContribution() == null || change.getContext() == null : "post link not broken"; //$NON-NLS-1$ 
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert change!=null && context!=null && contrib != null : "pre something is null"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        change.setContribution(contrib);
        change.setContext(context);
        if (contrib!=null) contrib.setContribution(contrib.getContribution()); // force UI refreshes. 

        testPreConditions();
    }
}