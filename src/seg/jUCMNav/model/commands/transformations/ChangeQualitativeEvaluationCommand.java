package seg.jUCMNav.model.commands.transformations;

import grl.IntentionalElementRef;
import grl.QualitativeLabel;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * 
 * @author Andrew Miga
 */

public class ChangeQualitativeEvaluationCommand extends Command implements JUCMNavCommand {
    private boolean removed = false;
    EvaluationStrategyManager esm;

    private final static QualitativeLabel values[] = { QualitativeLabel.SATISFIED_LITERAL, QualitativeLabel.WEAKLY_SATISFIED_LITERAL,
            QualitativeLabel.NONE_LITERAL, QualitativeLabel.WEAKLY_DENIED_LITERAL, QualitativeLabel.DENIED_LITERAL, QualitativeLabel.CONFLICT_LITERAL,
            QualitativeLabel.UNKNOWN_LITERAL };

    public final static int INCREASE = values.length;
    public final static int DECREASE = values.length + 1;

    private class IElementState {
        IntentionalElementRef intElemRef;
        QualitativeLabel oldQeval, newQeval;
        int oldEval;
    }

    Vector intElementStates = new Vector();

    public ChangeQualitativeEvaluationCommand(List intElemRefs, int id) {
        setLabel(Messages.getString("UrnContextMenuProvider.SetQualitativeEvaluation")); //$NON-NLS-1$

        esm = EvaluationStrategyManager.getInstance();

        for (Iterator iter = intElemRefs.iterator(); iter.hasNext();) {

            IntentionalElementRef currentIERef = (IntentionalElementRef) iter.next();
            IElementState ies = new IElementState();

            ies.intElemRef = currentIERef;
            ies.oldQeval = esm.getEvaluationObject(currentIERef.getDef()).getQualitativeEvaluation();
            ies.oldEval = esm.getEvaluation(currentIERef.getDef()); // store old numerical value for undo operations
            intElementStates.add(ies);

            if (id < INCREASE) // input from sub-menu
                ies.newQeval = values[id];
            else if (id == INCREASE) // increase evaluation if possible
                ies.newQeval = values[index(ies.oldQeval) - 1];
            else if (id == DECREASE) // decrease evaluation if possible
                ies.newQeval = values[index(ies.oldQeval) + 1];
        }
    }

    public void execute() {
        redo();
    }

    public void redo() {
        testPreConditions();

        for (Iterator iter = intElementStates.iterator(); iter.hasNext();) {
            IElementState ies = (IElementState) iter.next();
            esm.setIntentionalElementQualitativeEvaluation(ies.intElemRef.getDef(), ies.newQeval);
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
            esm.setIntentionalElementQualitativeEvaluation(ies.intElemRef.getDef(), ies.oldQeval);
            esm.setIntentionalElementEvaluation(ies.intElemRef.getDef(), ies.oldEval);
        }

        testPreConditions();
    }

    private int index(QualitativeLabel ql) {
        for (int i = 0; i < values.length; i++) {
            if (ql == values[i])
                return i;
        }
        return -1;
    }

}
