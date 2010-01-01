package seg.jUCMNav.editparts;

import grl.BeliefLink;
import grl.GrlPackage;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.element.BeliefLinkComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ConnectionFeedbackEditPolicy;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.property.URNElementPropertySource;
import urncore.IURNDiagram;

/**
 * Edit part associate with BeliefLink. These connection connect a belief to an IntentionalElementRef of a LinkRef
 * 
 * @author Jean-François Roy
 * 
 */
public class BeliefLinkEditPart extends AbstractConnectionEditPart {

    /**
     * Because GEF's AbstractConnectionEditPart has methods conflicting with EMF's Adapter, we needed an internal class to act as a listener.
     * 
     */
    private class ElementLinkAdapter implements Adapter {
        private Notifier target;

        public ElementLinkAdapter(Notifier target) {
            this.target = target;
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#getTarget()
         */
        public Notifier getTarget() {
            return target;
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
         */
        public boolean isAdapterForType(Object type) {
            return type.equals(getModel().getClass());
        }

        /**
         * When connection's condition is changed, refresh map and path graph.
         */
        public void notifyChanged(Notification notification) {

            int type = notification.getEventType();
            int featureId = notification.getFeatureID(GrlPackage.class);
            if (type == Notification.SET) { // If a modification to the properties have been done
                refreshVisuals();
            }
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
         */
        public void setTarget(Notifier newTarget) {
            target = newTarget;
        }
    }

    ElementLinkAdapter adapter;

    private IURNDiagram diagram;
    protected IPropertySource propertySource = null;

    /**
     * The BeliefLink edit part
     */
    public BeliefLinkEditPart(BeliefLink link, IURNDiagram diagram) {
        super();

        setModel(link);
        this.diagram = diagram;

        adapter = new ElementLinkAdapter((Notifier) getModel());
    }

    /**
     * Add ElementLinkAdapter to listeners.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            ((EObject) getModel()).eAdapters().add(adapter);
        super.activate();
    }

    /**
     * Creates edit policies.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ConnectionFeedbackEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new BeliefLinkComponentEditPolicy());
    }

    /**
     * Creates a Connection and adds appropriate decorations.
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        PolylineConnection connection = new PolylineConnection();

        connection.setLineWidth(2);
        connection.setLineStyle(SWT.LINE_DASH);
        connection.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        return connection;
    }

    /**
     * Removes the adapter.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive())
            ((EObject) getModel()).eAdapters().remove(adapter);
        super.deactivate();
    }

    /**
     * Returns a URNElementPropertySource
     * 
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (IPropertySource.class == adapter) {
            if (propertySource == null) {
                propertySource = new URNElementPropertySource((EObject) getModel());
            }
            return propertySource;
        }
        return super.getAdapter(adapter);
    }

    /**
     * Returns the BeliefLink
     * 
     * @return BeliefLink.
     */
    public BeliefLink getBeliefLink() {
        return (BeliefLink) getModel();
    }

    /**
     * 
     * @return the diagram containing the connection.
     */
    public IURNDiagram getDiagram() {
        return diagram;
    }

    /**
     * Is informed when the model changes.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();
    }
}
