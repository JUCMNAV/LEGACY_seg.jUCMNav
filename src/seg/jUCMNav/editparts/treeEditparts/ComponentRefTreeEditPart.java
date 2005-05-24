package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ComponentRefComponentEditPolicy;
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
        if (getCompRef().getCompDef() != null)
            getCompRef().getCompDef().eAdapters().add(this);
    }

    public void deactivate() {
        super.deactivate();
        if (getCompRef().getCompDef() != null)
            getCompRef().getCompDef().eAdapters().remove(this);
    }

    protected String getText() {
        ComponentElement comp = getCompRef().getCompDef();
        if (comp != null)
            return "ref " + getCompRef().getId() + ": " + comp.getName();
        else
            return "ref " + getCompRef().getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentRefComponentEditPolicy());
    }

    /**
     * @return
     */
    private ComponentRef getCompRef() {
        return (ComponentRef) getModel();
    }

    protected Image getImage() {
        if (super.getImage() == null)
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage());
        return super.getImage();
    }
}