package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ComponentElementComponentEditPolicy;
import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;

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

    protected ComponentElement getComp() {
        return (ComponentElement) getModel();
    }

    protected String getText() {
        return getComp().getId() + ": " + getComp().getName(); //$NON-NLS-1$
    }

    protected Image getImage() {
        if (super.getImage() == null) {
            if (getComp() instanceof Component) {
                switch (((Component) getComp()).getKind().getValue()) {
                case ComponentKind.AGENT:
                    setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Agent16.gif")).createImage()); //$NON-NLS-1$
                    break;
                case ComponentKind.ACTOR:
                    setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Actor16.gif")).createImage()); //$NON-NLS-1$
                    break;
                case ComponentKind.OBJECT:
                    setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Object16.gif")).createImage()); //$NON-NLS-1$
                    break;
                case ComponentKind.PROCESS:
                    setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Process16.gif")).createImage()); //$NON-NLS-1$
                    break;
                default:
                    setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage()); //$NON-NLS-1$
                }
            } else
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage()); //$NON-NLS-1$		return super.getImage();
        }
        return super.getImage();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentElementComponentEditPolicy());
    }
}