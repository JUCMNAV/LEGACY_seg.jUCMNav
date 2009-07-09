/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.Map;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.PathNode;
import ucm.map.RespRef;

/**
 * This command is only be called from the edit part. 
 * 
 * If the path node is a responsibility, and there is no more references,
 * depending of the preferences, it might also delete the definition.
 * 
 * @author jfroy
 *
 */
public class DeletePathNodeCommand extends CompoundCommand {

	private PathNode pathNode;
	private Map editPartRegistry;
	
	public DeletePathNodeCommand(PathNode pn, java.util.Map editpartregistry)
	{
		this.pathNode = pn;
		this.editPartRegistry = editpartregistry;
		setLabel(Messages.getString("DeletePathNodeCommand.deletePathNode"));
		
	}
	
    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0) {
            return true;
        }
        return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }
    
    /**
     * Late building
     */
    public void execute() {
    	build();
        super.execute();
    }

    /**
     * Builds a sequence of DeleteGRLNodeCommands
     * 
     */
	private void build() {

		add(new RemovePathNodeCommand(pathNode, editPartRegistry));
		
        //Verify if it is a responsibility and if the definition can be delete.
        if(pathNode instanceof RespRef
        		&& (((RespRef)pathNode).getRespDef().getRespRefs().size() == 0 ||
        		DeletePreferences.getDeleteDefinition(pathNode)))
        {
        	add(new DeleteResponsibilityCommand(((RespRef)pathNode).getRespDef()));
        } 

	}
}
