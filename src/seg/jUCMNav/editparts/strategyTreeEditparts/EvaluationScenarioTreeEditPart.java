/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.EvaluationStrategyComponentEditPolicy;

/**
 * TreeEditPart for Scenario in the scenario view
 * 
 * @author Jean-François Roy
 *
 */
public class EvaluationScenarioTreeEditPart extends ScenarioUrnModelElementTreeEditPart {

    /**
     * @param model
     */
    public EvaluationScenarioTreeEditPart(EvaluationStrategy model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new EvaluationStrategyComponentEditPolicy());
    }
    
    public EvaluationStrategy getEvaluationScenario(){
        return (EvaluationStrategy)getModel();
    }
    
    /**
     * Returns the icon 
     */
    protected Image getImage() {
        if (super.getImage() == null) {       
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")).createImage()); //$NON-NLS-1$
        }
        return super.getImage();
    }
}
