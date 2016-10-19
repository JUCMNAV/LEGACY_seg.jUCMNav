package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.DynamicContextComponentEditPolicy;
import ucm.scenario.ScenarioDef;

/**
 * TreeEditPart for a scenario in the dynamic context view
 * 
 * @author aprajita
 * 
 */
public class ScenarioDefTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the scenario
     */
    public ScenarioDefTreeEditPart(ScenarioDef model) {
        super(model);
    }
    
    /**
     * Listens to the model element.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {

        if (!isActive() && getScenario() != null)
            getScenario().eAdapters().add(this);
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DynamicContextComponentEditPolicy()); // deletion
    }

    /**
     * 
     * Stops listening to the model element and destroys image.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getScenario() != null) {
            getScenario().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon for a scenario
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/grlstrat16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * 
     * @return the actual {@link ScenarioDef}
     */
    private ScenarioDef getScenario() {
    	ScenarioDef scenario = null;
        scenario = (ScenarioDef) getModel();
        return scenario;
    }

    /**
     * Returns the scenario's name and sets the label as grayed out if it is inherited {@link #isInherited()}
     */
    protected String getText() {
        if (widget != null && !widget.isDisposed()) {
        	((TreeItem) widget).setForeground(BLACK);
        }

        return super.getText();
    }
}
