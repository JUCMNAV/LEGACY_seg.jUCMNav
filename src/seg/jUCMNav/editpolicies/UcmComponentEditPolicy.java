/*
 * Created on 2005-02-07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.DeleteNodeCommand;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.UcmDiagram;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UcmComponentEditPolicy extends ComponentEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parent = getHost().getParent().getModel();
		Object node = getHost().getModel();
		if(parent instanceof UcmDiagram && node instanceof Node){
			DeleteNodeCommand command = new DeleteNodeCommand();
			return command;
		}
		return super.createDeleteCommand(deleteRequest);
	}
}
