/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Actor;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveActorCommand;
import urn.URNlink;

/**
 * Delete an actor definition. 
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteActorCommand extends CompoundCommand{

    /**
     * @param ar
     *          the ActorRef to delete
     */
    public DeleteActorCommand(Actor actor) {
        setLabel("Delete Actor");
        
        //Remove the URNlinks
        for (Iterator it = actor.getFromLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        for (Iterator it = actor.getToLinks().iterator(); it.hasNext();){
            URNlink link = (URNlink)it.next();
            add(new DeleteURNlinkCommand(link));
        }
        add(new RemoveActorCommand(actor));
    }

}
