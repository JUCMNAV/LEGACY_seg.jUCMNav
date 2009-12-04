package seg.jUCMNav.editpolicies.element;

import grl.LinkRef;
import grl.LinkRefBendpoint;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import seg.jUCMNav.model.commands.changeConstraints.MoveLinkRefBendpointCommand;
import seg.jUCMNav.model.commands.create.AddLinkRefBendpointCommand;
import seg.jUCMNav.model.commands.delete.DeleteLinkRefBendpointCommand;

/**
 * Edit policy to support Bendpoint in the LinkRef connections
 * 
 * @author Jean-François Roy
 * 
 */
public class LinkRefBendpointEditPolicy extends BendpointEditPolicy {

    /**
     * Creates a bend point
     * 
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getCreateBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    protected Command getCreateBendpointCommand(BendpointRequest request) {
        AddLinkRefBendpointCommand com = new AddLinkRefBendpointCommand((LinkRef) request.getSource().getModel(), request.getIndex());
        Point p = request.getLocation();
        getConnection().translateToRelative(p);
        com.setX(p.x);
        com.setY(p.y);

        return com;
    }

    /**
     * Deletes a bendpoint.
     * 
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getDeleteBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        LinkRef ref = (LinkRef) request.getSource().getModel();
        return new DeleteLinkRefBendpointCommand((LinkRefBendpoint) ref.getBendpoints().get(request.getIndex()));
    }

    /**
     * Moves a bendpoint
     * 
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getMoveBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        Point p = request.getLocation();
        getConnection().translateToRelative(p);
        LinkRef ref = (LinkRef) request.getSource().getModel();
        return new MoveLinkRefBendpointCommand((LinkRefBendpoint) ref.getBendpoints().get(request.getIndex()), p.x, p.y);
    }

}
