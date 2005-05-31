package seg.jUCMNav.figures.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.PointList;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.SplineConnection;
import ucm.map.AndJoin;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * Created 2005-03-02
 * 
 * @author Etienne Tremblay
 */
public class BSplineConnectionRouter extends AbstractRouter {

    /**
     * The connection is the key and the spline is the value. This hashmap will allow us to know to which spline a connection bellong to.
     */
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

    /** The PathGraph of the model. */
    private PathGraph pathGraph;

    /**
     * Used to know when all the connection asked to be routed when the diagram is loaded. When all the connections are routed one time initialy, we set this to
     * true.
     */
    private boolean initialized = false;

    /**
     * Generate all is set to true when we add or remove a node. In this case, since we don't know what changed in the diagram, we have to generate everthing
     * again.
     */
    private boolean generateAll = false;
    private Map editpartregistry;

    private BSplineConnectionRouter(Map editpartregistry) {
        this.editpartregistry = editpartregistry;
    }

    /**
     * Build a connection router with a PathGraph.
     */
    public BSplineConnectionRouter(Map editpartregistry, PathGraph diagram) {
        super();
        this.pathGraph = diagram;
        this.editpartregistry = editpartregistry;
    }

    /**
     * This function will find ALL the patterns in our diagram that should be represented by splines and update the splines.
     *  
     */
    protected void generateSplines() {

        // Update the points
        updatePoints();

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

        // TODO Don't forget to clean the splines hashmaps with the useless
        // values.
    }

    /**
     * Find the start, the end and the fork points of the graph.
     */
    private void updatePoints() {
        starts = new ArrayList();
        forks = new ArrayList();
        ends = new ArrayList();

        // Find all the start point, end points and forks in our graph.
        for (Iterator i = pathGraph.getPathNodes().iterator(); i.hasNext();) {
            PathNode node = (PathNode) i.next();
            if (node instanceof StartPoint)
                starts.add(node);
            else if (node.getPred().size() > 1 || node.getSucc().size() > 1 || node instanceof Stub)
            	forks.add(node);
            else if (node instanceof EndPoint)
                ends.add(node);
        }
    }

    /**
     * This Function updates the spline starting with this point.
     * 
     * @param startNode
     *            The node from which you want to generate a spline. It has to be a start point or a fork.
     */
    private void generateSpline(PathNode startNode) {
        PathNode start = startNode;
        ArrayList nodes;

        // Checks if this is a fork
        if (!forks.contains(start)) {
            // If it's not a fork, then it's a StartPoint
            nodes = new ArrayList();
            // bug 244
            if (startNode.getSucc() == null || startNode.getSucc().size() == 0)
                return;
            NodeConnection link = (NodeConnection) startNode.getSucc().get(0);
            nodes.add(start);
            BSpline newSpline = new BSpline();
            // While we don't encounter an EndPoint or a fork, continue to add
            // to the node list for this spline.
            while (!(link.getTarget() instanceof EndPoint || forks.contains(link.getTarget()))) {
                // This connection belongs to this spline.
                conSplines.put(link, newSpline);
                startNode = link.getTarget();
                // bug 244
                if (startNode==null || startNode.getSucc() == null || startNode.getSucc().size() == 0)
                    return;
                link = (NodeConnection) startNode.getSucc().get(0);
                nodes.add(startNode);
            }
            nodes.add(link.getTarget()); // Add the last node
            conSplines.put(link, newSpline);
            newSpline.setPoints(nodes);
        } else {
            // For each path going out of the fork, create a spline too.
            for (Iterator i = startNode.getSucc().iterator(); i.hasNext();) {
                nodes = new ArrayList();
                BSpline newSpline = new BSpline();
                nodes.add(start); // Always add the start of the fork to the point list
                NodeConnection link = (NodeConnection) i.next();

                do {
                    conSplines.put(link, newSpline); // This connection belongs to this spline.
                    startNode = link.getTarget();
                    nodes.add(startNode);
                    if (!(startNode instanceof EndPoint || forks.contains(startNode)))
                        link = (NodeConnection) startNode.getSucc().get(0); // If not at an EndPoint or a Fork, get the next link
                    else
                        link = null;
                } while (link != null); // While we don't encounter an EndPoint or a fork,
                						// continue to add to the node list for this spline.
                newSpline.setPoints(nodes);
            }
        }
    }

    /**
     * Refresh the spline containing the following connection.
     * 
     * @param con
     *            The connection contained in the spline to refresh.
     */
    public void refreshSpline(NodeConnection con) {
        BSpline spline = (BSpline) conSplines.get(con);
        PathNode start = spline.getStartPoint();
        generateSpline(start);



    }

    /**
     * This method returns a point list for a given connection.
     * 
     * @param connection
     *            The connection you need the point list.
     * @return The point list following the spline for this connection.
     */
    protected PointList getPointsFor(Connection conn) {
        SplineConnection con = (SplineConnection) conn;
        NodeConnection link = con.getLink();

        BSpline bSpline = (BSpline) conSplines.get(link);
 
        // jkealey: sometimes crashed here. adding more defensive code. I think it is due to refreshing too early.   
        if (bSpline==null) return new PointList();
        PointList points = bSpline.getPointsBetween(link.getSource(), link.getTarget());
        return points;
    }

    /**
     * This method is called when a new connection is inserted in the path.
     * 
     * @param conn
     */
    protected void insertConnection(Connection conn) {
        // Update the NodeConnections hashmap with the new connection
        SplineConnection con = (SplineConnection) conn;

        // We have to add it to the connection list
        if (conn != null)
            conns.add(new Object[] { conn, ((SplineConnection) conn).getLink().getSource(), ((SplineConnection) conn).getLink().getTarget() });

        
        // The NodeConnection is the key, the SplineConnection the value
        connections.put(con.getLink(), con);

        // We have to generate all the splines since we don't know what changed
        // exactly.
        generateAll = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.ConnectionRouter#route(org.eclipse.draw2d.Connection)
     */
    public void route(Connection conn) {
        boolean simpleMove = false;

        // If the router doesn't contain the connection
        if (!conns.contains(new Object[] { conn, ((SplineConnection) conn).getLink().getSource(), ((SplineConnection) conn).getLink().getTarget() })) {
            // We add it to the list and invalidate all the connections
            insertConnection(conn);
            if (allLoaded())
                initialized = true;
        } else {
            simpleMove = true; // The user didn't add or remove anything, he
            // just moved a node.
            initialized = true;
        }

        // When the diagram is completly initialized and that we have to
        // generate everything
        if (initialized && generateAll) {
            // Then generate all the splines and draw them
            generateAll = false;
            generateSplines();
            drawSplines();
        } else if (initialized && !generateAll && simpleMove) {
            // If the diagram is initialized, that the user is simply moving a
            // node, then we have to refresh only the spline of this connection
            // and draw it.
            refreshSpline(((SplineConnection) conn).getLink());
            simpleMove = false;

            NodeConnection routedCon = ((SplineConnection) conn).getLink();
            BSpline spline = (BSpline) conSplines.get(routedCon);

            drawSpline(spline);
        }

    }

    /**
     * Change the points of a connection to follow the spline
     * 
     * @param conn
     *            The connection to draw.
     */
    public void drawConnection(Connection conn) {
        SplineConnection link = (SplineConnection) conn;

        PointList points = getPointsFor(conn);

        // Set the points for the given connection
        conn.setPoints(points);
        // The connection now follow the spline.

        // refresh outgoing andjoin only.
        if (link.getLink().getSource() instanceof AndJoin)
        {
            PathNodeEditPart edit = (PathNodeEditPart) editpartregistry.get(link.getLink().getSource());
            if (edit != null) {
                edit.refreshVisuals();
            }
            
        }
        
        // rest are refreshed ingoing.
        PathNodeEditPart edit = (PathNodeEditPart) editpartregistry.get(link.getLink().getTarget());
        if (edit != null) {
            edit.refreshVisuals();
        }
    }

    /**
     * Draw the spline. Draw all the connections belonging to this spline.
     * 
     * @param spline
     *            The spline to draw.
     */
    public void drawSpline(BSpline spline) {
        ArrayList nodes = spline.getPoints();
        SplineConnection con;

        for (int j = 0; j <= nodes.size() - 2; j++) {
            // Find the link between nodeA and nodeB
            PathNode nodeA = (PathNode) nodes.get(j);
            PathNode nodeB = (PathNode) nodes.get(j + 1);
            for (int k = 0; k < nodeA.getSucc().size(); k++) {
                // Examine link k in outbound links from nodeA and draw it if it
                // goes to nodeB
                NodeConnection link = (NodeConnection) nodeA.getSucc().get(k);
                if (link.getTarget().equals(nodeB)) {
                    con = (SplineConnection) connections.get(link);
                    if (con != null)
                        drawConnection(con);
                }
            }
        }
    }

    /**
     * Draw all the splines (all the connections of the graph).
     */
    public void drawSplines() {
        for (Iterator i = conns.iterator(); i.hasNext();)
            drawConnection((SplineConnection) ((Object[])i.next())[0]);
    }

    /**
     * This method is used to know if all the connections are in our connection list.
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
        conns.remove(new Object[] { connection, ((SplineConnection) connection).getLink().getSource(), ((SplineConnection) connection).getLink().getTarget() });
        connections.remove(con.getLink());
        conSplines.remove(con.getLink());

        generateAll = true;
        //		initialized = false;

        super.remove(connection);
    }
}