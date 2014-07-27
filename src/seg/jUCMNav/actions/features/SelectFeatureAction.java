package seg.jUCMNav.actions.features;

import fm.Feature;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * This action is used to select one or several features (set numerical evaluation to 100).
 * 
 * @author orujahmadov, gunterm
 * 
 */
public class SelectFeatureAction extends URNSelectionAction {
	public static final String SELECTFEATURE = Messages.getString("SelectFeatureAction.SelectFeature"); //$NON-NLS-1$

	/**
	 * @param part
	 */
	public SelectFeatureAction(IWorkbenchPart part) {
		super(part);
		setId(SELECTFEATURE);
		setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/SelectFeature.gif")); //$NON-NLS-1$
	}

	/**
	 * We need to have an intentional element reference of a feature definition selected when an evaluation is active and the evaluations are not all 100
	 */
	protected boolean calculateEnabled() {
		EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy(); 
		if ( strategy == null)
			return false;

		boolean oneEvaluationNot100 = false;
		for (Iterator<?> iter = getSelectedObjects().iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (!(obj instanceof IntentionalElementEditPart) || 
					(obj instanceof IntentionalElementEditPart) && !(((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef() instanceof Feature)) 
				return false;
			else {
                Evaluation evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef());
                if (evaluation.getStrategies() != strategy || (evaluation.getStrategies() == strategy && evaluation.getEvaluation() != 100)) 
                	oneEvaluationNot100 = true;
			}
		}

		return oneEvaluationNot100;
	}

	public void run() {
		Vector<IntentionalElementRef> intElementRefs = new Vector<IntentionalElementRef>();
		for (Iterator<?> iter = getSelectedObjects().iterator(); iter.hasNext();) {
			IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) iter.next()).getModel());
			intElementRefs.add(ier);
		}
		execute(new ChangeNumericalEvaluationCommand(intElementRefs, 0, 0, getCommandStack()));
		intElementRefs.removeAllElements();
	}

}
