/**
 * 
 */
package seg.jUCMNav.actions;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.delete.DeleteEvaluationCommand;
import seg.jUCMNav.model.util.EvaluationStrategyManager;

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
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/StrategyView16.gif")); //$NON-NLS-1$
    }

    /**
     * We need an evaluation set.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
            IntentionalElementRef selection = sel.getIntentionalelementref();
            EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
            if (strategy != null){
                evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(selection.getDef());
                if (evaluation.getStrategies() == strategy){
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
