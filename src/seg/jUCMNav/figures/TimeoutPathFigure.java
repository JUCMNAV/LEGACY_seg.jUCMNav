package seg.jUCMNav.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * Figure for a TimeoutPath. Is intented to be placed on a SplineConnection.
 * 
 * Attempts to make rotateable failed.
 * 
 * @author jkealey
 * 
 */
public class TimeoutPathFigure extends Figure {

    public final static int WIDTH = 20;
    public final static int HEIGHT = 16;
    public final static int DELTA = 2;

    // the figure
    private Polyline poly;

    /**
     *  
     */
    public TimeoutPathFigure() {
        createFigure();
    }

    /**
     * Is a Z like figure determined by the three static constants, WIDTH, HEIGHT and DELTA
     * 
     */
    protected void createFigure() {
        poly = new Polyline();
        poly.setLineWidth(3);
        poly.addPoint(getInitial());
        poly.addPoint(new Point(-WIDTH / 2, HEIGHT / 2));
        poly.addPoint(new Point(WIDTH / 2, -HEIGHT / 2));
        poly.addPoint(new Point(WIDTH / 2, -DELTA));
        poly.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        add(poly);
    }

    /**
     * Returns the minimum size to contain the figure.
     * 
     * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(WIDTH + poly.getLineWidth(), HEIGHT + poly.getLineWidth());
    }

    /**
     * Change to offset figure placement.
     * 
     * @return the initial point for the drawing.
     */
    public Point getInitial() {
        return new Point(-WIDTH / 2, DELTA);
    }

}