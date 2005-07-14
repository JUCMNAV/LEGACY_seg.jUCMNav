package seg.jUCMNav.editpolicies.element;

import java.util.ArrayList;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.viewers.StructuredSelection;

import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.OutlineRootEditPart;
import seg.jUCMNav.model.commands.delete.DeleteMultiNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import seg.jUCMNav.model.commands.delete.DeletePathCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * ComponentEditPolicy for UCM PathNodes. Returns delete commands.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class PathNodeComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns either a DeletePathCommand, DeleteNodeCommand or DeleteMultiNodeCommand.
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object parent = getHost().getParent().getModel();
        Object node = getHost().getModel();
        java.util.Map registry;
        if (getHost().getViewer() instanceof TreeViewer) {
            // we need an editpart registry with NodeConnectionEditParts
            registry = ((UCMNavMultiPageEditor) ((OutlineRootEditPart) getHost().getViewer().getRootEditPart().getChildren().get(0)).getModel())
                    .getCurrentPage().getGraphicalViewer().getEditPartRegistry();
        } else
            registry = getHost().getViewer().getEditPartRegistry();

        if (parent instanceof Map && node instanceof StartPoint) {
            DeletePathCommand command = new DeletePathCommand((StartPoint) node, registry);
            return command;
        } else if (parent instanceof Map && node instanceof EndPoint) {
            DeletePathCommand command = new DeletePathCommand((EndPoint) node, registry);
            return command;
        } else if (parent instanceof Map && node instanceof Stub) {
            return new DeleteMultiNodeCommand((PathNode) node, registry);
        } else if (parent instanceof Map && ((PathNode) node).getPred().size() == 1 && ((PathNode) node).getSucc().size() == 1) {
            NodeConnection in = (NodeConnection) ((PathNode) node).getPred().get(0);
            NodeConnection out = (NodeConnection) ((PathNode) node).getSucc().get(0);
            if (!in.getSource().equals(out.getTarget())) {
                Command command = new DeleteNodeCommand((PathNode) node);
                return command;
            } else {
                // if deleting the last node on a loop leading to a stub, get it to be disconnected instead of deleted.
                ArrayList lIn = new ArrayList();
                ArrayList lOut = new ArrayList();
                // inversed because out of this node is in of stub.
                lOut.add(in);
                lIn.add(out);

                Command command = new DeleteMultiNodeCommand(in.getSource(), lIn, lOut, registry);
                return command;
            }

        } else if (parent instanceof Map && ((PathNode) node).getPred().size() > 1 || ((PathNode) node).getSucc().size() > 1) {
            return new DeleteMultiNodeCommand((PathNode) node, registry);
        } else if (parent instanceof Map && node instanceof Stub && ((PathNode) node).getPred().size() <= 1 || ((PathNode) node).getSucc().size() <= 1) {
            return new DeleteMultiNodeCommand((PathNode) node, registry);
        }

        return super.createDeleteCommand(request);
    }

    /**
     * jkealey: This method is supposed to handle requests. However, it seems to have been implemented early on when we didn't know too well how GEF worked. The
     * function runs but I don't think it ever ends up running LINE A or LINE B, but we may be able to simply delete it.
     * 
     * I'm not 100% sure though, so I am leaving the code here and adding debug information to help us resolve this matter.
     * 
     * This method returns an UnexecutableCommand when trying to resize a PathNode, used to indicate that this action is illegal.
     * 
     * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
     */
    public Command getCommand(Request request) {
        if (request.getType() == CutPathAction.CUTPATH_REQUEST) {
            EmptyPoint ep = ((EmptyPoint) ((EditPart) getHost()).getModel());
            CutPathCommand cp = new CutPathCommand(ep.getPathGraph(), ep);
            // LINE A
            System.out.println("Please review PathNodeComponentEditPolicy.getCommand() and indicate how you managed to get LINE A to run."); //$NON-NLS-1$
            return cp;
        }
        if (request.getType() == REQ_CREATE) {
            Object newObjectType = null;
            if (((CreateRequest) request).getNewObject() != null)
                newObjectType = ((CreateRequest) request).getNewObjectType();
            if (newObjectType instanceof EndPoint) {
                // LINE B
                System.out.println("Please review PathNodeComponentEditPolicy.getCommand() and indicate how you managed to get LINE B to run."); //$NON-NLS-1$
                getHost().getViewer().setSelection(new StructuredSelection(getHost()));
            }
        } else if (request.getType() == REQ_RESIZE) {
            // This line gives feedback to the user when he attempts to resize a pathnode. This is illegal.
            return UnexecutableCommand.INSTANCE;
        }

        return super.getCommand(request);
    }
}