package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * ComponentEditPolicy for UCM Maps. Returns delete commands.
 * 
 * @author jkealey
 */
public class MapComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteMapCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof UCMmap) {
            UCMmap diagram = (UCMmap) elem;
            URNspec urn = (URNspec) diagram.eContainer().eContainer();
            int nbDiagrams = urn.getUrndef().getSpecDiagrams().size();
            if (nbDiagrams > 1) {
                // we can delete it, not the last GRL/UCM diagram
                return new DeleteMapCommand(diagram);
            } else {
                // last diagram, cannot delete!
                return null;
            }
        } else
            return null;
    }
}