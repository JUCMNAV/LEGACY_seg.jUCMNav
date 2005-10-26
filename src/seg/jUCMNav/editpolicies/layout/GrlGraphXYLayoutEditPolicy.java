/**
 * 
 */
package seg.jUCMNav.editpolicies.layout;

import grl.GRLGraph;
import grl.IntentionalElementRef;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveSpecificationNodeCommand;
import urncore.Label;
import urncore.SpecificationNode;

/**
 * XYLayoutEditPolicy for the GrlGraphEditPart. Handles creation of new elements and moving/resizing of existing ones.
 * @author Jean-François Roy
 *
 */
public class GrlGraphXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {
        return null;
    }
    
    /**
     * Returns a command to be executed when the palette tries to create something
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        Object newObjectType = null;
        if (request.getNewObject() != null)
            newObjectType = request.getNewObjectType();

        // converts relative to absolute positions (so that zooms work properly)
        Rectangle constraint = (Rectangle) getConstraintFor(request);
        Command createCommand = null;

        if (newObjectType == IntentionalElementRef.class) {
            createCommand = handleCreateIntentionalElementRef(request, constraint);
        }
        return createCommand;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
     */
    protected Command getDeleteDependantCommand(Request request) {
        return null;
    }

    /**
     * Convenience method to prevent casting
     * 
     * @return the graph
     */
    protected GRLGraph getGraph() {
        return (GRLGraph) getHost().getModel();
    }
  
    /**
     * Handles moving/resizing of MapAndPathGraphEditPart's children.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (child.getModel() instanceof SpecificationNode) {
            return handleMoveSpecificationNode(child, constraint);
        } else if (child.getModel() instanceof Label) {
            //Moving label is not allow in GRL
            return null;
        }else {
            System.out.println(Messages.getString("GrlGraphXYLayoutEditPolicy.unknownModelElement")); //$NON-NLS-1$
            return null;
        }
    
    }

    /**
     * @param request
     *            the CreateRequest containing the new ComponentRef
     * @param constraint
     *            where the new object should be created
     * @return a command that adds a new IntentionalElementRef on the Graph and moves/resizes it into place.
     */ 
    private Command handleCreateIntentionalElementRef(CreateRequest request, Rectangle constraint) {
        Command createCommand;
        IntentionalElementRef elementRef = (IntentionalElementRef) request.getNewObject();

        AddIntentionalElementRefCommand create = new AddIntentionalElementRefCommand(getGraph(), elementRef);
        MoveSpecificationNodeCommand move  = new MoveSpecificationNodeCommand(elementRef, constraint.x, constraint.y);

        // after creation, move and resize the component;
        createCommand = create.chain(move);

        return createCommand;
    }
    
    /**
     * Handles moving an IntentionalElementRef.
     * 
     * @param child
     *            the IntentionalElementRefEditPart
     * @param constraint
     *            where it should be moved.
     * @return a SetConstraintIntentionalElementRefCommand
     */
    private Command handleMoveSpecificationNode(EditPart child, Object constraint) {
        Rectangle rect = (Rectangle) constraint;
        IntentionalElementRef intRef = (IntentionalElementRef) child.getModel();

        MoveSpecificationNodeCommand move = new MoveSpecificationNodeCommand(intRef, rect.getLocation().x, rect
                .getLocation().y);

        return move;
    }
}
