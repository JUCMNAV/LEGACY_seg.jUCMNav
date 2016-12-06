/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Actor;
import grl.ActorRef;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;

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
    
    /**
     * Deletes all the changes associated with the selected ActorRef
     */
    private void deleteChanges() {
    	Actor actor = (Actor) actorRef.getContDef();
    	for (Iterator it = (actor.getGrlspec().getUrnspec().getDynamicContexts().iterator()); it.hasNext();) {
            DynamicContext dyn = (DynamicContext) it.next();
            
            //Delete Actor Changes
            for (Iterator itEval = DynamicContextsUtils.getAllAvailableChanges(actorRef, dyn, actor.getGrlspec().getUrnspec()).iterator(); itEval.hasNext();) {
                Change change = (Change) itEval.next();
                add(new DeleteChangeCommand(change));                
            }
        }
    }

    public void execute() {
        // Verify if this reference is the only one
    	if (actorRef.getContDef().getContRefs().size() <= 1) {
    		
    		//Delete the changes everytime all the references are deleted
        	deleteChanges();
        	
        	if (DeletePreferences.getDeleteDefinition(actorRef))
        		add(new DeleteActorCommand((Actor) actorRef.getContDef()));
        }
        super.execute();
    }

}
