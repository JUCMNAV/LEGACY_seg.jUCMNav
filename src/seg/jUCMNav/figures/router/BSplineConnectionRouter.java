/*
 * Created on 2005-03-02
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.StartPoint;
import seg.jUCMNav.model.ucm.UcmDiagram;

/**
 * Created 2005-03-02
 * 
 * @author Etienne Tremblay
 */
public class BSplineConnectionRouter extends AbstractRouter {
	BSpline spline; // The spline used to return points.

	ArrayList conns = new ArrayList(); // The connections

	HashMap links = new HashMap();
	boolean initializing = true;
	
	UcmDiagram diagram;

	/**
	 *  
	 */
	public BSplineConnectionRouter() {
		super();
	}

	/**
	 * Return all the points of the spline defined by the connections.
	 * 
	 * @return
	 */
	protected PointList getAllPoints() {
		PointList list = new PointList();
		Point start, end;

		int j=0;
		for (Iterator i = conns.iterator(); i.hasNext();) {
			SplineConnection con = (SplineConnection) i.next();
			Link link = con.getLink();
			start = new Point(link.getSource().getX(), link.getSource().getY());
			end = new Point(link.getTarget().getX(), link.getTarget().getY());
//			start = getStartPoint(con);
//			end = getEndPoint(con);
//			con.translateToRelative(start);
//			con.translateToRelative(end);
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
	 * This function sort all the connections in comparison to the model
	 * 
	 * @return The new ordered connection array.
	 */
	protected ArrayList updateOrder() {
		//Try to find the first link in this path.
		Stack stack = new Stack();
		// Most of the time when the conns array is sorted, the first one will
		// be the first link. Sometimes not...
		Link link = ((SplineConnection) conns.get(0)).getLink(); // The first we
																 // look at.
		// Loop trough the links until we find the start point
		while (!(link.getSource() instanceof StartPoint)) {
			stack.push(links.get(link)); // Push the connection corresponding to
										 // this link.
			link = link.getSource().getUpLink(); // Get the previous connection
		}

		// Push the first link (the last we looked at)
		stack.push(links.get(link));

		ArrayList finalList = new ArrayList();

		// Transform the stacked connections to an sorted array
		while (!stack.isEmpty()){
			SplineConnection popped = (SplineConnection)stack.pop();
			link = popped.getLink();
			finalList.add(popped);
		}

		if (!(link.getTarget() instanceof EndPoint))
			link = link.getTarget().getDownLink(); // Get the next connection
		
		// Add each links until the endpoint to the list.
		while (!(link.getTarget() instanceof EndPoint)) {
			finalList.add(links.get(link));
			link = link.getTarget().getDownLink(); // Get the next connection
		}
		// Add the last connection too.
		finalList.add(links.get(link));
//		conns.clear();

		return finalList;
	}

	protected BSpline generateSpline() {
		return new BSpline(getAllPoints());
	}

	protected PointList getPointsFor(Connection connection) {
		PointList points = spline.getPointsBetween(this.getStartPoint(connection), this.getEndPoint(connection));
		return points;
	}

	protected void insertConnection(Connection conn) {
		if(conn != null)
			conns.add(conn);
		
		SplineConnection c = (SplineConnection)conn;
		if(diagram == null)
			diagram = c.getLink().getSource().getDiagram();
		
		// Update the links hashmap with
		for (Iterator i = conns.iterator(); i.hasNext();) {
			SplineConnection con = (SplineConnection) i.next();
			// The link is the key, the connection the value
			links.put(con.getLink(), con);
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
				for (Iterator i = conns.iterator(); i.hasNext();) {
					drawSpline((SplineConnection) i.next());
				}
			}
		}
		else
		{
			// Redraw all the splines.
			for (Iterator i = conns.iterator(); i.hasNext();) {
				SplineConnection con = (SplineConnection) i.next();
				drawSpline(con);
			}
		}
	}
	
	public void drawSpline(Connection conn){
		// Update the spline to draw
		spline = generateSpline();
		
		PointList points = getPointsFor(conn);
		
		PointList clipped = clipPointList(getStartPoint(conn), getEndPoint(conn), points);
		// Set the points for the given connection
		conn.setPoints(points);
		// The connection now follow the spline.
	}
	
	protected boolean allLoaded(){
		if(conns.size() == diagram.getLinks().size())
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.ConnectionRouter#remove(org.eclipse.draw2d.Connection)
	 */
	public void remove(Connection connection) {
		SplineConnection con = (SplineConnection) connection;
		conns.remove(connection);
		links.remove(con.getLink());
		initializing = true;

		super.remove(connection);
	}
	
	/**
	 * Clip a point list so that no point of the connection is IN a node...
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