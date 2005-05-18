package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.ComponentRef;
import urncore.ComponentElement;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class ComponentRefTreeEditPart extends UcmModelElementTreeEditPart {

	/**
	 * @param model
	 */
	public ComponentRefTreeEditPart(Object model) {
		super(model);
	}
	
	public void activate() {
		super.activate();
		getCompRef().getCompDef().eAdapters().add(this);
	}
	
	public void deactivate() {
		super.deactivate();
		getCompRef().getCompDef().eAdapters().remove(this);
		
	}
	protected String getText() {
		ComponentElement comp = getCompRef().getCompDef();
		return comp.getId() + ": " + comp.getName();
	}
	
	/**
	 * @return
	 */
	private ComponentRef getCompRef() {
		return (ComponentRef)getModel();
	}

	protected Image getImage() {
		return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage();
	}
}
