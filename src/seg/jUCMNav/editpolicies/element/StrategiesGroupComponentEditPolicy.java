/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.ContributionContextGroup;
import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteStrategiesGroupCommand;
import ucm.scenario.ScenarioGroup;

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
        } else if (obj instanceof ScenarioGroup) {

            ScenarioGroup group = (ScenarioGroup) obj;
            if (group.getScenarios().size() == 0) {
                DeleteStrategiesGroupCommand deleteCommand = new DeleteStrategiesGroupCommand(group);
                return deleteCommand;
            } else
                return null;
        }  else if (obj instanceof ContributionContextGroup) {

            ContributionContextGroup group = (ContributionContextGroup) obj;
            if (group.getContribs().size() == 0) {
                DeleteStrategiesGroupCommand deleteCommand = new DeleteStrategiesGroupCommand(group);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}
