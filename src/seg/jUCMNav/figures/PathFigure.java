/*
 * Created on 2005-02-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;

/**
 * Created 2005-02-22
 * 
 * @author Etienne Tremblay
 */
public class PathFigure extends Figure {
	
	private Path path;
	private XYLayout layout;

	/**
	 * 
	 */
	public PathFigure(Path path) {
		super();
		this.path = path;
		layout = new XYLayout();
		this.setLayoutManager(layout);
//		this.setBounds(	new Rectangle(path.getStartpoint().getX(), path.getStartpoint().getY(),
//				path.getEndpoint().getX()-path.getStartpoint().getX()+5, path.getEndpoint().getY()-path.getStartpoint().getY()+20));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.IFigure#getBounds()
	 */
	public Rectangle getBounds() {
		Node start = path.getStartpoint();
		List nodes = path.getNodes();
		Node end = path.getEndpoint();
		nodes.add(start);
		nodes.add(end);
		
		int minX = ((Node)nodes.get(0)).getX();
		int maxX = ((Node)nodes.get(0)).getX();
		int minY = ((Node)nodes.get(0)).getY();
		int maxY = ((Node)nodes.get(0)).getY();
		
		for (Iterator i = nodes.iterator(); i.hasNext();) {
			Node node = (Node) i.next();
			if(node.getX() < minX)
				minX = node.getX();
			else if(node.getX() > maxX)
				maxX = node.getX();
			
			if(node.getY() < minY)
				minY = node.getY();
			else if(node.getY() > maxY)
				maxY = node.getY();
		}
		
		return new Rectangle(minX, minY, maxX-minX, maxY-minY);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		g.setLineWidth(2);
		
		Node start = path.getStartpoint();
		List nodes = path.getNodes();
		int j=0;
		Node node;
		Node last = start;
		for (Iterator i = nodes.iterator(); i.hasNext();) {
			node = (Node) i.next();
			g.drawLine(last.getX(), last.getY()+10, node.getX(), node.getY()+10);
			last = node;
		}
//		Node end = path.getEndpoint();
//		g.drawLine(last.getX(), last.getY()+10, end.getX(), end.getY()+10);
	}
}
