/*
 * Created on 2005-02-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;

/**
 * Created 2005-02-22
 * 
 * @author Etienne Tremblay
 */
public class PathFigure extends FreeformLayer {
	
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
		this.setBounds(	new Rectangle(path.getStartpoint().getX()-20, path.getStartpoint().getY()-20,
				path.getEndpoint().getX()+20, path.getEndpoint().getY()+20));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		g.setLineWidth(2);
		g.setBackgroundColor(new Color(null, 0, 0, 0));
		
		Node start = path.getStartpoint();
		List nodes = path.getNodes();
		int j=0;
		Node node;
		Node last = start;
		for (Iterator i = nodes.iterator(); i.hasNext();) {
			node = (Node) i.next();
			g.drawLine(last.getX()+r.x, last.getY()+10+r.y, node.getX()+r.x, node.getY()+10+r.y);
			last = node;
		}
		Node end = path.getEndpoint();
		g.drawLine(last.getX()+r.x, last.getY()+10+r.y, end.getX()+r.x, end.getY()+10+r.y);
	}
}
