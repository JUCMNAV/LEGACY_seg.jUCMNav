/*
 * Created on 2005-03-07
 *
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Created 2005-03-07
 * 
 * @author Etienne Tremblay
 */
public class EmptyNodeFigure extends NodeFigure {
	
	private Ellipse ellipse;

	/**
	 * 
	 */
	public EmptyNodeFigure() {
		super();
	}
	
	protected void createFigure(){
		ellipse = new Ellipse();
		ellipse.setBounds(new Rectangle(preferedSize.width/4,preferedSize.height/4, 12,12));
		add(ellipse);
	}
	
	protected void initAnchor(){
		incomingAnchor = new EllipseAnchor(ellipse);
		outgoingAnchor = new EllipseAnchor(ellipse);
	}

}
