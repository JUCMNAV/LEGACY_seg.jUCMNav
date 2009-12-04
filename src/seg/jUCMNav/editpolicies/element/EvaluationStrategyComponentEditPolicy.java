package seg.jUCMNav.editpolicies.element;

import grl.EvaluationStrategy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioLabelTreeEditPart;
import seg.jUCMNav.model.commands.delete.DeleteIncludedScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategyCommand;
import ucm.scenario.ScenarioDef;

/**
 * ComponentEditPolicy for EvaluationStrategy/ScenarioDef. Return the delete command for a strategy
 * 
 * @author Jean-François Roy, jkealey
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
            if (getHost().getParent().getModel() instanceof String) {
                // included scenario.
                ScenarioDef parent = (ScenarioDef) getHost().getParent().getParent().getModel();
                if (getHost().getParent().getChildren().indexOf(getHost()) >= ((ScenarioLabelTreeEditPart) getHost().getParent()).getModelChildren().size()
                        - ((ScenarioDef) getHost().getParent().getParent().getModel()).getIncludedScenarios().size())
                    return new DeleteIncludedScenarioCommand(parent, scenario);
                else
                    return null;
            } else {
                DeleteScenarioCommand deleteCommand = new DeleteScenarioCommand(scenario);
                return deleteCommand;
            }
        }

        return null;
    }

}
