/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import seg.jUCMNav.editpolicies.NodeConnectionEditPolicy;
import seg.jUCMNav.editpolicies.NodeConnectionEndpointEditPolicy;
import seg.jUCMNav.editpolicies.NodeConnectionXYLayoutEditPolicy;
import seg.jUCMNav.figures.SplineConnection;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;

/**
 * @author Etienne Tremblay
 *
 */
public class NodeConnectionEditPart extends AbstractConnectionEditPart {
	
	private PathGraph diagram;
	
	public NodeConnectionEditPart(NodeConnection link, PathGraph diagram) {
		super();
		setModel(link);
		this.diagram = diagram;
	}
	
	public PathGraph getPathGraph(){
		return diagram;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy( EditPolicy.CONNECTION_ENDPOINTS_ROLE, new NodeConnectionEndpointEditPolicy());
//		installEditPolicy( EditPolicy.CONNECTION_BENDPOINTS_ROLE, new LinkSelectionHandlesEditPolicy() );
		installEditPolicy( EditPolicy.CONNECTION_ROLE, new NodeConnectionEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new NodeConnectionXYLayoutEditPolicy());
	}
	
	private NodeConnection getLink() {
		return (NodeConnection)getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		SplineConnection connection = new SplineConnection(getLink());
		connection.setLineWidth(3);
//		PolygonDecoration p = new PolygonDecoration();
//		connection.setTargetDecoration(p); // arrow at target endpoint
		return connection;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
	}
}
