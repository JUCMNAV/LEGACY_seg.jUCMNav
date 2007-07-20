package seg.jUCMNav.editparts;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;
import grl.kpimodel.KpimodelPackage;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.element.KPIModelLinkRefComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ConnectionFeedbackEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.KPIModelLinkRefConnection;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.KPIModelLinkRefPropertySource;
import urncore.IURNDiagram;

/**
 * Edit part associate with the KPIModelLinkRef in GRL diagram
 * 
 * @author pchen
 * 
 */
public class KPIModelLinkRefEditPart extends AbstractConnectionEditPart {

    /**
     * Because GEF's AbstractConnectionEditPart has methods conflicting with EMF's Adapter, we needed an internal class to act as a listener.
     * 
     */
    private class KPIModelLinkAdapter implements Adapter {
        private Notifier target;

        public KPIModelLinkAdapter(Notifier target) {
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
            int featureId = notification.getFeatureID(KpimodelPackage.class);
            if (type == Notification.ADD || type == Notification.REMOVE) {
                // if (featureId == GrlPackage.LINK_REF__BENDPOINTS) {
                refreshVisuals();
                // }
                if (featureId == KpimodelPackage.KPI_MODEL_LINK) {
                    EvaluationStrategyManager.getInstance().calculateEvaluation();
                }
            } else if (type == Notification.SET) { // If a modification to the properties have been done
                if (featureId == KpimodelPackage.KPI_MODEL_LINK) {
                    EvaluationStrategyManager.getInstance().calculateEvaluation();
                }
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

    KPIModelLinkAdapter adapter;
    private IURNDiagram diagram;
    protected IPropertySource propertySource = null;

    private Image img;
    private Label kpiModelLinkLabel;

    /**
     * The Edit Part for KPIModelLinkRefs
     */
    public KPIModelLinkRefEditPart(KPIModelLinkRef link, IURNDiagram diagram) {
        super();
        setModel(link);
        this.diagram = diagram;

        adapter = new KPIModelLinkAdapter((Notifier) getModel());
    }

    /**
     * We want to listen for the reference and definition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            ((EObject) getModel()).eAdapters().add(adapter);

            if (getModel() instanceof KPIModelLinkRef && ((KPIModelLinkRef) getModel()).getLink() != null) {
                ((KPIModelLinkRef) getModel()).getLink().eAdapters().add(adapter);
            }
        }
        super.activate();
    }

    /**
     * Create Edit Policies
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // Remove the ConnectionEndPoints role because we did want user to be able to reconnect Links
        // installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
        // installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new LinkRefBendpointEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ConnectionFeedbackEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new KPIModelLinkRefComponentEditPolicy());
    }

    /**
     * Creates a Connection and adds appropriate decorations.
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        KPIModelLinkRefConnection connection = new KPIModelLinkRefConnection();
        connection.setRoutingConstraint(getKPIModelLinkRef());

        ConnectionEndpointLocator ce = new ConnectionEndpointLocator(connection, true);
        ce.setUDistance(0);
        ce.setVDistance(0);

        // Create the kpiModelLink label
        ConnectionEndpointLocator kpiModelLinkce = new ConnectionEndpointLocator(connection, true);
        kpiModelLinkce.setUDistance(10);
        kpiModelLinkce.setVDistance(10);

        kpiModelLinkLabel = new Label();
        kpiModelLinkLabel.setForegroundColor(ColorManager.KPIMODELLINKREFLABEL);
        connection.add(kpiModelLinkLabel, kpiModelLinkce);
        kpiModelLinkLabel.setVisible(false);

        return connection;
    }

    /**
     * Removes the adapter.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {

            if (img != null) {
                img.dispose();
                img = null;
            }
            ((EObject) getModel()).eAdapters().remove(adapter);
            if (getModel() instanceof KPIModelLinkRef && ((KPIModelLinkRef) getModel()).getLink() != null) {
                ((KPIModelLinkRef) getModel()).getLink().eAdapters().remove(adapter);
            }

        }
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
                propertySource = new KPIModelLinkRefPropertySource((EObject) getModel());
            }
            return propertySource;
        }
        return super.getAdapter(adapter);
    }

    /**
     * 
     * @return the diagram containing the connection.
     */
    public IURNDiagram getDiagram() {
        return diagram;
    }

    /**
     * Returns the KPIModelLinkRef
     * 
     * @return KPIModelLinkRef.
     */
    public KPIModelLinkRef getKPIModelLinkRef() {
        return (KPIModelLinkRef) getModel();
    }

    /**
     * Returns the Figure associated with this
     * 
     * @return KPIModelLinkRefFigure.
     */
    private KPIModelLinkRefConnection getKPIModelLinkRefFigure() {
        return (KPIModelLinkRefConnection) getFigure();
    }

    /**
     * Is informed when the model changes.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();

    }

    /**
     * Updates the bendpoints, based on the model.
     */
    /*
     * protected void refreshBendpoints() { EList modelConstraint = getLinkRef().getBendpoints();
     * 
     * List figureConstraint = new ArrayList(); for (int i = 0; i < modelConstraint.size(); i++) { LinkRefBendpoint bendpoint = (LinkRefBendpoint)
     * modelConstraint.get(i); AbsoluteBendpoint abp = new AbsoluteBendpoint(bendpoint.getX(), bendpoint.getY()); figureConstraint.add(abp); }
     * getConnectionFigure().setRoutingConstraint(figureConstraint); }
     */

    /**
     * Refreshes the connections
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // refreshBendpoints();
        if (getKPIModelLinkRef().getLink() instanceof KPIModelLink) {
            KPIModelLink kpiModelLink = (KPIModelLink) getKPIModelLinkRef().getLink();
            getKPIModelLinkRefFigure().setType(KPIModelLinkRefConnection.TYPE_KPIMODEL_LINK);
        }

    }
}
