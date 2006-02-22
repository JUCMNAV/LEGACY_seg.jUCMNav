/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.EvaluationStrategy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteEvaluationStrategyCommand;

/**
 * ComponentEditPolicy for EvaluationStrategy. Return the delete command for a strategy
 * @author Jean-François Roy
 *
 */
public class EvaluationStrategyComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteEvaluationStrategyCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof EvaluationStrategy) {

            EvaluationStrategy strategy = (EvaluationStrategy) obj;
            DeleteEvaluationStrategyCommand deleteCommand = new DeleteEvaluationStrategyCommand(strategy);
            return deleteCommand;
        }

        return null;
    }

}
