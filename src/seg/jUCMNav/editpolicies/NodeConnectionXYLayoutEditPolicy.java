/*
 * Created on 2005-02-25
 *
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.model.commands.SplitLinkCommand;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;

/**
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class NodeConnectionXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/**
	 * 
	 */
	public NodeConnectionXYLayoutEditPolicy() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		Object	newObjectType = null;
		if(request.getNewObject() != null)
			newObjectType = request.getNewObjectType();
		Command	createCommand = null;
		
		if ( newObjectType == EmptyPoint.class || newObjectType == RespRef.class)
		{
			NodeConnection oldLink = (NodeConnection)this.getHost().getModel();
//			if(oldLink.getSource() instanceof StartPoint || oldLink.getTarget() instanceof EndPoint)
//				return null;

			Point where = getLocation(request);
			
			SplitLinkCommand create = new SplitLinkCommand();
			create.setLocation(where);
			create.setNode((PathNode)request.getNewObject());
			create.setOldLink(oldLink);
			create.setLabel("Create a node");
			create.setDiagram(getPathGraph());
			createCommand = create;
		}
		return createCommand;
	}
	
	/**
	 * @param request
	 * @return
	 */
	private Point getLocation(CreateRequest request) {
		IFigure figure = getLayoutContainer();
		Point where = request.getLocation().getCopy();
		Dimension size = request.getSize();
		figure.translateToRelative(where);
		figure.translateFromParent(where);
		return where;
	}

	/**
	 * The NodeConnectionEditPart contain a reference to the diagram too.  This function query the editpart and return the PathGraph.
	 * @return The PathGraph this NodeConnection is associated with.
	 */
	private PathGraph getPathGraph(){
		return ((NodeConnectionEditPart)getHost()).getPathGraph();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

}
