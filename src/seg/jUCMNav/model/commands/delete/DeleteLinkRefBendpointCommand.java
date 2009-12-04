/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.LinkRef;
import grl.LinkRefBendpoint;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Delete a Bendpoint from a LinkRef
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteLinkRefBendpointCommand extends Command implements JUCMNavCommand {

    LinkRefBendpoint oldBendpoint;
    LinkRef link;

    /**
     * Constructor
     * 
     * @param oldBendpoint
     */
    public DeleteLinkRefBendpointCommand(LinkRefBendpoint oldBendpoint) {
        this.oldBendpoint = oldBendpoint;
        link = oldBendpoint.getLinkref();

        setLabel(Messages.getString("DeleteLinkRefBendpointCommand.DeleteLinkRefBendpoint")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        oldBendpoint.setLinkref(null);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldBendpoint != null : "Pre bendpoint is null"; //$NON-NLS-1$
        assert link != null : "Pre link is null"; //$NON-NLS-1$

        assert oldBendpoint.getLinkref() == link : "Pre linkref"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldBendpoint != null : "Post bendpoint is null"; //$NON-NLS-1$
        assert link != null : "Post link is null"; //$NON-NLS-1$

        assert oldBendpoint.getLinkref() != link : "Pre linkref"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        oldBendpoint.setLinkref(link);
        testPreConditions();
    }
}
