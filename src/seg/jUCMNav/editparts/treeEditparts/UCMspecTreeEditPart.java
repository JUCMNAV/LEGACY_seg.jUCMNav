/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import ucm.UCMspec;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UCMspecTreeEditPart extends UcmModelElementTreeEditPart {

	/**
	 * @param model
	 */
	public UCMspecTreeEditPart(Object model) {
		super(model);
	}
	
	public List getModelChildren() {
		ArrayList list = new ArrayList();
		list.addAll(getUCMspec().getMaps());
		list.add(Messages.getString("UCMspecTreeEditPart.components")); //$NON-NLS-1$
		list.add(Messages.getString("UCMspecTreeEditPart.responsibility")); //$NON-NLS-1$
		return list;
	}
	
	/**
	 * @return
	 */
	private UCMspec getUCMspec() {
		return (UCMspec)getModel();
	}

	protected String getText() {
		return Messages.getString("UCMspecTreeEditPart.ucmSpec"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
	 */
	protected Image getImage() {
		if(super.getImage() == null)
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage()); //$NON-NLS-1$
		return super.getImage();
	}

}
