/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import ucm.scenario.EnumerationType;

/**
 * Command to delete an EnumerationType
 * 
 * @author jkealey
 * 
 */
public class DeleteEnumerationTypeCommand extends CompoundCommand {

    /**
     * @param et
     *            the Variable to delete
     */
    public DeleteEnumerationTypeCommand(EnumerationType et) {
        setLabel(Messages.getString("DeleteEnumerationTypeCommand.DeleteEnumerationType")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(et));
        add(new RemoveURNmodelElementCommand(et));
    }

}
