/*
 * Created on 2005-02-15
 *
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * Created 2005-02-15
 * 
 * @author Etienne Tremblay
 */
public class EndPointFigure extends PathNodeFigure {
	
	private RectangleFigure rect;
	private Polyline line;
	//TODO Make the EndPoint draw depending on the orientation of the spline.
	private Ray entryVect;

	/**
	 * 
	 */
	public EndPointFigure() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.NodeFigure#createFigure()
	 */
	protected void createFigure() {
		rect = new RectangleFigure();
		rect.setBounds(new Rectangle(10, 0, 4, 24));
		rect.setBackgroundColor(new Color(null, 0, 0, 0));
		add(rect);
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.NodeFigure#initAnchor()
	 */
	protected void initAnchor() {
		incomingAnchor = new ChopboxAnchor(rect);
		outgoingAnchor = new ChopboxAnchor(rect);
	}
//	/* (non-Javadoc)
//	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
//	 */
//	public void paintFigure(Graphics g) {
//		Rectangle r = getBounds().getCopy();
//		Point c = r.getCenter();
//		g.setBackgroundColor(this.getBackgroundColor());
//		g.setLineWidth(4);
//		g.drawLine(r.x+10, r.y, r.x+10, r.y+24);
//	}
}
