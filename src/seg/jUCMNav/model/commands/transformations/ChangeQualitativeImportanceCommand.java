package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import grl.ImportanceType;
import grl.IntentionalElementRef;
import org.eclipse.gef.commands.Command;

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
        IntentionalElementRef intElemRef;
        ImportanceType oldType, newType;
        int oldValue;
    }

    Vector intElementStates = new Vector();

    public ChangeQualitativeImportanceCommand(List intElemRefs, int id) {
        intElementStates = new Vector();

        for (Iterator iter = intElemRefs.iterator(); iter.hasNext();) {

            IntentionalElementRef currentIERef = (IntentionalElementRef) iter.next();
            IElementState ies = new IElementState();

            ies.intElemRef = currentIERef;
            ies.oldType = currentIERef.getDef().getImportance();
            ies.oldValue = currentIERef.getDef().getImportanceQuantitative(); // store old numerical value for undo operations
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
            EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeImportance(ies.intElemRef.getDef(), ies.newType);
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
            EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeImportance(ies.intElemRef.getDef(), ies.oldType);
            EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance(ies.intElemRef.getDef(), ies.oldValue);
        }

        testPreConditions();
    }

}
