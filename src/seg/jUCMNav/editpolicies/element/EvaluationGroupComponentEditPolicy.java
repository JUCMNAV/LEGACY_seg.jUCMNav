/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.EvaluationGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteEvaluationGroupCommand;

/**
 * ComponentEditPolicy for EvaluationGroup. Return the delete command
 * 
 * @author Jean-François Roy
 *
 */
public class EvaluationGroupComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteEvaluationGroupCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof EvaluationGroup) {

            EvaluationGroup group = (EvaluationGroup) obj;
            if (group.getScenarios().size() == 0) {
                DeleteEvaluationGroupCommand deleteCommand = new DeleteEvaluationGroupCommand(group);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}
