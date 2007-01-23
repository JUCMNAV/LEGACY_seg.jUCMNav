package seg.jUCMNav.editpolicies.element;

import grl.GRLGraph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteGRLGraphCommand;

/**
 * ComponentEditPolicy for a GRLGraph. Return the delete command
 * @author Jean-François Roy
 *
 */
public class GRLGraphComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteGRLGraphCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof GRLGraph)
            return new DeleteGRLGraphCommand((GRLGraph) elem);
        else
            return null;
    }

}
