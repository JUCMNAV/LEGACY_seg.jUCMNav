package seg.jUCMNav.actions;

import grl.Actor;
import grl.ActorRef;
import grl.ImportanceType;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeQualitativeImportanceCommand;

/**
 * 
 * @author Andrew Miga
 */

public class SetQualitativeImportanceAction extends URNSelectionAction {
    public static final String SET_QUALITATIVE_IMPORTANCE = "seg.jUCMNav.SET_QUALITATIVE_IMPORTANCE"; //$NON-NLS-1$
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "(H)igh", "(M)edium", "(L)ow", "None", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            Messages.getString("SetEvaluation.Increase") + "   (x)", //$NON-NLS-1$ //$NON-NLS-2$ 
            Messages.getString("SetEvaluation.Decrease") + "   (z)" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public SetQualitativeImportanceAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_QUALITATIVE_IMPORTANCE + id);
        setText(values[id]);
        if (id == 4)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_up.gif")); //$NON-NLS-1$
        else if (id == 5)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_down.gif")); //$NON-NLS-1$

        this.id = id;
    }

    protected Command getCommand() {
        return new ChangeQualitativeImportanceCommand(intElementRefs, id);
    }
    protected boolean hasImportance(Object obj) {
        return obj instanceof IntentionalElementEditPart || obj instanceof ActorRefEditPart || obj instanceof IntentionalElementRef || obj instanceof ActorRef;
    }
    
    protected ImportanceType getImportanceFromEditPart(EditPart obj) {
        return getImportance(obj.getModel());
    }

    protected ImportanceType getImportance(Object obj) {
        ImportanceType oldEval = ImportanceType.NONE_LITERAL;

        if (obj instanceof IntentionalElementRef) {
            IntentionalElementRef ier = (IntentionalElementRef) obj;
            oldEval = ier.getDef().getImportance();
        } else if (obj instanceof ActorRef) {
            ActorRef ier = (ActorRef) obj;
            oldEval = ((Actor) ier.getContDef()).getImportance();
        }
        return oldEval;
    }
    /**
     * We need to have an intentional element reference selected. For increase and decrease operations verify if possible
     */
    protected boolean calculateEnabled() {
        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!hasImportance(obj)) // all must have it. 
                return false;

            if (id < ChangeQualitativeImportanceCommand.INCREASE) // operation is not increase or decrease, skip further tests
                continue;

            ImportanceType type = getImportanceFromEditPart((EditPart)obj);

            if (id == ChangeQualitativeImportanceCommand.INCREASE) { // increase operation, verify if possible
                if (type == ImportanceType.HIGH_LITERAL)
                    return false; // can't increase from HIGH
            } else if (id == ChangeQualitativeImportanceCommand.DECREASE) { // decrease operation, verify if possible
                if (type == ImportanceType.NONE_LITERAL)
                    return false; // can't decrease from NONE
            }
        }

        intElementRefs = new Vector(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            EObject ier = (EObject) (((EditPart) iter.next()).getModel());
            intElementRefs.add(ier);
        }

        return true;
    }

    public static String generateId(int id) {
        return SET_QUALITATIVE_IMPORTANCE + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_QUALITATIVE_IMPORTANCE + index;
        }
        return null;
    }

}
