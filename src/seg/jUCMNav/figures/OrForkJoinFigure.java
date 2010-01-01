package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * Represents an OR-Fork and an OR-Join in the model. No visuals; only the container.
 * 
 * @author jpdaigle
 */
public class OrForkJoinFigure extends PathNodeFigure {

    private Ellipse ellipse;

    /**
     * An invisible ellipse two which to bind the anchors; not used otherwise.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        ellipse = new Ellipse();
        ellipse.setBounds(new Rectangle(preferredSize.width / 4, preferredSize.height / 4, 0, 0)); // Size: 0 (invisible)
        ellipse.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        add(ellipse);
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new EllipseAnchor(ellipse);
        outgoingAnchor = new EllipseAnchor(ellipse);
    }

}