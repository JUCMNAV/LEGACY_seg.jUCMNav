package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;

/**
 * Created 2005-02-15
 * 
 * This figure represent and EndPoint
 * 
 * @author Etienne Tremblay
 */
public class EndPointFigure extends PathNodeFigure implements Rotateable {
	
	private Polygon mainFigure;
	private PointList edges;

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
    	Point center = new Point(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

    	for(int i = 0; i<edges.size(); i++) {
    		Point newPoint = t.getTransformed(new Point(edges.getPoint(i).x - center.x, edges.getPoint(i).y - center.y)); 
    		newEdges.addPoint(new Point(center.x - newPoint.x, center.y - newPoint.y));
    	}
    	
    	mainFigure.setPoints(newEdges);
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
}