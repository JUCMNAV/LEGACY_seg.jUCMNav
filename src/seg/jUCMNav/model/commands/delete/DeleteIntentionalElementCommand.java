/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIModelLink;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveIntentionalElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import urn.URNlink;

/**
 * Delete an IntentionalElement (including all the ElementLink associate to it)
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class DeleteIntentionalElementCommand extends CompoundCommand {

    IntentionalElement element;

    /**
     * @param element
     *            The intentionalElement to delete
     */
    public DeleteIntentionalElementCommand(IntentionalElement element) {
        setLabel(Messages.getString("DeleteIntentionalElementCommand.deleteIntentionalElement")); //$NON-NLS-1$
        this.element = element;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0) {
            return true;
        }
        return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    private void deleteElementLink() {
        if (element.getLinksDest().size() > 0 || element.getLinksSrc().size() > 0) {
            for (int i = 0; i < element.getLinksDest().size(); i++) {
                ElementLink link = (ElementLink) element.getLinksDest().get(i);
                add(new DeleteElementLinkCommand(link));
            }
            for (int i = 0; i < element.getLinksSrc().size(); i++) {
                ElementLink link = (ElementLink) element.getLinksSrc().get(i);
                add(new DeleteElementLinkCommand(link));
            }
        }
    }

    private void deleteKPIModelLink() {
        if (element instanceof Indicator) {
            Indicator ind = (Indicator) element;

            if (ind.getKpiModelLinksDest().size() > 0) {
                for (int i = 0; i < ind.getKpiModelLinksDest().size(); i++) {
                    KPIModelLink link = (KPIModelLink) ind.getKpiModelLinksDest().get(i);
                    add(new DeleteKPIModelLinkCommand(link));
                }
            }
        }
    }

    // TODO Fix delete Evaluations.
    // A modification is required in the metamodel to implement a bidirectional links between Intentional Elements and Evaluations (instead of unidirectional).
    // Should access the evaluation to delete using this association.
    private void deleteEvaluations() {
        for (Iterator it = element.getGrlspec().getStrategies().iterator(); it.hasNext();) {
            EvaluationStrategy strategy = (EvaluationStrategy) it.next();
            for (Iterator itEval = strategy.getEvaluations().iterator(); itEval.hasNext();) {
                Evaluation eval = (Evaluation) itEval.next();
                if (eval.getIntElement() != null && eval.getIntElement().equals(element)) {
                    add(new DeleteEvaluationCommand(eval));
                }
            }
        }
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Builds a sequence of DeleteGRLNodeCommands
     * 
     */
    private void build() {

        // Verify if the definition can be delete.
        if (element.getRefs().size() == 0 || DeletePreferences.getDeleteReference(element)) {
            // Delete all the URNlink
            for (Iterator it = element.getFromLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            for (Iterator it = element.getToLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                add(new DeleteURNlinkCommand(link));
            }
            deleteEvaluations();

            // Delete all the ElementLink associate with the IntentionalElement
            deleteElementLink();

            // Delete all the KPIModelLink associate with the IntentionalElement
            deleteKPIModelLink();

            // Delete of all the references
            for (Iterator it = element.getRefs().iterator(); it.hasNext();) {
                IntentionalElementRef reference = (IntentionalElementRef) it.next();
                add(new PreDeleteUrnModelElementCommand(reference));
                add(new RemoveURNmodelElementCommand(reference));
            }

            add(new RemoveIntentionalElementCommand(element));
        }

    }
}
