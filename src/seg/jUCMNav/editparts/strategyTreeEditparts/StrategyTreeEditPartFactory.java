package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.StrategiesGroup;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.QualitativeMapping;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import ucm.UCMspec;
import ucm.scenario.EnumerationType;
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
 * @author Jean-Fran�ois Roy
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

    /**
     * Creates a new editpart for a model element.
     * 
     * @param context
     *            {@link ScenarioDef} used only for the creation of a {@link ScenarioLabelTreeEditPart} because we need to know its parent
     * @param model
     *            the element for which we are creating an edit part
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
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
            return new EvaluationStrategyTreeEditPart((EvaluationStrategy) model);
        } else if (model instanceof ScenarioDef) {
            return new ScenarioDefTreeEditPart((ScenarioDef) model);
        } else if (model instanceof Variable) {
            return new VariableTreeEditPart((Variable) model);
        } else if (model instanceof EnumerationType) {
            return new EnumerationTypeTreeEditPart((EnumerationType) model);
        } else if (model instanceof Initialization) {
            return new VariableInitializationTreeEditPart((Initialization) model);
        } else if (model instanceof EList) {
            if (model == urn.getGrlspec().getContributionGroups())
                return new ContributionContextGroupListTreeEditPart(urn.getGrlspec());
            else if (model == urn.getGrlspec().getKPIConversion())
                return new KPIConversionListTreeEditPart(urn.getGrlspec());
            else
                return new VariableListTreeEditPart(urn.getUcmspec(), urn.getUcmspec().getEnumerationTypes() == model);
        } else if (model instanceof KPIConversion) {
            return new KPIConversionTreeEditPart((KPIConversion) model);
        } else if (model instanceof QualitativeMapping) {
            return new QualitativeMappingTreeEditPart((QualitativeMapping) model);
        } else if (model instanceof ScenarioStartPoint) {
            return new ScenarioPathNodeTreeEditPart((ScenarioStartPoint) model);
        } else if (model instanceof ScenarioEndPoint) {
            return new ScenarioPathNodeTreeEditPart((ScenarioEndPoint) model);
        } else if (model instanceof Condition) {
            return new ConditionTreeEditPart((Condition) model);
        } else if (model instanceof ContributionContextGroup) {
            return new ContributionContextGroupTreeEditPart((ContributionContextGroup) model);
        } else if (model instanceof ContributionContext) {
            return new ContributionContextTreeEditPart((ContributionContext) model);
        } else if (model instanceof ContributionChange) {
            return new ContributionChangeTreeEditPart((ContributionChange) model);
        } else if (model instanceof String)
            if (context.getModel() instanceof EvaluationStrategy)
                return new StrategyLabelTreeEditPart(model, (EvaluationStrategy) context.getModel());
            else if (context.getModel() instanceof ContributionContext)
                return new ContributionContextLabelTreeEditPart(model, (ContributionContext) context.getModel());
            else
                return new ScenarioLabelTreeEditPart(model, (ScenarioDef) context.getModel());

        else {
            return null;
        }
    }

}
