package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
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
     * @param cond
     *            the Condition to delete
     */
    public DeleteScenarioConditionCommand(Condition cond) {
        setLabel(Messages.getString("DeleteScenarioConditionCommand.DeleteScenarioPrePostCondition")); //$NON-NLS-1$
        add(new RemoveURNmodelElementCommand(cond));
    }

}
