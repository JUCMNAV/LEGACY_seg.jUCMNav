/*
 * Created on 8-May-2005
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

/**
 * @author jpdaigle, Jordan McManus, jkealey
 *  
 */
public class AndForkFigure extends PathNodeFigure implements Rotateable {

	protected static int DEFAULT_WIDTH = 16;
	protected static int DEFAULT_HEIGHT = 16;
	
    private Polygon mainFigure;
    private PointList edges;
    private int branchCount;
    private double angle;

    public AndForkFigure() {
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
        edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT/2);
        edges.addPoint(DEFAULT_WIDTH / 2, 3*DEFAULT_HEIGHT/2);
        edges.addPoint(DEFAULT_WIDTH / 2, 2*DEFAULT_HEIGHT);

        mainFigure.setLineWidth(6);
        mainFigure.setPoints(edges);
        add(mainFigure);
    }

    public void rotate(double angle) {
        if (this.angle != angle) {
            this.angle = angle;
            Transform t = new Transform();
            t.setRotation(angle);
            PointList newEdges = new PointList();
            Point center = new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT * branchCount / 2);
            Point centerScaledRotated = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);
            for (int i = 0; i < edges.size(); i++) {
                Point newPoint = t.getTransformed(new Point(edges.getPoint(i).x - center.x, edges.getPoint(i).y - center.y));
                newEdges.addPoint(newPoint);
            }
            newEdges.translate(centerScaledRotated.x, centerScaledRotated.y);
            mainFigure.setPoints(newEdges);
            ((ChopboxAnchor) outgoingAnchor).ancestorMoved(this);
        }
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
            mainFigure.setLineWidth(9);
        else
            mainFigure.setLineWidth(6);
    }

    /**
     * @return Returns the default dimension.
     */
    public static Dimension getDefaultDimension() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public PointList getEdges() {
        return edges;
    }

    public int getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(int branchCount) {
        if (branchCount == 0)
            return;
        assert branchCount > 0 : "invalid branch count";

        if (this.branchCount != branchCount) {

            this.branchCount = branchCount;
            PointList newEdges = new PointList();
            newEdges.addPoint(DEFAULT_WIDTH/2, 0);
            for (int i = 0; i < branchCount; i++) {
                newEdges.addPoint(DEFAULT_WIDTH / 2, i * DEFAULT_HEIGHT + DEFAULT_HEIGHT/2);
            }
            newEdges.addPoint(DEFAULT_WIDTH/2, DEFAULT_HEIGHT*branchCount);

            edges = newEdges;

            // force refresh
            double ang = angle;
            angle = ang + 1;
            rotate(ang);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        return new Dimension(DEFAULT_WIDTH * branchCount, DEFAULT_WIDTH * branchCount);
        //        Dimension d = getRotatedSize();
        //        if (d.width < DEFAULT_WIDTH)
        //            d.width = DEFAULT_WIDTH;
        //        else if (d.height < DEFAULT_HEIGHT)
        //            d.height = DEFAULT_HEIGHT;
        //        return d;
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
}