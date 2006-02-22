/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Evaluation;
import grl.EvaluationStrategy;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.RemoveEvaluationStrategyCommand;

/**
 * This command delete a strategy. It generate command to delete 
 * all evaluations and delete the strategy after.
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteEvaluationStrategyCommand extends CompoundCommand {

    public DeleteEvaluationStrategyCommand(EvaluationStrategy strategy) {
        setLabel("DeleteEvaluationStrategyCommand"); 
        
        for (Iterator iter = strategy.getEvaluations().iterator(); iter.hasNext();){
            Evaluation eval = (Evaluation)iter.next();
            add(new DeleteEvaluationCommand(eval));
        }
        add(new RemoveEvaluationStrategyCommand(strategy));
    }

}
