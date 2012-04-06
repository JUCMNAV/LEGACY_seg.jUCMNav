package seg.jUCMNav.model.commands.transformations;

import grl.Evaluation;
import grl.EvaluationRange;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Changes an Evaluation's EvaluationRange.
 * 
 * @author jkealey
 */
public class SetEvaluationRangeCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private boolean previouslyExisted = false;
    private int start = 0;
    private int end = 0;
    private int step = 1;
    private int oldStart = 0;
    private int oldEnd = 0;
    private int oldStep = 1;
    private Evaluation evaluation;

    private EvaluationRange range;

    private boolean shouldDelete = false;

    /**
     * Deletes the range associated with this ev.
     * 
     * @param urn
     *            the URn containing ev
     * @param ev
     *            the evaluation for which the range needs to be deleted.
     */
    public SetEvaluationRangeCommand(URNspec urn, Evaluation ev) {
        this.urn = urn;
        this.evaluation = ev;
        this.shouldDelete = true;

        setLabel(Messages.getString("SetEvaluationRangeCommand.SetEvaluationRange")); //$NON-NLS-1$
    }

    /**
     * Set this evaluation to this specified range.
     * 
     * @param urn
     *            the URN containing ev
     * @param ev
     *            the evaluation for which the range needs to be set.
     * @param start
     * @param end
     * @param step
     */
    public SetEvaluationRangeCommand(URNspec urn, Evaluation ev, int start, int end, int step) {
        this.urn = urn;
        this.evaluation = ev;
        this.start = start;
        this.end = end;
        this.step = step;
        this.shouldDelete = false;
        setLabel(Messages.getString("SetEvaluationRangeCommand.SetEvaluationRange")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (evaluation.getEvalRange() != null) {
            range = evaluation.getEvalRange();

            previouslyExisted = true;
        } else {
            if (!shouldDelete)
                range = (EvaluationRange) ModelCreationFactory.getNewObject(urn, EvaluationRange.class);
            else
                range = null;
            previouslyExisted = false;
        }

        redo();
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public EvaluationRange getRange() {
        return range;
    }

    public URNspec getUrn() {
        return urn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (shouldDelete)
            evaluation.setEvalRange(null);
        else {
            if (range != null) {
                range.setStart(start);
                range.setEnd(end);
                range.setStep(step);
            }

            evaluation.setEvalRange(range);
        }

        testPostConditions();
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public void setRange(EvaluationRange range) {
        this.range = range;
    }

    public void setUrn(URNspec urn) {
        this.urn = urn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && evaluation != null : "post is null!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && evaluation != null : "pre is null"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (!previouslyExisted) {
            evaluation.setEvalRange(null);
        } else {
            if (!shouldDelete) {
                if (range != null) {
                    range.setStart(oldStart);
                    range.setEnd(oldEnd);
                    range.setStep(oldStep);
                }
            }
            evaluation.setEvalRange(range);
        }

        testPreConditions();
    }

}