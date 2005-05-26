/*
 * Created on 25-May-2005
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Represents an AND-Join in the model. Visually, lines join together on an invisible figure.
 * @author jpdaigle
 */
public class AndJoinFigure extends PathNodeFigure {

    RectangleFigure anchor;	

    public AndJoinFigure() {
		super();
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
	 */
	protected void createFigure() {
		Dimension center = new Dimension(preferredSize.width / 2,
				preferredSize.height / 2);
		anchor = new RectangleFigure();
		anchor.setLocation(new Point(center.width - 1, center.height - 1));
		anchor.setSize(new Dimension(1, 1));
		add(anchor);
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
	 */
	protected void initAnchor() {
		incomingAnchor = new EllipseAnchor(anchor);
		outgoingAnchor = new EllipseAnchor(anchor);
	}

	public void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		g.setLineWidth(4);
		Point c = r.getCenter();

		g.drawLine(c.x, c.y - 10, c.x, c.y + 10);
	}

	/**
	 * @return Returns the default dimension.
	 */
	public static Dimension getDefaultDimension() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
