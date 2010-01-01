package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * This figure represent a timer in the model.
 * 
 * @author jkealey, jmcmanus
 */
public class TimerFigure extends PathNodeFigure {

    /**
     * @return Returns the default dimension.
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    // The base figure is an ellipse.
    private Ellipse ellipse;

    // with a line in it.
    private Polyline poly;

    /**
     * Creates a filled circle containing an L like segment to simulate a clock.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        ellipse = new Ellipse();
        // we're making it larger than the empty point.
        ellipse.setBounds(new Rectangle(preferredSize.width / 8, preferredSize.height / 8, DEFAULT_WIDTH * 3 / 4, DEFAULT_HEIGHT * 3 / 4));
        ellipse.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        poly = new Polyline();
        poly.addPoint(new Point(DEFAULT_WIDTH / 2, preferredSize.height / 8));
        poly.addPoint(new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2));
        poly.addPoint(new Point(preferredSize.width * 7 / 8, DEFAULT_HEIGHT / 2));
        poly.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        ellipse.add(poly);
        ellipse.setLineWidth(2);
        ellipse.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        poly.setLineWidth(2);

        add(ellipse);

    }

    /**
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(this);
        outgoingAnchor = new EllipseAnchor(this);
    }

    /**
     * We're using local coordinates in our calculation.
     */
    protected boolean useLocalCoordinates() {
        return true;
    }

    protected void setColors() {
        if (selected) {
            ellipse.setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            ellipse.setForegroundColor(ColorManager.TRAVERSAL);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);
        } else {
            ellipse.setForegroundColor(ColorManager.LINE);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);
        }
    }

}