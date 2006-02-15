/**
 * 
 */
package seg.jUCMNav.actions;

import grl.Evaluation;
import grl.EvaluationScenario;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.delete.DeleteEvaluationCommand;
import seg.jUCMNav.model.util.EvaluationScenarioManager;

/**
 * @author Jean-François Roy
 *
 */
public class DeleteEvaluationAction extends URNSelectionAction {

    public static final String DELETEEVALUATION = "Delete User Evaluation";
    private Evaluation evaluation;
    /**
     * @param part
     */
    public DeleteEvaluationAction(IWorkbenchPart part) {
        super(part);
        setId(DELETEEVALUATION);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ScenarioView16.gif")); //$NON-NLS-1$
    }

    /**
     * We need an evaluation set.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
            IntentionalElementRef selection = sel.getIntentionalelementref();
            EvaluationScenario scenario = EvaluationScenarioManager.getInstance().getEvaluationScenario();
            if (scenario != null){
                evaluation = EvaluationScenarioManager.getInstance().getEvaluationObject(selection.getDef());
                if (evaluation.getScenario() == scenario){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * @return Builds the command to delete the evaluation
     */
    protected Command getCommand() {
        return  new DeleteEvaluationCommand(evaluation);
    }
}
