package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import asd.ASDiagram;
import asd.DivisionOfLabour;
import seg.jUCMNav.editparts.DivisionOfLabourEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeDivisionOfLabourNameCommand;

public class DivisionOfLabourDirectEditPolicy extends DirectEditPolicy 
{
	 private String oldValue;
	 private ASDiagram asdiagram;
	 
	 public DivisionOfLabourDirectEditPolicy(ASDiagram asdiagram)
	 {
		 this.asdiagram=asdiagram;
	 }
	    /**
	     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected Command getDirectEditCommand(DirectEditRequest request) {
	        DivisionOfLabour lbl = (DivisionOfLabour) getHost().getModel();

	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ChangeDivisionOfLabourNameCommand cmd = new ChangeDivisionOfLabourNameCommand(lbl, value,asdiagram);
	        return cmd;
	    }

	    /**
	     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected void showCurrentEditValue(DirectEditRequest request) {
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	       DivisionOfLabourEditPart lblPart = (DivisionOfLabourEditPart) getHost();
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
	        DivisionOfLabourEditPart lblPart = (DivisionOfLabourEditPart) getHost();
	        lblPart.revertNameChange();
	    }
}
