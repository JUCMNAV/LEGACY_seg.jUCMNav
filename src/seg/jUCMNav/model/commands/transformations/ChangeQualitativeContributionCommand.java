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

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.LinkRefPropertySource;

/**
 * Command to change the qualitative value of a GRL contribution
 * @author damyot
 */

public class ChangeQualitativeContributionCommand extends Command implements JUCMNavCommand {
    private boolean removed = false;
    EvaluationStrategyManager esm;

    private final static ContributionType values[] = { ContributionType.MAKE_LITERAL, ContributionType.SOME_POSITIVE_LITERAL,  
        ContributionType.HELP_LITERAL, ContributionType.UNKNOWN_LITERAL, 
        ContributionType.HURT_LITERAL, ContributionType.SOME_NEGATIVE_LITERAL,  
        ContributionType.BREAK_LITERAL };

    public final static int INCREASE = values.length;
    public final static int DECREASE = values.length + 1;

    private class LinkState {
        LinkRef linkRef;
        ContributionType oldQcontrib, newQcontrib;
        int oldNcontrib;
        ContributionContext activeContext;
        ContributionChange change;

    }

    Vector linkStates = new Vector();

    public ChangeQualitativeContributionCommand(List linkRefs, int id) {
        setLabel(Messages.getString("UrnContextMenuProvider.SetQualitativeContribution")); //$NON-NLS-1$

        for (Iterator iter = linkRefs.iterator(); iter.hasNext();) {

            LinkRef currentLinkRef = (LinkRef) iter.next();
            LinkState ls = new LinkState();

            ls.linkRef = currentLinkRef;
            Contribution contrib = (Contribution) currentLinkRef.getLink();
            //ls.oldQcontrib = ((Contribution) currentLinkRef.getLink()).getContribution();
            ls.oldQcontrib = EvaluationStrategyManager.getInstance().getActiveContribution(contrib);
            //ls.oldNcontrib = ((Contribution) currentLinkRef.getLink()).getQuantitativeContribution();
            ls.oldNcontrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
            ls.change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, false);
            ls.activeContext = EvaluationStrategyManager.getInstance().getContributionContext();
            linkStates.add(ls);

            if (id < INCREASE) // input from sub-menu
            ls.newQcontrib = values[id];
            else if (id == INCREASE) // increase evaluation if possible
                ls.newQcontrib = values[index(ls.oldQcontrib) - 1];
            else if (id == DECREASE) // decrease evaluation if possible
                ls.newQcontrib = values[index(ls.oldQcontrib) + 1];
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
            //contrib.setContribution(ls.newQcontrib);
            EvaluationStrategyManager.getInstance().setActiveContribution(ls.activeContext, contrib, ls.newQcontrib);
            LinkRefPropertySource.syncElementLinkQuantitativeContribution (contrib, ls.newQcontrib);
        }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); //Refresh strategy, if any

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
            //((Contribution) ls.linkRef.getLink()).setContribution(ls.oldQcontrib);
            EvaluationStrategyManager.getInstance().setActiveContribution(ls.activeContext, contrib, ls.oldQcontrib);
            //((Contribution) ls.linkRef.getLink()).setQuantitativeContribution(ls.oldNcontrib);
            EvaluationStrategyManager.getInstance().setActiveQuantitativeContribution(ls.activeContext, contrib, ls.oldNcontrib);
            
            if (ls.change == null) // if we had no change before, we need to delete our newly added one.  
            {
                ContributionChange change = EvaluationStrategyManager.findApplicableContributionChange(ls.activeContext, contrib, false);
                if (change!=null) {
                    change.setContribution(null);
                    change.setContext(null);
                    if (contrib!=null)contrib.setQuantitativeContribution(contrib.getQuantitativeContribution()); // forces refreshes.
                }
            }
        }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); //Refresh strategy, if any

        testPreConditions();
    }

    private int index(ContributionType ql) {
        for (int i = 0; i < values.length; i++) {
            if (ql == values[i])
                return i;
        }
        return -1;
    }

}
