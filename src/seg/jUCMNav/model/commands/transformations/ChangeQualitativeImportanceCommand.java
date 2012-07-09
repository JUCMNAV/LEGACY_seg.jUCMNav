package seg.jUCMNav.model.commands.transformations;

import grl.Actor;
import grl.ActorRef;
import grl.ImportanceType;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * 
 * @author Andrew Miga
 */

public class ChangeQualitativeImportanceCommand extends Command implements JUCMNavCommand {
    public final static int INCREASE = ImportanceType.VALUES.size();
    public final static int DECREASE = ImportanceType.VALUES.size() + 1;

    private class IElementState {
        EObject intElemRef; // IntentionalElementRef or ActorRef
        ImportanceType oldType, newType;
        int oldValue;
    }
    
    public static ImportanceType getImportance(EObject o) {
        if (o instanceof IntentionalElementRef) {
            IntentionalElementRef ref = (IntentionalElementRef) o;
            return ref.getDef().getImportance();
        } else if (o instanceof ActorRef) {
            ActorRef ref = (ActorRef) o;
            return ((Actor) ref.getContDef()).getImportance();
        } else
            throw new UnsupportedOperationException();
    }

    public static void setImportance(EObject o, ImportanceType value) {
        if (o instanceof IntentionalElementRef) {
            IntentionalElementRef ref = (IntentionalElementRef) o;
            EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeImportance(ref.getDef(), value);
        } else if (o instanceof ActorRef) {
            ActorRef ref = (ActorRef) o;
            EvaluationStrategyManager.getInstance().setActorQualitativeImportance(((Actor) ref.getContDef()), value);
        } else
            throw new UnsupportedOperationException();
    }

    Vector intElementStates = new Vector();

    public ChangeQualitativeImportanceCommand(List intElemRefs, int id) {
        setLabel(Messages.getString("UrnContextMenuProvider.SetQualitativeImportance")); //$NON-NLS-1$

        intElementStates = new Vector();

        for (Iterator iter = intElemRefs.iterator(); iter.hasNext();) {

            EObject currentIERef = (EObject) iter.next();
            IElementState ies = new IElementState();

            ies.intElemRef = currentIERef;
            ies.oldType = getImportance(currentIERef);
            ies.oldValue = ChangeNumericalImportanceCommand.getImportanceQuantitative(currentIERef); // store old numerical value for undo operations
            intElementStates.add(ies);

            if (id < INCREASE) { // input from sub-menu High --> None
                ies.newType = ImportanceType.get(id);
            } else if (id == INCREASE) { // increase importance
                int index = ImportanceType.VALUES.indexOf(ies.oldType);
                ies.newType = ImportanceType.get(index - 1);
            } else if (id == DECREASE) { // decrease importance if possible
                int index = ImportanceType.VALUES.indexOf(ies.oldType);
                ies.newType = ImportanceType.get(index + 1);
            }
        }
    }

    public void execute() {
        redo();
    }

    public void redo() {
        testPreConditions();

        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            setImportance(ies.intElemRef, ies.newType);
        }

        testPostConditions();
    }

    public void testPostConditions() {
        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            assert ies.intElemRef != null : "post no element!"; //$NON-NLS-1$
        }
    }

    public void testPreConditions() {
        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            assert ies.intElemRef != null : "pre no element!"; //$NON-NLS-1$
        }
    }

    public void undo() {
        testPostConditions();

        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            setImportance(ies.intElemRef, ies.oldType);
            ChangeNumericalImportanceCommand.setImportanceQuantitative(ies.intElemRef, ies.oldValue);
        }

        testPreConditions();
    }

}
