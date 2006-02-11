/**
 * 
 */
package seg.jUCMNav.editparts.scenarioTreeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import grl.EvaluationScenario;

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
    public EvaluationScenarioTreeEditPart(EvaluationScenario model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
    }
    
    public EvaluationScenario getEvaluationScenario(){
        return (EvaluationScenario)getModel();
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
