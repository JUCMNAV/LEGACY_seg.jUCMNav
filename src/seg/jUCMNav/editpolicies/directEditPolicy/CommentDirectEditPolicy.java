/**
 * 
 */
package seg.jUCMNav.editpolicies.directEditPolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import seg.jUCMNav.editparts.CommentEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeCommentDescriptionCommand;
import urncore.Comment;

/**
 * Edit policy for direct editing of comment
 * 
 * Based on LabelDirectEditPolicy
 * 
 * @author jkealey
 * 
 */
public class CommentDirectEditPolicy extends DirectEditPolicy {

    private String oldValue;

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected Command getDirectEditCommand(DirectEditRequest request) {
        Comment node = (Comment) getHost().getModel();

        ChangeCommentDescriptionCommand cmd = new ChangeCommentDescriptionCommand(node, (String) request.getCellEditor().getValue());
        return cmd;
    }

    /**
     * handles the name change.
     * 
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected void showCurrentEditValue(DirectEditRequest request) {
        String value = (String) request.getCellEditor().getValue();
        CommentEditPart lblPart = (CommentEditPart) getHost();
        lblPart.handleNameChange(value);
    }

    /**
     * @param request
     *            Saves the initial text value so that if the user's changes are not committed then
     */
    protected void storeOldEditValue(DirectEditRequest request) {

        CellEditor cellEditor = request.getCellEditor();
        oldValue = (String) cellEditor.getValue();
    }

    /**
     * @param request
     *            reverts to the old value.
     */
    protected void revertOldEditValue(DirectEditRequest request) {
        CellEditor cellEditor = request.getCellEditor();
        cellEditor.setValue(oldValue);
        CommentEditPart lblPart = (CommentEditPart) getHost();
        lblPart.revertNameChange();
    }

}
