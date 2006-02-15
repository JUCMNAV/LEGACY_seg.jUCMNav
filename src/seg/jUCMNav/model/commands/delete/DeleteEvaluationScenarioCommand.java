/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Evaluation;
import grl.EvaluationScenario;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveEvaluationScenarioCommand;

/**
 * This command delete a scenario. It generate command to delete 
 * all evaluations and delete the scenario after.
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteEvaluationScenarioCommand extends CompoundCommand {

    public DeleteEvaluationScenarioCommand(EvaluationScenario scenario) {
        setLabel("DeleteEvaluationScenarioCommand"); 
        
        for (Iterator iter = scenario.getEvaluations().iterator(); iter.hasNext();){
            Evaluation eval = (Evaluation)iter.next();
            add(new DeleteEvaluationCommand(eval));
        }
        add(new RemoveEvaluationScenarioCommand(scenario));
    }

}
