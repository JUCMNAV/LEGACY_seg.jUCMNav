package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

/**
 * Figure for a Direction Arrow. An arrow that can be rotated.
 * 
 * @author Jordan McManus
 */
public class DirectionArrowFigure extends PathNodeFigure implements Rotateable {

    private Polygon edge1;
    private Polygon edge2;
    private PointList enpoints1;
    private PointList enpoints2;

    /**
     * Create arrow taking half the horizontal and vertical space.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        edge1 = new Polygon();
        enpoints1 = new PointList();

        edge2 = new Polygon();
        enpoints2 = new PointList();

        enpoints1.addPoint(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT / 4);
        enpoints1.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

        enpoints2.addPoint(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT - DEFAULT_HEIGHT / 4);
        enpoints2.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

        edge1.setLineWidth(3);
        edge1.setPoints(enpoints1);

        edge2.setLineWidth(3);
        edge2.setPoints(enpoints2);

        add(edge1);
        add(edge2);
    }

    /**
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(this);
        outgoingAnchor = new ChopboxAnchor(this);
    }

    /**
     * Rotate the pointlists.
     */
    public void rotate(double angle) {
        Transform t = new Transform();
        t.setRotation(angle);

        PointList newEdges1 = new PointList();
        PointList newEdges2 = new PointList();
        Point center = new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

        for (int i = 0; i < enpoints1.size(); i++) {
            Point newPoint = t.getTransformed(new Point(enpoints1.getPoint(i).x - center.x, enpoints1.getPoint(i).y - center.y));
            newEdges1.addPoint(new Point(center.x - newPoint.x, center.y - newPoint.y));
        }

        for (int i = 0; i < enpoints2.size(); i++) {
            Point newPoint = t.getTransformed(new Point(enpoints2.getPoint(i).x - center.x, enpoints2.getPoint(i).y - center.y));
            newEdges2.addPoint(new Point(center.x - newPoint.x, center.y - newPoint.y));
        }

        edge1.setPoints(newEdges1);
        edge2.setPoints(newEdges2);
    }

    /**
     * Make line thicker on hover.
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover) {
            edge1.setLineWidth(5);
            edge2.setLineWidth(5);
        } else {
            edge1.setLineWidth(3);
            edge2.setLineWidth(3);
        }

    }

    /**
     * All transformations require local coordinates.
     */
    protected boolean useLocalCoordinates() {
        return true;
    }
}