/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Actor;
import grl.ActorRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;

/**
 * Command to delete a ActorRef
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteActorRefCommand extends CompoundCommand {

    private ActorRef actorRef;

    /**
     * @param ar
     *            the ActorRef to delete
     */
    public DeleteActorRefCommand(ActorRef ar) {
        this.actorRef = ar;
        setLabel(Messages.getString("DeleteActorRefCommand.deleteActorRef")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(ar));
        add(new RemoveURNmodelElementCommand(ar));
    }

    public void execute() {
        // Verify if this reference is the only one
        if (actorRef.getContDef().getContRefs().size() <= 1 && DeletePreferences.getDeleteDefinition(actorRef)) {
            add(new DeleteActorCommand((Actor) actorRef.getContDef()));
        }
        super.execute();
    }

}
