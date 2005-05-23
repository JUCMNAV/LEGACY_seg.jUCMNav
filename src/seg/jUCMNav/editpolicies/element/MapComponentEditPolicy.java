/*
 * Created on 2005-05-23
 *
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import ucm.map.Map;

/**
 * @author jkealey
 */
public class MapComponentEditPolicy extends ComponentEditPolicy {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof Map)
            return new DeleteMapCommand((Map) elem);
        else
            return null;
    }
}