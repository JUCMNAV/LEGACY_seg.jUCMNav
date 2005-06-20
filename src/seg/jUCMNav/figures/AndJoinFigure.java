/*
 * Created on 25-May-2005
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

/**
 * Represents an AND-Join in the model. Visually, lines join together on an invisible figure.
 * 
 * @author jpdaigle, Jordan McManus
 */
public class AndJoinFigure extends PathNodeFigure implements Rotateable {

    private Polygon mainFigure;
    private PointList edges;

    public AndJoinFigure() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        mainFigure = new Polygon();
        edges = new PointList();

        edges.addPoint(DEFAULT_WIDTH / 2, 0);
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT);

        mainFigure.setLineWidth(3);
        mainFigure.setPoints(edges);
        add(mainFigure);
    }

    public void rotate(double angle) {
        Transform t = new Transform();
        t.setRotation(angle);

        PointList newEdges = new PointList();
        Point center = new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

        for (int i = 0; i < edges.size(); i++) {
            Point newPoint = t.getTransformed(new Point(edges.getPoint(i).x - center.x, edges.getPoint(i).y - center.y));
            newEdges.addPoint(new Point(center.x - newPoint.x, center.y - newPoint.y));
        }

        mainFigure.setPoints(newEdges);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(this);
        outgoingAnchor = new ChopboxAnchor(this);
    }

    protected boolean useLocalCoordinates() {
        return true;
    }

    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover)
            mainFigure.setLineWidth(6);
        else
            mainFigure.setLineWidth(3);
    }

    /**
     * @return Returns the default dimension.
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}