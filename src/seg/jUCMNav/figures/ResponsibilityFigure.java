/*
 * Created on 2005-02-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Created 2005-02-14
 * 
 * @author Etienne Tremblay
 */
public class ResponsibilityFigure extends NodeFigure {

	public ResponsibilityFigure(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.IFigure#getMinimumSize(int, int)
	 */
	public Dimension getMinimumSize(int wHint, int hHint) {
		return getPreferredSize();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.IFigure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(20, 20);
	}
	/* (non-Javadoc)
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
