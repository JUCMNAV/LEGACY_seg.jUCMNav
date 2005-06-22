/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import seg.jUCMNav.editpolicies.element.PathNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.PathNodeNonResizableEditPolicy;
import seg.jUCMNav.editpolicies.layout.PathNodeXYLayoutEditPolicy;
import seg.jUCMNav.figures.AndForkFigure;
import seg.jUCMNav.figures.AndJoinFigure;
import seg.jUCMNav.figures.DirectionArrowFigure;
import seg.jUCMNav.figures.EmptyPointFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.OrForkFigure;
import seg.jUCMNav.figures.OrJoinFigure;
import seg.jUCMNav.figures.PathNodeFigure;
import seg.jUCMNav.figures.ResponsibilityFigure;
import seg.jUCMNav.figures.Rotateable;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.figures.StartPointFigure;
import seg.jUCMNav.figures.StubFigure;
import seg.jUCMNav.figures.TimeoutPathFigure;
import seg.jUCMNav.figures.TimerFigure;
import ucm.UcmPackage;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;

/**
 * EditPart associated with PathNodes. All model elements that extend PathNode should be associated with this EditPart.
 * 
 * @author Etienne Tremblay, jkealey
 *  
 */
public class PathNodeEditPart extends ModelElementEditPart implements NodeEditPart {

    private PathGraph diagram;

    public PathNodeEditPart(PathNode model, PathGraph diagram) {
        super();
        setModel(model);
        this.diagram = diagram;
    }

    /**
     * Overriding because we also have to listen to the responsibility definition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getNode() instanceof RespRef && ((RespRef) getNode()).getRespDef() != null)
            ((RespRef) getNode()).getRespDef().eAdapters().add(this);

        super.activate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // install the edit policy to handle connection creation
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathNodeComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PathNodeNonResizableEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new PathNodeXYLayoutEditPolicy());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        PathNodeFigure figure = null;
        if (getModel() instanceof EmptyPoint)
            figure = new EmptyPointFigure();
        if (getModel() instanceof RespRef)
            figure = new ResponsibilityFigure();
        else if (getModel() instanceof EndPoint)
            figure = new EndPointFigure();
        else if (getModel() instanceof Timer)
            figure = new TimerFigure();
        else if (getModel() instanceof StartPoint || getModel() instanceof WaitingPlace)
            figure = new StartPointFigure();
        else if (getModel() instanceof OrFork)
            figure = new OrForkFigure();
        else if (getModel() instanceof AndFork)
            figure = new AndForkFigure();
        else if (getModel() instanceof OrJoin)
            figure = new OrJoinFigure();
        else if (getModel() instanceof AndJoin)
            figure = new AndJoinFigure();
        else if (getModel() instanceof DirectionArrow)
            figure = new DirectionArrowFigure();
        else if (getModel() instanceof Stub) {
            Stub stub = (Stub) getModel();
            figure = new StubFigure(stub.isDynamic());
        }
        return figure;
    }

    /**
     * Overriding because we also have to listen to the responsibility definition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getNode() instanceof RespRef && ((RespRef) getNode()).getRespDef() != null)
            ((RespRef) getNode()).getRespDef().eAdapters().remove(this);
        super.deactivate();

    }

    /**
     * @return Returns the diagram.
     */
    public PathGraph getDiagram() {
        return diagram;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return new DragPathNodeTracker(this);
    }

    /**
     * All succ NodeConnections except those that lead to connects.
     */
    protected List getModelSourceConnections() {
        ArrayList v = new ArrayList();
        for (Iterator iter = getNode().getSucc().iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            if (!(element.getTarget() instanceof Connect))
                v.add(element);
        }
        return v;
    }

    /**
     * All pred NodeConnections except those that lead to connects.
     */
    protected List getModelTargetConnections() {
        ArrayList v = new ArrayList();
        for (Iterator iter = getNode().getPred().iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            if (!(element.getSource() instanceof Connect))
                v.add(element);
        }
        return v;
    }

    protected PathNode getNode() {
        return (PathNode) getModel();
    }

    public PathNodeFigure getNodeFigure() {
        return (PathNodeFigure) getFigure();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getSourceConnectionAnchor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return getNodeFigure().getSourceConnectionAnchor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getTargetConnectionAnchor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeFigure().getTargetConnectionAnchor();
    }

    /**
     * @param nodeFigure
     * @return
     */
    private boolean needsMove(PathNodeFigure nodeFigure) {
        return nodeFigure.getBounds().getCenter().x != ((PathNode) getModel()).getX() || nodeFigure.getBounds().getCenter().y != ((PathNode) getModel()).getY();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (featureId) {
        case MapPackage.PATH_NODE__PRED:
            refreshTargetConnections();
            ((MapAndPathGraphEditPart) getParent()).notifyChanged(notification);
            break;
        case MapPackage.PATH_NODE__SUCC:
            refreshSourceConnections();
            ((MapAndPathGraphEditPart) getParent()).notifyChanged(notification);
            break;
        case MapPackage.PATH_NODE__LABEL:
        case MapPackage.START_POINT__PRECONDITION:
        case MapPackage.END_POINT__POSTCONDITION:
            ((MapAndPathGraphEditPart) getParent()).notifyChanged(notification);
            break;
        default:
            //refreshVisuals();
            break;
        }

        refreshVisuals();
    }

    /**
     * @param nodeFigure
     */
    private void rotateFromNext(PathNodeFigure nodeFigure) {
        NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(((PathNode) getModel()).getSucc().get(0));
        if (nc != null) {

            SplineConnection sp = (SplineConnection) nc.getFigure();
            //sp.layout();
            if (sp != null) {
                PointList list = sp.getPoints();
                if (list != null && sp.getPoints().size() > 0) {

                    Ray r;

                    if (list.size() > 2) {
                        r = new Ray(list.getFirstPoint(), list.getPoint(1));
                    } else {
                        r = new Ray(list.getFirstPoint(), list.getMidpoint());
                    }

                    double angle = Math.atan2((double) r.y, (double) r.x);

                    ((Rotateable) nodeFigure).rotate(angle - Math.PI);
                }
            }
        }
    }

    /**
     * @param nc
     */
    private boolean refreshNodeConnection(NodeConnection conn) {
        NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(conn);
        if (nc != null) {
            for (Iterator iter = nc.getFigure().getChildren().iterator(); iter.hasNext();) {
                IFigure fig = (IFigure) iter.next();
                if (fig instanceof TimeoutPathFigure || fig instanceof Label) {
                    Locator loc = (Locator) ((SplineConnection) nc.getFigure()).getLayoutManager().getConstraint(fig);
                    // don't know why isn't refreshing stub labels
                    // probably has to do with implementation of locator
                    loc.relocate(fig);
                }
            }
            return true;
        }
        return false;

    }

    /**
     * @param nodeFigure
     */
    private void rotateFromPrevious(PathNodeFigure nodeFigure) {
        NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(((PathNode) getModel()).getPred().get(0));
        if (nc != null) {

            SplineConnection sp = (SplineConnection) nc.getFigure();
            //sp.layout();
            if (sp != null) {
                PointList list = sp.getPoints();
                if (list != null && list.size() > 0) {

                    Ray r;

                    if (list.size() > 2) {
                        r = new Ray(list.getPoint(list.size() - 2), list.getLastPoint());
                    } else {
                        r = new Ray(list.getMidpoint(), list.getLastPoint());
                    }

                    double angle = Math.atan2((double) r.y, (double) r.x);

                    ((Rotateable) nodeFigure).rotate(angle - Math.PI);
                }
            }
        }
    }

    private boolean refreshStubLabels(PathNodeFigure nodeFigure) {
        boolean b = false;
        if (nodeFigure instanceof StubFigure && !needsMove(nodeFigure)) {
            for (Iterator iter = ((Stub) getModel()).getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                b = b || refreshNodeConnection(nc);
            }
            for (Iterator iter = ((Stub) getModel()).getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                b = b || refreshNodeConnection(nc);
            }
        }
        return b;
    }

    /**
     * @param nodeFigure
     */
    private boolean refreshTimeoutPath(PathNodeFigure nodeFigure) {
        // we don't want to move the label if we are moving the node because it will stop the execution of refresh visuals
        // we don't want to refresh it either if no timeout path exists.
        if (!needsMove(nodeFigure) && nodeFigure instanceof TimerFigure && ((PathNode) getModel()).getSucc().size() > 1) {
            return refreshNodeConnection((NodeConnection) ((PathNode) getModel()).getSucc().get(1));
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    public void refreshVisuals() {
        PathNodeFigure nodeFigure = getNodeFigure();

        // refresh node connection decorations
        // must not continue or will cause infinite loops
        if (refreshTimeoutPath(nodeFigure))
            return;
        else if (refreshStubLabels(nodeFigure))
            return;

        Dimension dim = nodeFigure.getPreferredSize().getCopy();
        Point location = new Point(getNode().getX() - (dim.width / 2), getNode().getY() - (dim.height / 2)); // The
        // position of the current figure
        Rectangle bounds = new Rectangle(location, dim);
        figure.setBounds(bounds);
        if (!(nodeFigure instanceof AndJoinFigure) && nodeFigure instanceof Rotateable && ((PathNode) getModel()).getPred().size() > 0) {
            rotateFromPrevious(nodeFigure);
        } else if (nodeFigure instanceof AndJoinFigure && nodeFigure instanceof Rotateable && ((PathNode) getModel()).getSucc().size() > 0) {
            rotateFromNext(nodeFigure);
        }

        if (getModel() instanceof EmptyPoint) {
            ((IFigure) getFigure().getChildren().get(0)).setVisible(((ConnectionOnBottomRootEditPart) getRoot()).getMode() == 0);
        }
        // should we offset it so that it doesn't overlap another element?
        if (getModel() instanceof EndPoint) {
            ((EndPointFigure) nodeFigure).setOffset((((EndPoint) getModel()).getSucc().size() > 0));
        }
        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);

    }

    /**
     * @param diagram
     *            The diagram to set.
     */
    public void setDiagram(PathGraph diagram) {
        this.diagram = diagram;
    }
}