package seg.jUCMNav.actions.features;

import fm.Feature;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.delete.DeleteEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * This action is used to unselect a feature (removes user-defined numerical evaluation).
 * 
 * @author orujahmadov, gunterm
 * 
 */
public class UnselectFeatureAction extends URNSelectionAction {
    public static final String UNSELECTFEATURE = Messages.getString("UnselectFeatureAction.UnselectFeature"); //$NON-NLS-1$
    private Evaluation evaluation;

    /**
     * @param part
     */
    public UnselectFeatureAction(IWorkbenchPart part) {
        super(part);
        setId(UNSELECTFEATURE);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/delete16.gif")); //$NON-NLS-1$
    }

	/**
	 * We need to have an intentional element reference of a feature definition selected when an evaluation is active and a user defined evaluation exists
	 */
    protected boolean calculateEnabled() {
		if (getSelectedObjects().size() != 1)
			return false;
	
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF && (sel.getIntentionalElementRef().getDef() instanceof Feature)) {
            IntentionalElementRef selection = sel.getIntentionalElementRef();
            EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
            if (strategy != null) {
                evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(selection.getDef());
                if (evaluation.getStrategies() == strategy) {
                    return true;
                }
            }
        }
        return false;
    }

	public void run() {
		execute(new DeleteEvaluationCommand(evaluation));
	}
	
}

