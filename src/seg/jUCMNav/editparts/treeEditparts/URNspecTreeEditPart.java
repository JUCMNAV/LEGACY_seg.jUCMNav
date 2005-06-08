package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
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
	
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()){
            getURNspec().eAdapters().add(this);
            getURNspec().getUcmspec().eAdapters().add(this);
        }
        super.activate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getURNspec().eAdapters().remove(this);
            getURNspec().getUcmspec().eAdapters().remove(this);
            if (image != null)
                image.dispose();
        }
        super.deactivate();
    }	
	
	protected Image getImage() {
		if(super.getImage() == null)
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")).createImage()); //$NON-NLS-1$
		return super.getImage();
	}	

	protected List getModelChildren() {
		ArrayList list = new ArrayList();
		list.addAll(getURNspec().getUcmspec().getMaps());
		list.add(Messages.getString("URNspecTreeEditPart.components")); //$NON-NLS-1$
		list.add(Messages.getString("URNspecTreeEditPart.responsibilities")); //$NON-NLS-1$
		return list;	}

	protected String getText() {
		return getURNspec().getName();
	}

	/**
	 * @return
	 */
	private URNspec getURNspec() {
		return (URNspec)getModel();
	}
	
}
