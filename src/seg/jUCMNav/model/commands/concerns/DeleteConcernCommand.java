package seg.jUCMNav.model.commands.concerns;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.CleanRelationshipsCommand;
import urncore.Concern;

/**
 * CompoundCommand to delete a Concern (remove it from the model)
 * 
 * @author gunterm
 */
public class DeleteConcernCommand extends CompoundCommand {

    /**
     * @param concern
     *            to delete
     */
    public DeleteConcernCommand(Concern concern) {
        setLabel(Messages.getString("DeleteConcernCommand.DeleteConcern")); //$NON-NLS-1$
        // get rid of associations
        add(new CleanRelationshipsCommand(concern));
        // remove the concern itself
        add(new InternalDeleteConcernCommand(concern));
    }

}