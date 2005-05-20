package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ResponsibilityComponentEditPolicy;
import urncore.Responsibility;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class ResponsibilityTreeEditPart extends UcmModelElementTreeEditPart {

	/**
	 * @param model
	 */
	public ResponsibilityTreeEditPart(Object model) {
		super(model);
	}

	
	protected Responsibility getResponsibility(){
		return (Responsibility)getModel();
	}	
	protected String getText() {
		return getResponsibility().getId() + ": " + getResponsibility().getName();
	}
	
	protected Image getImage() {
		if(super.getImage() == null)
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage());
		return super.getImage();
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ResponsibilityComponentEditPolicy());
    }		
}
