package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteComponentCommand;
import seg.jUCMNav.model.commands.delete.RemoveResourceFromComponentCommand;
import seg.jUCMNav.model.wrappers.ComponentTreeWrapper;
import urncore.Component;

/**
 * Component Edit Policy for URN Component Elements. Component Edit policies return delete commands.
 * 
 * @author jkealey
 */
public class ComponentComponentEditPolicy extends ComponentEditPolicy {

    ComponentTreeWrapper wrapper;

    public ComponentComponentEditPolicy(ComponentTreeWrapper wrapper) {
        this.wrapper = wrapper;
    }

    /**
     * Return a DeleteComponentCommand or a RemoveResourceFromComponenCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof Component) {
            Component elem = (Component) comp;

            if (wrapper == null) {
                DeleteComponentCommand deleteCommand = new DeleteComponentCommand(elem);
                return deleteCommand;
            } else {
                RemoveResourceFromComponentCommand deleteCommand = new RemoveResourceFromComponentCommand(wrapper.getComp(), wrapper.getResx());
                return deleteCommand;
            }
        }

        return null;
    }
}