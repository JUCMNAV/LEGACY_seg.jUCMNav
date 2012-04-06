/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * This command adds a contribution change in another one. 
 * 
 * @author jkealey
 * 
 */
public class AddContributionChangeCommand extends Command implements JUCMNavCommand {

    private ContributionContext parent;
    private ContributionChange change;
    private Contribution contribution;
    
    private boolean aborted=false;
    
    /**
	 * Add a contribution change to the model. Contribution change should not already be be bound to contribution. 
	 */
    public AddContributionChangeCommand(ContributionContext parent, Contribution contribution, ContributionChange change) {
        this.parent = parent;
        this.change = change;
        this.contribution = contribution;
        setLabel(Messages.getString("AddContributionChangeCommand.AddContributionChange")); //$NON-NLS-1$
    }


    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && change != null && contribution!=null && !isAlreadyOverridden() && change.getContribution()==null;
    }

    public boolean isAlreadyOverridden()
    {
        if (parent!=null && change!=null)
        {
            for (Iterator iterator = parent.getChanges().iterator(); iterator.hasNext();) {
                ContributionChange c = (ContributionChange) iterator.next();
                if (c == change || (c.getContribution()!=null && c.getContribution() == change.getContribution()))
                {
                    return true;
                }
            }
        }

        return false;
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
            aborted = true; // another command in same compoundcommand invalidated our preconditions
            return;
        }
        testPreConditions();
        this.parent.getChanges().add(change);
        change.setContribution(contribution);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && change!= null : "post not null"; //$NON-NLS-1$
        assert parent.getChanges().contains(change) && change.getContribution()!=null : "post child not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && change != null : "pre not null"; //$NON-NLS-1$
        assert !parent.getIncludedContexts().contains(change) && change.getContribution()==null : "pre child not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        this.parent.getChanges().remove(change);
        change.setContribution(null);
        testPreConditions();
    }
}
