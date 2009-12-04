package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteNodeConnectionCommand;
import ucm.map.NodeConnection;

/**
 * ComponentEditPolicy for UCM NodeConnections. Returns DeleteNodeConnectionCommands
 * 
 * @author jkealey
 * 
 */
public class NodeConnectionComponentEditPolicy extends ComponentEditPolicy {
    /**
     * Returns a CompoundCommand disconnecting the node connection from its extremities, if possible.
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {

        NodeConnection nc = (NodeConnection) getHost().getModel();

        return new DeleteNodeConnectionCommand(nc, getHost().getViewer().getEditPartRegistry());
    }
}