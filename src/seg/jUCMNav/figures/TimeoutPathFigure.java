package seg.jUCMNav.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Created on 16-Jun-2005
 * 
 * @author jkealey
 *  
 */
public class TimeoutPathFigure extends Figure implements Rotateable {

    public final static int WIDTH = 20;
    public final static int HEIGHT = 16;
    public final static int DELTA = 2;

    Polyline poly;

    /**
     *  
     */
    public TimeoutPathFigure() {
        createFigure();
    }

    protected void createFigure() {
        poly = new Polyline();
        poly.setLineWidth(3);
        poly.addPoint(getInitial());
        poly.addPoint(new Point(-WIDTH / 2, HEIGHT / 2));
        poly.addPoint(new Point(WIDTH / 2, -HEIGHT / 2));
        poly.addPoint(new Point(WIDTH / 2, -DELTA));

        add(poly);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(WIDTH + poly.getLineWidth(), HEIGHT + poly.getLineWidth());
    }

    public Point getInitial() {
        return new Point(-WIDTH / 2, DELTA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.Rotateable#rotate(double)
     */
    public void rotate(double angle) {
        // force refresh of bounds
        poly.setPoints(poly.getPoints());

    }

}