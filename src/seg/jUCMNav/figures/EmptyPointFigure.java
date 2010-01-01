package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * This figure represent an empty point in the model.
 * 
 * @author Etienne Tremblay
 */
public class EmptyPointFigure extends PathNodeFigure {

    /** The empty point is an ellipse. */
    private Ellipse ellipse;

    /**
     * A simple filled circle taking up half the available space.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        ellipse = new Ellipse();
        ellipse.setBounds(new Rectangle(preferredSize.width / 4, preferredSize.height / 4, DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2));
        ellipse.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        add(ellipse);
    }

    /**
     * Returns the figure.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#getFigure()
     */
    public Figure getFigure() {
        return ellipse;
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(ellipse);
        outgoingAnchor = new EllipseAnchor(ellipse);
    }

    protected void setColors() {
        if (selected) {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            setForegroundColor(ColorManager.TRAVERSAL);
            setColor(ColorManager.FILL);
        } else {
            setForegroundColor(ColorManager.LINE);
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);
        }
    }
}