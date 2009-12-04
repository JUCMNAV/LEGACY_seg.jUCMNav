package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteScenarioConditionCommand;
import urncore.Condition;

/**
 * ComponentEditPolicy for Scenario Conditions. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class ScenarioConditionComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteVariableCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof Condition) {

            Condition var = (Condition) obj;
            DeleteScenarioConditionCommand deleteCommand = new DeleteScenarioConditionCommand(var);
            return deleteCommand;
        }

        return null;
    }
}
