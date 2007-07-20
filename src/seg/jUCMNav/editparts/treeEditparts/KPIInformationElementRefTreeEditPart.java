package seg.jUCMNav.editparts.treeEditparts;

import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;
import seg.jUCMNav.views.property.KPIInformationElementPropertySource;

/**
 * TreeEditPart for KPIInformationElementRef
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementRefTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the KPIInformationElementRef
     */
    public KPIInformationElementRefTreeEditPart(KPIInformationElementRef model) {
        super(model);
    }

    /**
     * Listens to both reference and definition.
     */
    public void activate() {
        super.activate();
        if (getKPIInformationElementRef().getDef() != null)
            getKPIInformationElementRef().getDef().eAdapters().add(this);
    }

    /**
     * Stops listening to both reference and definition.
     */
    public void deactivate() {
        super.deactivate();
        if (getKPIInformationElementRef().getDef() != null)
            getKPIInformationElementRef().getDef().eAdapters().remove(this);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
    }

    /**
     * return the KPIInformationElementRef associated with this edit part
     */
    protected KPIInformationElementRef getKPIInformationElementRef() {
        return (KPIInformationElementRef) getModel();
    }

    /**
     * Returns an image representing the KPIInformationElement.
     */
    protected Image getImage() {

        KPIInformationElementRef element = getKPIInformationElementRef();

        if (super.getImage() == null && element.getDef() != null) {
            setImage(JUCMNavPlugin.getImage("icons/Dimension16.gif")); //$NON-NLS-1$
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
            // to be added if there is more types in future
        }
        super.notifyChanged(notification);
    }

    /**
     * Returns a KPIInformationElementPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new KPIInformationElementPropertySource((KPIInformationElementRef) getModel());

        return propertySource;
    }
}
