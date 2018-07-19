package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import asd.ASDiagram;
import asd.Subject;
import seg.jUCMNav.editparts.SubjectEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeSubjectNameCommand;

public class SubjectDirectEditPolicy extends DirectEditPolicy 
{
	 private String oldValue;
	 
	 public ASDiagram asdiagram;
	 
	 public SubjectDirectEditPolicy(ASDiagram asdiagram)
	 {
		 this.asdiagram=asdiagram;	
	 }

	    /**
	     * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected Command getDirectEditCommand(DirectEditRequest request) {
	        Subject lbl = (Subject) getHost().getModel();

	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	        ChangeSubjectNameCommand cmd = new ChangeSubjectNameCommand(lbl, value, asdiagram);
	        return cmd;
	    }

	    /**
	     * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	     */
	    protected void showCurrentEditValue(DirectEditRequest request) {
	        String value = (String) request.getCellEditor().getValue();
	        value = value.trim();
	       SubjectEditPart lblPart = (SubjectEditPart) getHost();
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
	        SubjectEditPart lblPart = (SubjectEditPart) getHost();
	        lblPart.revertNameChange();
	    }
}
