package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * This figure is used to draw the circle associate with the connection in a decomposition
 * 
 * @author Jean-François Roy
 * 
 */
public class DecompositionFigure extends Shape {

    // default sizes
    protected static final int DEFAULT_HEIGHT = 25;
    protected static final int DEFAULT_WIDTH = 25;

    protected ConnectionAnchor anchor;

    /**
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * 
     */
    public DecompositionFigure() {
        super();
        setLineWidth(1);
        setAntialias(GeneralPreferencePage.getAntialiasingPref());

        initAnchor();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocation(new Point(100, 100));
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        anchor = new ChopboxAnchor(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
     */
    protected void fillShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        graphics.drawOval(r);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        graphics.fillOval(r);
    }

}
