package seg.jUCMNav.figures.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.PointList;

import seg.jUCMNav.figures.SplineConnection;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Created 2005-03-02
 * 
 * @author Etienne Tremblay
 */
public class BSplineConnectionRouter extends AbstractRouter {
    /** The spline used to return points. */
    private BSpline spline;

    /** The connection is the key and the spline is the value. This hashmap will allow us to know to wich spline a connection bellong to. */
    private HashMap conSplines = new HashMap();

    /** The list of start points of the PathGraph. */
    private ArrayList starts = new ArrayList();
    /** The list of end points of the PathGraph. */
    private ArrayList ends = new ArrayList();
    /** The list of forks of the PathGraph. */
    private ArrayList forks = new ArrayList();

    /** The connections that called the route() function. */
    private ArrayList conns = new ArrayList();

    /** NodeConnection is the key and SplineConnection the value. */
    private HashMap connections = new HashMap();

    private boolean initializing = true;
    private boolean generateSplines = false;

    /** The PathGraph of the model. */
    private PathGraph pathGraph;

    private BSplineConnectionRouter() {

    }

    /**
     * Build a connection router with a PathGraph.
     */
    public BSplineConnectionRouter(PathGraph diagram) {
        super();
        this.pathGraph = diagram;
        findSplines();
    }

    /**
     * This function will find ALL the patterns in our diagram that should be represented by splines and update the splines.
     *  
     */
    protected void findSplines() {
        System.out.println("findSplines called...");
        starts = new ArrayList();
        forks = new ArrayList();
        ends = new ArrayList();

        // Find all the start point, end points and forks in our graph.
        for (Iterator i = pathGraph.getPathNodes().iterator(); i.hasNext();) {
            PathNode node = (PathNode) i.next();
            if (node instanceof StartPoint)
                starts.add(node);
            else if (node.getPred().size() > 1 || node.getSucc().size() > 1)
                forks.add(node);
        }

        if (starts.size() > 0) {
            for (Iterator i = starts.iterator(); i.hasNext();) {
                PathNode node = (PathNode) i.next();
                generateSpline(node);
            }
        }

        if (forks.size() > 0) {
            for (Iterator i = forks.iterator(); i.hasNext();) {
                PathNode node = (PathNode) i.next();
                generateSpline(node);
            }
        }

        // TODO Don't forget to clean the splines hashmaps with the useless values.
    }

    /**
     * This Function update the spline starting with this point.
     * 
     * @param startNode
     *            The node from wich you want to generate a spline. It has to be a start point or a fork.
     */
    private void generateSpline(PathNode startNode) {
        PathNode start = startNode;
        ArrayList nodes = new ArrayList();

        // Checks if this is a fork
        if (!forks.contains(start)) {
            // If it's not a fork, then it's a StartPoint
            NodeConnection link = (NodeConnection) startNode.getSucc().get(0);
            nodes.add(start);
            BSpline newSpline = new BSpline();
            // While we don't encounter an EndPoint or a fork, continue to add to the node list for this spline.
            while (!(link.getTarget() instanceof EndPoint || forks.contains(link.getTarget()))) {
                // This connection belong to this spline.
                conSplines.put(link, newSpline);

                startNode = link.getTarget();
                link = (NodeConnection) startNode.getSucc().get(0);

                nodes.add(startNode);
            }
            nodes.add(link.getTarget()); // Add the last node
            conSplines.put(link, newSpline);
            newSpline.setPoints(nodes);
        } else {
            // TODO Make generation of spline in function of forks work.
            BSpline newSpline = new BSpline(nodes);
            // For each path going out of the fork, create a spline too.
            for (Iterator i = startNode.getSucc().iterator(); i.hasNext();) {
                // Always add the start of the fork to the point list
                nodes.add(start);
                // Set this to the first point in the list of next path nodes
                startNode = ((NodeConnection) i.next()).getTarget();
                NodeConnection link = (NodeConnection) startNode.getSucc().get(0);

                // While we don't encounter an EndPoint or a fork, continue to add to the node list for this spline.
                while (!(startNode instanceof EndPoint || forks.contains(startNode))) {
                    nodes.add(startNode);

                    // This connection belong to this spline.
                    conSplines.put(link, newSpline);

                    startNode = link.getTarget();
                    link = (NodeConnection) startNode.getSucc().get(0);
                }

                // Add this spline to the hashmap
                newSpline.setPoints(nodes);
            }
        }
    }

    public void refreshSpline(NodeConnection con) {
        BSpline spline = (BSpline) conSplines.get(con);
        PathNode start = spline.getStartPoint();
        generateSpline(start);
    }

    /**
     * This function return a point list for a given connection.
     * 
     * @param connection
     *            The connection you need the point list.
     * @return The point list following the spline for this connection.
     */
    protected PointList getPointsFor(Connection conn) {
        SplineConnection con = (SplineConnection) conn;
        NodeConnection link = con.getLink();

        BSpline bSpline = (BSpline) conSplines.get(link);

        PointList points = bSpline.getPointsBetween(link.getSource(), link.getTarget());
        return points;
    }

    /**
     * This methode is called when a new connection is inserted in the path.
     * 
     * @param conn
     */
    protected void insertConnection(Connection conn) {
        // We have to add it to the connection list
        if (conn != null)
            conns.add(conn);

        // Update the NodeConnections hashmap with the new connection
        for (Iterator i = conns.iterator(); i.hasNext();) {
            SplineConnection con = (SplineConnection) i.next();
            // The NodeConnection is the key, the connection the value
            connections.put(con.getLink(), con);
        }

        // If we're not in the initializing phase
        if (!initializing) {
            //			findSplines();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.ConnectionRouter#route(org.eclipse.draw2d.Connection)
     */
    public void route(Connection conn) {
        // If the router doesn't contain the connection
        if (!conns.contains(conn)) {
            // We add it to the list and invalidate all the connections
            insertConnection(conn);
            // If all the connections are now updated in the connection array, initialization is finished
            if (allLoaded()) {
                //				initializing = false;
                // So we can really route all the connections
                generateSplines = true;
                drawSplines();
            }
        } else {
            // Redraw all the splines.
            generateSplines = true;
            drawSplines();
        }
    }

    /**
     * Generage the spline if necessary and set the point list of the connection.
     * 
     * @param conn
     */
    public void drawSpline(Connection conn) {
        SplineConnection link = (SplineConnection) conn;

        PointList points = getPointsFor(conn);

        //		PointList clipped = clipPointList(getStartPoint(conn), getEndPoint(conn), points);
        // Set the points for the given connection
        conn.setPoints(points);
        // The connection now follow the spline.
    }

    public void drawSplines() {
        //		 Update the spline to draw
        if (generateSplines) {
            //			refreshSpline(link.getLink());
            findSplines();
            generateSplines = false;
        }
        for (Iterator i = conns.iterator(); i.hasNext();)
            drawSpline((SplineConnection) i.next());
    }

    /**
     * This function is used to know if all the connections are in our connection list.
     * 
     * @return true if all the connections of the diagram are in our connection list, false otherwise.
     */
    protected boolean allLoaded() {
        if (conns.size() == pathGraph.getNodeConnections().size())
            return true;
        else
            return false;
    }

    /**
     * Remove a connection from this connection router.
     * 
     * @see org.eclipse.draw2d.ConnectionRouter#remove(org.eclipse.draw2d.Connection)
     */
    public void remove(Connection connection) {
        SplineConnection con = (SplineConnection) connection;
        // Remove from the connection list and the hashmap
        conns.remove(connection);
        connections.remove(con.getLink());
        conSplines.remove(con.getLink());

        super.remove(connection);
    }
}