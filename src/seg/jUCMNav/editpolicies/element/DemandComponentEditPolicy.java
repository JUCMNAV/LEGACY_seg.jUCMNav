package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteDemandCommand;
import ucm.performance.Demand;

/**
 * Component Edit Policy for demands. Component Edit policies return delete commands.
 * 
 * @author jkealey
 */
public class DemandComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteDemandCommand.
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof Demand) {

            Demand elem = (Demand) obj;
            DeleteDemandCommand deleteCommand = new DeleteDemandCommand(elem);
            return deleteCommand;
        }

        return null;
    }
}