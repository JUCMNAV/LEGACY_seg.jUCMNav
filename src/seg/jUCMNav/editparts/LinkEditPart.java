/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import seg.jUCMNav.editpolicies.LinkEditPolicy;
import seg.jUCMNav.editpolicies.LinkEndpointEditPolicy;
import seg.jUCMNav.editpolicies.LinkXYLayoutEditPolicy;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.model.ucm.Link;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LinkEditPart extends AbstractConnectionEditPart {
	
	public LinkEditPart(Link link) {
		super();
		setModel(link);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy( EditPolicy.CONNECTION_ENDPOINTS_ROLE, new LinkEndpointEditPolicy());
//		installEditPolicy( EditPolicy.CONNECTION_BENDPOINTS_ROLE, new LinkSelectionHandlesEditPolicy() );
		installEditPolicy( EditPolicy.CONNECTION_ROLE, new LinkEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LinkXYLayoutEditPolicy());
	}
	
	private Link getLink() {
		return (Link)getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		SplineConnection connection = new SplineConnection();
		connection.setLink(getLink());
		connection.setLineWidth(3);
//		PolygonDecoration p = new PolygonDecoration();
//		connection.setTargetDecoration(p); // arrow at target endpoint
		return connection;
	}
}
