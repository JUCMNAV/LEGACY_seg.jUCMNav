package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import urn.URNspec;

/**
 * The TreeEditPart associated with URNspec.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class URNspecTreeEditPart extends UcmModelElementTreeEditPart {

    /**
     * @param model
     *            the URNspec being edited.
     */
    public URNspecTreeEditPart(URNspec model) {
        super(model);
    }

    /**
     * Listens to both the URNspec and the UCMspec
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getURNspec().getUcmspec().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * Stops listening to both the URNspec and the UCMspec.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getURNspec().getUcmspec().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with URNspec
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")).createImage()); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of maps and the component and responsibility definition labels
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getURNspec().getUcmspec().getMaps());
        Collections.sort(list, new EObjectClassNameComparator());
        list.add(Messages.getString("URNspecTreeEditPart.components")); //$NON-NLS-1$
        list.add(Messages.getString("URNspecTreeEditPart.responsibilities")); //$NON-NLS-1$
        return list;

    }

    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return getURNspec().getName();
    }

    /**
     * @return the URNspec being edited.
     */
    private URNspec getURNspec() {
        return (URNspec) getModel();
    }

}