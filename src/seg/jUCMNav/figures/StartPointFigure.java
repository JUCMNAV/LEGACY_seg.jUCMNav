/*
 * Created on 2005-02-15
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * Created 2005-02-15
 * 
 * @author Etienne Tremblay
 */
public class StartPointFigure extends NodeFigure {

	/**
	 * 
	 */
	public StartPointFigure() {
		super();
		this.setBackgroundColor(new Color(null, 0, 0, 0));
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
		return new Dimension(24, 24);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		Rectangle rec = this.getBounds();
//		if(selected)
//			g.setBackgroundColor(this.getBackgroundColor());
//		else
		g.setBackgroundColor(new Color(null, 0, 0, 0));
		g.fillOval(rec.x, rec.y, 20, 20);
		g.setLineWidth(2);
		g.setBackgroundColor(new Color(null, 0, 0, 0));
		g.drawOval(rec.x, rec.y, 20, 20);
	}

}
