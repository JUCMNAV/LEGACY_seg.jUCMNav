/*
 * Created on 2005-02-15
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * Created 2005-02-15
 * 
 * @author Etienne Tremblay
 */
public class StartPointFigure extends NodeFigure {
	
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
		ellipse.setBounds(new Rectangle(2, 2, 20, 20));
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

}
