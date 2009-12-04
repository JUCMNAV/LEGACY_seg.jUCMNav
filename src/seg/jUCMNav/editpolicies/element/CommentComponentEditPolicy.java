package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteCommentCommand;
import urncore.Comment;

/**
 * ComponentEditPolicy for Comment
 * 
 * @author jkealey
 * 
 */
public class CommentComponentEditPolicy extends ComponentEditPolicy {

    /**
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {

        Comment node = (Comment) getHost().getModel();
        return new DeleteCommentCommand(node);
    }

}
