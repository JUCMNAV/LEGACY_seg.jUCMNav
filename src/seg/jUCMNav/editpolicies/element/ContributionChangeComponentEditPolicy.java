package seg.jUCMNav.editpolicies.element;

import grl.ContributionChange;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteContributionChangeCommand;

/**
 * ComponentEditPolicy for ContributionChanges. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class ContributionChangeComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteVariableCommand / DeleteVariableInitializationCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof ContributionChange) {
            ContributionChange var = (ContributionChange) obj;
            DeleteContributionChangeCommand deleteCommand = new DeleteContributionChangeCommand(var);
            return deleteCommand;
        }

        return null;
    }
}
