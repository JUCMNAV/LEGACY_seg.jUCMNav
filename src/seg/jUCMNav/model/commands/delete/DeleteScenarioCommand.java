/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

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
//        for (Iterator iter = strategy.getEvaluations().iterator(); iter.hasNext();){
//            Evaluation eval = (Evaluation)iter.next();
//            add(new DeleteEvaluationCommand(eval));
//        }
//        add(new RemoveEvaluationStrategyCommand(strategy));
        this.scenario = scenario;
        
        
    }
    public boolean canExecute() { return true; }
    public void execute() {
    	scenario.setGroup(null);
    }

}
