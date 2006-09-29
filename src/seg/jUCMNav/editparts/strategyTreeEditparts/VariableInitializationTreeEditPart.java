/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.VariableComponentEditPolicy;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.Initialization;
import ucm.scenario.Variable;

/**
 * This class is the edit part for a variable initialization.  
 * 
 * @author jkealey
 *
 */
public class VariableInitializationTreeEditPart extends StrategyUrnModelElementTreeEditPart {
    
    /**
     * @param model
     *          The Initialization model
     */
    public VariableInitializationTreeEditPart(Initialization model) {
        super(model);
    }

    /**
     * Listens to Initialization
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
        	getInitialization().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new VariableComponentEditPolicy());
    }
    
    /**
     * Stops listening to the variable. 
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
        	getInitialization().eAdapters().remove(this);
        }
        super.deactivate();
    }
    
    /**
     * @return the icon associated with URNspec
     */
    protected Image getImage() {
		if (super.getImage() == null) {
			if (ScenarioUtils.sTypeBoolean.equals(getVariable().getType())) {
				setImage(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif").createImage()); //$NON-NLS-1$
			} else if (ScenarioUtils.sTypeInteger.equals(getVariable().getType())) {
				setImage(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/GoalTag16.gif").createImage()); //$NON-NLS-1$
			} else {
				setImage(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ISR16.gif").createImage()); //$NON-NLS-1$
			}
		}
		return super.getImage();
    }
    
    /**
	 * Variables Initialization have no children.
	 * 
	 * @return empty list
	 */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    private Initialization getInitialization() {
    	return (Initialization)getModel();
    }
    private Variable getVariable(){
        return getInitialization().getVariable();
    }
    
    /**
     * @return the Initialization name.
     */
    protected String getText() {
    	if (getVariable()==null) 
    		return "";
    	else 
    		return getVariable().getName() + "=" + getInitialization().getValue();
    }
}
