package seg.jUCMNav.editparts.treeEditparts;

import grl.ActorRef;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ActorRefComponentEditPolicy;
import seg.jUCMNav.views.property.ContainerPropertySource;

/**
 * Tree edit part for the actor ref
 * 
 * @author Jean-François Roy
 * 
 */
public class ActorRefTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the actor ref
     */
    public ActorRefTreeEditPart(ActorRef model) {
        super(model);
    }

    /**
     * Listens to reference and definition.
     */
    public void activate() {
        super.activate();
        if (getActorRef().getContDef() != null)
            getActorRef().getContDef().eAdapters().add(this);
    }

    /**
     * Stops listening to reference and definition.
     */
    public void deactivate() {
        super.deactivate();
        if (getActorRef().getContDef() != null)
            getActorRef().getContDef().eAdapters().remove(this);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActorRefComponentEditPolicy());
    }

    /**
     * @return the actor ref model element.
     */
    private ActorRef getActorRef() {
        return (ActorRef) getModel();
    }

    /**
     * Returns the icon appropriate
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/GRLActor16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * Returns a ContainerPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new ContainerPropertySource(getActorRef());

        return propertySource;
    }
}
