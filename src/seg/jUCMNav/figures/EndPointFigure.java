/*
 * Created on 2005-02-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Created 2005-02-15
 * 
 * @author Etienne Tremblay
 */
public class EndPointFigure extends NodeFigure {

	/**
	 * 
	 */
	public EndPointFigure() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		Point c = r.getCenter();
		g.setBackgroundColor(this.getBackgroundColor());
		g.setLineWidth(5);
		g.drawLine(r.x+12, r.y, r.x+12, r.y+20);
	}
}
