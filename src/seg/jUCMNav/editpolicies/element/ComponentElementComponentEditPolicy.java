package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteComponentElementCommand;
import urncore.ComponentElement;

/**
 * Component Edit Policy for URN Component Elements. Component Edit policies return delete commands.
 * 
 * @author jkealey
 */
public class ComponentElementComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteComponentElementCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof ComponentElement) {

            ComponentElement elem = (ComponentElement) comp;
            if (elem.getContRefs().size() == 0) {
                DeleteComponentElementCommand deleteCommand = new DeleteComponentElementCommand(elem);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}