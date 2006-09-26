
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import urncore.Condition;

/**
 * Command to delete a Scenario Pre/Post Condition
 * 
 * @author jkealey
 *
 */
public class DeleteScenarioConditionCommand extends CompoundCommand {

    /**
     * @param cibd
     *          the Condition to delete
     */
    public DeleteScenarioConditionCommand(Condition cond) {
        setLabel("Delete Scenario Pre/Post Condition");
        add(new RemoveURNmodelElementCommand(cond));
    }




}
