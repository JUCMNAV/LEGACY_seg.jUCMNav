/*
 * Created on 2005-03-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editpolicies.element;

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
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Created 2005-03-21
 * 
 * @author Etienne Tremblay, jkealey
 */
public class PathNodeComponentEditPolicy extends ComponentEditPolicy {

    /**
     *  
     */
    public PathNodeComponentEditPolicy() {
        super();
    }

    /*
     * (non-Javadoc)
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
        } else if (parent instanceof Map && ((PathNode) node).getPred().size() == 1 && ((PathNode) node).getSucc().size() == 1) {
            DeleteNodeCommand command = new DeleteNodeCommand((PathNode) node);
            return command;
        } else if (parent instanceof Map && ((PathNode) node).getPred().size() > 1 || ((PathNode) node).getSucc().size() > 1) {
            DeleteMultiNodeCommand command = new DeleteMultiNodeCommand((PathNode) node, registry);
            return command;
        }

        return super.createDeleteCommand(request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
     */
    public Command getCommand(Request request) {
        if (request.getType() == CutPathAction.CUTPATH_REQUEST) {
            EmptyPoint ep = ((EmptyPoint) ((EditPart) getHost()).getModel());
            CutPathCommand cp = new CutPathCommand(ep.getPathGraph(), ep);
            return cp;
        }
        if (request.getType() == REQ_CREATE) {
            Object newObjectType = null;
            if (((CreateRequest) request).getNewObject() != null)
                newObjectType = ((CreateRequest) request).getNewObjectType();
            if (newObjectType instanceof EndPoint)
                getHost().getViewer().setSelection(new StructuredSelection(getHost()));
        } else if (request.getType() == REQ_RESIZE)
            return UnexecutableCommand.INSTANCE;

        return super.getCommand(request);
    }
}