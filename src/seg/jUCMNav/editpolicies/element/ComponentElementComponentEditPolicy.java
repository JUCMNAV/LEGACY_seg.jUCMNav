package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteComponentElementCommand;
import seg.jUCMNav.model.commands.delete.RemoveResourceFromComponentCommand;
import seg.jUCMNav.model.wrappers.ComponentTreeWrapper;
import urncore.ComponentElement;

/**
 * Component Edit Policy for URN Component Elements. Component Edit policies return delete commands.
 * 
 * @author jkealey
 */
public class ComponentElementComponentEditPolicy extends ComponentEditPolicy {

    ComponentTreeWrapper wrapper;
    
    public ComponentElementComponentEditPolicy(ComponentTreeWrapper wrapper) {
        this.wrapper=wrapper;    
    }
    
    /**
     * Return a DeleteComponentElementCommand or a RemoveResourceFromComponenCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof ComponentElement) {
            ComponentElement elem = (ComponentElement) comp;
            
            if (wrapper==null) {
                if (elem.getContRefs().size() == 0) {
                    DeleteComponentElementCommand deleteCommand = new DeleteComponentElementCommand(elem);
                    return deleteCommand;
                } else
                    return null;
            } else {
                RemoveResourceFromComponentCommand deleteCommand = new RemoveResourceFromComponentCommand(wrapper.getComp(), wrapper.getResx());
                return deleteCommand;
            }
        }

        return null;
    }
}