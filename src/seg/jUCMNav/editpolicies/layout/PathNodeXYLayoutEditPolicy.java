package seg.jUCMNav.editpolicies.layout;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrJoin;

/**
 * Created on 25-May-2005
 * 
 * @author jkealey
 *
 */
public class PathNodeXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
     */
    protected EditPolicy createChildEditPolicy(EditPart child) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
     */
    protected Command getDeleteDependantCommand(Request request) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getMoveChildrenCommand(org.eclipse.gef.Request)
     */
    protected Command getMoveChildrenCommand(Request request) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {
        if (child.getModel() instanceof EndPoint && getHost().getModel() instanceof EmptyPoint )
        {
            // drag start over empty.            
            EmptyPoint target =  (EmptyPoint) getHost().getModel();
            OrJoin join = (OrJoin) ModelCreationFactory.getNewObject(target.getPathGraph().getMap().getUcmspec().getUrnspec(), OrJoin.class);
            return  new JoinPathsCommand(target, (EndPoint) child.getModel(), join );
        }
        
        // don't allow drop
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        return null;
    }

}
