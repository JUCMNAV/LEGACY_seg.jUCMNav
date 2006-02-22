/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.EvaluationGroup;
import grl.EvaluationStrategy;
import grl.GRLspec;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * @author Jean-François Roy
 *
 */
public class RemoveEvaluationStrategyCommand extends Command implements JUCMNavCommand {

    private EvaluationStrategy strategy;
    private EvaluationGroup group;
    private GRLspec grl;
    
    /**
     * 
     */
    public RemoveEvaluationStrategyCommand(EvaluationStrategy strategy) {
        this.strategy = strategy;
        setLabel("RemoveEvaluationStrategyCommand");       
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        grl = strategy.getGrlspec();
        group = strategy.getGroup();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        EvaluationGroup element;
        
        group.getStrategies().remove(strategy);
        
        grl.getStrategies().remove(strategy);

        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert grl != null && strategy != null && group !=null: "pre something null"; //$NON-NLS-1$
        assert grl.getStrategies().contains(strategy): "pre strategy in grl"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert grl != null && strategy != null && group !=null: "post something null"; //$NON-NLS-1$
        assert !grl.getStrategies().contains(strategy): "post strategy in grl"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        group.getStrategies().add(strategy);
        grl.getStrategies().add(strategy);
                
        testPreConditions();
    }
}
