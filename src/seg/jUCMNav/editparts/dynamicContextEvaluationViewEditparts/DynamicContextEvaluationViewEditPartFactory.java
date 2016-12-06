package seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import urn.dyncontext.DynamicContext;
import urn.dyncontext.TimepointGroup;
/**
 * The DynamicContextEvaluationView EditPart Factory
 * 
 * @author aprajita
 * 
 */
public class DynamicContextEvaluationViewEditPartFactory implements EditPartFactory {
	
	public DynamicContextEvaluationViewEditPartFactory() {
    }

    public EditPart createEditPart(EditPart context, Object model) {
    	if (model instanceof TimepointGroup) {
            return new DynamicContextEvaluationViewTimepointGroupEditPart((TimepointGroup) model);
        } else if (model instanceof DynamicContext) {
            return new DynamicContextEvaluationViewDCEditPart((DynamicContext) model);
        } else if (model instanceof DynamicContextEvaluationViewObject) {
            return new DynamicContextEvaluationViewObjectEditPart((DynamicContextEvaluationViewObject) model);
        } else {
            return new DynamicContextEvaluationViewRootEditPart(model);
        }
    }

}
