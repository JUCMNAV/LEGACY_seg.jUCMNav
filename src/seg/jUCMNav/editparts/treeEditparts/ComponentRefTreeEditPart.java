package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ComponentRefComponentEditPolicy;
import ucm.map.ComponentRef;
import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;

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
            return "ref " + getCompRef().getId() + ": " + comp.getName(); //$NON-NLS-1$ //$NON-NLS-2$
        else
            return "ref " + getCompRef().getId(); //$NON-NLS-1$
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
        ComponentElement comp = getCompRef().getCompDef();
        if (super.getImage() == null) {
            if (comp instanceof Component) {
                switch (((Component) comp).getKind().getValue()) {
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
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage()); //$NON-NLS-1$
        }
        return super.getImage();
    }
}