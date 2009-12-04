/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveLinkedInfoCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import ucm.scenario.Variable;

/**
 * Command to delete a Variable
 * 
 * @author jkealey
 * 
 */
public class DeleteVariableCommand extends CompoundCommand {

    /**
     * @param var
     *            the Variable to delete
     */
    public DeleteVariableCommand(Variable var) {
        setLabel(Messages.getString("DeleteVariableCommand.DeleteVariable")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(var));
        add(new RemoveURNmodelElementCommand(var));
        // we are doing this here, after removing it, because we have a listener on ucmspec that needs to know the enumeration type when doing an undo
        add(new RemoveLinkedInfoCommand(var));

    }

}
