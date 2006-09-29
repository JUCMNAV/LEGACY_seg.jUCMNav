/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.StrategiesGroup;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import ucm.UCMspec;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Condition;

/**
 * This EditPartFactory is associate with the Strategy view
 * 
 * @author Jean-François Roy
 * 
 */
public class StrategyTreeEditPartFactory implements EditPartFactory {

	// the urn spec being edited.
	protected URNspec urn;

	/**
	 * @param urn
	 *            the urnspec to display
	 */
	public StrategyTreeEditPartFactory(URNspec urn) {
		this.urn = urn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof UCMNavMultiPageEditor) {
			return new StrategyRootEditPart((UCMNavMultiPageEditor) model);
		} else if (model instanceof GRLspec) {
			return new GRLspecStrategyTreeEditPart((GRLspec) model);
		} else if (model instanceof UCMspec) {
			return new UCMspecScenarioTreeEditPart((UCMspec) model);
		} else if (model instanceof StrategiesGroup) {
			return new StrategiesGroupTreeEditPart((StrategiesGroup) model);
		} else if (model instanceof ScenarioGroup) {
			return new ScenarioGroupTreeEditPart((ScenarioGroup) model);
		} else if (model instanceof EvaluationStrategy) {
			return new EvaluationStategyTreeEditPart((EvaluationStrategy) model);
		} else if (model instanceof ScenarioDef) {
			return new ScenarioDefTreeEditPart((ScenarioDef) model);
		} else if (model instanceof Variable) {
			return new VariableTreeEditPart((Variable)model);
		} else if (model instanceof Initialization) {
			return new VariableInitializationTreeEditPart((Initialization)model);
		} else if (model instanceof EList) {
			return new VariableListTreeEditPart(urn.getUcmspec());
		} else if (model instanceof ScenarioStartPoint) {
			return new ScenarioPathNodeTreeEditPart((ScenarioStartPoint)model);
		} else if (model instanceof ScenarioEndPoint) {
			return new ScenarioPathNodeTreeEditPart((ScenarioEndPoint)model);			
		} else if (model instanceof Condition) {
			return new ConditionTreeEditPart((Condition)model);			
		} else if (model instanceof String)
	        return new ScenarioLabelTreeEditPart(model, (ScenarioDef) context.getModel());
		 else {
			return null;
		}
	}

}
