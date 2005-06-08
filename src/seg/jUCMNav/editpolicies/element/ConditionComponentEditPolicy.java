/*
 * Created on Jun 1, 2005
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteConditionCommand;
import urncore.Condition;

/**
 * @author Jordan
 */
public class ConditionComponentEditPolicy extends ComponentEditPolicy {
	
	public ConditionComponentEditPolicy() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		DeleteConditionCommand deleteCommand = new DeleteConditionCommand((Condition) getHost().getModel());
		return deleteCommand;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
	    return super.getCommand(request);
	}
}
