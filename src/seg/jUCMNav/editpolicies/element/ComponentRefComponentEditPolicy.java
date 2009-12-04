package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import ucm.map.ComponentRef;

/**
 * 
 * ComponentEditPolicy for UCM ComponentRefs. ComponentEditPolicies return delete commands.
 * 
 * @author jkealey
 */
public class ComponentRefComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteComponentRefCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof ComponentRef) {
            DeleteComponentRefCommand deleteCommand = new DeleteComponentRefCommand((ComponentRef) comp);
            return deleteCommand;
        }

        return null;
    }
}