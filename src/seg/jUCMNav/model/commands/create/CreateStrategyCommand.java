/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Creates a new strategy.
 * 
 * @author Jean-Fran�ois Roy
 * 
 */
public class CreateStrategyCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private StrategiesGroup group;
    private EvaluationStrategy strategy;

    /**
     * 
     */
    public CreateStrategyCommand(URNspec urn, StrategiesGroup group) {
        this.urn = urn;
        this.group = group;
        strategy = (EvaluationStrategy) ModelCreationFactory.getNewObject(urn, EvaluationStrategy.class);
        setLabel(Messages.getString("CreateStrategyCommand.createStrategy")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
    }

    public EvaluationStrategy getStrategy() {
        return strategy;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getGrlspec().getStrategies().add(strategy);
        group.getStrategies().add(strategy);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && strategy != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !group.getStrategies().contains(strategy) : "pre strategy in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && strategy != null && group != null : "post not null"; //$NON-NLS-1$
        assert group.getStrategies().contains(strategy) : "post strategy not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getGrlspec().getStrategies().remove(strategy);
        group.getStrategies().remove(strategy);
        testPreConditions();
    }
}
