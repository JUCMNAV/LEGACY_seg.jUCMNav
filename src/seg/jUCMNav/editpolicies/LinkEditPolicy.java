/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * @author Etienne Tremblay
 *
 */
public class LinkEditPolicy extends ConnectionEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		return null;
	}

}
