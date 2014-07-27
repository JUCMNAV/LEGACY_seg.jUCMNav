package seg.jUCMNav.actions;

import fm.Feature;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.wizards.IntegerInputRangeDialog;

/**
 * 
 * @author Andrew Miga
 */

public class SetNumericalEvaluationAction extends URNSelectionAction {
    public static final String SET_NUMERICAL_EVALUATION = "seg.jUCMNav.SET_NUMERICAL_EVALUATION"; //$NON-NLS-1$
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "+100", "+75", "+50", "+25", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            "0", "-25", "-50", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
            "-75", "-100", //$NON-NLS-1$ //$NON-NLS-2$  
            Messages.getString("SetEvaluation.Other"), //$NON-NLS-1$ 
            Messages.getString("SetEvaluation.Increase") + "   (Shift+H)", //$NON-NLS-1$ //$NON-NLS-2$ 
            Messages.getString("SetEvaluation.Decrease") + "   (Shift+N)" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public SetNumericalEvaluationAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_NUMERICAL_EVALUATION + id);
        setText(values[id]);
        if (id == ChangeNumericalEvaluationCommand.INCREASE)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_up.gif")); //$NON-NLS-1$
        else if (id == ChangeNumericalEvaluationCommand.DECREASE)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_down.gif")); //$NON-NLS-1$
        this.id = id;
    }

    /**
     * We need to have an intentional element reference selected.
     */
    protected boolean calculateEnabled() {
        // doing this here instead of the constructor just because it will refresh properly after we change the preferences.
        try {
            int val = Integer.parseInt(values[id].replace("+", "")); //$NON-NLS-1$ //$NON-NLS-2$
            val = StrategyEvaluationPreferences.getEquivalentValueIn0To100RangeIfApplicable(getUrnspec(), val);

            if (val > 0)
                setText("+" + Integer.toString(val)); //$NON-NLS-1$
            else
                setText(Integer.toString(val));

        } catch (NumberFormatException ex) {
        } // ignore non numerical values.

        if (EvaluationStrategyManager.getInstance().getEvaluationStrategy() == null)
            return false;

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!(obj instanceof IntentionalElementEditPart) || 
            		((obj instanceof IntentionalElementEditPart) && (((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef() instanceof Feature)))
                return false;

            if (id < ChangeNumericalEvaluationCommand.INCREASE) // operation is not increase or decrease, skip further tests
                continue;

            IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) obj).getModel());
            int oldEval = EvaluationStrategyManager.getInstance().getEvaluation(ier.getDef());

            if (id == ChangeNumericalEvaluationCommand.INCREASE) { // increase operation, verify if possible
                if (oldEval >=  100)
                    return false; // can't increase from 100
            } else if (id == ChangeNumericalEvaluationCommand.DECREASE) { // decrease operation, verify if possible
                if (oldEval <= (StrategyEvaluationPreferences.getVisualizeAsPositiveRange(getUrnspec()) ? 0 : -100))
                    return false; // can't decrease from -100
            }
        }

        intElementRefs = new Vector(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) iter.next()).getModel());
            intElementRefs.add(ier);
        }

        return true;
    }

    public void run() {
        if (id < ChangeNumericalEvaluationCommand.USER_ENTRY || id >= ChangeNumericalEvaluationCommand.INCREASE)
            execute(new ChangeNumericalEvaluationCommand(intElementRefs, id, 1, getCommandStack()));
        else if (id == ChangeNumericalEvaluationCommand.USER_ENTRY) {
            String currentEval = ""; //$NON-NLS-1$

            if (intElementRefs.size() <= 1) {
                int val = EvaluationStrategyManager.getInstance().getEvaluation(((IntentionalElementRef) (intElementRefs.get(0))).getDef());
                //val = StrategyEvaluationPreferences.getValueToVisualize(val);
                currentEval = Integer.toString(val);
            }
            Integer userEntry = enterEvaluation(currentEval);
            if (userEntry != null) {
                int enteredValue = userEntry.intValue();
                //enteredValue = StrategyEvaluationPreferences.getModelValueFromVisualization(getUrnspec(), enteredValue);
                execute(new ChangeNumericalEvaluationCommand(intElementRefs, id, enteredValue, getCommandStack()));
            }
        }
    }

    private Integer enterEvaluation(String currentEval) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);

        if (StrategyEvaluationPreferences.getVisualizeAsPositiveRange(getUrnspec())) {
            return (dialog.open(Messages.getString("SetEvaluation.WindowEval").replace("-100", "0"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    Messages.getString("SetEvaluation.TextEval"), //$NON-NLS-1$ 
                    currentEval, 0, 100));
        } else
            return (dialog.open(Messages.getString("SetEvaluation.WindowEval"), //$NON-NLS-1$
                    Messages.getString("SetEvaluation.TextEval"), //$NON-NLS-1$ 
                    currentEval, -100, 100));
    }

    public static String generateId(int id) {
        return SET_NUMERICAL_EVALUATION + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_NUMERICAL_EVALUATION + index;
        }
        return null;
    }

}
