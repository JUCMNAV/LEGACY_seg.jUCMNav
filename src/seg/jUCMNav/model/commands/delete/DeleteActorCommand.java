/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Actor;
import grl.ActorRef;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveActorCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import urn.URNlink;

/**
 * Delete an actor definition.
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteActorCommand extends CompoundCommand {

    private Actor actor;

    /**
     * @param actor
     *            the ActorRef to delete
     */
    public DeleteActorCommand(Actor actor) {
        setLabel(Messages.getString("DeleteActorCommand.deleteActor")); //$NON-NLS-1$
        this.actor = actor;
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Build the compound command.
     * 
     */
    private void build() {
        // Verify if the definition can be delete.
        if (actor.getContRefs().size() == 0 || DeletePreferences.getDeleteReference(actor)) {
            // Remove all the actor references
            for (Iterator it = actor.getContRefs().iterator(); it.hasNext();) {
                ActorRef actorRef = (ActorRef) it.next();
                add(new PreDeleteUrnModelElementCommand(actorRef));
                add(new RemoveURNmodelElementCommand(actorRef));
            }

            // Remove the URNlinks
            for (Iterator it = actor.getFromLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            for (Iterator it = actor.getToLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            add(new RemoveActorCommand(actor));
        }
    }

}
