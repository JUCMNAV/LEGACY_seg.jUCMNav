package seg.jUCMNav.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * Figure that represent a belief in GRL
 * 
 * @author Jean-François Roy
 * 
 */
public class BeliefFigure extends GrlNodeFigure {

    // default sizes
    protected final static int DEFAULT_HEIGHT = 50;
    protected final static int DEFAULT_WIDTH = 100;

    /**
     * Override this method if your figure is not of the default size.
     * 
     * @return Returns the default dimension.
     * @see seg.jUCMNav.figures.util.JUCMNavFigure
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Constructor of the belief figure the line style is in dash
     */
    public BeliefFigure() {
        super();
        setLineWidth(2);
        setLineStyle(Graphics.LINE_SOLID);
        setAntialias(GeneralPreferencePage.getAntialiasingPref());
        autoResize = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.GrlNodeFigure#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        r.x += getLineWidth() / 2;
        r.y += getLineWidth() / 2;
        r.width -= getLineWidth();
        r.height -= getLineWidth();
        graphics.drawOval(r);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.GrlNodeFigure#fillShape(org.eclipse.draw2d.Graphics)
     */
    protected void fillShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        r.x += getLineWidth() / 2;
        r.y += getLineWidth() / 2;
        r.width -= getLineWidth();
        r.height -= getLineWidth();
        graphics.fillOval(r);

    }
}
