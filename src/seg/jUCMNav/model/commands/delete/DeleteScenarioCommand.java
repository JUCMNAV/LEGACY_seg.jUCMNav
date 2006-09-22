/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveScenarioCommand;
import ucm.scenario.ScenarioDef;

/**
 * This command delete a scenario. It generate command to delete 
 * all initializations, pre-conditions, post-conditions, relations with parent scenarios and delete the scenario after.
 * 
 * @author jkealey
 *
 */
public class DeleteScenarioCommand extends CompoundCommand {

	private ScenarioDef scenario;
    public DeleteScenarioCommand(ScenarioDef scenario) {
        setLabel("Delete Scenario");  
        
        // TODO: delete all relationships
        for (Iterator iter = scenario.getIncludedScenarios().iterator(); iter.hasNext();) {
			ScenarioDef child = (ScenarioDef) iter.next();
			add(new DeleteIncludedScenarioCommand(scenario, child));
		}
        for (Iterator iter = scenario.getParentScenarios().iterator(); iter.hasNext();) {
			ScenarioDef parent = (ScenarioDef) iter.next();
			add(new DeleteIncludedScenarioCommand(parent, scenario));
		}        
        add(new RemoveScenarioCommand(scenario));
        this.scenario = scenario;
        
        
    }


}
