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
import ucm.scenario.EnumerationType;

/**
 * This class is the root edit part for an enumeration type.  
 * 
 * @author jkealey
 *
 */
public class EnumerationTypeTreeEditPart extends StrategyUrnModelElementTreeEditPart {
    
    /**
     * @param model
     *          The EnumerationType model
     */
    public EnumerationTypeTreeEditPart(EnumerationType model) {
        super(model);
    }

    /**
     * Listens to Variable
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getEnumerationType().eAdapters().add(this);
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
        	getEnumerationType().eAdapters().remove(this);
        }
        super.deactivate();
    }
    
    /**
     * @return the icon associated with URNspec
     */
    protected Image getImage() {
		if (super.getImage() == null) {
			setImage(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ISR16.gif").createImage()); //$NON-NLS-1$
		}
		return super.getImage();
    }
    
    /**
	 * Variables have no children.
	 * 
	 * @return empty list
	 */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    private EnumerationType getEnumerationType(){
        return (EnumerationType)getModel();
    }
    
    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return getEnumerationType().getName();
    }
}
