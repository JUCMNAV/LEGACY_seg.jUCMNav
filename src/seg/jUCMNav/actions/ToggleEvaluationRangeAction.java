/**
 * 
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.SetDesiredEvaluationRangeCommand;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import urn.URNspec;

/**
 * This action toggles the evaluation from [-100,100] to [0,100] and back again. 
 * 
 * @author jkealey
 * 
 */
public class ToggleEvaluationRangeAction extends URNSelectionAction {
    public static final String TOGGLEEVALUATIONRANGE = "seg.jUCMNav.ToggleEvaluationRange"; //$NON-NLS-1$

    public ToggleEvaluationRangeAction(IWorkbenchPart part) {
        super(part);
        setId(TOGGLEEVALUATIONRANGE);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/grl16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we select the URNSpec
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.URNSPEC) {
            URNspec urn = sel.getUrnspec();
            boolean b = StrategyEvaluationRangeHelper.getCurrentRange(urn);
            if (b)
                setText(Messages.getString("ToggleEvaluationRangeAction.ChangeRangeToMinus100To100")); //$NON-NLS-1$
            else
                setText(Messages.getString("ToggleEvaluationRangeAction.ChangeRangeTo0to100")); //$NON-NLS-1$
            return true;
        }
        return false;
    }

    protected Command getCommand() {
        SelectionHelper selection = new SelectionHelper(getSelectedObjects());
        if (selection.getSelectionType() == SelectionHelper.URNSPEC) {
            URNspec urn = selection.getUrnspec();
            return new SetDesiredEvaluationRangeCommand(urn);
        } else {
            return null;
        }

    }
}
