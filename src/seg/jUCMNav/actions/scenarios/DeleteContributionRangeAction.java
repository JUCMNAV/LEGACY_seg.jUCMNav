package seg.jUCMNav.actions.scenarios;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionRange;
import grl.LinkRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.transformations.SetContributionRangeCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Deletes a user contribution range
 * 
 * @author jkealey
 * 
 */
public class DeleteContributionRangeAction extends URNSelectionAction {

    public static final String DELETECONTRIBUTIONRANGE = "DeleteContributionRange"; //$NON-NLS-1$
    private ContributionRange range;
    private URNspec urn;

    /**
     * @param part
     */
    public DeleteContributionRangeAction(IWorkbenchPart part) {
        super(part);
        setId(DELETECONTRIBUTIONRANGE);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ContributionRangeDelete16.gif")); //$NON-NLS-1$
    }

    protected void autoDirectEdit(Command cmd) {
           EvaluationStrategyManager.getInstance().calculateEvaluation();
    }

    /**
     * We need an evaluation range set.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.LINKREF) {
            urn = sel.getUrnspec();
            LinkRef selection = sel.getLinkref();
            ContributionContext context  = EvaluationStrategyManager.getInstance().getContributionContext();
            if (context != null && selection.getLink() instanceof Contribution) {
                Contribution contrib = (Contribution)selection.getLink();
                ContributionChange change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, false);
                if (change!=null && change.getContribRange() != null && change.getContext() == context) {
                    range = change.getContribRange();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return Builds the command to delete the evaluation range
     */
    protected Command getCommand() {
        return new SetContributionRangeCommand(urn, range.getChange());
    }
}
