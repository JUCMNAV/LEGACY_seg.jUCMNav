/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.LinkRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteLinkRefCommand;

/**
 * ComponentEditPolicy for LinkRef. Return the command to delete a LinkRef
 * 
 * @author Jean-François Roy
 * 
 */
public class LinkRefComponentEditPolicy extends ComponentEditPolicy {

    /**
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {

        LinkRef linkref = (LinkRef) getHost().getModel();

        return new DeleteLinkRefCommand(linkref);
    }

}
