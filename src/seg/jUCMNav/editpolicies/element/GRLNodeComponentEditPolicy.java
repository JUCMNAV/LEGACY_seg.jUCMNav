package seg.jUCMNav.editpolicies.element;

import grl.GRLNode;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteGRLNodeCommand;

/**
 * ComponentEditPolicy for GRLNode (Belief and IntentionalElementRef and KPIModelElementRef). Return the delete command
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class GRLNodeComponentEditPolicy extends ComponentEditPolicy {

    /**
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {

        GRLNode node = (GRLNode) getHost().getModel();
        return new DeleteGRLNodeCommand(node);
    }

}
