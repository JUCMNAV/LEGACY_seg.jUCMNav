/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.StrategiesGroup;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import urn.URNspec;

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

    /* (non-Javadoc)
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor){
            return new StrategyRootEditPart((UCMNavMultiPageEditor)model);
        } else if (model instanceof GRLspec){
            return new GRLspecStrategyTreeEditPart((GRLspec) model);
        } else if (model instanceof StrategiesGroup){
            return new StrategiesGroupTreeEditPart((StrategiesGroup) model);
        } else if (model instanceof EvaluationStrategy){
            return new EvaluationStategyTreeEditPart((EvaluationStrategy) model);
        } else {
            return null;
        }
    }

}
