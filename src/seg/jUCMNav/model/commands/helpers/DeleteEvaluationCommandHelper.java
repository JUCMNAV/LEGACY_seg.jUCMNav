package seg.jUCMNav.model.commands.helpers;

import fm.Feature;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;

public class DeleteEvaluationCommandHelper implements Command {

    
    private Evaluation evaluation;
    private EvaluationStrategy strategy;
    private IntentionalElement intentional;

    /**
     * 
     */
    public DeleteEvaluationCommandHelper(Evaluation eval) {
        this.evaluation = eval;
   //     setLabel(Messages.getString("DeleteEvaluationCommand.deleteEvaluation")); //$NON-NLS-1$
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

    @Override
    public boolean canExecute() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
