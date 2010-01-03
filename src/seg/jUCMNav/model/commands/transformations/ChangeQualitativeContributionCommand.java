package seg.jUCMNav.model.commands.transformations;

import grl.Contribution;
import grl.ContributionType;
import grl.LinkRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

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
    }

    Vector linkStates = new Vector();

    public ChangeQualitativeContributionCommand(List linkRefs, int id) {
        for (Iterator iter = linkRefs.iterator(); iter.hasNext();) {

            LinkRef currentLinkRef = (LinkRef) iter.next();
            LinkState ls = new LinkState();

            ls.linkRef = currentLinkRef;
            ls.oldQcontrib = ((Contribution) currentLinkRef.getLink()).getContribution();
            ls.oldNcontrib = ((Contribution) currentLinkRef.getLink()).getQuantitativeContribution();
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
            contrib.setContribution(ls.newQcontrib);
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
            ((Contribution) ls.linkRef.getLink()).setContribution(ls.oldQcontrib);
            ((Contribution) ls.linkRef.getLink()).setQuantitativeContribution(ls.oldNcontrib);
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
