package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import grl.ContributionContext;
import grl.EvaluationStrategy;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.strategyTreeEditparts.ContributionContextLabelTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioLabelTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyLabelTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.TimepointGroupListTreeEditPart;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * This EditPartFactory is associate with the Dynamic Context view
 * 
 * @author aprajita
 * 
 */
public class DynamicContextTreeEditPartFactory implements EditPartFactory {
	
	// the urn spec being edited.
    protected URNspec urn;

    /**
     * @param urn
     *            the urnspec to display
     */
    public DynamicContextTreeEditPartFactory(URNspec urn) {
        this.urn = urn;
    }
    /**
     * Creates a new editpart for a model element.
     * 
     * @param context
     *            used only for the creation of a {@link ScenarioLabelTreeEditPart} because we need to know its parent
     * @param model
     *            the element for which we are creating an edit part
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor) {
            return new DynamicContextRootEditPart((UCMNavMultiPageEditor) model);
        } else if (model instanceof URNspec) {
            return new URNspecDynamicContextTreeEditPart((URNspec) model);
        } else if (model instanceof EList && model == urn.getTimepointGroups()) {
            return new TimepointGroupListTreeEditPart(urn);
        } else if (model instanceof DynamicContextGroup) {
            return new DynamicContextGroupTreeEditPart((DynamicContextGroup) model);
        } else if (model instanceof DynamicContext) {
            return new DynamicContextTreeEditPart((DynamicContext) model);
        } else if (model instanceof TimepointGroup) {
            return new TimepointGroupTreeEditPart((TimepointGroup) model);
        } else if (model instanceof Timepoint) {
            return new TimepointTreeEditPart((Timepoint) model);
        } else if (model instanceof EvaluationStrategy) {
            return new seg.jUCMNav.editparts.dynamicContextTreeEditparts.EvaluationStrategyTreeEditPart((EvaluationStrategy) model);
        } else if (model instanceof ScenarioDef) {
            return new seg.jUCMNav.editparts.dynamicContextTreeEditparts.ScenarioDefTreeEditPart((ScenarioDef) model);
        } else if (model instanceof ContributionContext) {
            return new seg.jUCMNav.editparts.dynamicContextTreeEditparts.ContributionContextTreeEditPart((ContributionContext) model);
        } else if (model instanceof Change) {
            return new seg.jUCMNav.editparts.dynamicContextTreeEditparts.ChangeTreeEditPart((Change) model);
        } else if (model instanceof String)
            if (context.getModel() instanceof EvaluationStrategy)
                return new StrategyLabelTreeEditPart(model, (EvaluationStrategy) context.getModel());
            else if (context.getModel() instanceof ContributionContext)
                return new ContributionContextLabelTreeEditPart(model, (ContributionContext) context.getModel());
            else
                return new DynamicContextLabelTreeEditPart(model, (DynamicContext) context.getModel());

        else {
        	return null;
            
            
        }
    }

}
