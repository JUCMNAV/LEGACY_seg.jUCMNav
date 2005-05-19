package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
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
}
