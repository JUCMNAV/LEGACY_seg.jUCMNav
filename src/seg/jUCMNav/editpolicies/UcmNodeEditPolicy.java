/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import seg.jUCMNav.editparts.UcmNodeEditPart;
import seg.jUCMNav.model.ucm.Node;

/**
 * @author Etienne Tremblay
 *
 */
public class UcmNodeEditPolicy extends GraphicalNodeEditPolicy {

	private Node getNetworkNode(){
		return (Node)getHost().getModel();
	}
	
	private UcmNodeEditPart getNetworkNodeEditPart() {
		return (UcmNodeEditPart)getHost();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
	 */
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
	 */
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
	 */
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
	 */
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		return null;
	}

}
