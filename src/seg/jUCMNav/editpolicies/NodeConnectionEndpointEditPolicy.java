/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

/**
 * @author Etienne Tremblay
 *
 */
public class NodeConnectionEndpointEditPolicy extends ConnectionEndpointEditPolicy {

	/**
	 * 
	 */
	public NodeConnectionEndpointEditPolicy() {
		super();
	}

	protected void addSelectionHandles(){
//		super.addSelectionHandles();
	}

	protected PolylineConnection getConnectionFigure(){
		return (PolylineConnection)((GraphicalEditPart)getHost()).getFigure();
	}

	protected void removeSelectionHandles(){
//		super.removeSelectionHandles();
	}
	
}
