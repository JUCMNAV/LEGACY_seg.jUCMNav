package seg.jUCMNav.editpolicies.layout;

import java.util.Vector;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Anything;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;

/**
 * XYLayoutEditPolicy for node connections. Adds support for creating elements on node connection from the palette and drag&drop of PathNodes on
 * NodeConnections.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class NodeConnectionXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /**
     * Is executed when someone drag&drops an existing PathNode on a NodeConnection.
     * 
     * If does not create illegal loops (see SafePathChecker) will return a DividePathOnNodeConnectionCompoundCommand. Otherwise, moves the dragged PathNode
     * using SetConstraintCommand.
     * 
     * Only start/end points can be dragged onto NodeConnections.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {
        Vector selection = new Vector();
        selection.add(child);
        selection.add(getHost());

        SelectionHelper sel = new SelectionHelper(selection);
        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_NODECONNECTION:
            if (!SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getNodeconnection()))
                break;
            return new DividePathCommand(sel.getEndpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y, true);
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            if (!SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getNodeconnection()))
                break;

            return new DividePathCommand(sel.getStartpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y, true);
        }

        if (child instanceof PathNodeEditPart) {
            // We're going to allow dropping here to simply move the pathnode
            // This is not a creation command so I don't like the placement of this too much but it works
            Dimension dim = ((PathNodeEditPart) child).getNodeFigure().getPreferredSize().getCopy();
            Point location = new Point(((Rectangle) constraint).x + (dim.width / 2), ((Rectangle) constraint).y + (dim.height / 2));
            PathNode node = (PathNode) child.getModel();
            return new SetConstraintCommand(node, location.x, location.y);
        }
        return null;
    }

    /**
     * Not used.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        return null;
    }

    /**
     * Used to insert PathNodes on paths, using the palette. Uses SplitLinkCommand for most purposes except for forks/joins.
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        Object newObjectType = null;
        if (request.getNewObject() != null)
            newObjectType = request.getNewObjectType();
        Command createCommand = null;

        // converts relative to absolute positions (so that zooms work properly)
        Point constraint = getLocation(request);

        if (newObjectType == EmptyPoint.class || newObjectType == RespRef.class || newObjectType == DirectionArrow.class || newObjectType == Stub.class
                || newObjectType == WaitingPlace.class || newObjectType == Timer.class || newObjectType ==  FailurePoint.class || newObjectType == Anything.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            createCommand = new SplitLinkCommand(getPathGraph(), (PathNode) request.getNewObject(), oldLink, constraint.x, constraint.y);
        } else if (newObjectType == OrFork.class || newObjectType == AndFork.class || newObjectType == OrJoin.class || newObjectType == AndJoin.class) {

            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            PathNode newNode = (PathNode) ModelCreationFactory.getNewObject(getPathGraph().getUrndefinition().getUrnspec(), (Class) newObjectType);
            createCommand = new DividePathCommand(newNode, oldLink, constraint.x, constraint.y);

        }

        return createCommand;
    }

    /**
     * This method is a subset of getConstraintFor(request).
     * 
     * JK: eTremblay doesn't know why he created it, he knows it had something to do with fixing a bug. I attempted to change the calling code to use
     * getConstraintFor but the code no longer works. Will leave as is as I'm not familiar with this structure.
     * 
     * @param request
     * @return the translated location.
     */
    private Point getLocation(CreateRequest request) {
        IFigure figure = getLayoutContainer();
        Point where = request.getLocation().getCopy();
        figure.translateToRelative(where);
        figure.translateFromParent(where);
        return where;
    }

    /**
     * Returns CutPathCommand if request is a CUTPATH_REQUEST. Implemented when we didn't know much about actions. Could be changed and put directly in the
     * action instead of having the action query the edit part.
     */
    public Command getCommand(Request request) {
        if (request.getType() == CutPathAction.CUTPATH_REQUEST) {
            CutPathCommand cp = new CutPathCommand(getPathGraph(), (NodeConnection) (getHost().getModel()));
            return cp;
        } else {
            return super.getCommand(request);

        }
    }

    /**
     * The NodeConnectionEditPart contain a reference to the diagram too. This function query the editpart and return the PathGraph.
     * 
     * @return The PathGraph this NodeConnection is associated with.
     */
    private UCMmap getPathGraph() {
        return (UCMmap) ((NodeConnectionEditPart) getHost()).getMap();
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
     * Overridden because we don't have an xylayout on the node connection and we don't really care about this right now.
     * 
     * Returns {@link XYLayout#getOrigin(IFigure)}.
     * 
     * @see ConstrainedLayoutEditPolicy#getLayoutOrigin()
     */
    protected Point getLayoutOrigin() {
        return new Point(0, 0);
    }

}