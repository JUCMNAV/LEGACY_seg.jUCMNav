/*
 * Created on 2005-02-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;

import seg.jUCMNav.model.ucm.Path;

/**
 * Created 2005-02-22
 * 
 * @author Etienne Tremblay
 */
public class PathFigure extends Figure {
	
	Path path;

	/**
	 * 
	 */
	public PathFigure(Path path) {
		super();
		this.path = path;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	public void paintFigure(Graphics g) {
		
	}
}
