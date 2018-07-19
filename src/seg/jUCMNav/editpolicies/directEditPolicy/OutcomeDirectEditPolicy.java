package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import asd.Outcome;
import seg.jUCMNav.editparts.OutcomeEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeOutcomeNameCommand;

public class OutcomeDirectEditPolicy extends DirectEditPolicy 
{
	 private String oldValue;

	    /**
	     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected Command getDirectEditCommand(DirectEditRequest request) {
	        Outcome lbl = (Outcome) getHost().getModel();

	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ChangeOutcomeNameCommand cmd = new ChangeOutcomeNameCommand(lbl, value);
	        return cmd;
	    }

	    /**
	     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected void showCurrentEditValue(DirectEditRequest request) {
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	       OutcomeEditPart lblPart = (OutcomeEditPart) getHost();
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
	        OutcomeEditPart lblPart = (OutcomeEditPart) getHost();
	        lblPart.revertNameChange();
	    }
}
