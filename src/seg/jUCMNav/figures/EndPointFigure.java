package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

/**
 * Created 2005-02-15
 * 
 * This figure represent and EndPoint
 * 
 * @author Etienne Tremblay, Jordan McManus
 */
public class EndPointFigure extends PathNodeFigure implements Rotateable {

    private Polygon mainFigure;
    private PointList edges;
    private boolean offset;
    public static double RESIZEFACTOR = 1.4;

    /**
     *  
     */
    public EndPointFigure() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.NodeFigure#createFigure()
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
        Point center = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);

        edges = new PointList();
        edges.addPoint(DEFAULT_WIDTH / 2, 0);
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT);
        for (int i = 0; i < edges.size(); i++) {
            Point newPoint = t.getTransformed(new Point(edges.getPoint(i).x - center.x, edges.getPoint(i).y - center.y));
            Point pt = new Point(center.x - newPoint.x, center.y - newPoint.y);
            newEdges.addPoint(pt);
        }
        if (offset)
            newEdges.translate(t.getTransformed(new Point(DEFAULT_WIDTH * (RESIZEFACTOR - 1) / 2, -DEFAULT_WIDTH * (RESIZEFACTOR - 1) / 2)));

        mainFigure.setPoints(newEdges);
    }

    /**
     * @param offset
     *            Should this end point be offset from its center (and its bounding box grown) so that it can be connected to other elements without overlapping
     *            them.
     */
    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    protected boolean useLocalCoordinates() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.NodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(this);
        outgoingAnchor = new ChopboxAnchor(this);
    }

    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover)
            mainFigure.setLineWidth(5);
        else
            mainFigure.setLineWidth(3);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        if (!offset)
            return super.getPreferredSize(wHint, hHint);
        else
            return super.getPreferredSize(wHint, hHint).getCopy().scale(RESIZEFACTOR);
    }
}