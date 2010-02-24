package seg.jUCMNav.figures.router;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.ConnectionEditPart;

import seg.jUCMNav.editparts.AndForkJoinEditPart;
import seg.jUCMNav.editparts.ConditionEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ConnectionSplineFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ConnectionSplineFinder.QFindSpline;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;

/**
 * Connection router for a use case map. Builds BSplines and refreshes them only when necessary.
 * 
 * Uses the ConnectionSplineFinder to find affected connections.
 * 
 * @author jkealey
 * 
 */
public class UCMConnectionRouter extends AbstractRouter implements Adapter {
    private HashMap connections;
    private Map editpartregistry;
    private UCMmap pathgraph;
    private Notifier target;
    private boolean isDuringRoute=false;

    /**
     * 
     * @param editpartregistry
     *            the editpartregistry containing all our editparts.
     * @param pathgraph
     *            the UCMmap containing all the conections.
     */
    public UCMConnectionRouter(Map editpartregistry, UCMmap pathgraph) {
        assert pathgraph != null : "null path graph!"; //$NON-NLS-1$

        this.pathgraph = pathgraph;
        this.editpartregistry = editpartregistry;

        registerListeners(pathgraph);
        refreshConnections();
    }

    /**
     * Draws a particular node connection given its spline (which contains the points).
     * 
     * @param nc
     *            the node connection to draw
     * @param bspline
     *            the spline it is on
     * @param iLeftPointIndex
     *            the index of its left point. this is mandatory because the BSpline cannot differentiate multiple occurences of the same point. We cannot do it
     *            internally in this method because you could have multiple node connections to and from the same points, in the same order.
     */
    private void drawConnection(NodeConnection nc, BSpline bspline, int iLeftPointIndex) {
        if (editpartregistry.get(nc) == null)
            return;
        SplineConnection conn = (SplineConnection) ((NodeConnectionEditPart) editpartregistry.get(nc)).getFigure();
        if (conn != null) {

            // PointList pts = bspline.getPointsBetween(getLeftPoint(nc), getRightPoint(nc));
            PointList pts = bspline.getPointBetween(iLeftPointIndex, iLeftPointIndex + 1);
            boolean hasMoved = !pts.getFirstPoint().equals(conn.getPoints().getFirstPoint());
            conn.setPoints(pts);
            connections.put(nc, Boolean.TRUE);

            // refresh conditions.
            if (nc.getCondition() != null) {
                ConditionEditPart edit = (ConditionEditPart) editpartregistry.get(nc.getCondition());
                if (edit != null) {
                    edit.refreshVisuals();
                }

            }

            // refresh outgoing andjoin/timer/stub only. (for rotation/node connection labels)
            if (nc.getSource() instanceof AndJoin || nc.getSource() instanceof Timer || nc.getSource() instanceof Stub) {
                PathNodeEditPart edit = (PathNodeEditPart) editpartregistry.get(nc.getSource());
                if (edit != null) {
                    edit.refreshVisuals();
                }

            }

            // if we moved a connection anchor for an and fork/join, we need to update all the paths.
            if (hasMoved && nc.getSource() instanceof AndFork) {
                AndForkJoinEditPart edit = (AndForkJoinEditPart) editpartregistry.get(nc.getSource());
                edit.anchorMoved(null);
            } else if (hasMoved && nc.getTarget() instanceof AndJoin) {
                AndForkJoinEditPart edit = (AndForkJoinEditPart) editpartregistry.get(nc.getTarget());
                edit.anchorMoved(null);
            }

            // rest are refreshed ingoing.
            PathNodeEditPart edit = (PathNodeEditPart) editpartregistry.get(nc.getTarget());
            if (edit != null) {
                edit.refreshVisuals();
            }
        }

    }

    /**
     * Draw a spline, given one of its SplineConections.
     * 
     * @param source
     */
    private void drawSpline(SplineConnection source) {
        // refresh spline
        QFindSpline qReachableConnections = new ConnectionSplineFinder().new QFindSpline(source.getLink());
        ConnectionSplineFinder.RSpline rReachableConnections = (ConnectionSplineFinder.RSpline) GraphExplorer.run(qReachableConnections);
        Vector vReachable = rReachableConnections.getConnections();
        if (vReachable.size() > 0) {
            PointList pts = new PointList();
            NodeConnection nc;
            // this array is used to memorize the index of each node connection's left point in the pts PointList.
            // the reason we need to pass this is the BSpline class cannot differentiate between multiple occurrences of the same point, if loops occur.
            int[] iPositions = new int[vReachable.size()];
            int i = 0;

            // build point sequence
            for (Iterator iter = vReachable.iterator(); iter.hasNext();) {
                nc = (NodeConnection) iter.next();
                Point left = getLeftPoint(nc);
                Point right = getRightPoint(nc);
                // prevent double adding
                if (pts.size() == 0 || !pts.getLastPoint().equals(left))
                    pts.addPoint(left);
                pts.addPoint(right);
                // memorize left point position
                iPositions[i++] = pts.size() - 2;
            }

            // build a spline from the sequence
            BSpline bspline = new BSpline(pts);
            bspline.findCPoints();

            for (i = 0; i < vReachable.size(); i++) {
                nc = (NodeConnection) vReachable.get(i);
                drawConnection(nc, bspline, iPositions[i]);
            }
        }
    }

    /**
     * @return A hashmap with NodeConnection/Boolean couples. if the boolean is Boolean.TRUE, refresh is not needed.
     */
    public HashMap getConnections() {
        return connections;
    }

    /**
     * @return the editpart registry. Used for refresh for figures that must be rotated.
     */
    public Map getEditpartregistry() {
        return editpartregistry;
    }

    /**
     * Given a NodeConnection, we want its starting point.
     * 
     * @param nc
     *            the node connection
     * @return connection's left point
     */
    private Point getLeftPoint(NodeConnection nc) {
        if (nc.getSource() instanceof AndFork) {
            return ((PathNodeEditPart) editpartregistry.get(nc.getSource())).getTargetConnectionAnchor((ConnectionEditPart) editpartregistry.get(nc))
                    .getLocation(new Point(nc.getTarget().getX(), nc.getTarget().getY()));
        } else
            return new Point(nc.getSource().getX(), nc.getSource().getY());
    }

    /**
     * 
     * @return the pathgraph containing the connections
     */
    public UCMmap getPathgraph() {
        return pathgraph;
    }

    /**
     * Given a NodeConnection, we want its ending point.
     * 
     * @param nc
     *            the node connection
     * @return connection's right point
     */
    private Point getRightPoint(NodeConnection nc) {
        if (nc.getTarget() instanceof AndJoin) {
            return ((PathNodeEditPart) editpartregistry.get(nc.getTarget())).getSourceConnectionAnchor((ConnectionEditPart) editpartregistry.get(nc))
                    .getLocation(new Point(nc.getSource().getX(), nc.getSource().getY()));
        } else
            return new Point(nc.getTarget().getX(), nc.getTarget().getY());
    }

    /**
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /**
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(UCMmap.class) || type.equals(PathNode.class) || type.equals(NodeConnection.class);
    }

    /**
     * If the PathGraph, PathNodes or NodeConnections that the router listens to is changed, the impact of the change is calculated and the connections HashMap
     * is updated.
     */
    public void notifyChanged(Notification notification) {
        if (getPathgraph() == null)
            return;

        int type = notification.getEventType();
        EObject notifier = (EObject) notification.getNotifier();

        if (notification.getFeature() instanceof EStructuralFeature) {
            EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
            if (type == Notification.SET && notifier instanceof PathNode) {
                PathNode pn = (PathNode) notifier;
                if (feature.getName().equals("x") || feature.getName().equals("y")) { //$NON-NLS-1$ //$NON-NLS-2$
                    if (pn.getDiagram() != null) {
                        // System.out.println("moved pathnode");
                        refreshConnections(pn);
                    }
                }
            } else if (type == Notification.SET && notifier instanceof NodeConnection) {
                NodeConnection nc = (NodeConnection) notifier;
                if (feature.getName().equals("source") || feature.getName().equals("target")) { //$NON-NLS-1$ //$NON-NLS-2$
                    if (nc.getDiagram() != null && nc.getSource() != null && nc.getTarget() != null) {
                        // System.out.println("changed connections");
                        refreshConnections(nc);
                    }
                }
            } else if (notifier instanceof UCMmap) {

                switch (type) {
                case Notification.ADD:
                    registerListeners(getPathgraph());
                    refreshConnections(); // might not be necessary; adding to replace fallthrough that was there before
                    break;
                case Notification.REMOVE:
                    /*
                     * if (feature.getName().equals("pathNodes")) { System.out.println("added or removed pathnodes"); } else if
                     * (feature.getName().equals("nodeConnections")) { System.out.println("added or removed nodeconnections"); }
                     */
                    refreshConnections();

                    break;
                }

            }
        }

    }

    /**
     * Refresh all the connections; simply marks them as dirty in the connections HashMap.
     */
    public void refreshConnections() {
        if (isDuringRoute) return; // can cause infinite loops. 
        connections = new HashMap(getPathgraph().getNodes().size());
        for (Iterator iter = getPathgraph().getConnections().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();

            // never want to refresh Connects
            if (nc.getSource() instanceof Connect || nc.getTarget() instanceof Connect)
                connections.put(nc, Boolean.TRUE);
            else {
                connections.put(nc, Boolean.FALSE);
            }
        }
    }

    /**
     * Refresh the connections found on a certain spline, defined by qSpline. Simply marks them as dirty in the connections HashMap.
     * 
     * @param qSpline
     *            the query returning the spline to refresh
     */
    private void refreshConnections(ConnectionSplineFinder.QFindSpline qSpline) {
        ConnectionSplineFinder.RSpline rReachableConnections = (ConnectionSplineFinder.RSpline) GraphExplorer.run(qSpline);
        Vector vReachable = rReachableConnections.getConnections();

        for (Iterator iter = vReachable.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            // never want to redraw connects
            if (nc.getSource() instanceof Connect || nc.getTarget() instanceof Connect)
                connections.put(nc, Boolean.TRUE);
            else {
                connections.put(nc, Boolean.FALSE);
            }
        }

        if (vReachable.size() > 0 && ((NodeConnection) vReachable.lastElement()).getTarget() instanceof AndFork) {
            for (Iterator iter = ((NodeConnection) vReachable.lastElement()).getTarget().getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                refreshConnections(nc);
            }
        }

        if (vReachable.size() > 0 && ((NodeConnection) vReachable.firstElement()).getSource() instanceof AndJoin) {
            for (Iterator iter = ((NodeConnection) vReachable.firstElement()).getSource().getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                refreshConnections(nc);
            }
        }
    }

    /**
     * Given a NodeConnection, refresh spline containing it.
     * 
     * @param nc
     *            the node connection
     */
    private void refreshConnections(NodeConnection nc) {
        QFindSpline qReachableConnections = new ConnectionSplineFinder().new QFindSpline(nc);
        refreshConnections(qReachableConnections);
    }

    /**
     * Given a PathNode, refresh the splines containing it (there may be many)
     * 
     * @param pn
     *            the path node
     */
    private void refreshConnections(PathNode pn) {
        if (pn instanceof Connect)
            return;
        QFindSpline qReachableConnections;
        for (Iterator iter = pn.getPred().iterator(); iter.hasNext();) {
            qReachableConnections = new ConnectionSplineFinder().new QFindSpline((NodeConnection) iter.next());
            refreshConnections(qReachableConnections);
        }
        for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
            qReachableConnections = new ConnectionSplineFinder().new QFindSpline((NodeConnection) iter.next());
            refreshConnections(qReachableConnections);
        }

    }

    /**
     * Listens to the pathgraph, node connections and pathnodes. (Except for Connects and connections leading to Connects).
     * 
     * @param pathgraph
     *            the pathgraph containing all these elements.
     */
    private void registerListeners(UCMmap pathgraph) {
        if (!pathgraph.eAdapters().contains(this))
            pathgraph.eAdapters().add(this);
        for (Iterator iter = pathgraph.getConnections().iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            if (!element.eAdapters().contains(this) && !(element.getSource() instanceof Connect) && !(element.getTarget() instanceof Connect))
                element.eAdapters().add(this);
        }
        for (Iterator iter = pathgraph.getNodes().iterator(); iter.hasNext();) {
            EObject element = (EObject) iter.next();
            if (!element.eAdapters().contains(this) && !(element instanceof Connect))
                element.eAdapters().add(this);
        }
    }

    /**
     * If the passed SplineConnection is dirty (as defined by the connections HashMap), redraw it.
     * 
     * Otherwise, redraw is not required.
     * 
     * @see org.eclipse.draw2d.ConnectionRouter#route(org.eclipse.draw2d.Connection)
     */
    public void route(Connection connection) {
        SplineConnection spline = (SplineConnection) connection;

        if (connections == null || spline.getLink() == null || connections.get(spline.getLink()) == null) {
            // Gunter once observed a NPE in the next if so we try to catch it before it causes a problem.
            // We don't know why he obtained an error; might be something else that caused it.
            return;
        }
        if (connections.get(spline.getLink()).equals(Boolean.FALSE)) {
            isDuringRoute=true;
            drawSpline(spline);
            isDuringRoute=false;
        }
    }

    /**
     * 
     * @param connections
     *            the HashMap defining which connections are dirty.
     */
    public void setConnections(HashMap connections) {
        this.connections = connections;
    }

    /**
     * 
     * @param editpartregistry
     *            the editpartregistry containing all the editparts that must be informed when redrawing splines.
     */
    public void setEditpartregistry(Map editpartregistry) {
        this.editpartregistry = editpartregistry;
    }

    /**
     * 
     * @param pathgraph
     *            the pathgraph containing all the model elements relevant to this connection router.
     */
    public void setPathgraph(UCMmap pathgraph) {
        this.pathgraph = pathgraph;
    }

    /**
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    public void dispose() {
        if (pathgraph.eAdapters().contains(this))
            pathgraph.eAdapters().remove(this);
        for (Iterator iter = pathgraph.getConnections().iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            if (element.eAdapters().contains(this))
                element.eAdapters().remove(this);
        }
        for (Iterator iter = pathgraph.getNodes().iterator(); iter.hasNext();) {
            EObject element = (EObject) iter.next();
            if (element.eAdapters().contains(this))
                element.eAdapters().remove(this);
        }

        connections = null;
        editpartregistry = null;
        pathgraph = null;
        target = null;
    }
}