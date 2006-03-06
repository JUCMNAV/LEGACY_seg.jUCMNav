package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import ucm.map.ComponentRef;

/**
 * Command to delete a ComponentRef. (Remove it from the model).
 * 
 * @author jkealey
 * 
 */
public class DeleteComponentRefCommand extends CompoundCommand {

    /**
     * 
     * @param cr
     *            the component reference to delete.
     */
    public DeleteComponentRefCommand(ComponentRef cr) {
        setLabel(Messages.getString("DeleteComponentRefCommand.deleteComponentRef")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(cr));
        add(new RemoveURNmodelElementCommand(cr));
    }
}