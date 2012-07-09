package seg.jUCMNav.model.commands.transformations;

import grl.Actor;
import grl.ActorRef;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * 
 * @author Andrew Miga
 */

public class ChangeNumericalImportanceCommand extends Command implements JUCMNavCommand {
    private int operation;
    private boolean removed = false;
    private CommandStack commandStack;

    private final static int[] values = { 100, 75, 50, 25, 0 };

    public final static int USER_ENTRY = values.length;
    public final static int INCREASE = values.length + 1;
    public final static int DECREASE = values.length + 2;

    private class IElementState {
        EObject intElemRef; // IntentionalElementRef or ActorRef
        int oldValue, newValue;
    }

    public static int getImportanceQuantitative(EObject o) {
        if (o instanceof IntentionalElementRef) {
            IntentionalElementRef ref = (IntentionalElementRef) o;
            return ref.getDef().getImportanceQuantitative();
        } else if (o instanceof ActorRef) {
            ActorRef ref = (ActorRef) o;
            return ((Actor) ref.getContDef()).getImportanceQuantitative();
        } else
            throw new UnsupportedOperationException();
    }

    public static void setImportanceQuantitative(EObject o, int value) {
        if (o instanceof IntentionalElementRef) {
            IntentionalElementRef ref = (IntentionalElementRef) o;
            EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance(ref.getDef(), value);
        } else if (o instanceof ActorRef) {
            ActorRef ref = (ActorRef) o;
            EvaluationStrategyManager.getInstance().setActorQuantitativeImportance(((Actor) ref.getContDef()), value);
        } else
            throw new UnsupportedOperationException();
    }

    Vector intElementStates = new Vector();

    public ChangeNumericalImportanceCommand(List intElemRefs, int id, int enteredImportance, CommandStack stack) {
        setLabel(Messages.getString("UrnContextMenuProvider.SetNumericalImportance")); //$NON-NLS-1$

        operation = id;
        commandStack = stack;

        for (Iterator iter = intElemRefs.iterator(); iter.hasNext();) {

            EObject currentIERef = (EObject) iter.next();
            IElementState ies = new IElementState();

            ies.intElemRef = currentIERef;
            ies.oldValue = getImportanceQuantitative(currentIERef);
            intElementStates.add(ies);

            if (id < USER_ENTRY) // input from sub-menu +100 -> 0
                ies.newValue = values[id];
            else if (id == USER_ENTRY) // new value entered through user dialog
                ies.newValue = enteredImportance;
            else if (id == INCREASE) // increase evaluation
                ies.newValue = ies.oldValue + 1;
            else if (id == DECREASE) // decrease evaluation
                ies.newValue = ies.oldValue - 1;
        }

        if ((id == INCREASE) || (id == DECREASE)) { // increase or decrease operation, check if undo merging is needed
            if (commandStack.getUndoCommand() instanceof ChangeNumericalImportanceCommand) {
                ChangeNumericalImportanceCommand prevCommand = (ChangeNumericalImportanceCommand) commandStack.getUndoCommand();

                if (prevCommand.isRepeatedIncDecOperation(intElemRefs)) { // merge undo operations, overwrite new "old values" with those of previous operation
                    for (Iterator iter = prevCommand.prevElementStates().iterator(); iter.hasNext();) {
                        IElementState pes = (IElementState) iter.next();
                        for (Iterator iter2 = intElementStates.iterator(); iter2.hasNext();) {
                            IElementState ces = (IElementState) iter2.next();
                            if (ces.intElemRef == pes.intElemRef)
                                ces.oldValue = pes.oldValue;
                        }
                    }
                    // remove unneeded previous command from undo stack
                    prevCommand.SetRemoved(); // set flag in previous command object so undo does nothing
                    commandStack.undo(); // pop previous command from undo stack and do nothing, workaround due to Eclipse GEF API shortcomings
                }
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
            setImportanceQuantitative(ies.intElemRef, ies.newValue);
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

        if (removed)
            return; // used in undo merging

        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            setImportanceQuantitative(ies.intElemRef, ies.newValue);
        }

        testPreConditions();
    }

    public boolean isRepeatedIncDecOperation(List intElemRefs) {
        if (!((operation == INCREASE) || (operation == DECREASE)))
            return false;

        if (intElemRefs.size() != intElementStates.size())
            return false;

        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            if (!intElemRefs.contains(ies.intElemRef))
                return false;
        }

        return true;
    }

    public List prevElementStates() {
        return intElementStates;
    }

    public void SetRemoved() {
        removed = true;
    }

}
