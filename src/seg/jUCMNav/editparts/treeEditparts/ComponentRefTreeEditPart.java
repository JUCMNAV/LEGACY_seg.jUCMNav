package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.element.ComponentRefComponentEditPolicy;
import seg.jUCMNav.views.property.ContainerPropertySource;
import ucm.map.ComponentRef;
import urncore.Component;
import urncore.ComponentKind;

/**
 * TreeEditPart for ComponentRefs
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ComponentRefTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the componentRef
     */
    public ComponentRefTreeEditPart(ComponentRef model) {
        super(model);
    }

    /**
     * Listens to reference and definition.
     */
    public void activate() {
        super.activate();
        if (getCompRef().getContDef() != null)
            getCompRef().getContDef().eAdapters().add(this);
    }

    /**
     * Stops listening to reference and definition.
     */
    public void deactivate() {
        super.deactivate();
        if (getCompRef().getContDef() != null)
            getCompRef().getContDef().eAdapters().remove(this);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentRefComponentEditPolicy());
    }

    /**
     * @return the component ref model element.
     */
    private ComponentRef getCompRef() {
        return (ComponentRef) getModel();
    }

    /**
     * Returns the icon appropriate for the definition's kind
     */
    protected Image getImage() {
        Component comp = (Component) getCompRef().getContDef();
        if (super.getImage() == null) {
            if (comp instanceof Component) {
                setImage(ComponentTreeEditPart.getComponentImage(((Component) comp).getKind()));
            } else {
                setImage(ComponentTreeEditPart.getComponentImage(ComponentKind.TEAM_LITERAL));
            }
        }
        return super.getImage();
    }

    /**
     * Change image if kind changes.
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() instanceof EStructuralFeature) {
            EStructuralFeature structuralFeature = (EStructuralFeature) notification.getFeature();
            if (structuralFeature.getEType().getInstanceClass() == ComponentKind.class) {
                // next getImage() will refresh it. (refreshVisuals() in parent will do it)
                // if (getImage() != null) {
                // getImage().dispose();
                // setImage(null);
                // }
            }
        }
        super.notifyChanged(notification);
    }

    /**
     * Returns a ContainerPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new ContainerPropertySource(getCompRef());

        return propertySource;
    }
}