/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.NodeFigure;

/**
 * Created 2005-03-04
 * 
 * @author Etienne Tremblay
 */
public class PathNodeNonRezizableEditPolicy extends SelectionEditPolicy {

	/**
	 * 
	 */
	public PathNodeNonRezizableEditPolicy() {
		super();
	}
	
	private NodeFigure getFigure(){
		return (NodeFigure)((PathNodeEditPart)this.getHost()).getFigure();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
	 */
	protected void hideSelection() {
		getFigure().setBackgroundColor(new Color(null, 255, 255, 255));
		getFigure().setSelected(false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
	 */
	protected void showSelection() {
		getFigure().setBackgroundColor(new Color(null, 0, 102, 204));
		getFigure().setSelected(true);
	}
}
