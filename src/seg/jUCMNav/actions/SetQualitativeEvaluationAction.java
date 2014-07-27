package seg.jUCMNav.actions;

import fm.Feature;
import grl.IntentionalElementRef;
import grl.QualitativeLabel;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeQualitativeEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * 
 * @author Andrew Miga
 */

public class SetQualitativeEvaluationAction extends URNSelectionAction {
    public static final String SET_QUALITATIVE_EVALUATION = "seg.jUCMNav.SET_QUALITATIVE_EVALUATION"; //$NON-NLS-1$
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "Satisfied", "Weakly Satisfied", "None", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "Weakly Denied", "Denied", "Conflict", "Unknown", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            Messages.getString("SetEvaluation.Increase") + "   (h)", //$NON-NLS-1$ //$NON-NLS-2$ 
            Messages.getString("SetEvaluation.Decrease") + "   (n)" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public SetQualitativeEvaluationAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_QUALITATIVE_EVALUATION + id);
        this.id = id;

        if (id == 0)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/satisficed.gif")); //$NON-NLS-1$
        else if (id == 1)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/wsatisficed.gif")); //$NON-NLS-1$
        else if (id == 2)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/none.gif")); //$NON-NLS-1$
        else if (id == 3)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/wdenied.gif")); //$NON-NLS-1$
        else if (id == 4)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/denied.gif")); //$NON-NLS-1$
        else if (id == 5)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/conflict.gif")); //$NON-NLS-1$
        else if (id == 6)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/undecided.gif")); //$NON-NLS-1$
        else if (id == 7)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_up.gif")); //$NON-NLS-1$
        else if (id == 8)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_down.gif")); //$NON-NLS-1$

        setText(values[id]);
    }

    protected Command getCommand() {
        return new ChangeQualitativeEvaluationCommand(intElementRefs, id);
    }

    /**
     * We need to have an intentional element reference selected. For increase and decrease operations verify if possible
     */
    protected boolean calculateEnabled() {
        EvaluationStrategyManager esm = EvaluationStrategyManager.getInstance();

        if (esm.getEvaluationStrategy() == null) // cannot set an evaluation for a strategy if none is defined
            return false;

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!(obj instanceof IntentionalElementEditPart) || 
            		((obj instanceof IntentionalElementEditPart) && (((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef() instanceof Feature)))
                return false;

            if (id < ChangeQualitativeEvaluationCommand.INCREASE) // operation is not increase or decrease, skip further tests
                continue;

            IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) obj).getModel());
            QualitativeLabel oldQeval = esm.getEvaluationObject(ier.getDef()).getQualitativeEvaluation();

            if (id == ChangeQualitativeEvaluationCommand.INCREASE) { // increase operation, verify if possible
                if (oldQeval == QualitativeLabel.SATISFIED_LITERAL)
                    return false; // can't increase from SATISFIED
            } else if (id == ChangeQualitativeEvaluationCommand.DECREASE) { // decrease operation, verify if possible
                if (oldQeval == QualitativeLabel.UNKNOWN_LITERAL)
                    return false; // can't decrease from UNKNOWN
            }
        }

        intElementRefs = new Vector(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) iter.next()).getModel());
            intElementRefs.add(ier);
        }

        return true;
    }

    public static String generateId(int id) {
        return SET_QUALITATIVE_EVALUATION + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_QUALITATIVE_EVALUATION + index;
        }
        return null;
    }

}
