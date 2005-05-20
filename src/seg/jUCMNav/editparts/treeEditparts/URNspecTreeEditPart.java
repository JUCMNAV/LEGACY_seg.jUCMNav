package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import urn.URNspec;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class URNspecTreeEditPart extends UcmModelElementTreeEditPart {

	/**
	 * @param model
	 */
	public URNspecTreeEditPart(Object model) {
		super(model);
	}

	protected List getModelChildren() {
		ArrayList list = new ArrayList();
		list.addAll(getURNspec().getUcmspec().getMaps());
		list.add("Components");
		list.add("Responsibilities");
		return list;	}

	/**
	 * @return
	 */
	private URNspec getURNspec() {
		return (URNspec)getModel();
	}

	protected String getText() {
		return getURNspec().getName();
	}
	
	protected Image getImage() {
		if(super.getImage() == null)
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")).createImage());
		return super.getImage();
	}	
	
}
