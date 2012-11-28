package seg.jUCMNav.editparts;

import java.util.Map;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editpolicies.element.NodeConnectionComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.ConnectionFeedbackEditPolicy;
import seg.jUCMNav.editpolicies.layout.NodeConnectionXYLayoutEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.figures.TimeoutPathFigure;
import seg.jUCMNav.figures.util.NodeConnectionLocator;
import seg.jUCMNav.figures.util.StubConnectionEndpointLocator;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.property.URNElementPropertySource;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import ucm.UcmPackage;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.Stub;
import ucm.map.Timer;
import urncore.IURNDiagram;

/**
 * EditPart associated with NodeConnection.
 * 
 * @author Etienne Tremblay, jmcmanus, jkealey
 * 
 */
public class NodeConnectionEditPart extends AbstractConnectionEditPart {

    /**
     * Because GEF's AbstractConnectionEditPart has methods conflicting with EMF's Adapter, we needed an internal class to act as a listener.
     * 
     */
    private class NodeConnectionAdapter implements Adapter {
        private Notifier target;

        public NodeConnectionAdapter(Notifier target) {
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
            int featureId = notification.getFeatureID(UcmPackage.class);
            switch (type) {
            case Notification.SET:
                if (featureId == MapPackage.NODE_CONNECTION__CONDITION) {
                    EditPartViewer viewer = getViewer();
                    if (viewer != null) {
                        Map registry = viewer.getEditPartRegistry();
                        if (registry != null) {
                            URNDiagramEditPart part = (URNDiagramEditPart) registry.get(getMap());
                            if (part != null) {
                                part.notifyChanged(notification);
                            }
                        }
                    }
                } else if (featureId == MapPackage.NODE_CONNECTION__THRESHOLD) {
                    EditPartViewer viewer = getViewer();
                    if (viewer != null) {
                        Map registry = viewer.getEditPartRegistry();
                        if (registry != null) {
                            NodeConnectionEditPart part = (NodeConnectionEditPart) registry.get(getLink());
                            if (part != null) {
                                part.refreshVisuals();
                            }
                        }
                    }
                }
                break;
            }
        }

        /**
         * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
         */
        public void setTarget(Notifier newTarget) {
            target = newTarget;
        }
    }

    NodeConnectionAdapter adapter;

    private IURNDiagram diagram;

    private Label endLabel, startLabel;

    protected IPropertySource propertySource = null;

    private TimeoutPathFigure timeout;
    
    // @author: nikiforov
    // use to highlight path by user color
    private Color userColor;

    /**
     * Build an edit part for the given link, in the given pathgraph.
     * 
     * @param link
     *            to be represented
     * @param diagram
     *            the map which contains it.
     */
    public NodeConnectionEditPart(NodeConnection link, IURNDiagram diagram) {
        super();
        setModel(link);
        this.diagram = diagram;

    }

    /**
     * Add NodeConnectionAdapter to listeners.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            adapter = new NodeConnectionAdapter((Notifier) getModel());
            ((EObject) getModel()).eAdapters().add(adapter);
        }
        super.activate();
    }

    /**
     * Given a connection which goes into a Stub, adds the appropriate label.
     * 
     * @param connection
     */
    private void addEndLabel(SplineConnection connection) {
        cleanEndLabel();
        int index = getLink().getTarget().getPred().indexOf(getLink());
        StubConnectionEndpointLocator targetEndpointLocator = new StubConnectionEndpointLocator(connection, true);
        targetEndpointLocator.setVDistance(5);
        targetEndpointLocator.setUDistance(30);
        endLabel = new Label(Messages.getString("NodeConnectionEditPart.IN") + Integer.toString(index + 1)+ " "); //$NON-NLS-1$ //$NON-NLS-2$
        endLabel.setForegroundColor(ColorManager.STUBLABEL);
        endLabel.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
        connection.add(endLabel, targetEndpointLocator);
    }

    private void cleanEndLabel() {
        if (endLabel != null && getFigure().getChildren().contains(endLabel)) {
            getFigure().remove(endLabel);
        }
    }

    private void cleanStartLabel() {
        if (startLabel != null && getFigure().getChildren().contains(startLabel)) {
            getFigure().remove(startLabel);
        }
    }

    /**
     * Given a connection which originates from a Stub, adds the appropriate label.
     * 
     * @param connection
     */
    private void addStartLabel(SplineConnection connection) {
        cleanStartLabel();
        StubConnectionEndpointLocator targetEndpointLocator = new StubConnectionEndpointLocator(connection, false);
        targetEndpointLocator.setVDistance(5);
        targetEndpointLocator.setUDistance(30);

        String startText = getStartText();

        startLabel = new Label(startText);
        startLabel.setForegroundColor(ColorManager.STUBLABEL);
        startLabel.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
        connection.add(startLabel, targetEndpointLocator);
    }

    private String getStartText() {
        int index = getLink().getSource().getSucc().indexOf(getLink());

        String startText = Messages.getString("NodeConnectionEditPart.OUT") + Integer.toString(index + 1) + " "; //$NON-NLS-1$ //$NON-NLS-2$
        startText += getSuffix();
        return startText;
    }

    private String getSuffix() {
        if (getLink().getThreshold() != null && getLink().getThreshold().length() > 0)
            return "[" + getLink().getThreshold().replaceAll("\n", "").replace("\r", "") + "] "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        else
            return ""; //$NON-NLS-1$
    }

    /**
     * Given a connection, draw the TimeoutPathFigure on the node connection.
     * 
     * @param connection
     */
    private void addTimeout(SplineConnection connection) {
        if (timeout != null)
            getFigure().remove(timeout);
        int index = getLink().getSource().getSucc().indexOf(getLink());
        if (index == 1) {
            NodeConnectionLocator constraint = new NodeConnectionLocator(connection, ConnectionLocator.MIDDLE);
            constraint.setRelativePosition(PositionConstants.CENTER);
            timeout = new TimeoutPathFigure();
            connection.add(timeout, constraint);
        }

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new NodeConnectionXYLayoutEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ConnectionFeedbackEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeConnectionComponentEditPolicy());
    }

    /**
     * Creates a SplineConnection and adds appropriate decorations.
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        SplineConnection connection = new SplineConnection(getLink());
        connection.setRoutingConstraint(getLink());
        connection.setLineWidth(3);
        // PolygonDecoration p = new PolygonDecoration();
        // connection.setTargetDecoration(p); // arrow at target endpoint

        if (getLink().getTarget() instanceof Stub) {
            addEndLabel(connection);
        }
        if (getLink().getSource() instanceof Stub) {
            addStartLabel(connection);
        }
        if (getLink().getSource() instanceof Timer && getLink().getSource().getSucc().indexOf(getLink()) == 1) {
            addTimeout(connection);
        }

        return connection;
    }

    /**
     * Removes the adapter.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            ((EObject) getModel()).eAdapters().remove(adapter);
            adapter = null;
        }
        super.deactivate();

        // jkealey: removed during cleanup; i think the figure tree will remove
        // these automatically.
        // leaving in case testing needs to be done.
        // if (endLabel != null) {
        // ((SplineConnection) getFigure()).remove(endLabel);
        // endLabel = null;
        // }
        // if (startLabel != null) {
        // ((SplineConnection) getFigure()).remove(startLabel);
        // startLabel = null;
        // }

        refreshChildren();

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
     * @return the concerned node connection.
     */
    private NodeConnection getLink() {
        return (NodeConnection) getModel();
    }

    /**
     * Queries the figure to obtain its middle point. This method has no knowledge of whether the connection has been routed or not. If not, you might get
     * invalid results. Used to encapsulate access to the middle point in this class instead of having everyone directly access the figure (still is bad code
     * though).
     * 
     * @return The middle point of the spline.
     */
    public Point getMiddlePoint() {
        if (getFigure() == null || ((SplineConnection) getFigure()).getPoints() == null || ((SplineConnection) getFigure()).getPoints().size() == 0)
            if (getLink().getSource() != null && getLink().getTarget() != null)
                return new Point(getLink().getTarget().getX() - getLink().getSource().getX(), getLink().getTarget().getY() - getLink().getSource().getY());
            else
                return new Point(0, 0);
        else
            return ((SplineConnection) getFigure()).getPoints().getMidpoint();
    }

    /**
     * 
     * @return the pathgraph containing the connection.
     */
    public IURNDiagram getMap() {
        return diagram;
    }

    /**
     * Refreshes the connection; adds/removes/replaces connection decorations if appropriate.
     * 
     * Hides the stub label if in print mode.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    public void refreshVisuals() {
        if (getLink().getTarget() instanceof Stub) {
            addEndLabel((SplineConnection) getFigure());
        } else
            cleanEndLabel();

        if (getLink().getSource() instanceof Stub) {
            addStartLabel((SplineConnection) getFigure());
        } else
            cleanStartLabel();

        if (getLink().getSource() instanceof Timer && getLink().getSource().getSucc().indexOf(getLink()) == 1) {
            addTimeout((SplineConnection) getFigure());
        } else if (timeout != null) {
            ((SplineConnection) getFigure()).remove(timeout);
            timeout = null;
        }

        // hide in print mode.
        if (startLabel != null) {
            //boolean hide = ((UCMConnectionOnBottomRootEditPart) getRoot()).getMode() >= 2;
            boolean hide = !GeneralPreferencePage.getUcmStubLabelVisible();
            startLabel.setVisible(!hide);
            startLabel.setText(getStartText());

            if (hide && getSuffix().length() > 0) {
                startLabel.setVisible(true);
                startLabel.setText(getSuffix());
            }
        }

        // hide in print mode.
        if (endLabel != null) {
            //endLabel.setVisible(((UCMConnectionOnBottomRootEditPart) getRoot()).getMode() < 2);
            endLabel.setVisible(GeneralPreferencePage.getUcmStubLabelVisible());
        }

        if(userColor!=null){
            // @author: nikiforov
            // highlight connection
            getFigure().setForegroundColor(userColor);
            getFigure().setBackgroundColor(userColor);
        }else if (ScenarioUtils.getActiveScenario(getLink()) != null && ScenarioUtils.getTraversalHitCount(getLink()) > 0) {
            getFigure().setForegroundColor(ColorManager.TRAVERSAL);
            getFigure().setBackgroundColor(ColorManager.TRAVERSAL);
        } else {
            getFigure().setForegroundColor(ColorManager.LINE);
            getFigure().setBackgroundColor(ColorManager.LINE);
        }

        if (ScenarioUtils.getActiveScenario(getLink()) != null)
            getFigure().setToolTip(new Label(Messages.getString("NodeConnectionEditPart.Hits") + ScenarioUtils.getTraversalHitCount(getLink()))); //$NON-NLS-1$
        else
            getFigure().setToolTip(null);

        super.refreshVisuals();
    }

    /**
     * Show direct edit on node connection with condition on double click, f2 or delay. Pops up the condition editor.
     */
    public void performRequest(Request request) {
        if (request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
            if (getLink() != null && getLink().getCondition() != null) {
                openEditor(getLink().getCondition());
            } else if (getLink().getSource() instanceof Stub) {
                Stub stub = (Stub) getLink().getSource();
                if (stub.isSynchronization()) {
                    openEditor(getLink());
                }
            }
        }
    }

    private void openEditor(EObject model) {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        CodeEditor wizard = new CodeEditor();

        wizard.init(PlatformUI.getWorkbench(), null, model);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }
    
    /**
     * @author: nikiforov
     * Set user color to highlight connection
     */
    public void setUserColor(Color userColor){
    	this.userColor = userColor;
    }
    
    /**
     * @author: nikiforov
     * Reset user color to remove user highlighting
     */
    public void resetUserColor(){
    	this.userColor = null;
    }
}
