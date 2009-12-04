/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * This command link a Evaluation to it IntentionalElement and Strategy
 * 
 * @author Jean-François Roy
 * 
 */
public class AddEvaluationCommand extends Command implements JUCMNavCommand {

    private Evaluation evaluation;
    private IntentionalElement element;
    private EvaluationStrategy strategy;

    /**
     * Constructor
     */
    public AddEvaluationCommand(Evaluation evaluation, IntentionalElement elem, EvaluationStrategy strategy) {
        this.evaluation = evaluation;
        this.element = elem;
        this.strategy = strategy;

        setLabel(Messages.getString("AddEvaluationCommand.addEvaluation")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if ((evaluation != null && element != null && strategy != null) && (evaluation.getIntElement() == null) && (evaluation.getStrategies() == null)) {
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
        strategy.getEvaluations().add(evaluation);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert evaluation != null && element != null && strategy != null : "pre null"; //$NON-NLS-1$
        assert evaluation.getIntElement() != element && evaluation.getStrategies() != strategy : "pre link set!"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert evaluation != null && element != null && strategy != null : "post null"; //$NON-NLS-1$
        assert !(evaluation.getIntElement() != element && evaluation.getStrategies() != strategy) : "post link set"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        strategy.getEvaluations().remove(evaluation);
        evaluation.setIntElement(null);
        testPreConditions();
    }
}
