package seg.jUCMNav.figures.util;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import seg.jUCMNav.figures.TimeoutPathFigure;

/**
 * This is a simple addition to ConnectionLocator (which we use to find the middle of a SplineConnection) to force the refresh of the TimeoutPathFigure when the
 * locator is moved.
 * 
 * @author jkealey
 *  
 */
public class NodeConnectionLocator extends ConnectionLocator {
    /**
     * @param connection
     * @param align
     */
    public NodeConnectionLocator(Connection connection, int align) {
        super(connection, align);
    }

    /**
     * After trying to debug why the timeout figure wasn't being translated, I ended up recoding this method. Ugly hack :(
     * 
     * @param target
     *            The figure to relocate
     */
    public void relocate(IFigure target) {
        Dimension prefSize = target.getPreferredSize();
        Point center = getReferencePoint();
        target.translateToRelative(center);
        if (target instanceof TimeoutPathFigure) {
            TimeoutPathFigure fig = (TimeoutPathFigure) target;

            Polyline child = (Polyline) fig.getChildren().get(0);
            child.getPoints().performTranslate(center.x - child.getPoints().getFirstPoint().x + fig.getInitial().x,
                    center.y - child.getPoints().getFirstPoint().y + fig.getInitial().y);

            // force refresh bounds
            child.setPoints(child.getPoints());
        }

        target.setBounds(getNewBounds(prefSize, center));
    }

}