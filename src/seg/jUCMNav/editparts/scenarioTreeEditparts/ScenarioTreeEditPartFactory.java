/**
 * 
 */
package seg.jUCMNav.editparts.scenarioTreeEditparts;

import grl.EvaluationGroup;
import grl.EvaluationScenario;
import grl.GRLspec;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import urn.URNspec;

/**
 * This EditPartFactory is associate with the Scenario view
 * 
 * @author Jean-François Roy
 *
 */
public class ScenarioTreeEditPartFactory implements EditPartFactory {

    // the urn spec being edited.
    protected URNspec urn;

    /**
     * @param urn
     *            the urnspec to display
     */
    public ScenarioTreeEditPartFactory(URNspec urn) {
        this.urn = urn;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor){
            return new ScenarioRootEditPart((UCMNavMultiPageEditor)model);
        } else if (model instanceof GRLspec){
            return new GRLspecScenarioTreeEditPart((GRLspec) model);
        } else if (model instanceof EvaluationGroup){
            return new EvaluationGroupTreeEditPart((EvaluationGroup) model);
        } else if (model instanceof EvaluationScenario){
            return new EvaluationScenarioTreeEditPart((EvaluationScenario) model);
        } else {
            return null;
        }
    }

}
