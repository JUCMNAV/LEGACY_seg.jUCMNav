package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import urn.URNspec;

/**
 * The TreeEditPart associated with URNspec.
 * 
 * @author Etienne Tremblay, jkealey, gunterm
 */
public class URNspecTreeEditPart extends UrnModelElementTreeEditPart {

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
            getURNspec().getGrlspec().eAdapters().add(this);
            getURNspec().getUrndef().eAdapters().add(this);
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
            getURNspec().getGrlspec().eAdapters().remove(this);
            getURNspec().getUrndef().eAdapters().remove(this);
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
        list.addAll(getURNspec().getUrndef().getSpecDiagrams());
        //We want to keep the spec diagram in the order of the tabs
        //Collections.sort(list, new EObjectClassNameComparator());
        //Instead of having all type of definition in the main category, we divided defs in grl and ucm
        list.add(Messages.getString("URNspecTreeEditPart.ucmDefs")); //$NON-NLS-1$
        list.add(Messages.getString("URNspecTreeEditPart.grlDefs")); //$NON-NLS-1$
        list.add(Messages.getString("URNspecTreeEditPart.concerns")); //$NON-NLS-1$
        
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