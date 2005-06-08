/*
 * Created on 2005-02-07
 *
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import ucm.map.ComponentRef;

/**
 * @author Etienne Tremblay
 */
public class ComponentRefEditPolicy extends ComponentEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		Object comp = getHost().getModel();
		if(comp instanceof ComponentRef) {
			DeleteComponentRefCommand deleteCommand = new DeleteComponentRefCommand((ComponentRef)comp);
			return deleteCommand;
		}

		return null;
	}
}
