package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveTimepointCommand;
import urn.dyncontext.Timepoint;

/**
 * Command to delete the selected timepoint
 * 
 * @author aprajita
 * 
 */
public class DeleteTimepointCommand extends CompoundCommand {
	
	public DeleteTimepointCommand(Timepoint tp) {
        setLabel(Messages.getString("DeleteTimepointCommand.DeleteTimepoint")); //$NON-NLS-1$
        add(new RemoveTimepointCommand(tp));
    }

}
