package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Comment;
import urncore.IURNDiagram;

/**
 * Command to delete a comment. (Remove it from the model). Can only do it if it has no references.
 * 
 * @author jkealey
 * 
 */
public class DeleteCommentCommand extends Command implements JUCMNavCommand {

    // the diagram it was in
    private IURNDiagram diag;

    // the comment
    private Comment comment;

    public DeleteCommentCommand(Comment comment) {
        this.comment = comment;
        setLabel(Messages.getString("DeleteCommentCommand.DeleteComment")); //$NON-NLS-1$
    }

    /**
     * Only if not referenced.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return comment != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        diag = comment.getDiagram();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        comment.setDiagram(null);

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert comment != null && diag != null : "post something is null"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // lists could be empty but not null
        assert comment != null && diag != null : "pre something is null"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add comment
        comment.setDiagram(diag);

        testPreConditions();
    }
}