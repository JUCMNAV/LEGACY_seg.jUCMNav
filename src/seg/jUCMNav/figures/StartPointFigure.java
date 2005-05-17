package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * Created 2005-02-15
 * 
 * This figure represent a StartPoint
 * 
 * @author Etienne Tremblay
 */
public class StartPointFigure extends PathNodeFigure {
	private static final int DEFAULT_WIDTH = 20;
	private static final int DEFAULT_HEIGHT = 20;
	
	private Ellipse ellipse;

	/**
	 * 
	 */
	public StartPointFigure() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.NodeFigure#createFigure()
	 */
	protected void createFigure() {
		ellipse = new Ellipse();
		ellipse.setBounds(new Rectangle(2, 2, DEFAULT_WIDTH, DEFAULT_HEIGHT));
		ellipse.setBackgroundColor(new Color(null, 0, 0,0));
		add(ellipse);
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.NodeFigure#initAnchor()
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
