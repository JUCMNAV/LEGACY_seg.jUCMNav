/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Evaluation;
import grl.EvaluationScenario;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.EvaluationScenarioManager;

/**
 * This command delete an GRL evaluation
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteEvaluationCommand extends Command implements JUCMNavCommand {

    private Evaluation evaluation;
    private EvaluationScenario scenario;
    private IntentionalElement intentional;
    
    /**
     * 
     */
    public DeleteEvaluationCommand(Evaluation eval) {
        this.evaluation = eval;
        setLabel("DeleteEvaluationCommand");
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        scenario = evaluation.getScenario();
        intentional = evaluation.getIntElement();
        redo();
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        //Remove the evaluation object from the EvaluationManager to calculate the new value
        EvaluationScenarioManager.getInstance().setEvaluationForElement(intentional,
                (Evaluation)ModelCreationFactory.getNewObject(scenario.getGrlspec().getUrnspec(), Evaluation.class));

        evaluation.setScenario(null);
        evaluation.setIntElement(null);

        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert evaluation != null && scenario != null && intentional != null: "pre something is null"; //$NON-NLS-1$
        assert scenario.getEvaluations().contains(evaluation) : "pre evaluation in scenario"; //$NON-NLS-1$
        assert intentional.getEvals().contains(evaluation): "pre evaluation in intentional element"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert evaluation != null && scenario != null && intentional != null: "post something is null"; //$NON-NLS-1$
        assert !scenario.getEvaluations().contains(evaluation) : "post evaluation in scenario"; //$NON-NLS-1$
        assert !intentional.getEvals().contains(evaluation): "post evaluation in intentional element"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        //Set the evaluation object from the EvaluationManager
        EvaluationScenarioManager.getInstance().setEvaluationForElement(intentional, evaluation);

        evaluation.setScenario(scenario);
        evaluation.setIntElement(intentional);

        testPreConditions();
    }
}
