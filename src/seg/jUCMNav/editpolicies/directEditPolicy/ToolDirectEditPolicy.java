package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import asd.ASDiagram;
import asd.Tool;
import seg.jUCMNav.editparts.ToolEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeToolNameCommand;

public class ToolDirectEditPolicy extends DirectEditPolicy 
{
	 private String oldValue;
	 private ASDiagram asdiagram;
	 
	 public ToolDirectEditPolicy(ASDiagram asdiagram)
	 {
		 	this.asdiagram=asdiagram;
	 }
	    /**
	     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected Command getDirectEditCommand(DirectEditRequest request) {
	        Tool lbl = (Tool) getHost().getModel();
	        
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ChangeToolNameCommand cmd = new ChangeToolNameCommand(lbl, value,asdiagram);
	        return cmd;
	    }

	    /**
	     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected void showCurrentEditValue(DirectEditRequest request) {
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ToolEditPart lblPart = (ToolEditPart) getHost();
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
	        ToolEditPart lblPart = (ToolEditPart) getHost();
	        lblPart.revertNameChange();
	    }
}
