package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import asd.ASDiagram;
import asd.Community;
import seg.jUCMNav.editparts.CommunityEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeCommunityNameCommand;

public class CommunityDirectEditPolicy extends DirectEditPolicy 
{
	 private String oldValue;
	 private ASDiagram asdiagram;
	 
	 public CommunityDirectEditPolicy(ASDiagram asdiagram)
	 {
		 this.asdiagram=asdiagram;
	 }
	    /**
	     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected Command getDirectEditCommand(DirectEditRequest request) {
	        Community lbl = (Community) getHost().getModel();

	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ChangeCommunityNameCommand cmd = new ChangeCommunityNameCommand(lbl, value, asdiagram);
	        return cmd;
	    }

	    /**
	     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected void showCurrentEditValue(DirectEditRequest request) {
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	       CommunityEditPart lblPart = (CommunityEditPart) getHost();
	        lblPart.handleNameChange(value);
	    }

	    /**
	     * @param request
	     *            Saves the initial text value so that if the user's changes are not committed then
	     */
	    protected void storeOldEditValue(DirectEditRequest request) {

	        CellEditor cellEditor = request.getCellEditor();
	        oldValue = (String) cellEditor.getValue();
	        oldValue = oldValue.trim();
	    }

	    /**
	     * @param request
	     *            reverts to the old value.
	     */
	    protected void revertOldEditValue(DirectEditRequest request) {
	        CellEditor cellEditor = request.getCellEditor();
	        cellEditor.setValue(oldValue);
	        CommunityEditPart lblPart = (CommunityEditPart) getHost();
	        lblPart.revertNameChange();
	    }
}
