/*
 * Created on 2005-03-02
 *
 */
package seg.jUCMNav.figures.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import seg.jUCMNav.figures.SplineConnection;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.StartPoint;

/**
 * Created 2005-03-02
 * 
 * @author Etienne Tremblay
 */
public class BSplineConnectionRouter extends AbstractRouter {
	private BSpline spline; // The spline used to return points.

	private ArrayList conns = new ArrayList(); // The connections

	private HashMap NodeConnections = new HashMap();
	private boolean initializing = true;
	private boolean generateSpline;
	
	private PathGraph diagram;

	/**
	 *  
	 */
	public BSplineConnectionRouter(PathGraph diagram) {
		super();
		this.diagram = diagram;
	}

	/**
	 * This function will return a list of points passing on each nodes of the path.
	 * This algorithm consider that the connection array is sorted.
	 * 
	 * @return
	 */
	protected PointList getAllPoints() {
		PointList list = new PointList();
		Point start, end;

		int j=0;
		for (Iterator i = conns.iterator(); i.hasNext();) {
			SplineConnection con = (SplineConnection) i.next();
			NodeConnection node = con.getLink();
			start = new Point(node.getSource().getX(), node.getSource().getY());
			end = new Point(node.getTarget().getX(), node.getTarget().getY());
			con.translateToParent(start);
			con.translateToParent(end);
			if(j == 0)
				list.addPoint(start);
			list.addPoint(end);
			j++;
		}

		return list;
	}

	/**
	 * This function sort all the connections in comparison to the model.
	 * The first connection will be the connection going from the start point to the first node, etc.
	 * 
	 * @return The new ordered connection array.
	 */
	protected ArrayList updateOrder() {
		//Try to find the first NodeConnection in this path.
		Stack stack = new Stack();
		// Most of the time when the conns array is sorted, the first one will
		// be the first NodeConnection. Sometimes not...
		NodeConnection link = ((SplineConnection) conns.get(0)).getLink(); // The first we
																 // look at.
		// Loop trough the NodeConnections until we find the start point
		while (!(link.getSource() instanceof StartPoint)) {
			stack.push(NodeConnections.get(link)); // Push the connection corresponding to
										 // this NodeConnection.
			link = (NodeConnection)link.getSource().getPred().get(0); // Get the previous connection
		}

		// Push the first NodeConnection (the last we looked at)
		stack.push(NodeConnections.get(link));

		ArrayList finalList = new ArrayList();

		// Transform the stacked connections to an sorted array
		while (!stack.isEmpty()){
			SplineConnection popped = (SplineConnection)stack.pop();
			link = popped.getLink();
			finalList.add(popped);
		}

		if (!(link.getTarget() instanceof EndPoint))
			link = (NodeConnection)link.getTarget().getSucc().get(0); // Get the next connection
		
		// Add each NodeConnections until the endpoint to the list.
		while (!(link.getTarget() instanceof EndPoint)) {
			finalList.add(NodeConnections.get(link));
			link = (NodeConnection)link.getTarget().getSucc().get(0); // Get the next connection
		}
		// Add the last connection too.
		finalList.add(NodeConnections.get(link));
//		conns.clear();

		return finalList;
	}

	/**
	 * This function generate the BSpline with the location of each nodes.
	 * @return The BSpline representing the path.
	 */
	protected BSpline generateSpline() {
		return new BSpline(getAllPoints());
	}

	/**
	 * This function return a point list for a given connection.
	 * @param connection The connection you need the point list.
	 * @return The point list following the spline for this connection.
	 */
	protected PointList getPointsFor(Connection conn) {
		SplineConnection con = (SplineConnection)conn;
		NodeConnection NodeConnection = con.getLink();
		Point start = new Point(NodeConnection.getSource().getX(), NodeConnection.getSource().getY());
		Point end = new Point(NodeConnection.getTarget().getX(), NodeConnection.getTarget().getY());
		con.translateToParent(start);
		con.translateToParent(end);
		
		PointList points = spline.getPointsBetween(start, end);
		return points;
	}

	/**
	 * This methode is called when a new connection is inserted in the path.
	 * @param conn
	 */
	protected void insertConnection(Connection conn) {
		// We have to add it to the connection list
		if(conn != null)
			conns.add(conn);
		
		// The first time we insert a connection, initialize the PathGraph.
		SplineConnection c = (SplineConnection)conn;
		
		// Update the NodeConnections hashmap with the new connection
		for (Iterator i = conns.iterator(); i.hasNext();) {
			SplineConnection con = (SplineConnection) i.next();
			// The NodeConnection is the key, the connection the value
			NodeConnections.put(con.getLink(), con);
		}
		
		// If we're not in the initializing phase
		if(!initializing){
			conns = updateOrder();
			spline = generateSpline();
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
			if(allLoaded() && initializing){
				initializing = false;
				conns = updateOrder();
				// So we can really route all the connections
				generateSpline = true;
				for (Iterator i = conns.iterator(); i.hasNext();)
					drawSpline((SplineConnection) i.next());
			}
		}
		else
		{
			// Redraw all the splines.
			generateSpline = true;
			for (Iterator i = conns.iterator(); i.hasNext();)
				drawSpline((SplineConnection) i.next());
		}
	}
	
	/**
	 * Generage the spline if necessary and set the point list of the connection.
	 * @param conn
	 */
	public void drawSpline(Connection conn){
		// Update the spline to draw
		if(generateSpline){
			spline = generateSpline();
			generateSpline = false;
		}
		
		PointList points = getPointsFor(conn);
		
//		PointList clipped = clipPointList(getStartPoint(conn), getEndPoint(conn), points);
		// Set the points for the given connection
		conn.setPoints(points);
		// The connection now follow the spline.
	}
	
	/**
	 * This function is used to know if all the connections are in our connection list.
	 * @return true if all the connections of the diagram are in our connection list, false otherwise.
	 */
	protected boolean allLoaded(){
		if(conns.size() == diagram.getNodeConnections().size())
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
		NodeConnections.remove(con.getLink());
		// Now we'll have to wait to receive route() calls from all the connections that changed
		initializing = true;

		super.remove(connection);
	}
	
	/**
	 * Clip a point list so that no point of the connection is IN a node...
	 * 
	 * @param start The start point of the connection/
	 * @param end The end point of the connection
	 * @param points The points describing the connection
	 * @return A pointlist not going past the start and end point.
	 */
	public PointList clipPointList(Point start, Point end, PointList points) {
		// We have to have a point list of at least two.
		if(points.size() >= 2){
			PointList clipped = new PointList();
			double xi, yi, r2Start, r2StartOld, r2End, r2EndOld;
			Point finalStart, finalEnd;
			boolean foundStart = false, foundEnd = false;
			
			// Calculate the first distance between the start and end points.
			xi = points.getPoint(0).x - start.x;
			yi = points.getPoint(0).y - start.y;
			r2StartOld = xi * xi + yi * yi;
			
			xi = points.getPoint(0).x - end.x;
			yi = points.getPoint(0).y - end.y;
			r2EndOld = xi * xi + yi * yi;
			
//			clipped.addPoint(start);
	
			Point point;
			// Loop trough all the points
			for (int i = 1; i < points.size(); i++) {
				point = points.getPoint(i);
				// Do that until we find the nearest point to the start point in the point list
				if(!foundStart){
					xi = point.x - start.x;
					yi = point.y - start.y;
					r2Start = (xi * xi) + (yi * yi); // Calculate the distance to the real start point
					if ((r2Start - r2StartOld) > 0) { // Once the new distance become bigger than the distance before, we know that we found the nearest point to the start.
						finalStart = point;
						foundStart = true;
					}
					r2StartOld = r2Start;
				}
				
				// Do the same thing with the end.
				if(!foundEnd){
					xi = point.x - end.x;
					yi = point.y - end.y;
					r2End = (xi*xi) + (yi*yi);
					if ((r2End - r2EndOld) > 0) {
						finalEnd = point;
						foundEnd = true;
					}
					r2EndOld = r2End;
				}
				
				// If we found both start and end, then finish the loop.  If we found at least the start, then add the points.
				if(foundStart && foundEnd)
					i = points.size();
				else
					clipped.addPoint(point);
			}
			
//			clipped.addPoint(end);
	
			return clipped;
		}
		else
			return points;
	}
}