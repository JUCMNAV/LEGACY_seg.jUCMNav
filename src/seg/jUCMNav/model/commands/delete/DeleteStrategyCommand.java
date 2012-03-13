/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.kpimodel.KPIInformationConfig;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveEvaluationStrategyCommand;

/**
 * This command delete a strategy. It generate command to delete all evaluations, and all kpiinformationconfigs and delete the strategy after.
 * 
 * @author Jean-François Roy, pchen, jkealey
 * 
 */
public class DeleteStrategyCommand extends CompoundCommand {

    public DeleteStrategyCommand(EvaluationStrategy strategy) {
        setLabel(Messages.getString("DeleteEvaluationStrategyCommand.deleteEvaluationStrategy")); //$NON-NLS-1$

        for (Iterator iter = strategy.getEvaluations().iterator(); iter.hasNext();) {
            Evaluation eval = (Evaluation) iter.next();
            add(new DeleteEvaluationCommand(eval));
        }

        for (Iterator iter = strategy.getKpiInfoConfig().iterator(); iter.hasNext();) {
            KPIInformationConfig config = (KPIInformationConfig) iter.next();
            add(new DeleteKPIInformationConfigCommand(config));
        }
        
        for (Iterator iter = strategy.getIncludedStrategies().iterator(); iter.hasNext();) {
            EvaluationStrategy child = (EvaluationStrategy) iter.next();
            add(new DeleteIncludedStrategyCommand(strategy, child));
        }
        for (Iterator iter = strategy.getParentStrategies().iterator(); iter.hasNext();) {
            EvaluationStrategy parent = (EvaluationStrategy) iter.next();
            add(new DeleteIncludedStrategyCommand(parent, strategy));
        }

        add(new RemoveEvaluationStrategyCommand(strategy));
    }

}
