package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteScenarioPathNodeCommand;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * ComponentEditPolicy for ScenarioStartPoints and ScenarioEndPoints. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class ScenarioPathNodeComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteVariableCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof ScenarioStartPoint) {

            ScenarioStartPoint pt = (ScenarioStartPoint) obj;
            DeleteScenarioPathNodeCommand deleteCommand = new DeleteScenarioPathNodeCommand(pt);
            return deleteCommand;
        } else if (obj instanceof ScenarioEndPoint) {

            ScenarioEndPoint pt = (ScenarioEndPoint) obj;
            DeleteScenarioPathNodeCommand deleteCommand = new DeleteScenarioPathNodeCommand(pt);
            return deleteCommand;
        }

        return null;
    }
}
