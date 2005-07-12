package seg.jUCMNav.editpolicies.layout;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ConditionEditPart;
import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.commands.changeConstraints.ConditionSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.commands.create.AddComponentRefCommand;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.model.commands.transformations.ExtendPathCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;

/**
 * 
 * XYLayoutEditPolicy for the MapAndPathGraphEditPart. Handles creation of new elements and moving/resizing of existing ones. 
 * 
 * @author etremblay, jkealey
 *  
 */
public class MapAndPathGraphXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /**
     * Not used.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {
        return null;
    }

    /**
     * Convenience method to prevent casting
     * 
     * @return the path graph
     */
    protected PathGraph getPathGraph() {
        return ((Map) getHost().getModel()).getPathGraph();
    }

    /**
     * Convenience method to prevent casting
     * 
     * @return the map
     */
    protected Map getMap() {
        return (Map) getHost().getModel();
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

        AddComponentRefCommand create = new AddComponentRefCommand(getMap(), compRef);
        SetConstraintComponentRefCommand moveResize = new SetConstraintComponentRefCommand(compRef, constraint.x, constraint.y, constraint.width,
                constraint.height);

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
                createCommand = new ExtendPathCommand(getPathGraph(), (EndPoint) selected.getModel(), constraint.x, constraint.y);
            } else if (selected.getModel() instanceof StartPoint) {
                // extend start point
                createCommand = new ExtendPathCommand(getPathGraph(), (StartPoint) selected.getModel(), constraint.x, constraint.y);
            } else {
                // create new path
                if (request.getNewObject() instanceof StartPoint)
                    createCommand = new CreatePathCommand(getPathGraph(), (StartPoint) request.getNewObject(), constraint.x, constraint.y);
                else
                    createCommand = new CreatePathCommand(getPathGraph(), constraint.x, constraint.y);
            }
        }
        return createCommand;
    }

    /**
     * Not used.
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
     */
    protected Command getDeleteDependantCommand(Request request) {
        return null;
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
            return handleMoveResizeComponentRef(child, constraint);
        } else if (child.getModel() instanceof Condition && ((Condition) child.getModel()).getNodeConnection() != null) {
            return handleMoveNodeConnectionCondition(child, constraint);
        } else if (child.getModel() instanceof Label) {
            return handleMoveLabel(child, constraint);
        } else {
            System.out.println(Messages.getString("MapAndPathGraphXYLayoutEditPolicy.unknownModelElement")); //$NON-NLS-1$
            return null;
        }

    }

    /**
     * Moves a label. Doesn't move it if its path not is being moved as well. Moves PathNode conditions as well. Keeps the label centered on its location.
     * 
     * @param child
     *            the LabelEditPart
     * @param constraint
     *            where it should be moved
     * @return the command to accomplish the move
     */
    private Command handleMoveLabel(EditPart child, Object constraint) {
        Label label = (Label) child.getModel();
        LabelSetConstraintCommand locationCommand = new LabelSetConstraintCommand();
        locationCommand.setLabel(label);

        // Adjust the coordinates with the coordinates of the figure too
        // since the x,y coordinates is the center of the figure.
        Dimension dim = ((LabelEditPart) child).getLabelFigure().getPreferredSize().getCopy();
        int x, y;

        // to prevent double moving
        if (((IStructuredSelection) getHost().getViewer().getSelection()).toList().size() == 1) {

            // get corner of rectangle
            if (label instanceof ComponentLabel) {
                ComponentRef component = (ComponentRef) (((LabelEditPart) child).getUCMmodelElement());
                x = component.getX() - ((Rectangle) constraint).x;
                y = component.getY() - ((Rectangle) constraint).y - dim.height;

            } else if (label instanceof Condition || label instanceof NodeLabel) {
                // get middle of pathnode
                PathNode node = null;
                if (label instanceof Condition) {
                    Condition condition = (Condition) label;
                    if (condition.getStartPoint() != null) {
                        node = condition.getStartPoint();
                    } else if (condition.getEndPoint() != null) {
                        node = condition.getEndPoint();
                    }
                } else
                    node = (PathNode) (((LabelEditPart) child).getUCMmodelElement());

                int height = ((PathNodeEditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(node)).getFigure().getBounds().getCopy().height;
                x = node.getX() - ((Rectangle) constraint).x - (dim.width / 2);
                y = node.getY() - ((Rectangle) constraint).y - (dim.height + height / 2);

            } else {
                // unknown label
                x = 0;
                y = 0;
            }

        } else {
            x = label.getDeltaX();
            y = label.getDeltaY();
        }

        locationCommand.setNewPosition(x, y);

        return locationCommand;
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
        ConditionSetConstraintCommand locationCommand = new ConditionSetConstraintCommand();

        NodeConnectionEditPart nc = (NodeConnectionEditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(
                ((ConditionEditPart) child).getNodeConnection());
        if (nc != null) {
            // to prevent double moving
            if (((IStructuredSelection) getHost().getViewer().getSelection()).toList().size() == 1) {
                locationCommand.setNewPosition(nc.getMiddlePoint().x - ((Rectangle) constraint).x, nc.getMiddlePoint().y - ((Rectangle) constraint).y);
            } else
                locationCommand.setNewPosition(condition.getDeltaX(), condition.getDeltaY());
        }

        locationCommand.setCondition(condition);
        return locationCommand;
    }

    /**
     * Handles moving/resizing a ComponentRef.
     * 
     * @param child
     *            the ComponentRefEditPart
     * @param constraint
     *            where it should be moved and its new dimensions.
     * @return a SetConstraintBoundComponentRefCompoundCommand
     */
    private Command handleMoveResizeComponentRef(EditPart child, Object constraint) {
        Rectangle rect = (Rectangle) constraint;
        ComponentRef compRef = (ComponentRef) child.getModel();

        SetConstraintBoundComponentRefCompoundCommand moveResize = new SetConstraintBoundComponentRefCompoundCommand(compRef, rect.getLocation().x, rect
                .getLocation().y, rect.width, rect.height);

        return moveResize;
    }

    /**
     * Handles moving a PathNode.
     * 
     * @param child
     *            the PathNodeEditPart
     * @param constraint
     *            where it should be moved to.
     * @return
     */
    private Command handleMovePathNode(EditPart child, Object constraint) {
        // Adjust the coordinates with the coordinates of the figure too
        // since the x,y coordinates is
        // the center of the figure.
        Dimension dim = ((PathNodeEditPart) child).getNodeFigure().getPreferredSize().getCopy();

        Point location = new Point(((Rectangle) constraint).x + (dim.width / 2), ((Rectangle) constraint).y + (dim.height / 2));

        PathNode node = (PathNode) child.getModel();

        return new SetConstraintCommand(node, location.x, location.y);
    }
}