package seg.jUCMNav.actions.scenarios;

import grl.Evaluation;
import grl.EvaluationRange;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.transformations.SetEvaluationRangeCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Deletes a user evaluationrange
 * 
 * @author jkealey
 * 
 */
public class DeleteEvaluationRangeAction extends URNSelectionAction {

    public static final String DELETEEVALUATIONRANGE = "DeleteEvaluationRange";
    private EvaluationRange range;
    private URNspec urn;

    /**
     * @param part
     */
    public DeleteEvaluationRangeAction(IWorkbenchPart part) {
        super(part);
        setId(DELETEEVALUATIONRANGE);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/urnstratscenon16.gif")); //$NON-NLS-1$
    }

    protected void autoDirectEdit(Command cmd) {
           EvaluationStrategyManager.getInstance().calculateEvaluation();
    }

    /**
     * We need an evaluation range set.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
            urn = sel.getUrnspec();
            IntentionalElementRef selection = sel.getIntentionalElementRef();
            EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
            if (strategy != null) {
                Evaluation evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(selection.getDef());
                range = evaluation.getEvalRange();
                if (range != null && evaluation.getStrategies() == strategy) {
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
        return new SetEvaluationRangeCommand(urn, range.getEval());
    }
}
