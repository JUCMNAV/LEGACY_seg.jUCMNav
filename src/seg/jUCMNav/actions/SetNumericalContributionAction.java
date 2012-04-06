package seg.jUCMNav.actions;

import grl.Contribution;
import grl.LinkRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalContributionCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.wizards.IntegerInputRangeDialog;

/**
 * 
 * @author damyot, jkealey
 */

public class SetNumericalContributionAction extends URNSelectionAction {
    public static final String SET_NUMERICAL_CONTRIBUTION = "seg.jUCMNav.SET_NUMERICAL_CONTRIBUTION"; //$NON-NLS-1$
    private Vector linkRefs;
    private int id;
    private static String[] values = { "+100", "+75", "+50", "+25", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        "0", "-25", "-50", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
        "-75", "-100", //$NON-NLS-1$ //$NON-NLS-2$  
        Messages.getString("SetEvaluation.Other"), //$NON-NLS-1$ 
        Messages.getString("SetEvaluation.Increase") + "   (Shift+Y)", //$NON-NLS-1$ //$NON-NLS-2$ 
        Messages.getString("SetEvaluation.Decrease") + "   (Shift+U)" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public SetNumericalContributionAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_NUMERICAL_CONTRIBUTION + id);
        setText(values[id]);
        if (id == ChangeNumericalContributionCommand.INCREASE)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_up.gif")); //$NON-NLS-1$
        else if (id == ChangeNumericalContributionCommand.DECREASE)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_down.gif")); //$NON-NLS-1$
        this.id = id;
    }

    /**
     * We need to have a link reference selected.
     */
    protected boolean calculateEnabled() {
        boolean isPositiveRange = StrategyEvaluationPreferences.getVisualizeAsPositiveRange(getUrnspec());
        isPositiveRange = false; // disabled for contributions
        
        // doing this here instead of the constructor just because it will refresh properly after we change the preferences.
        try {
            int val = Integer.parseInt(values[id].replace("+", "")); //$NON-NLS-1$ //$NON-NLS-2$
            //val = StrategyEvaluationPreferences.getValueToVisualize(val);
            
            if (val>0)
                setText("+" + Integer.toString(val)); //$NON-NLS-1$
            else 
                setText(Integer.toString(val));
        
        } catch (NumberFormatException ex) { } // ignore non numerical values. 
        
        
        
        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!(obj instanceof LinkRefEditPart))
                return false;

            LinkRef lr = (LinkRef) (((LinkRefEditPart) obj).getModel());
            if (!(lr.getLink() instanceof Contribution))
                return false; // not a contribution

            if (id < ChangeNumericalContributionCommand.INCREASE) // operation is not increase or decrease, skip further tests
                continue;           

            //int oldContrib = ((Contribution) lr.getLink()).getQuantitativeContribution();
            int oldContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution((Contribution) lr.getLink());

            if (id == ChangeNumericalContributionCommand.INCREASE) { // increase operation, verify if possible
                if (oldContrib ==  (isPositiveRange ? 99 : 100))
                    return false; // can't increase from 100
            } else if (id == ChangeNumericalContributionCommand.DECREASE) { // decrease operation, verify if possible
                if (oldContrib <= (isPositiveRange ? 0 : -100))
                    return false; // can't decrease from -100
            }
        }

        linkRefs = new Vector(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            LinkRef lr = (LinkRef) (((LinkRefEditPart) iter.next()).getModel());
            linkRefs.add(lr);
        }

        return true;
    }

    public void run() {
        boolean isPositiveRange = StrategyEvaluationPreferences.getVisualizeAsPositiveRange(getUrnspec());
        isPositiveRange = false; // disabled for contributions
        if (id < ChangeNumericalContributionCommand.USER_ENTRY || id >= ChangeNumericalContributionCommand.INCREASE)
            execute(new ChangeNumericalContributionCommand(linkRefs, id, 1, getCommandStack()));
        else if (id == ChangeNumericalContributionCommand.USER_ENTRY) {
            String currentContrib = ""; //$NON-NLS-1$

            if (linkRefs.size() <= 1) {
                //int val = ((Contribution) ((LinkRef) (linkRefs.get(0))).getLink()).getQuantitativeContribution();
                int val = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution((Contribution) ((LinkRef) (linkRefs.get(0))).getLink());
                //val = StrategyEvaluationPreferences.getValueToVisualize(val);
                currentContrib = Integer.toString(val);
            }
            
            Integer userEntry = enterContribution(currentContrib);
            if (userEntry != null) {
                int enteredValue = userEntry.intValue();
                //enteredValue = StrategyEvaluationPreferences.getModelValueFromVisualization(enteredValue);
                execute(new ChangeNumericalContributionCommand(linkRefs, id, enteredValue, getCommandStack()));
            }
        }
    }

    private Integer enterContribution(String currentContrib) {
        boolean isPositiveRange = StrategyEvaluationPreferences.getVisualizeAsPositiveRange(getUrnspec());
        isPositiveRange = false; // disabled for contributions
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);

        if (isPositiveRange) {
            return (dialog.open(Messages.getString("SetContribution.WindowEval").replace("-100", "0"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    Messages.getString("SetContribution.TextEval"), //$NON-NLS-1$ 
                    currentContrib, 0, 100));
        } else
            return (dialog.open(Messages.getString("SetContribution.WindowEval"), //$NON-NLS-1$
                    Messages.getString("SetContribution.TextEval"), //$NON-NLS-1$ 
                    currentContrib, -100, 100));
    }

    public static String generateId(int id) {
        return SET_NUMERICAL_CONTRIBUTION + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_NUMERICAL_CONTRIBUTION + index;
        }
        return null;
    }

}
