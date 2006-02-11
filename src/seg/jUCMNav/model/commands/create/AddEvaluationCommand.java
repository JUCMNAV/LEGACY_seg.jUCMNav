/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Evaluation;
import grl.EvaluationScenario;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * This command link a Evaluation to it IntentionalElement and Scenario
 * @author Jean-François Roy
 *
 */
public class AddEvaluationCommand extends Command implements JUCMNavCommand {

    private Evaluation evaluation;
    private IntentionalElement element;
    private EvaluationScenario scenario;
    
    /**
     * Constructor
     */
    public AddEvaluationCommand(Evaluation evaluation, IntentionalElement elem, EvaluationScenario scenario) {
        this.evaluation = evaluation;
        this.element = elem;
        this.scenario = scenario;
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if ((evaluation != null && element != null && scenario != null) && 
                (evaluation.getIntElement() == null) && (evaluation.getScenario() == null)){
            return true;
        } 
        return false;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        evaluation.setIntElement(element);
        evaluation.setScenario(scenario);
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert evaluation != null && element != null && scenario != null : "pre null"; //$NON-NLS-1$
        assert evaluation.getIntElement() != element && evaluation.getScenario() != scenario : "pre link set!"; //$NON-NLS-1$

    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert evaluation != null && element != null && scenario != null : "post null"; //$NON-NLS-1$
        assert !(evaluation.getIntElement() != element && evaluation.getScenario() != scenario) : "post link set"; //$NON-NLS-1$

    }
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        evaluation.setIntElement(null);
        evaluation.setScenario(null);
        testPreConditions();
    }
}
