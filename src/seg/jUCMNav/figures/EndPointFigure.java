package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
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
    	
    }
    
    public void paint(Graphics graphics) {
    	Rectangle rect = this.getBounds().getCopy();
    	Point center = rect.getCenter();
    	
    	Ray half1 = new Ray(-entryVect.y, entryVect.x);
    	Ray half2 = new Ray(entryVect.y, -entryVect.x);
    	
    	graphics.setLineWidth(3);
    	graphics.drawLine(center.x,center.y , center.x + half1.x, center.y + half1.y);
    	graphics.drawLine(center.x,center.y , center.x + half2.x, center.y + half2.y);
    	
    }
    
    public void setEntryRay(Ray r) {
    	entryVect = r;
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