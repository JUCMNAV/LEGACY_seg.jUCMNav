/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import fm.Feature;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;

/**
 * This command delete an GRL evaluation
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteEvaluationCommand extends Command implements JUCMNavCommand {

    private Evaluation evaluation;
    private EvaluationStrategy strategy;
    private IntentionalElement intentional;

    /**
     * 
     */
    public DeleteEvaluationCommand(Evaluation eval) {
        this.evaluation = eval;
        setLabel(Messages.getString("DeleteEvaluationCommand.deleteEvaluation")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        strategy = evaluation.getStrategies();
        intentional = evaluation.getIntElement();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // Remove the evaluation object from the EvaluationManager to calculate the new value
        EvaluationStrategyManager.getInstance().setEvaluationForElement(intentional,
                (Evaluation) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class));

        evaluation.setStrategies(null);
        evaluation.setIntElement(null);
        
        // if Feature Model Evaluation algo, the command being performed on a feature, and "Auto Select" option being enabled, then 
        // set the strategy again to force a refresh (addressed a problem where a mandatory child of the feature is not properly refreshed)
        if (EvaluationStrategyManager.getInstance().getEvaluationAlgorithm().getEvaluationType() == IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL && 
        		intentional instanceof Feature && StrategyEvaluationPreferences.getAutoSelectMandatoryFeatures()) {
			EvaluationStrategyManager.getInstance().setStrategy(EvaluationStrategyManager.getInstance().getEvaluationStrategy());
		}

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert evaluation != null && strategy != null && intentional != null : "pre something is null"; //$NON-NLS-1$
        assert strategy.getEvaluations().contains(evaluation) : "pre evaluation in strategy"; //$NON-NLS-1$
        assert evaluation.getIntElement().equals(intentional) : "pre evaluation in intentional element"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert evaluation != null && strategy != null && intentional != null : "post something is null"; //$NON-NLS-1$
        assert !strategy.getEvaluations().contains(evaluation) : "post evaluation in strategy"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        evaluation.setStrategies(strategy);
        evaluation.setIntElement(intentional);

        // Set the evaluation object from the EvaluationManager
        EvaluationStrategyManager.getInstance().setEvaluationForElement(intentional, evaluation);

        testPreConditions();
    }
}
