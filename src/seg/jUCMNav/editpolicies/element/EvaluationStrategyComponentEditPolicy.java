/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.EvaluationStrategy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategyCommand;
import ucm.scenario.ScenarioDef;

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
            DeleteStrategyCommand deleteCommand = new DeleteStrategyCommand(strategy);
            return deleteCommand;
        } else if (obj instanceof ScenarioDef) {

        	ScenarioDef scenario = (ScenarioDef) obj;
            DeleteScenarioCommand deleteCommand = new DeleteScenarioCommand(scenario);
            return deleteCommand;
        }


        return null;
    }

}
