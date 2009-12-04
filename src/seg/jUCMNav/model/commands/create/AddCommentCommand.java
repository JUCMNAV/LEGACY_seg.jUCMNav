package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Comment;
import urncore.IURNDiagram;

/**
 * 
 * Adds a comment to a diagram
 * 
 * @author jkealey
 * 
 */
public class AddCommentCommand extends Command implements JUCMNavCommand {

    private Comment comment;

    // Graph where the element has been added.
    private IURNDiagram graph;

    /**
     * @param graph
     *            graph where to add the comment
     * @param comment
     *            comment to add
     */
    public AddCommentCommand(IURNDiagram graph, Comment comment) {
        super();
        this.graph = graph;
        this.comment = comment;
        setLabel(Messages.getString("AddCommentCommand.CreateComment")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        graph.getComments().add(comment);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert comment != null : "pre comment"; //$NON-NLS-1$
        assert graph != null : "pre graph"; //$NON-NLS-1$

        assert !graph.getComments().contains(comment) : "pre comment in graph"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert comment != null : "post comment"; //$NON-NLS-1$
        assert graph != null : "post graph"; //$NON-NLS-1$

        assert graph.getComments().contains(comment) : "post comment in graph"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        graph.getComments().remove(comment);

        testPreConditions();
    }
}
