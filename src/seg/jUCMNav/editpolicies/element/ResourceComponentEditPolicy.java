package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteGeneralResourceCommand;
import ucm.performance.GeneralResource;

/**
 * Component Edit Policy for resources. Component Edit policies return delete commands.
 * 
 * @author jkealey
 */
public class ResourceComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteResourceCommand.
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof GeneralResource) {

            GeneralResource elem = (GeneralResource) obj;
            // if (elem.getDemands().size() == 0) {
            DeleteGeneralResourceCommand deleteCommand = new DeleteGeneralResourceCommand(elem);
            return deleteCommand;
            // } else
            // return null;
        }

        return null;
    }
}