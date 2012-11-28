package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.figures.util.TransformationHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

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
    private Polyline line;
    private PointList linePts;
    
    private boolean isLocal = false;

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
        
        int barWidth = 6 / 2;

        edges.addPoint(DEFAULT_WIDTH / 2 - barWidth, 1);
        edges.addPoint(DEFAULT_WIDTH / 2 - barWidth, DEFAULT_HEIGHT-1);

        edges.addPoint(DEFAULT_WIDTH / 2 + barWidth, DEFAULT_HEIGHT-1);
        edges.addPoint(DEFAULT_WIDTH / 2 + barWidth, 1);
        edges.addPoint(DEFAULT_WIDTH / 2 - barWidth, 1);

        mainFigure.setLineWidth(2);
        mainFigure.setPoints(edges);
        mainFigure.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        mainFigure.setForegroundColor(ColorManager.GRAY);
        mainFigure.setBackgroundColor(ColorManager.LINE);
        mainFigure.setFill(true);
        
        add(mainFigure);
        
        line = new Polyline();
        linePts = new PointList();
        linePts.addPoint(new Point(DEFAULT_WIDTH / 2 - barWidth+1, 0));
        linePts.addPoint(new Point(DEFAULT_WIDTH / 2 + barWidth-1, DEFAULT_HEIGHT - 1));
        line.setPoints(linePts);
        line.setLineWidth(2);
        line.setAntialias(GeneralPreferencePage.getAntialiasingPref());
        line.setForegroundColor(ColorManager.WHITE);
        
        add(line);
    }

    /**
     * Rotate the line. Figure knows that center moves if there offset==true
     */
    public void rotate(double angle) {
        PointList newEdges = rotatePoints(angle, edges);
        mainFigure.setPoints(newEdges);
        
        newEdges = rotatePoints(angle, linePts);
        line.setPoints(newEdges);
    }
    
    /**
     * For a given angle, rotate the point list.
     * 
     * @param angle
     * @param points
     * @return
     */
    protected PointList rotatePoints(double angle, PointList points) {
        Transform t = new Transform();
        t.setRotation(angle);
        
        Point center = new Point(getPreferredSize().width / 2, getPreferredSize().height / 2);

        PointList newEdges = TransformationHelper.rotatePoints(angle, edges, center);
        
        if (offset)
            newEdges.translate(t.getTransformed(new PrecisionPoint(DEFAULT_WIDTH * (RESIZEFACTOR - 1) / 2, -DEFAULT_WIDTH * (RESIZEFACTOR - 1) / 2)));
        
        return newEdges;
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
            mainFigure.setLineWidth(2);
        else
            mainFigure.setLineWidth(1);
    }

    /**
     * the color of an end point depends on whether it is selected, traversed, part of a pointcut, or the cursor is hovering over it
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#setColors()
     */
    protected void setColors() {
   	 	if(userColor!=null){
   	 		// @author: nikiforov
   	 		// highlight PathNode by user color
   	 		setForegroundColor(userColor);
   	 		setColor(userColor);
   	 	}else if (selected) {
            setForegroundColor(ColorManager.LINE);
            setColor(ColorManager.SELECTED);
        } else if (traversed) {
            setForegroundColor(ColorManager.TRAVERSAL);
            setColor(ColorManager.TRAVERSAL);
        } else if (isPointcutBorder) {
            setForegroundColor(ColorManager.POINTCUTBORDER);
            setColor(ColorManager.POINTCUTBORDER);
        } else {
            if(isLocal)
                mainFigure.setForegroundColor(ColorManager.DARKGRAY);
            else
                mainFigure.setForegroundColor(ColorManager.LINE);
            
            if (hover)
                setColor(ColorManager.HOVER);
            else
                setColor(ColorManager.LINE);
        }
    }
    
    public void setType(boolean isLocal) {
        this.isLocal = isLocal;
        
        line.setVisible(isLocal);
        if(isLocal)
            mainFigure.setForegroundColor(ColorManager.DARKGRAY);
        else
            mainFigure.setForegroundColor(ColorManager.LINE);
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

    @Override
    public void setColor(Color bg) {
        mainFigure.setBackgroundColor(bg);
    }

    @Override
    public void setForegroundColor(Color fg) {
        mainFigure.setForegroundColor(fg);
    }
}