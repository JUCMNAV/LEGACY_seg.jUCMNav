/*
 * Created on 2005-02-25
 *
 */
package seg.jUCMNav.editpolicies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.model.commands.SplitLinkCommand;
import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Responsibility;
import seg.jUCMNav.model.ucm.StartPoint;

/**
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class LinkXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/**
	 * 
	 */
	public LinkXYLayoutEditPolicy() {
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
		
		if ( newObjectType == Node.class 
			|| newObjectType == Responsibility.class)
		{
			Link oldLink = (Link)this.getHost().getModel();
			if(oldLink.getSource() instanceof StartPoint || oldLink.getTarget() instanceof EndPoint)
				return null;
			
			SplitLinkCommand create = new SplitLinkCommand();
			create.setLocation(request.getLocation());
			create.setNode((Node)request.getNewObject());
			create.setOldLink(oldLink);
			create.setLabel("Create a node");
			createCommand = create;
		}
		return createCommand;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

}
