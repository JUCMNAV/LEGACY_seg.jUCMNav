package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

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

	protected Image getImage() {
		if(super.getImage() == null)
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage()); //$NON-NLS-1$
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
    
    /* (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getResponsibility().getRespRefs().size()==0)
            ((TreeItem) widget).setForeground(new Color(null, 150, 150, 150));
        else
            ((TreeItem) widget).setForeground(new Color(null, 0, 0, 0));

        super.refreshVisuals();
    }    
}
