package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import asd.ASDiagram;
import asd.Rule;
import seg.jUCMNav.editparts.RuleEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeRuleNameCommand;

public class RuleDirectEditPolicy extends DirectEditPolicy 
{
	 private String oldValue;
     private ASDiagram asdiagram;
     
     
     public RuleDirectEditPolicy(ASDiagram asdiagram)
     {
    	 this.asdiagram=asdiagram;
     }
	    /**
	     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected Command getDirectEditCommand(DirectEditRequest request) {
	        Rule lbl = (Rule) getHost().getModel();

	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ChangeRuleNameCommand cmd = new ChangeRuleNameCommand(lbl, value,asdiagram);
	        return cmd;
	    }

	    /**
	     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected void showCurrentEditValue(DirectEditRequest request) {
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	       RuleEditPart lblPart = (RuleEditPart) getHost();
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
	        RuleEditPart lblPart = (RuleEditPart) getHost();
	        lblPart.revertNameChange();
	    }
}
