/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.model.commands.CreatePathCommand;
import seg.jUCMNav.model.commands.ExtendPathCommand;
import seg.jUCMNav.model.commands.SetConstraintCommand;
import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.StartPoint;
import seg.jUCMNav.model.ucm.UcmDiagram;

public class UcmXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	protected Command createAddCommand(EditPart child, Object constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	protected UcmDiagram getModel(){
		return (UcmDiagram)getHost().getModel();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		Object	newObjectType = null;
		if(request.getNewObject() != null)
			newObjectType = request.getNewObjectType();
		Command	createCommand = null;
		int childCount = ((UcmDiagram)this.getHost().getModel()).getNodes().size();
		
		if(newObjectType == StartPoint.class && childCount == 0){
			CreatePathCommand create = new CreatePathCommand();
			create.setDiagram((UcmDiagram)getHost().getModel());
			create.setStart((StartPoint)request.getNewObject());
			Rectangle constraint = (Rectangle)getConstraintFor(request);
			create.setPosition(constraint.getLocation());
			createCommand = create;
		}		
		else if (newObjectType == EndPoint.class)
		{
			ExtendPathCommand create = new ExtendPathCommand();
			create.setDiagram((UcmDiagram)getHost().getModel());
			// This will only work when there's one path...
			if(getModel().getPaths().size() > 0){
				create.setPath((Path)(getModel()).getPaths().get(0));
				Rectangle constraint = (Rectangle)getConstraintFor(request);
				create.setLocation(constraint.getLocation());
				create.setNewEnd( (EndPoint)request.getNewObject() );
				create.setLabel("Create a node");
				createCommand = create;
			}
			else
				return null;
		}

		return createCommand;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

//	/* (non-Javadoc)
//	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
//	 */
//	protected EditPolicy createChildEditPolicy(EditPart child) {
//			return new NonResizableEditPolicy();
//	}
	
//	/* (non-Javadoc)
//	 * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#getMinimumSizeFor(org.eclipse.gef.GraphicalEditPart)
//	 */
//	protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
//		return child.getContentPane().getMinimumSize();
//	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		SetConstraintCommand locationCommand = new SetConstraintCommand();
		locationCommand.setNode((Node)child.getModel());
		Point location = new Point(((Rectangle)constraint).x, ((Rectangle)constraint).y);
		locationCommand.setNewPosition(new Point(location.x, location.y));
		return locationCommand;
	}


}
