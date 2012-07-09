package seg.jUCMNav.actions;

import grl.Actor;
import grl.ActorRef;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalImportanceCommand;
import seg.jUCMNav.views.wizards.IntegerInputRangeDialog;

/**
 * 
 * @author Andrew Miga
 */

public class SetNumericalImportanceAction extends URNSelectionAction {
    public static final String SET_NUMERICAL_IMPORTANCE = "seg.jUCMNav.SET_NUMERICAL_IMPORTANCE"; //$NON-NLS-1$
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "100", "75", "50", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "25", "0", //$NON-NLS-1$ //$NON-NLS-2$
            Messages.getString("SetEvaluation.Other"), //$NON-NLS-1$ 
            Messages.getString("SetEvaluation.Increase") + "   (Shift+X)", //$NON-NLS-1$ //$NON-NLS-2$ 
            Messages.getString("SetEvaluation.Decrease") + "   (Shift+Z)" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public SetNumericalImportanceAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_NUMERICAL_IMPORTANCE + id);
        setText(values[id]);
        if (id == ChangeNumericalImportanceCommand.INCREASE)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_up.gif")); //$NON-NLS-1$
        else if (id == ChangeNumericalImportanceCommand.DECREASE)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_down.gif")); //$NON-NLS-1$

        this.id = id;
    }

    protected int getQuantitativeImportanceFromEditPart(EditPart obj) {
        return getQuantitativeImportance(obj.getModel());
    }

    protected int getQuantitativeImportance(Object obj) {
        int oldEval = 0;

        if (obj instanceof IntentionalElementRef) {
            IntentionalElementRef ier = (IntentionalElementRef) obj;
            oldEval = ier.getDef().getImportanceQuantitative();
        } else if (obj instanceof ActorRef) {
            ActorRef ier = (ActorRef) obj;
            oldEval = ((Actor) ier.getContDef()).getImportanceQuantitative();
        }
        return oldEval;
    }
    
    /**
     * We need to have only intentional element references selected. For increase/decrease other tests are necessary.
     */
    protected boolean calculateEnabled() {
        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!hasImportance(obj)) // all must have imporance. 
                return false;

            if (id <= ChangeNumericalImportanceCommand.USER_ENTRY) // operation is not increase or decrease, skip further tests
                continue;

            
            int oldEval = getQuantitativeImportanceFromEditPart((EditPart)obj);

            if (id == ChangeNumericalImportanceCommand.INCREASE) { // increase operation, verify if possible
                if (oldEval == 100)
                    return false; // can't increase from 100
            } else if (id == ChangeNumericalImportanceCommand.DECREASE) { // decrease operation, verify if possible
                if (oldEval == 0)
                    return false; // can't decrease from 0
            }
        }

        intElementRefs = new Vector(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            EObject ier = (EObject) (((EditPart) iter.next()).getModel());
            intElementRefs.add(ier);
        }

        return true;
    }

    protected boolean hasImportance(Object obj) {
        return obj instanceof IntentionalElementEditPart || obj instanceof ActorRefEditPart || obj instanceof IntentionalElementRef || obj instanceof ActorRef;
    }

    public void run() {
        if (id < ChangeNumericalImportanceCommand.USER_ENTRY || id >= ChangeNumericalImportanceCommand.INCREASE)
            execute(new ChangeNumericalImportanceCommand(intElementRefs, id, 0, getCommandStack()));
        else if (id == ChangeNumericalImportanceCommand.USER_ENTRY) {
            String currentValue = (intElementRefs.size() > 1) ? "" : //$NON-NLS-1$
                    Integer.toString(getQuantitativeImportance(intElementRefs.get(0)));
            Integer userEntry = enterImportance(currentValue);
            if (userEntry != null) {
                int enteredImportance = userEntry.intValue();
                execute(new ChangeNumericalImportanceCommand(intElementRefs, id, enteredImportance, getCommandStack()));
            }
        }
    }

    private Integer enterImportance(String currentValue) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);

        return (dialog.open(Messages.getString("SetEvaluation.WindowImportance"), //$NON-NLS-1$
                Messages.getString("SetEvaluation.TextImportance"), //$NON-NLS-1$ 
                currentValue, 0, 100));
    }

    public static String generateId(int id) {
        return SET_NUMERICAL_IMPORTANCE + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_NUMERICAL_IMPORTANCE + index;
        }
        return null;
    }

}
