package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.Shape;

/**
 * A Polyline that can be filled.
 * 
 * @author Etienne Tremblay
 */
public class Polygon extends Polyline {

    /**
     * @see Shape#fillShape(Graphics)
     */
    protected void fillShape(Graphics g) {
        g.fillPolygon(getPoints());
    }
}