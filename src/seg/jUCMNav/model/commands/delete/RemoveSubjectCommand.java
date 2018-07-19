/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import asd.ASDiagram;
import asd.Subject;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;
import org.eclipse.gef.tools.DirectEditManager;

/**
 * Delete an actor definition.
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveSubjectCommand extends CompoundCommand implements IDelayedBuildCompoundCommand {

    public Subject subject;
    public  URNspec spec;
    public ASDiagram asDiagram;
    

    /**
     * @param actor
     *            the ActorRef to delete
     */
    public RemoveSubjectCommand(URNspec spec, Subject subject, ASDiagram asDiagram) {
        setLabel(Messages.getString("RemoveSubjectCommand.removeSubject")); //$NON-NLS-1$
        this.subject=subject;
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
            for (Iterator itEval = DynamicContextsUtils.getAllAvailableChanges(subject, dyn, spec).iterator(); itEval.hasNext();) {
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
            
            add(new DeleteSubjectCommand(spec,subject,asDiagram));
           super.execute();
    }

}
