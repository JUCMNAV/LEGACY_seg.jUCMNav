package seg.jUCMNav.editpolicies.element;

import grl.ActorRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteActorRefCommand;

/**
 * Component edit policy for ActorRef. This policy return the delete command
 * 
 * @author Jean-François Roy
 * 
 */
public class ActorRefComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteComponentRefCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof ActorRef) {
            DeleteActorRefCommand deleteCommand = new DeleteActorRefCommand((ActorRef) comp);
            return deleteCommand;
        }

        return null;
    }
}
