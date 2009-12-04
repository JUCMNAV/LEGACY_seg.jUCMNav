package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Comment;

/**
 * Changes the comment's description.
 * 
 * @author jkealey
 */
public class ChangeCommentDescriptionCommand extends Command implements JUCMNavCommand {
    private String description = "", oldDesc = ""; //$NON-NLS-1$ //$NON-NLS-2$
    private Comment elem;

    public ChangeCommentDescriptionCommand(Comment comment, String name) {
        this.elem = comment;
        this.description = name;
        setLabel(Messages.getString("ChangeCommentDescriptionCommand.ChangeComment")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        return elem != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldDesc = elem.getDescription();

        redo();
    }

    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        elem.setDescription(description);

        testPostConditions();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert elem != null : "post no elemement to name!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert elem != null : "pre no elemement to name!"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        elem.setDescription(oldDesc);
        testPreConditions();
    }

}