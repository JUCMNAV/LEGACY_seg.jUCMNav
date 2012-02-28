package seg.jUCMNav.editpolicies.layout;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ConditionEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urncore.Comment;
import urncore.Condition;
import urncore.Label;

/**
 * 
 * XYLayoutEditPolicy for the MapAndPathGraphEditPart. Handles creation of new elements and moving/resizing of existing ones.
 * 
 * @author etremblay, jkealey
 * 
 */
public class MapXYLayoutEditPolicy extends AbstractDiagramXYLayoutEditPolicy {

    /**
     * Convenience method to prevent casting
     * 
     * @return the map
     */
    protected UCMmap getMap() {
        return (UCMmap) getHost().getModel();
    }

    /**
     * Returns a command to be executed when the palette tries to create something on the MapAndPathGraphEditPart.
     * 
     * Extends path for PathTool and creates ComponentRefs. PathNodes are created on NodeConnections so are not handled here.
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

        // if PathTool
        if (newObjectType == EndPoint.class || newObjectType == StartPoint.class) {
            createCommand = handleCreateOrExtendPath(request, constraint);
        } else if (newObjectType == ComponentRef.class) {
            createCommand = handleCreateComponentRef(request, constraint);
        } else if (newObjectType == Comment.class) {
            createCommand = handleCreateComment(request, constraint);
        }

        return createCommand;
    }

    /**
     * @param request
     *            the CreateRequest containing the new ComponentRef
     * @param constraint
     *            where the new object should be created
     * @return a command that adds a new ComponentRef on the Map and moves/resizes it into place.
     */
    private Command handleCreateComponentRef(CreateRequest request, Rectangle constraint) {
        Command createCommand;
        ComponentRef compRef = (ComponentRef) request.getNewObject();

        AddContainerRefCommand create = new AddContainerRefCommand(getMap(), compRef);
        SetConstraintBoundContainerRefCompoundCommand moveResize = new SetConstraintBoundContainerRefCompoundCommand(compRef, constraint.x, constraint.y,
                constraint.width, constraint.height);

        // after creation, move and resize the component;
        createCommand = create.chain(moveResize);
        return createCommand;
    }

    /**
     * @param request
     *            the CreateRequest containing the new object
     * @param constraint
     *            where the object (startpoint /endpoint) should be created
     * @return the command to extend an existing path in the appropriate direction or create a new one.
     */
    private Command handleCreateOrExtendPath(CreateRequest request, Rectangle constraint) {
        Command createCommand = null;
        // Get the list of selected items
        List selectedParts = ((IStructuredSelection) getHost().getViewer().getSelection()).toList();

        // If there's only one item selected
        if (selectedParts.size() == 1) {
            EditPart selected = (EditPart) (selectedParts.get(0));
            if (selected.getModel() instanceof EndPoint) {
                // extend end point
                createCommand = new ExtendPathCommand(getMap(), (EndPoint) selected.getModel(), constraint.x, constraint.y);
            } else if (selected.getModel() instanceof StartPoint) {
                // extend start point
                createCommand = new ExtendPathCommand(getMap(), (StartPoint) selected.getModel(), constraint.x, constraint.y);
            } else {
                // create new path
                if (request.getNewObject() instanceof StartPoint)
                    createCommand = new CreatePathCommand(getMap(), (StartPoint) request.getNewObject(), constraint.x, constraint.y);
                else
                    createCommand = new CreatePathCommand(getMap(), constraint.x, constraint.y);
            }
        }
        return createCommand;
    }

    /**
     * Handles moving/resizing of MapAndPathGraphEditPart's children.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public Command createChangeConstraintCommand(EditPart child, Object constraint) {

        if (child.getModel() instanceof PathNode) {
            return handleMovePathNode(child, constraint);
        } else if (child.getModel() instanceof ComponentRef) {
            return handleMoveResizeContainerRef(child, constraint);
        } else if (child.getModel() instanceof Condition && ((Condition) child.getModel()).getNodeConnection() != null) {
            return handleMoveNodeConnectionCondition(child, constraint);
        } else if (child.getModel() instanceof Label) {
            return handleMoveLabel(child, constraint);
        } else if (child.getModel() instanceof Comment) {
            return handleMoveResizeComment(child, constraint);
        } else {
            System.out.println(Messages.getString("MapAndPathGraphXYLayoutEditPolicy.unknownModelElement")); //$NON-NLS-1$
            return null;
        }

    }

    /**
     * Handles moving a NodeConnection condition.
     * 
     * @param child
     *            the ConditionEditPart
     * @param constraint
     *            where it should be moved.
     * @return the command to accomplish the move
     */
    private Command handleMoveNodeConnectionCondition(EditPart child, Object constraint) {
        Condition condition = (Condition) child.getModel();
        LabelSetConstraintCommand locationCommand = new LabelSetConstraintCommand();

        NodeConnectionEditPart nc = (NodeConnectionEditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(
                ((ConditionEditPart) child).getNodeConnection());
        if (nc != null) {
            // to prevent double moving
            if (((IStructuredSelection) getHost().getViewer().getSelection()).toList().size() == 1) {
                locationCommand.setNewPosition(nc.getMiddlePoint().x - ((Rectangle) constraint).x, nc.getMiddlePoint().y - ((Rectangle) constraint).y);
            } else
                locationCommand.setNewPosition(condition.getDeltaX(), condition.getDeltaY());
        }

        locationCommand.setLabel(condition);
        return locationCommand;
    }

    /**
     * Handles moving a SpecificationNode.
     * 
     * @param child
     *            the SpecificationNodeEditPart
     * @param constraint
     *            where it should be moved to.
     * @return a SetConstraintCommand
     */
    protected Command handleMovePathNode(EditPart child, Object constraint) {
        // Adjust the coordinates with the coordinates of the figure too
        // since the x,y coordinates is
        // the center of the figure.
        Dimension dim = ((PathNodeEditPart) child).getNodeFigure().getPreferredSize().getCopy();

        Point location = new Point(((Rectangle) constraint).x + (dim.width / 2), ((Rectangle) constraint).y + (dim.height / 2));

        PathNode node = (PathNode) child.getModel();

        return new SetConstraintCommand(node, location.x, location.y);
    }

    @Override
    protected boolean isMultipleSelected(List nodes, List selectedNodes) {
        return false;
    }
}