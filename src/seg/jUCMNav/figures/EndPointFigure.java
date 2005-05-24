package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Created 2005-02-15
 * 
 * This figure represent and EndPoint
 * 
 * @author Etienne Tremblay
 */
public class EndPointFigure extends PathNodeFigure {
	
	protected static final int DEFAULT_WIDTH = 4;
	
    private Ray entryVect = new Ray(10,10);
    private Polyline line;

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
    	line = new Polyline();
    	line.setLineWidth(3);
    	add(line);
    }
    
    public void setEntryRay(Ray r) {
    	entryVect = r.getScaled((int)(100/r.length()));
    	
    	Rectangle rect = this.getBounds().getCopy();
    	Point center = rect.getCenter();

    	Ray half1 = new Ray(-entryVect.y, entryVect.x);
    	Ray half2 = new Ray(entryVect.y, -entryVect.x);
    	
    	PointList list = new PointList();
    	list.addPoint(new Point(center.x + half1.x, center.y + half1.y));
    	list.addPoint(new Point(center.x + half2.x, center.y + half2.y));
    	line.setPoints(list);
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
    
    public static Dimension getDefaultDimension() {
    	return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}