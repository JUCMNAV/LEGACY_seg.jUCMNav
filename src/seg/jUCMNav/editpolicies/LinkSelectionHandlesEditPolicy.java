/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editpolicies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.BendpointCreationHandle;
import org.eclipse.gef.handles.BendpointHandle;

/**
 * @author Etienne Tremblay
 *
 */
public class LinkSelectionHandlesEditPolicy extends SelectionHandlesEditPolicy {


	/**
	 * 
	 */
	public LinkSelectionHandlesEditPolicy() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#createSelectionHandles()
	 */
	protected List createSelectionHandles() {
		List list = new ArrayList();
		ConnectionEditPart connPart = (ConnectionEditPart)getHost();
		PointList points = getConnection().getPoints();
		for (int i = 0; i < points.size() - 2; i++) {
			BendpointHandle handle = new BendpointCreationHandle(connPart, 0, 
				new BendpointLocator(getConnection(), i + 1));
			list.add(handle);
		}
		return list;
	}

	protected Connection getConnection() {
		return (Connection)getHostFigure();
	}

}
