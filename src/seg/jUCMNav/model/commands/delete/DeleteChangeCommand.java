package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveChangeCommand;
import urn.dyncontext.Change;

/**
 * Command to delete a Change
 * 
 * @author aprajita
 * 
 */
public class DeleteChangeCommand extends CompoundCommand {
	
	/**
	 * 
	 * @param changeToDelete
	 * 						The Change that needs to be deleted
	 */
	public DeleteChangeCommand(Change changeToDelete) {
        setLabel(Messages.getString("DeleteChangeCommand.DeleteChange")); //$NON-NLS-1$

        add(new RemoveChangeCommand(changeToDelete));
    }


}
