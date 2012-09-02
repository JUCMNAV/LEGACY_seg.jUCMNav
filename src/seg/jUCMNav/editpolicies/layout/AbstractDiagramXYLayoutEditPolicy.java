package seg.jUCMNav.editpolicies.layout;

import grl.Contribution;
import grl.LinkRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.figures.util.JUCMNavFigure;
import seg.jUCMNav.model.commands.changeConstraints.LabelSetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommentCommand;
import seg.jUCMNav.model.commands.create.AddCommentCommand;
import ucm.map.PathNode;
import urncore.Comment;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.ConnectionLabel;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Label;
import urncore.NodeLabel;

/**
 * @author Jean-François Roy
 * 
 */
public abstract class AbstractDiagramXYLayoutEditPolicy extends XYLayoutEditPolicy {

    
    /**
     * Handles moving/resizing of Graph children.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        return null;
    }

    /**
     * Handles dropping of elements from palette.
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected abstract Command getCreateCommand(CreateRequest request);

    /**
     * Moves a label. Doesn't move it if its path not is being moved as well. Moves PathNode conditions as well. Keeps the label centered on its location.
     * 
     * @param child
     *            the LabelEditPart
     * @param constraint
     *            where it should be moved
     * @return the command to accomplish the move
     */
    protected Command handleMoveLabel(EditPart child, Object constraint) {
        Label label = (Label) child.getModel();
        LabelSetConstraintCommand locationCommand = new LabelSetConstraintCommand();
        locationCommand.setLabel(label);

        // Adjust the coordinates with the coordinates of the figure too
        // since the x,y coordinates is the center of the figure.
        Dimension dim = ((LabelEditPart) child).getLabelFigure().getPreferredSize().getCopy();
        int x = 0, y = 0;

        // to prevent double moving
        if (((IStructuredSelection) getHost().getViewer().getSelection()).toList().size() == 1) {

            // get corner of rectangle
            if (label instanceof ComponentLabel) {
                IURNContainerRef component = (IURNContainerRef) (((LabelEditPart) child).getURNmodelElement());
                x = component.getX() - ((Rectangle) constraint).x;
                y = component.getY() - ((Rectangle) constraint).y - dim.height;

            } else if (label instanceof Condition || label instanceof NodeLabel || label instanceof ConnectionLabel) {
                // get middle of node
                IURNNode node = null;
                if (label instanceof Condition) {
                    Condition condition = (Condition) label;
                    if (condition.getStartPoint() != null) {
                        node = condition.getStartPoint();
                    } else if (condition.getEndPoint() != null) {
                        node = condition.getEndPoint();
                    }
                } else if (label instanceof ConnectionLabel) {
                    LinkRef ref = (LinkRef)((ConnectionLabel) label).getConnection();
                    if(ref.getLink() instanceof Contribution) {
                        LinkRefEditPart nc = (LinkRefEditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(ref);
                        if (nc != null) {
                            /**
                             * Here the user is moving the label and dropped it in a position defined in constraint.
                             * We want to find what is the deltaX and deltaY from the last point of the connection.
                             * 
                             * The position of a ConnectionLabel is calculated perpendicularly to the connection.  In fact perpendicular to the last segment of the connection if the connection has bendpoints.
                             * If we consider the last segment of the connection as the vector A, then deltaY is added in the direction of vector A.
                             * DeltaX is added in the direction of the normal of A.
                             * 
                             * Consider vector B which starts at the last point of the connection and has deltaX and deltaY as his (x,y) components.  We are searching vector B here (deltaX, deltaY).
                             * 
                             * We use the vector projection of B on A to find deltaY
                             * We use the vector projection of B on the normal of A to find deltaX
                             */
                            PointList points = nc.getConnectionFigure().getPoints();
                            Point last = points.getLastPoint();
                            Point preLast = points.getPoint(points.size()-2);
                            
                            Dimension normal = new Dimension(last.x - preLast.x, last.y - preLast.y);
                            
                            Rectangle p = (Rectangle) constraint;
                            
                            double aX = (double)normal.width; double aY = (double)normal.height;
                            
                            double bX = p.x + (p.width/2) - last.x, bY = p.y + (p.height/2) - last.y;
                            
                            double lengthA = length(aX, aY);
                            
                            y = (int) (dotProduct(aX, aY, bX, bY) / lengthA);
                            x = (int) (dotProduct(aY, -aX, bX, bY) / lengthA);
                        }
                    }
                } else
                    node = (PathNode) (((LabelEditPart) child).getURNmodelElement());

                if (node != null) {
                    int height = JUCMNavFigure.getDimension(node).height / 2;
                    Rectangle rect = (Rectangle) constraint;
                    x = node.getX() - rect.x - (dim.width / 2);
                    y = node.getY() - rect.y - (dim.height) - height;
                }

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
    
    protected double dotProduct(double p1x, double p1y, double p2x, double p2y) {
        return p1x*p2x + p1y*p2y;
    }
    
    protected double length(double px, double py) {
        return Math.sqrt(Math.pow(Math.abs(px),2) + Math.pow(Math.abs(py), 2));
    }

    /**
     * Handles moving/resizing a SpecificationComponentRef.
     * 
     * @param child
     *            the ComponentRefEditPart
     * @param constraint
     *            where it should be moved and its new dimensions.
     * @return a SetConstraintBoundContainerRefCompoundCommand
     */
    protected Command handleMoveResizeContainerRef(EditPart child, Object constraint) {
        Rectangle rect = (Rectangle) constraint;
        IURNContainerRef compRef = (IURNContainerRef) child.getModel();
        
        List selected = getSelectedModel(child.getViewer());
        
        List allNodesInComp = getAllComponentNodes(compRef);
        selected.addAll(allNodesInComp);
        
        boolean multipleNodeMoved = isMultipleSelected(getAllComponentNodes(compRef), selected);

        SetConstraintBoundContainerRefCompoundCommand moveResize = new SetConstraintBoundContainerRefCompoundCommand(compRef, rect.getLocation().x, rect
                .getLocation().y, rect.width, rect.height, multipleNodeMoved);

        return moveResize;
    }
    
    protected abstract boolean isMultipleSelected(List nodes, List selectedNodes);

    protected Command handleCreateComment(CreateRequest request, Rectangle constraint) {

        Comment node = (Comment) request.getNewObject();
        AddCommentCommand create = new AddCommentCommand((IURNDiagram) getHost().getModel(), node);

        SetConstraintCommentCommand move = new SetConstraintCommentCommand(node, constraint.x, constraint.y, constraint.width, constraint.height);

        return create.chain(move);
    }

    /**
     * Handles moving an GRLNode.
     * 
     * @param child
     *            the IntentionalElementRefEditPart or BeliefEditPart
     * @param constraint
     *            where it should be moved and resize.
     * @return a SetConstraintIntentionalElementRefCommand
     */
    protected Command handleMoveResizeComment(EditPart child, Object constraint) {
        Rectangle rect = (Rectangle) constraint;
        Comment node = (Comment) child.getModel();

        return new SetConstraintCommentCommand(node, rect.getLocation().x, rect.getLocation().y, rect.width, rect.height);
    }
    
    protected List getSelectedModel(EditPartViewer viewer) {
        List selectedNodes = new Vector();
        
        for (Iterator i = viewer.getSelectedEditParts().iterator(); i.hasNext();) {
            EditPart selectedPart = (EditPart) i.next();
            selectedNodes.add(selectedPart.getModel());
        }
        return selectedNodes;
    }
    
    protected List getAllComponentNodes(IURNContainerRef container)
    {
        List result = new Vector();
        for (Iterator i = container.getChildren().iterator(); i.hasNext();) {
            IURNContainerRef node = (IURNContainerRef)i.next();
            result.addAll(getAllComponentNodes(node));
        }
        
        result.addAll(container.getNodes());
        
        return result;
    }
}
