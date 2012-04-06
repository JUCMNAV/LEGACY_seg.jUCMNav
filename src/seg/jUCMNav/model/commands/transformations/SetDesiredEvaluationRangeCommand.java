package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import urn.URNspec;

/**
 * Changes a URN model's range from [0,100] to [-100,100] and back again.
 * 
 * @author jkealey
 */
public class SetDesiredEvaluationRangeCommand extends Command implements JUCMNavCommand {
    private URNspec urn;

    private boolean use0to100EvaluationRange = false;

    private boolean olduse0to100EvaluationRange = false;

    
    /**
     * Toggle the value. 
     * @param urn
     */
    public SetDesiredEvaluationRangeCommand(URNspec urn)
    {
        this.urn = urn;
        this.use0to100EvaluationRange = !StrategyEvaluationRangeHelper.getCurrentRange(urn); 
        setLabel(Messages.getString("SetDesiredEvaluationRangeCommand.SetEvaluationRange")); //$NON-NLS-1$
    }
    /**
     * Set the value. 
     * @param urn
     * @param use0to100EvaluationRange
     */
    public SetDesiredEvaluationRangeCommand(URNspec urn, boolean use0to100EvaluationRange) {
        this.urn = urn;
        this.use0to100EvaluationRange = use0to100EvaluationRange;

        setLabel(Messages.getString("SetDesiredEvaluationRangeCommand.SetEvaluationRange")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        olduse0to100EvaluationRange = StrategyEvaluationRangeHelper.getCurrentRange(urn);

        redo();
    }

    public URNspec getUrn() {
        return urn;
    }
    public boolean isUse0to100EvaluationRange() {
        return use0to100EvaluationRange;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        StrategyEvaluationRangeHelper.setCurrentRange(urn, use0to100EvaluationRange);

        testPostConditions();
    }

    public void setUrn(URNspec urn) {
        this.urn = urn;
    }

    public void setUse0to100EvaluationRange(boolean use0to100EvaluationRange) {
        this.use0to100EvaluationRange = use0to100EvaluationRange;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null : "post is null!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null : "pre is null"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        StrategyEvaluationRangeHelper.setCurrentRange(urn,  olduse0to100EvaluationRange);
        testPreConditions();
    }

}