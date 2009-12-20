package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * ComponentEditPolicy for UCM PathNodes. Returns delete commands.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class PathNodeComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns either a DeletePathNodeCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object parent = getHost().getParent().getModel();
        Object node = getHost().getModel();
        java.util.Map registry;

        if (getHost().getViewer() instanceof TreeViewer) {
            // we need an editpart registry with NodeConnectionEditParts
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            registry = editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry();
        } else
            registry = getHost().getViewer().getEditPartRegistry();

        if (parent instanceof UCMmap && node instanceof PathNode) {
            return new DeletePathNodeCommand((PathNode) node, registry);
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
            PathNode ep = ((PathNode) (getHost()).getModel());
            CutPathCommand cp = new CutPathCommand((UCMmap) ep.getDiagram(), ep);
            // LINE A
            // System.out.println("Please review PathNodeComponentEditPolicy.getCommand() and indicate how you managed to get LINE A to run."); //$NON-NLS-1$
            // CutPathAction on PathNode.
            return cp;
        }
        if (request.getType() == REQ_CREATE && request instanceof CreateRequest) {
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