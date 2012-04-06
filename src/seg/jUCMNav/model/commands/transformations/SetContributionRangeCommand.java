package seg.jUCMNav.model.commands.transformations;

import grl.ContributionChange;
import grl.ContributionRange;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Changes a ContributionChange's ContributionRange.
 * 
 * @author jkealey
 */
public class SetContributionRangeCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private boolean previouslyExisted = false;
    private int start = 0;
    private int end = 0;
    private int step = 1;
    private int oldStart = 0;
    private int oldEnd = 0;
    private int oldStep = 1;
    private ContributionChange change;

    private ContributionRange range;

    private boolean shouldDelete = false;

    /**
     * Deletes the range associated with this ContributionChange.
     * 
     * @param urn
     *            the URn containing the contribution
     * @param contribution
     *            the Contribution Change for which the range needs to be deleted.
     */
    public SetContributionRangeCommand(URNspec urn, ContributionChange change) {
        this.urn = urn;
        this.change = change;
        this.shouldDelete = true;

        setLabel(Messages.getString("SetContributionRangeCommand.SetContributionRange")); //$NON-NLS-1$
    }

    /**
     * Set this Contribution to this specified range.
     * 
     * @param urn
     *            the URN containing contribution
     * @param change
     *            the Contribution for which the range needs to be set.
     * @param start
     * @param end
     * @param step
     */
    public SetContributionRangeCommand(URNspec urn, ContributionChange change, int start, int end, int step) {
        this.urn = urn;
        this.change = change;
        this.start = start;
        this.end = end;
        this.step = step;
        this.shouldDelete = false;
        setLabel(Messages.getString("SetContributionRangeCommand.SetContributionRange")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (change.getContribRange() != null) {
            range = change.getContribRange();
            previouslyExisted = true;
        } else {
            if (!shouldDelete)
                range = (ContributionRange) ModelCreationFactory.getNewObject(urn, ContributionRange.class);
            else
                range = null;
            previouslyExisted = false;
        }

        redo();
    }

    public ContributionChange getContribution() {
        return change;
    }

    public ContributionRange getRange() {
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
            change.setContribRange(null);
        else {

            if (range != null) {
                range.setStart(start);
                range.setEnd(end);
                range.setStep(step);
            }
            change.setContribRange(range);
        }

        testPostConditions();
    }

    public void setContribution(ContributionChange change) {
        this.change = change;
    }

    public void setRange(ContributionRange range) {
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
        assert urn != null && change != null : "post is null!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && change != null : "pre is null"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (!previouslyExisted) {
            change.setContribRange(null);
        } else {
            if (!shouldDelete) {
                if (range != null) {
                    range.setStart(oldStart);
                    range.setEnd(oldEnd);
                    range.setStep(oldStep);
                }
            }
            change.setContribRange(range);
        }

        testPreConditions();
    }

}