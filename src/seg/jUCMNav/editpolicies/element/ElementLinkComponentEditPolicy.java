package seg.jUCMNav.editpolicies.element;

import grl.ElementLink;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteElementLinkCommand;

/**
 * ComponentEditPolicy for ElementLink. Used in the outline, and return the command to delete an ElementLink
 * 
 * @author Jean-François Roy
 * 
 */
public class ElementLinkComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteElementLinkCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof ElementLink) {

            ElementLink link = (ElementLink) elem;
            DeleteElementLinkCommand deleteCommand = new DeleteElementLinkCommand(link);
            return deleteCommand;

        }

        return null;
    }

}
