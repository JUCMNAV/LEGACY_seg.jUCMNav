/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import asd.ASDiagram;
import asd.Aim;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import urn.URNspec;

/**
 * Delete an actor definition.
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveAimCommand extends CompoundCommand implements IDelayedBuildCompoundCommand {

    public Aim aim;
    public  URNspec spec;
    public ASDiagram asDiagram;
    

    /**
     * @param actor
     *            the ActorRef to delete
     */
    public RemoveAimCommand(URNspec spec, Aim aim, ASDiagram asDiagram) {
        setLabel(Messages.getString("RemoveAimCommand.removeaim")); //$NON-NLS-1$
        this.aim=aim;
        this.spec=spec;
        this.asDiagram=asDiagram;
       
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
       /* if (getCommands().size() == 0)
            return true;
        else*/
          //  return super.canExecute();
    	
    	return true;
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
       /* if (getCommands().size() == 0)
            return true;
        else*/
          //  return super.canUndo();
    	
    	return true;
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }
    
    /**
     * Deletes all the changes associated with the selected Actor
     */
    public  void deleteChanges() {
    	/*for (Iterator it = (spec.getDynamicContexts().iterator()); it.hasNext();) {
            DynamicContext dyn = (DynamicContext) it.next();
            
            //Delete Actor Changes
            for (Iterator itEval = DynamicContextsUtils.getAllAvailableChanges(aim, dyn, spec).iterator(); itEval.hasNext();) {
                Change change = (Change) itEval.next();
                add(new DeleteChangeCommand(change));                
            }
        }*/
    }

    /**
     * Build the compound command.
     * 
     */
    public void build() {
        // Verify if the definition can be delete.
       
           // deleteChanges();
            
            add(new DeleteAimCommand(spec,aim,asDiagram));
           super.execute();
    }

}
