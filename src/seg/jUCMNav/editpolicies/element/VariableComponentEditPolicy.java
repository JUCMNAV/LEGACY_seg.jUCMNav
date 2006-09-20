/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteVariableCommand;
import ucm.scenario.Variable;

/**
 * ComponentEditPolicy for Variables. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class VariableComponentEditPolicy extends ComponentEditPolicy {

	/**
	 * Return a DeleteVariableCommand
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		Object obj = getHost().getModel();
		if (obj instanceof Variable) {

			Variable var = (Variable) obj;
			DeleteVariableCommand deleteCommand = new DeleteVariableCommand(var);
			return deleteCommand;
		}

		return null;
	}
}
