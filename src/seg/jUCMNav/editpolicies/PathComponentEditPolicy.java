/*
 * Created on 2005-02-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * Created 2005-02-22
 * 
 * @author Etienne Tremblay
 */
public class PathComponentEditPolicy extends ComponentEditPolicy {

	/**
	 * 
	 */
	public PathComponentEditPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		// TODO Auto-generated method stub
		return super.createDeleteCommand(deleteRequest);
	}
}
