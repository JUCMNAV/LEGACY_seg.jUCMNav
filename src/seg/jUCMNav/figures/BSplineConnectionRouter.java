/*
 * Created on 2005-03-02
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

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
		return spline.getPointsBetween(this.getStartPoint(connection), this.getEndPoint(connection));
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
			// If all the connections are now updated in how array, initialization is finished
			if(allLoaded() && initializing){
				initializing = false;
				// So we can really route all the connections
				for (Iterator i = conns.iterator(); i.hasNext();) {
					drawSpline((SplineConnection) i.next());
				}
			}
		}
		else
		{
			drawSpline(conn);
			for (Iterator i = conns.iterator(); i.hasNext();) {
				SplineConnection con = (SplineConnection) i.next();
				if(con != conn)
					drawSpline(con);
			}
		}
	}
	
	public void drawSpline(Connection conn){
		// Update the spline to draw
		spline = generateSpline();
		// Set the points for the given connection
		conn.setPoints(getPointsFor(conn));
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
}