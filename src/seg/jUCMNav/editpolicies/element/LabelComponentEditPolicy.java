/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.editparts.PathNodeEditPart;
import ucm.map.PathGraph;

/**
 * @author Jordan
 */
public class LabelComponentEditPolicy extends ComponentEditPolicy {
    /**
	 * 
	 */
	public LabelComponentEditPolicy() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		//return new DeleteLabelCommand();
	    return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	public Command getCommand(Request request) {
	    return super.getCommand(request);
	}
	
	private PathGraph getPathGraph(){
		return ((PathNodeEditPart)getHost()).getDiagram();
	}
}
