/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
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
     * @param ar
     *          the Variable to delete
     */
    public DeleteVariableCommand(Variable var) {
        setLabel("Delete Variable");
        add(new PreDeleteUrnModelElementCommand(var));
        add(new RemoveURNmodelElementCommand(var));
    }



}
