package seg.jUCMNav.figures;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * Created 2005-05-11
 * 
 * @author Etienne Tremblay
 */
public class StubFigure extends PathNodeFigure implements Rotateable {
	
	private static final int DEFAULT_WIDTH = 34;
	private static final int DEFAULT_HEIGHT = 34;
	
	private boolean dynamic = false;

	private Polygon mainFigure;
	private PointList edges;

	/**
	 *  
	 */
	public StubFigure(boolean dynamic) {
		super();
		this.dynamic = dynamic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
	 */
	protected void createFigure() {
		mainFigure = new Polygon();
		edges = new PointList();
		preferredSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		edges.addPoint(DEFAULT_WIDTH / 2, 1);
		edges.addPoint(1, DEFAULT_HEIGHT / 2);
		edges.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT-1);
		edges.addPoint(DEFAULT_WIDTH-1, DEFAULT_HEIGHT / 2);
		edges.addPoint(DEFAULT_WIDTH / 2, 1);
		mainFigure.setLineWidth(2);
		mainFigure.setPoints(edges);
		mainFigure.setBackgroundColor(new Color(null, 255, 255, 255));
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

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
		if (dynamic == true) {
		    /* You won't be able to change the line width to 2 until the target platform is 3.0.2 or 3.1:
		     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=4853
		     */
			mainFigure.setLineWidth(1);
			mainFigure.setLineStyle(SWT.LINE_DOT);
		}
		else {
			mainFigure.setLineWidth(2);
			mainFigure.setLineStyle(SWT.LINE_SOLID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
	 */
	protected void initAnchor() {
		incomingAnchor = new ChopboxAnchor(mainFigure);
		outgoingAnchor = new ChopboxAnchor(mainFigure);
	}

	protected boolean useLocalCoordinates() {
		return true;
	}
	
	public static Dimension getDefaultDimension() {
    	return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}