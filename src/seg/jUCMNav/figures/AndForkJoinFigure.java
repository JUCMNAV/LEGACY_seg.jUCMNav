package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * Figure for AndForks and AndJoins. Core is like an end point but maintains a list of points to which connection anchors should be associated.
 * 
 * @author jpdaigle, Jordan McManus, jkealey
 * 
 */
public class AndForkJoinFigure extends PathNodeFigure implements IRotateable {
    protected static final int DEFAULT_HEIGHT = 16;

    // default size is not the real width/height.
    // see getPreferredSize()
    protected static final int DEFAULT_WIDTH = 16;

    /**
     * @return Returns the default dimension.
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    // the rotation angle.
    private double angle;

    // the number of outs (if and fork) or ins (if and join)
    private int branchCount;

    // a list of points which not only defines the line but also the list of points to which connections should be attached.
    private PointList edges;

    // the rotated line.
    private Polygon mainFigure;

    /**
     * Creates a non-rotated vertical line, in middle of bounds, with two branches.
     * 
     * The edges PointList will never be rotated. To obtain rotated points, look athe mainFigure.getPoints()
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        mainFigure = new Polygon();

        edges = new PointList();

        edges.addPoint(DEFAULT_WIDTH / 2, 0);
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);
        edges.addPoint(DEFAULT_WIDTH / 2, 3 * DEFAULT_HEIGHT / 2);
        edges.addPoint(DEFAULT_WIDTH / 2, 2 * DEFAULT_HEIGHT);

        mainFigure.setLineWidth(6);
        mainFigure.setPoints(edges);
        mainFigure.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        add(mainFigure);
    }

    /**
     * 
     * @return the number of outs for AndForks and ins for AndJoins.
     */
    public int getBranchCount() {
        return branchCount;
    }

    /**
     * @return the original non-rotated/scaled PointList.
     */
    public PointList getEdges() {
        return edges;
    }

    /**
     * Returns the size, scaled by the number of branches. Initial implementation used the rotated size.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(DEFAULT_WIDTH * branchCount, DEFAULT_WIDTH * branchCount);
        // Dimension d = getRotatedSize();
        // if (d.width < DEFAULT_WIDTH)
        // d.width = DEFAULT_WIDTH;
        // else if (d.height < DEFAULT_HEIGHT)
        // d.height = DEFAULT_HEIGHT;
        // return d;
    }

    /**
     * Supposed to get the size of the bounding box given the angle, but doesn't work or isn't queried late enough.
     * 
     */
    public Dimension getRotatedSize() {
        int w = Math.abs((int) (DEFAULT_WIDTH * branchCount * Math.cos(angle + Math.PI / 2)));
        int h = Math.abs((int) (DEFAULT_HEIGHT * branchCount * Math.sin(angle + Math.PI / 2)));
        return new Dimension(w, h);
    }

    /**
     * Initializes useless anchors.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
     */
    protected void initAnchor() {
        incomingAnchor = new ChopboxAnchor(this);
        outgoingAnchor = new ChopboxAnchor(this);
    }

    /**
     * Apply a rotation to the edges PointList and set the rotated point list as the mainFigure's PointList.
     * 
     * Rotation takes into consideration the fact that the center moves when the number of branches changes.
     */
    public void rotate(double angle) {
        if (this.angle != angle) {
            this.angle = angle;
            // build the transformation
            Transform t = new Transform();
            t.setRotation(angle);
            // build the new point list
            PointList newEdges = new PointList();

            // this is the center of the figure in the edges pointlist.
            Point center = new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT * branchCount / 2);

            // this is the center of the figure in the rotated, real size, point list.
            Point centerScaledRotated = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);

            // rotate edges points around center to generate newEdges
            for (int i = 0; i < edges.size(); i++) {
                Point newPoint = t.getTransformed(new Point(edges.getPoint(i).x - center.x, edges.getPoint(i).y - center.y));
                newEdges.addPoint(newPoint);
            }

            // move them to the center of the rotated figure
            newEdges.translate(centerScaledRotated.x, centerScaledRotated.y);
            mainFigure.setPoints(newEdges);

            // inform the edit part that we've rotated via anchor listeners.
            ((ChopboxAnchor) outgoingAnchor).ancestorMoved(this);
        }
    }

    public double getAngle() {
        return this.angle;
    }

    /**
     * Rebuilds the edges PointList and grows the figure so that we change the point count but keep the same space between all the points
     * 
     * @param branchCount
     *            the number of outs for AndForks and ins for AndJoins.
     */
    public void setBranchCount(int branchCount) {
        if (branchCount == 0)
            return;
        assert branchCount > 0 : "invalid branch count"; //$NON-NLS-1$

        if (this.branchCount != branchCount) {

            this.branchCount = branchCount;

            PointList newEdges = new PointList();
            // first point; no connections hookup here.
            newEdges.addPoint(DEFAULT_WIDTH / 2, 0);
            // middle ones, connections hookup here.
            for (int i = 0; i < branchCount; i++) {
                newEdges.addPoint(DEFAULT_WIDTH / 2, i * DEFAULT_HEIGHT + DEFAULT_HEIGHT / 2);
            }
            // last point; no connections hookup here.
            newEdges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT * branchCount);

            edges = newEdges;

            // force refresh by faking an angle change
            double ang = angle;
            angle = ang + 1;
            rotate(ang);
        }

    }

    /**
     * Hover feedback increases line width.
     */
    public void setHover(boolean hover) {
        this.hover = hover;

        if (hover)
            mainFigure.setLineWidth(9);
        else
            mainFigure.setLineWidth(6);
    }

    /**
     * So that calculations don't use absolute coordinates.
     */
    protected boolean useLocalCoordinates() {
        return true;
    }
}