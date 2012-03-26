package seg.jUCMNav.editpolicies.layout;

import grl.Contribution;
import grl.LinkRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.ModelElementEditPart;
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
     * Hnaldes dropping of elements from palette.
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
        int x, y;

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
                        node = ref.getTarget();
                    }
                } else
                    node = (PathNode) (((LabelEditPart) child).getURNmodelElement());

                if (node == null) {
                    x = 0;
                    y = 0;
                } else {
                    int height = ((ModelElementEditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(node)).getFigure().getBounds().getCopy().height;
                    x = node.getX() - ((Rectangle) constraint).x - (dim.width / 2);
                    y = node.getY() - ((Rectangle) constraint).y - (dim.height);
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
