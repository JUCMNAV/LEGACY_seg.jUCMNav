/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.EvaluationScenario;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteEvaluationScenarioCommand;

/**
 * ComponentEditPolicy for EvaluationScenario. Return the delete command for a scenario
 * @author Jean-François Roy
 *
 */
public class EvaluationScenarioComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteEvaluationScenarioCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof EvaluationScenario) {

            EvaluationScenario scenario = (EvaluationScenario) obj;
            DeleteEvaluationScenarioCommand deleteCommand = new DeleteEvaluationScenarioCommand(scenario);
            return deleteCommand;
        }

        return null;
    }

}
