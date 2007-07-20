package seg.jUCMNav.editparts;

import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KpimodelPackage;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.directEditPolicy.GrlNodeDirectEditPolicy;
import seg.jUCMNav.editpolicies.directEditPolicy.KPIInformationElementNodeEditPolicy;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.GrlNodeFeedbackEditPolicy;
import seg.jUCMNav.figures.KPIInformationElementFigure;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.KPIInformationElementPropertySource;
import urncore.IURNConnection;
import urncore.IURNNode;

/**
 * EditPart for all KPIInformationElementRef. It listen for changes in the references and the definitions
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementEditPart extends GrlNodeEditPart implements NodeEditPart {

    /**
     * 
     * @param model
     *            the KPI information element ref to draw
     */
    public KPIInformationElementEditPart(IURNNode model) {
        super();
        setModel(model);
    }

    /**
     * We need to listen for the reference and the definition for kpiInformationElement
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getNode() instanceof KPIInformationElementRef && (getNode()).getDef() != null) {
            (getNode()).getDef().eAdapters().add(this);
        }

        // listen to reference
        super.activate();
    }

    /**
     * Create the edit policies.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new GrlNodeFeedbackEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new KPIInformationElementNodeEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new GrlNodeDirectEditPolicy());
    }

    /**
     * Create the GrlNode figure.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        KPIInformationElementFigure fig = new KPIInformationElementFigure();

        return fig;
    }

    /**
     * When nodes are dragged in GEF, they explictly remove connections from being possible drop targets. By overriding DragEditPartsTracker, we allow this
     * behaviour.
     * 
     * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return new DragPathNodeTracker(this);
    }

    /**
     * 
     * @return the KPI information element.
     */
    private KPIInformationElementRef getNode() {
        return (KPIInformationElementRef) getModel();
    }

    /**
     * @return The node's figure
     */
    public KPIInformationElementFigure getNodeFigure() {
        return (KPIInformationElementFigure) getFigure();
    }

    /**
     * @return a KPIInformationElementPropertySource
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new KPIInformationElementPropertySource((EObject) getModel());
        }
        return propertySource;
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    protected List getModelSourceConnections() {
        return getNode().getSucc();
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
     */
    protected List getModelTargetConnections() {
        return getNode().getPred();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {

        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        if (getParent() == null)
            return;
        refreshTargetConnections();
        refreshSourceConnections();
        refreshVisuals();

        int featureId = notification.getFeatureID(KpimodelPackage.class);

        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /**
     * Refresh the figure and its associated labels.
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getNode().getX(), getNode().getY());
        // its size (the width of the elements should always be 2 the height of them
        Dimension size = getNodeFigure().getSize().getCopy();
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.setLocation(location);

        setText();

        // set information for specific drawing
        if ((getNode()).getDef() != null && ((getNode()).getDef() instanceof KPIInformationElement)) {
            KPIInformationElement elem = (getNode()).getDef();

            // Set the line color and fill color. Option only available in design view
            if (getParent() == null || !((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()) {
                ((KPIInformationElementPropertySource) getPropertySource()).setEvaluationStrategyView(false);
            } else {
                // Set strategy view to true
                ((KPIInformationElementPropertySource) getPropertySource()).setEvaluationStrategyView(true);
                // Get the kpi information configuration
                KPIInformationConfig kpiInformationConfig = EvaluationStrategyManager.getInstance().getKPIInformationConfigObject(getNode().getDef());

                refreshConnections();
            }

        }

        // Make the label recenter itself.
        figure.validate();

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        // if (getParent() != null)
        // (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);
    }

    /**
     * Refresh the successor edit parts.
     */
    private void refreshConnections() {
        for (Iterator iter = getNode().getSucc().iterator(); iter.hasNext();) {
            IURNConnection connect = (IURNConnection) iter.next();
            AbstractConnectionEditPart refEditPart = (AbstractConnectionEditPart) getViewer().getEditPartRegistry().get(connect);
            if (refEditPart != null) {
                refEditPart.refresh();
            }
        }
    }

    /**
     * Sets the label's text, given its referenced model element.
     */
    private void setText() {
        if (getNode().getDef() != null) {
            getNodeFigure().setEditableText(getNode().getDef().getName());
        }
    }
}
