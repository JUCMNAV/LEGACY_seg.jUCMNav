package seg.jUCMNav.figures;

import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Created 2005-02-14
 * 
 * This figure represent a responsibility
 * 
 * @author Etienne Tremblay
 */
public class ResponsibilityFigure extends PathNodeFigure {
	
	RectangleFigure anchor;

	public ResponsibilityFigure(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.NodeFigure#createFigure()
	 */
	protected void createFigure() {
//		Polyline line = new Polyline();
		Dimension center = new Dimension(preferedSize.width/2, preferedSize.height/2);
//		line.setStart(new Point(center.width-5, center.height-5));
//		line.setEnd(new Point(center.width+5, center.height+5));
//		line.setBackgroundColor(new Color(null, 0, 0, 0));
//		line.setLineWidth(2);
//		add(line);
//		
//		line = new Polyline();
//		line.setStart(new Point(center.width+5, center.height-5));
//		line.setEnd(new Point(center.width-5, center.height+5));
//		line.setBackgroundColor(new Color(null, 0, 0, 0));
//		line.setLineWidth(2);
//		add(line);
		
		anchor = new RectangleFigure();
		anchor.setLocation(new Point(center.width-1, center.height-1));
		anchor.setSize(new Dimension(1, 1));
		add(anchor);
	}
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.NodeFigure#initAnchor()
	 */
	protected void initAnchor() {
		incomingAnchor = new EllipseAnchor(anchor);
		outgoingAnchor = new EllipseAnchor(anchor);
	}
	
	/**
	 * We have to paint this figure manualy because it seems that Polyline doesn't draw. 
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		g.setLineWidth(3);
		Point c = r.getCenter();
		// The lines for the X
		g.drawLine(c.x - 5, c.y-5, c.x+5, c.y+5);
		g.drawLine(c.x+5, c.y-5, c.x-5, c.y+5);
	}
}
