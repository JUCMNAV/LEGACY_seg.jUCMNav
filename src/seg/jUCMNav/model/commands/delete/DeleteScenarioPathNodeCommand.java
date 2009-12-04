/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * Command to delete a ScenarioStartPoint/ScenarioEndPoint
 * 
 * @author jkealey
 * 
 */
public class DeleteScenarioPathNodeCommand extends CompoundCommand {

    /**
     * @param var
     *            the ScenarioStartPoint to delete
     */
    public DeleteScenarioPathNodeCommand(ScenarioStartPoint var) {
        setLabel(Messages.getString("DeleteScenarioPathNodeCommand.DeleteScenarioStartPoint")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(var));
        add(new RemoveURNmodelElementCommand(var));
    }

    /**
     * @param var
     *            the ScenarioStartPoint to delete
     */
    public DeleteScenarioPathNodeCommand(ScenarioEndPoint var) {
        setLabel(Messages.getString("DeleteScenarioPathNodeCommand.DeleteScenarioEndPoint")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(var));
        add(new RemoveURNmodelElementCommand(var));
    }

}
