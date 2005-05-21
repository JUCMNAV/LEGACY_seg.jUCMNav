/*
 * Created on 30-Apr-2005
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Represents an OR-Fork in the model. Visually, a circle like an EmptyPoint
 * @author jpdaigle
 */
public class OrForkFigure extends PathNodeFigure {
	
	private Ellipse ellipse;
	/**
	 * 
	 */
	public OrForkFigure() {
		super();
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
	 */
	protected void createFigure() {
		ellipse = new Ellipse();
		ellipse.setBounds(new Rectangle(preferredSize.width/4,preferredSize.height/4, 0,0)); // Size: 0 (invisible)
		add(ellipse);
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
	 */
	protected void initAnchor() {
		incomingAnchor = new EllipseAnchor(ellipse);
		outgoingAnchor = new EllipseAnchor(ellipse);
	}
	
	/**
	 * @return Returns the default dimension.
	 */
	public static Dimension getDefaultDimension() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}