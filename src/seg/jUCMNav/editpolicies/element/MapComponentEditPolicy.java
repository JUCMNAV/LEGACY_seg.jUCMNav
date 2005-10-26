package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import ucm.map.UCMmap;

/**
 * ComponentEditPolicy for UCM Maps. Returns delete commands.
 * 
 * @author jkealey
 */
public class MapComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteMapCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof UCMmap)
            return new DeleteMapCommand((UCMmap) elem);
        else
            return null;
    }
}