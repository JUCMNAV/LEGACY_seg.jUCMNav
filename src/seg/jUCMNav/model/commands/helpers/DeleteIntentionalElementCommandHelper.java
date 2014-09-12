package seg.jUCMNav.model.commands.helpers;

import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIModelLink;

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;

import seg.jUCMNav.views.preferences.DeletePreferences;
import urn.URNlink;

public class DeleteIntentionalElementCommandHelper extends CompoundCommand {

    
    IntentionalElement element;

    /**
     * @param element
     *            The intentionalElement to delete
     */
    public DeleteIntentionalElementCommandHelper(IntentionalElement element) {
   //     setLabel(Messages.getString("DeleteIntentionalElementCommand.deleteIntentionalElement")); //$NON-NLS-1$
        this.element = element;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommandList().size() == 0) {
            return true;
        }
        return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommandList().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    private void deleteElementLink() {
        if (element.getLinksDest().size() > 0 || element.getLinksSrc().size() > 0) {
            for (int i = 0; i < element.getLinksDest().size(); i++) {
                ElementLink link = (ElementLink) element.getLinksDest().get(i);
                append(new DeleteElementLinkCommandHelper(link));
            }
            for (int i = 0; i < element.getLinksSrc().size(); i++) {
                ElementLink link = (ElementLink) element.getLinksSrc().get(i);
                append(new DeleteElementLinkCommandHelper(link));
            }
        }
    }

    private void deleteKPIModelLink() {
        if (element instanceof Indicator) {
            Indicator ind = (Indicator) element;

            if (ind.getKpiModelLinksDest().size() > 0) {
                for (int i = 0; i < ind.getKpiModelLinksDest().size(); i++) {
                    KPIModelLink link = (KPIModelLink) ind.getKpiModelLinksDest().get(i);
                    append(new DeleteKPIModelLinkCommandHelper(link));
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
                    append(new DeleteEvaluationCommandHelper(eval));
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
        
        System.out.println(element.getRefs().size());

        // Verify if the definition can be delete.
        if (element.getRefs().size() == 0 ) {
            // Delete all the URNlink
            System.out.println("Here");
            for (Iterator it = element.getFromLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                append(new DeleteURNlinkCommandHelper(link));
            }
            for (Iterator it = element.getToLinks().iterator(); it.hasNext();) {
                URNlink link = (URNlink) it.next();
                append(new DeleteURNlinkCommandHelper(link));
            }
            deleteEvaluations();

            // Delete all the ElementLink associate with the IntentionalElement
            deleteElementLink();

            // Delete all the KPIModelLink associate with the IntentionalElement
            deleteKPIModelLink();

            // Delete of all the references
            for (Iterator it = element.getRefs().iterator(); it.hasNext();) {
                IntentionalElementRef reference = (IntentionalElementRef) it.next();
                append(new PreDeleteUrnModelElementCommandHelper(reference));
                append(new RemoveURNmodelElementCommandHelper(reference));
            }

            append(new RemoveIntentionalElementCommandHelper(element));
        }

    }
    
    
}
