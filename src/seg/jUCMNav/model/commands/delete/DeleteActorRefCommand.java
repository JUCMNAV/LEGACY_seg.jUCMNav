/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ActorRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;

/**
 * Command to delete a ActorRef
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteActorRefCommand extends CompoundCommand {

    /**
     * @param ar
     *          the ActorRef to delete
     */
    public DeleteActorRefCommand(ActorRef ar) {
        setLabel("DeleteActorRefCommand");
        add(new PreDeleteUrnModelElementCommand(ar));
        add(new RemoveURNmodelElementCommand(ar));
    }



}
