package seg.jUCMNav.editparts.treeEditparts;

import fm.Feature;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;
import seg.jUCMNav.views.property.IntentionalElementPropertySource;

/**
 * TreeEditPart for IntentionalElementRef
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class IntentionalElementRefTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the IntentionalElementRef
     */
    public IntentionalElementRefTreeEditPart(IntentionalElementRef model) {
        super(model);
    }

    /**
     * Listens to both reference and definition.
     */
    public void activate() {
        super.activate();
        if (getIntentionalElementRef().getDef() != null)
            getIntentionalElementRef().getDef().eAdapters().add(this);
    }

    /**
     * Stops listening to both reference and definition.
     */
    public void deactivate() {
        super.deactivate();
        if (getIntentionalElementRef().getDef() != null)
            getIntentionalElementRef().getDef().eAdapters().remove(this);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
    }

    /**
     * return the intentionalElementRef associated with this edit part
     */
    protected IntentionalElementRef getIntentionalElementRef() {
        return (IntentionalElementRef) getModel();
    }

    /**
     * Returns an image representing the Intentional element.
     */
    protected Image getImage() {

        IntentionalElementRef element = getIntentionalElementRef();

        if (super.getImage() == null && element.getDef() != null) {
            if (element.getDef().getType().getValue() == IntentionalElementType.GOAL)
                setImage((JUCMNavPlugin.getImage("icons/Goal16.gif"))); //$NON-NLS-1$
            else if (element.getDef().getType().getValue() == IntentionalElementType.SOFTGOAL)
                setImage((JUCMNavPlugin.getImage("icons/Softgoal16.gif"))); //$NON-NLS-1$
            else if (element.getDef().getType().getValue() == IntentionalElementType.TASK)
            	if (element.getDef() instanceof Feature)
            		setImage((JUCMNavPlugin.getImage("icons/Feature16.gif"))); //$NON-NLS-1$
            	else
            		setImage((JUCMNavPlugin.getImage("icons/Task16.gif"))); //$NON-NLS-1$
            else if (element.getDef().getType().getValue() == IntentionalElementType.RESSOURCE)
                setImage((JUCMNavPlugin.getImage("icons/Resource16.gif"))); //$NON-NLS-1$
            else if (element.getDef().getType().getValue() == IntentionalElementType.INDICATOR)
                setImage((JUCMNavPlugin.getImage("icons/Indicator16.gif"))); //$NON-NLS-1$
        }

        return super.getImage();
    }

    /**
     * Change image if type changes.
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        if (notification.getFeature() instanceof EStructuralFeature) {
            EStructuralFeature structuralFeature = (EStructuralFeature) notification.getFeature();
            if (structuralFeature.getEType().getInstanceClass() == IntentionalElementType.class) {
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
     * Returns a IntentionalElementPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new IntentionalElementPropertySource((IntentionalElementRef) getModel());

        return propertySource;
    }
}
