/*
 * Created on 2005-02-07
 *
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * @author Etienne Tremblay
 */
public class ComponentRefEditPolicy extends ComponentEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
//		Object parent = getHost().getParent().getModel();
//		Object node = getHost().getModel();
//		if(parent instanceof PathGraph && node instanceof PathNode){
//			DeleteNodeCommand command = new DeleteNodeCommand();
//			return command;
//		}
		return super.createDeleteCommand(deleteRequest);
	}
}
