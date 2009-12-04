package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * This figure represent a StartPoint and Waiting Place!
 * 
 * @author Etienne Tremblay, jkealey, gunterm
 */
public class StartPointFigure extends PathNodeFigure {
    private Ellipse ellipse;

    /**
     * An ellipse that fills 2/3 of the area.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        ellipse = new Ellipse();
        ellipse.setBounds(new Rectangle(DEFAULT_WIDTH / 6, DEFAULT_HEIGHT / 6, DEFAULT_WIDTH * 2 / 3, DEFAULT_HEIGHT * 2 / 3));
        ellipse.setBackgroundColor(ColorManager.LINE);
        ellipse.setAntialias(SWT.ON);
        add(ellipse);
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(ellipse);
        outgoingAnchor = new EllipseAnchor(ellipse);
    }

    public void setColor(Color bg) {
        super.setColor(bg);
        ellipse.setBackgroundColor(bg);
    }

    /**
     * Makes it larger on hover.
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover) {
            ellipse.setLocation(new Point(DEFAULT_WIDTH / 6 - 2, DEFAULT_HEIGHT / 6 - 2));
            ellipse.setSize(DEFAULT_WIDTH * 2 / 3 + 4, DEFAULT_HEIGHT * 2 / 3 + 4);

        } else {
            ellipse.setLocation(new Point(DEFAULT_WIDTH / 6, DEFAULT_HEIGHT / 6));
            ellipse.setSize(DEFAULT_WIDTH * 2 / 3, DEFAULT_HEIGHT * 2 / 3);
        }
    }

    /**
     * the color of a start point depends on whether it is selected, traversed, or the border of a pointcut expression
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#setColors()
     */
    protected void setColors() {
        if (selected) {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            setForegroundColor(ColorManager.TRAVERSAL);
            setColor(ColorManager.TRAVERSAL);
        } else if (isPointcutBorder) {
            setForegroundColor(ColorManager.POINTCUTBORDER);
            setColor(ColorManager.POINTCUTBORDER);
        } else {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.LINE);
        }
    }

    /**
     * We need local coordinates when resizing
     * 
     * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
     */
    protected boolean useLocalCoordinates() {

        return true;
    }
}