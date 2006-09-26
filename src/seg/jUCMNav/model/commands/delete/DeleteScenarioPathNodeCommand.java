/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

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
     *          the ScenarioStartPoint to delete
     */
    public DeleteScenarioPathNodeCommand(ScenarioStartPoint var) {
        setLabel("Delete Scenario Start Point");
        add(new PreDeleteUrnModelElementCommand(var));
        add(new RemoveURNmodelElementCommand(var));
    }

    /**
     * @param var
     *          the ScenarioStartPoint to delete
     */
    public DeleteScenarioPathNodeCommand(ScenarioEndPoint var) {
        setLabel("Delete Scenario End Point");
        add(new PreDeleteUrnModelElementCommand(var));
        add(new RemoveURNmodelElementCommand(var));
    }


}
