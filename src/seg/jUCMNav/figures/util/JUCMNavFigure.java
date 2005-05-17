/*
 * Created on May 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures.util;

import org.eclipse.draw2d.geometry.Dimension;

import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.ResponsibilityFigure;
import seg.jUCMNav.figures.StartPointFigure;
import seg.jUCMNav.figures.StubFigure;
import ucm.map.EndPoint;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * @author Jordan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JUCMNavFigure {
	public static Dimension getDimension(Object modelElement) {
		if(modelElement instanceof StartPoint) {
			Dimension dim = StartPointFigure.getDefaultDimension();
			//The plus 2 is to offset the coordinates in the figure
			//ellipse.setBounds(new Rectangle(2, 2, DEFAULT_WIDTH, DEFAULT_HEIGHT));
			return new Dimension(dim.width, dim.height+2);
		} else if(modelElement instanceof EndPoint) {
			return EndPointFigure.getDefaultDimension();
		} else if(modelElement instanceof Stub) {
			return StubFigure.getDefaultDimension();
		} else if(modelElement instanceof RespRef) {
			return ResponsibilityFigure.getDefaultDimension();
		}
		
		return new Dimension(0,0);
	}
}
