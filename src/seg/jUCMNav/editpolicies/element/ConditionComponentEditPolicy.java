package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteConditionCommand;
import urncore.Condition;

/**
 * ComponentEditPolicy for UCM Conditions. Returns delete commands.
 * 
 * @author Jordan
 */
public class ConditionComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a  DeleteConditionCommand
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        DeleteConditionCommand deleteCommand = new DeleteConditionCommand((Condition) getHost().getModel());
        return deleteCommand;
    }
}