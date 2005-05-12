package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.Shape;

/**
 * Created 2005-05-11
 * 
 * @author Etienne Tremblay
 */
public class Polygon extends Polyline {

	/**
	 * 
	 */
	public Polygon() {
		super();
	}
	
	/**
	 * @see Shape#fillShape(Graphics)
	 */
	protected void fillShape(Graphics g) {
		g.fillPolygon(getPoints());
	}
}
