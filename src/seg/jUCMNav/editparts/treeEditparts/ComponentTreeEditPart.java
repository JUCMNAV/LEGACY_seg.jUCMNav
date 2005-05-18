package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
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
		return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage();
	}
}
