package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteResponsibilityCommand;
import urncore.Responsibility;

/**
 * ComponentEditPolicy for URN Responsibilities. Returns delete commands.
 * 
 * @author jkealey
 */
public class ResponsibilityComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteResponsibilityCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object resp = getHost().getModel();
        if (resp instanceof Responsibility) {

            Responsibility elem = (Responsibility) resp;
            DeleteResponsibilityCommand deleteCommand = new DeleteResponsibilityCommand(elem);
            return deleteCommand;
        }
        return null;
    }
}