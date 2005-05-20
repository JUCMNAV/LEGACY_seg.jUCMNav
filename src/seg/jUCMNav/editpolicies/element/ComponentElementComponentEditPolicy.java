/*
 * Created on 2005-02-07
 *
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteComponentElementCommand;
import urncore.ComponentElement;

/**
 * @author jkealey
 */
public class ComponentElementComponentEditPolicy extends ComponentEditPolicy {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof ComponentElement) {

            ComponentElement elem = (ComponentElement) comp;
            if (elem.getCompRefs().size() == 0) {
                DeleteComponentElementCommand deleteCommand = new DeleteComponentElementCommand(elem);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}