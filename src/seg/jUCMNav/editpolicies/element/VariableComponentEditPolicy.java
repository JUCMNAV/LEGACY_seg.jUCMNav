package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteEnumerationTypeCommand;
import seg.jUCMNav.model.commands.delete.DeleteVariableCommand;
import seg.jUCMNav.model.commands.delete.DeleteVariableInitializationCommand;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.Variable;

/**
 * ComponentEditPolicy for Variables / Variable Initializations. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class VariableComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteVariableCommand / DeleteVariableInitializationCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof Variable) {

            Variable var = (Variable) obj;
            DeleteVariableCommand deleteCommand = new DeleteVariableCommand(var);
            return deleteCommand;
        } else if (obj instanceof Initialization) {
            Initialization initialization = (Initialization) obj;
            DeleteVariableInitializationCommand command = new DeleteVariableInitializationCommand(initialization);
            return command;
        } else if (obj instanceof EnumerationType) {
            EnumerationType enumtype = (EnumerationType) obj;
            if (enumtype.getInstances().size() == 0) {
                DeleteEnumerationTypeCommand command = new DeleteEnumerationTypeCommand(enumtype);
                return command;
            } else
                return null;
        }

        return null;
    }
}
