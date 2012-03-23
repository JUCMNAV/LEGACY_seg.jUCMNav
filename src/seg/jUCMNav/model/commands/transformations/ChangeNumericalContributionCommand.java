package seg.jUCMNav.model.commands.transformations;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionType;
import grl.LinkRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.LinkRefPropertySource;

/**
 * Command to change the quantitative value of a GRL contribution
 * 
 * @author damyot
 */

public class ChangeNumericalContributionCommand extends Command implements JUCMNavCommand {
    private int operation;
    private boolean removed = false;
    private CommandStack commandStack;

    private final static int[] values = { 100, 75, 50, 25, 0, -25, -50, -75, -100 };

    public final static int USER_ENTRY = values.length;
    public final static int INCREASE = values.length + 1;
    public final static int DECREASE = values.length + 2;

    private class LinkState {
        LinkRef linkRef;
        ContributionType oldQcontrib;
        int oldNcontrib, newNcontrib;
        ContributionContext activeContext;
        ContributionChange change;
    }

    Vector linkStates = new Vector();

    public ChangeNumericalContributionCommand(List linkRefs, int id, int enteredValue, CommandStack stack) {
        setLabel(Messages.getString("UrnContextMenuProvider.SetNumericalContribution")); //$NON-NLS-1$

        int enteredEval = 0;
        operation = id;
        commandStack = stack;

        for (Iterator iter = linkRefs.iterator(); iter.hasNext();) {

            LinkRef currentLinkRef = (LinkRef) iter.next();
            LinkState ls = new LinkState();

            ls.linkRef = currentLinkRef;
            Contribution contrib = (Contribution) currentLinkRef.getLink();
            // ls.oldQcontrib = ((Contribution) currentLinkRef.getLink()).getContribution();
            ls.oldQcontrib = EvaluationStrategyManager.getInstance().getActiveContribution(contrib);
            // ls.oldNcontrib = ((Contribution) currentLinkRef.getLink()).getQuantitativeContribution();
            ls.oldNcontrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
            ls.change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, false);
            ls.activeContext = EvaluationStrategyManager.getInstance().getContributionContext();
            linkStates.add(ls);

            if (id < USER_ENTRY) // input from sub-menu +100 -> -100
                ls.newNcontrib = values[id];
            else if (id == USER_ENTRY) // new value entered through user dialog
                ls.newNcontrib = enteredValue;
            else if (id == INCREASE) // increase evaluation
                ls.newNcontrib = ls.oldNcontrib + enteredValue;
            else if (id == DECREASE) // decrease evaluation if possible
                ls.newNcontrib = ls.oldNcontrib - enteredValue;

        }

        if ((id == INCREASE) || (id == DECREASE)) { // increase or decrease operation, check if undo merging is needed
            if (commandStack.getUndoCommand() instanceof ChangeNumericalContributionCommand) {

                ChangeNumericalContributionCommand prevCommand = (ChangeNumericalContributionCommand) commandStack.getUndoCommand();

                if (prevCommand.isRepeatedIncDecOperation(linkRefs)) { // merge undo operations, overwrite new "old values" with those of previous operation
                    for (Iterator iter = prevCommand.prevElementStates().iterator(); iter.hasNext();) {
                        LinkState pls = (LinkState) iter.next();
                        for (Iterator iter2 = linkStates.iterator(); iter2.hasNext();) {
                            LinkState cls = (LinkState) iter2.next();
                            if (cls.linkRef == pls.linkRef) {
                                cls.oldNcontrib = pls.oldNcontrib;
                                cls.oldQcontrib = pls.oldQcontrib;
                            }
                        }
                    }
                    // remove unneeded previous command from undo stack
                    prevCommand.SetRemoved(); // set flag in previous command object so undo does nothing
                    commandStack.undo(); // pop previous command from undo stack and do nothing, workaround due to Eclipse GEF API shortcomings
                }
            }
        }
    }

    public void execute() {
        redo();
    }

    public void redo() {
        testPreConditions();

        for (Iterator iter = linkStates.iterator(); iter.hasNext();) {
            LinkState ls = (LinkState) iter.next();
            Contribution contrib = (Contribution) ls.linkRef.getLink();
            // contrib.setQuantitativeContribution(ls.newNcontrib);
            EvaluationStrategyManager.getInstance().setActiveQuantitativeContribution(ls.activeContext, contrib, ls.newNcontrib);

            LinkRefPropertySource.syncElementLinkQualitativeContribution(contrib, ls.newNcontrib);
        }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); // Refresh strategy, if any

        testPostConditions();
    }

    public void testPostConditions() {
        for (Iterator iter = linkStates.iterator(); iter.hasNext();) {
            LinkState ls = (LinkState) iter.next();
            assert ls.linkRef != null : "post no element!"; //$NON-NLS-1$
        }
    }

    public void testPreConditions() {
        for (Iterator iter = linkStates.iterator(); iter.hasNext();) {
            LinkState ls = (LinkState) iter.next();
            assert ls.linkRef != null : "pre no element!"; //$NON-NLS-1$
        }
    }

    public void undo() {
        testPostConditions();

        if (removed)
            return; // used in undo merging

        for (Iterator iter = linkStates.iterator(); iter.hasNext();) {
            LinkState ls = (LinkState) iter.next();
            Contribution contrib = (Contribution) ls.linkRef.getLink();
            // ((Contribution) ls.linkRef.getLink()).setContribution(ls.oldQcontrib);
            EvaluationStrategyManager.getInstance().setActiveContribution(ls.activeContext, contrib, ls.oldQcontrib);
            // ((Contribution) ls.linkRef.getLink()).setQuantitativeContribution(ls.oldNcontrib);
            EvaluationStrategyManager.getInstance().setActiveQuantitativeContribution(ls.activeContext, contrib, ls.oldNcontrib);

            if (ls.change == null) // if we had no change before, we need to delete our newly added one.
            {
                ContributionChange change = EvaluationStrategyManager.findApplicableContributionChange(ls.activeContext, contrib, false);
                if (change != null) {
                    change.setContribution(null);
                    change.setContext(null);

                    if (contrib != null)
                        contrib.setQuantitativeContribution(contrib.getQuantitativeContribution()); // forces refreshes.
                }
            }
        }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); // Refresh strategy, if any

        testPreConditions();
    }

    public boolean isRepeatedIncDecOperation(List linkRefs) {
        if (!((operation == INCREASE) || (operation == DECREASE)))
            return false;

        if (linkRefs.size() != linkStates.size())
            return false;

        for (Iterator iter = linkStates.iterator(); iter.hasNext();) {
            LinkState ls = (LinkState) iter.next();
            if (!linkRefs.contains(ls.linkRef))
                return false;
        }

        return true;
    }

    public List prevElementStates() {
        return linkStates;
    }

    public void SetRemoved() {
        removed = true;
    }
}
