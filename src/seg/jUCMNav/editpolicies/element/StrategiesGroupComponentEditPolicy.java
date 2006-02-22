/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteStrategiesGroupCommand;

/**
 * ComponentEditPolicy for EvaluationGroup. Return the delete command
 * 
 * @author Jean-François Roy
 *
 */
public class StrategiesGroupComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteStrategiesGroupCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof StrategiesGroup) {

            StrategiesGroup group = (StrategiesGroup) obj;
            if (group.getStrategies().size() == 0) {
                DeleteStrategiesGroupCommand deleteCommand = new DeleteStrategiesGroupCommand(group);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}
