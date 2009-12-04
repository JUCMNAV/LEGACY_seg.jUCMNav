package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.SWT;

/**
 * This figure represent and EndPoint
 * 
 * @author Etienne Tremblay, Jordan McManus, gunterm
 */
public class EndPointFigure extends PathNodeFigure implements IRotateable {

    private Polygon mainFigure;
    private PointList edges;

    // is this end point connected to a start point
    private boolean offset;

    // by how much to scale when connected to start point.
    public static final double RESIZEFACTOR = 1.4;

    /**
     * A simple line, as wide as the bounds.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        mainFigure = new Polygon();
        edges = new PointList();

        edges.addPoint(DEFAULT_WIDTH / 2, 0);
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT);

        mainFigure.setLineWidth(5);
        mainFigure.setPoints(edges);
        mainFigure.setAntialias(SWT.ON);
        add(mainFigure);
    }

    /**
     * Rotate the line. Figure knows that center moves if there offset==true
     */
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

    /**
     * Must use local coordinates in calculations.
     */
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(this);
        outgoingAnchor = new ChopboxAnchor(this);
    }

    /**
     * Line thickens on hover
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover)
            mainFigure.setLineWidth(7);
        else
            mainFigure.setLineWidth(5);
    }

    /**
     * the color of an end point depends on whether it is selected, traversed, part of a pointcut, or the cursor is hovering over it
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
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.FILL);
        }
    }

    /**
     * if offset, returns a scaled (RESIZEFACTOR) copy of the regular size.
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