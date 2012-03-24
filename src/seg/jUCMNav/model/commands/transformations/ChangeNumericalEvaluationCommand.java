package seg.jUCMNav.model.commands.transformations;

import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urn.URNspec;

/**
 * 
 * @author Andrew Miga
 */

public class ChangeNumericalEvaluationCommand extends Command implements JUCMNavCommand {
    private int operation;
    private boolean removed = false;
    private CommandStack commandStack;
    EvaluationStrategyManager esm;

    private final static int[] values = { 100, 75, 50, 25, 0, -25, -50, -75, -100 };

    public final static int USER_ENTRY = values.length;
    public final static int INCREASE = values.length + 1;
    public final static int DECREASE = values.length + 2;

    private class IElementState {
        IntentionalElementRef intElemRef;
        int oldEval, newEval;
    }

    Vector intElementStates = new Vector();

    public ChangeNumericalEvaluationCommand(List intElemRefs, int id, int enteredValue, CommandStack stack) {
        setLabel(Messages.getString("UrnContextMenuProvider.SetNumericalEvaluation")); //$NON-NLS-1$

        int enteredEval = 0;
        operation = id;
        commandStack = stack;
        esm = EvaluationStrategyManager.getInstance();

        for (Iterator iter = intElemRefs.iterator(); iter.hasNext();) {

            IntentionalElementRef currentIERef = (IntentionalElementRef) iter.next();
            IElementState ies = new IElementState();

            ies.intElemRef = currentIERef;
            ies.oldEval = esm.getEvaluation(currentIERef.getDef());
            intElementStates.add(ies);

            if (id < USER_ENTRY) { // input from sub-menu +100 -> -100 
                ies.newEval = values[id];
                URNspec urn = null;
                if (ies.intElemRef.getDef()!=null && ies.intElemRef.getDef().getGrlspec()!=null)
                    urn = ies.intElemRef.getDef().getGrlspec().getUrnspec();
                
                ies.newEval = StrategyEvaluationPreferences.getEquivalentValueIn0To100RangeIfApplicable(urn, ies.newEval);
            }
            else if (id == USER_ENTRY) // new value entered through user dialog
                ies.newEval = enteredValue;
            else if (id == INCREASE) // increase evaluation
                ies.newEval = ies.oldEval + enteredValue;
            else if (id == DECREASE) // decrease evaluation if possible
                ies.newEval = ies.oldEval - enteredValue;
        }

        if ((id == INCREASE) || (id == DECREASE)) { // increase or decrease operation, check if undo merging is needed
            if (commandStack.getUndoCommand() instanceof ChangeNumericalEvaluationCommand) {

                ChangeNumericalEvaluationCommand prevCommand = (ChangeNumericalEvaluationCommand) commandStack.getUndoCommand();

                if (prevCommand.isRepeatedIncDecOperation(intElemRefs)) { // merge undo operations, overwrite new "old values" with those of previous operation
                    for (Iterator iter = prevCommand.prevElementStates().iterator(); iter.hasNext();) {
                        IElementState pes = (IElementState) iter.next();
                        for (Iterator iter2 = intElementStates.iterator(); iter2.hasNext();) {
                            IElementState ces = (IElementState) iter2.next();
                            if (ces.intElemRef == pes.intElemRef)
                                ces.oldEval = pes.oldEval;
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
            esm.setIntentionalElementEvaluation(ies.intElemRef.getDef(), ies.newEval);
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

        // if ( oldEval == Evaluation.EVALUATION_UNDEFINED )
        // esm.setEvaluationForElement( intElemRef.getDef(), null );

        
        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            esm.removeIntentionalElementEvaluation(ies.intElemRef.getDef(), ies.oldEval);
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
