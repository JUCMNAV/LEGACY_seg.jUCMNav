package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Created 2005-03-07
 * 
 * This figure represent an empty point in the model.
 * 
 * @author Etienne Tremblay
 */
public class EmptyPointFigure extends PathNodeFigure {
	
	/** The empty point is an ellipse. */
	private Ellipse ellipse;

	public EmptyPointFigure() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
	 */
	protected void createFigure(){
		ellipse = new Ellipse();
		ellipse.setBounds(new Rectangle(preferredSize.width/4,preferredSize.height/4, 12,12));
		add(ellipse);
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.figures.PathNodeFigure#initAnchor()
	 */
	protected void initAnchor(){
		incomingAnchor = new EllipseAnchor(ellipse);
		outgoingAnchor = new EllipseAnchor(ellipse);
	}

}
