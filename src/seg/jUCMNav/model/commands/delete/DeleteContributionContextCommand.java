/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ContributionChange;
import grl.ContributionContext;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveContributionContextCommand;

/**
 * This command delete a contribution context. It generate the command to delete all changes and remove all included strategies and deletes the strategy after.
 * 
 * @author jkealey
 * 
 */
public class DeleteContributionContextCommand extends CompoundCommand {

    public DeleteContributionContextCommand(ContributionContext context) {
        setLabel(Messages.getString("DeleteContributionContextCommand.DeleteContributionContext")); //$NON-NLS-1$

        for (Iterator iter = context.getChanges().iterator(); iter.hasNext();) {
            ContributionChange change = (ContributionChange) iter.next();
            add(new DeleteContributionChangeCommand(change));
        }
        
        for (Iterator iter = context.getIncludedContexts().iterator(); iter.hasNext();) {
            ContributionContext child = (ContributionContext) iter.next();
            add(new DeleteIncludedContributionContextCommand(context, child));
        }
        for (Iterator iter = context.getParentContexts().iterator(); iter.hasNext();) {
            ContributionContext parent = (ContributionContext) iter.next();
            add(new DeleteIncludedContributionContextCommand(parent, context));
        }

        add(new RemoveContributionContextCommand(context));
    }

}
