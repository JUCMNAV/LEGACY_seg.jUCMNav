/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import ucm.scenario.Initialization;

/**
 * Command to delete a Variable Initialization
 * 
 * @author jkealey
 *
 */
public class DeleteVariableInitializationCommand extends CompoundCommand {

    /**
     * @param init
     *          the Initialization to delete
     */
    public DeleteVariableInitializationCommand(Initialization init) {
        setLabel("Delete Variable Initialization");
        add(new PreDeleteUrnModelElementCommand(init));
        add(new RemoveURNmodelElementCommand(init));
    }



}
