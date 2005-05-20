package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ComponentElementComponentEditPolicy;
import urncore.ComponentElement;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class ComponentTreeEditPart extends UcmModelElementTreeEditPart {

	/**
	 * @param model
	 */
	public ComponentTreeEditPart(Object model) {
		super(model);
	}
	
	protected ComponentElement getComp(){
		return (ComponentElement)getModel();
	}

	protected String getText() {
		return getComp().getId() + ": " + getComp().getName();
	}
	
	protected Image getImage() {
		if(super.getImage() == null)
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage());
		return super.getImage();
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentElementComponentEditPolicy());
    }	
}
