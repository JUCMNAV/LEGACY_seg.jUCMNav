/*
 * Created on 2005-02-25
 *
 */
package seg.jUCMNav.editpolicies.layout;

import java.util.Vector;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddForkOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddJoinOnConnectionCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.DividePathOnNodeConnectionCompoundCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class NodeConnectionXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /**
     *  
     */
    public NodeConnectionXYLayoutEditPolicy() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {
        Vector selection = new Vector();
        selection.add(child);
        selection.add(getHost());

        SelectionHelper sel = new SelectionHelper(selection);
        PathNode checkNode;
        Vector vReachable;

        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_NODECONNECTION:
            // JP: use new querying capabilities to make sure the endpoint being dropped is not already reachable from
            // this nodeconnection.
            // If it's reachable, abort because this is an illegal action (destruction of endpoint / creation of loops).
            checkNode = sel.getNodeconnection().getTarget();
            EndPoint checkEndPoint = sel.getEndpoint();
            EndPointFinder.QFindReachableEndPoints qReachableEnds = new EndPointFinder().new QFindReachableEndPoints(
                    checkNode);
            EndPointFinder.RReachableEndPoints rReachableEnds = (EndPointFinder.RReachableEndPoints) GraphExplorer
                    .getInstance().run(qReachableEnds);
            vReachable = rReachableEnds.getNodes();
            if (!vReachable.contains(checkEndPoint)) {
                return new DividePathOnNodeConnectionCompoundCommand(sel.getEndpoint(), sel.getNodeconnection(), sel
                        .getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y, true);
            }
            break;
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            // JP: use new querying capabilities to make sure the startpoint being dropped is not already reachable from
            // this nodeconnection.
            // If it's reachable, abort because this is an illegal action (destruction of startpoint / creation of
            // loops).
            checkNode = sel.getNodeconnection().getTarget();
            StartPoint checkStartPoint = sel.getStartpoint();
            StartPointFinder.QFindReachableStartPoints qReachableStarts = new StartPointFinder().new QFindReachableStartPoints(
                    checkNode);
            StartPointFinder.RReachableStartPoints rReachableStarts = (StartPointFinder.RReachableStartPoints) GraphExplorer
                    .getInstance().run(qReachableStarts);
            vReachable = rReachableStarts.getNodes();
            if (!vReachable.contains(checkStartPoint)) {
                return new DividePathOnNodeConnectionCompoundCommand(sel.getStartpoint(), sel.getNodeconnection(), sel
                        .getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y, true);
            }
            break;
        }

        // don't allow drop
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        return null;
    }

    /*
     * (non-Javadoc)
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

        if (newObjectType == EmptyPoint.class || newObjectType == RespRef.class
                || newObjectType == DirectionArrow.class || newObjectType == Stub.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            //			if(oldLink.getSource() instanceof StartPoint || oldLink.getTarget() instanceof EndPoint)
            //				return null;

            createCommand = new SplitLinkCommand(getPathGraph(), (PathNode) request.getNewObject(), oldLink,
                    constraint.x, constraint.y);
        } else if (newObjectType == OrFork.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            OrFork newOrFork = (OrFork) ModelCreationFactory.getNewObject(getPathGraph().getMap().getUcmspec()
                    .getUrnspec(), OrFork.class);
            createCommand = new AddForkOnConnectionCommand(newOrFork, getPathGraph(), oldLink, constraint.x,
                    constraint.y);
        } else if (newObjectType == AndFork.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            AndFork newAndFork = (AndFork) ModelCreationFactory.getNewObject(getPathGraph().getMap().getUcmspec()
                    .getUrnspec(), AndFork.class);
            createCommand = new AddForkOnConnectionCommand(newAndFork, getPathGraph(), oldLink, constraint.x,
                    constraint.y);
        } else if (newObjectType == OrJoin.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            OrJoin newOrJoin = (OrJoin) ModelCreationFactory.getNewObject(getPathGraph().getMap().getUcmspec()
                    .getUrnspec(), OrJoin.class);
            createCommand = new AddJoinOnConnectionCommand(newOrJoin, getPathGraph(), oldLink, constraint.x,
                    constraint.y);
        } else if (newObjectType == AndJoin.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            AndJoin newAndJoin = (AndJoin) ModelCreationFactory.getNewObject(getPathGraph().getMap().getUcmspec()
                    .getUrnspec(), AndJoin.class);
            createCommand = new AddJoinOnConnectionCommand(newAndJoin, getPathGraph(), oldLink, constraint.x,
                    constraint.y);
        }

        return createCommand;
    }

    /**
     * This method is a subset of getConstraintFor(request).
     * 
     * JK: eTremblay doesn't know why he created it, he knows it had something to do with fixing a bug. I attempted to
     * change the calling code to use getConstraintFor but the code no longer works. Will leave as is as I'm not
     * familiar with this structure.
     * 
     * @param request
     * @return
     */
    private Point getLocation(CreateRequest request) {
        IFigure figure = getLayoutContainer();
        Point where = request.getLocation().getCopy();
        Dimension size = request.getSize();
        figure.translateToRelative(where);
        figure.translateFromParent(where);
        return where;
    }

    public Command getCommand(Request request) {
        if (request.getType() == CutPathAction.CUTPATH_REQUEST) {
            CutPathCommand cp = new CutPathCommand(getPathGraph(), (NodeConnection) (getHost().getModel()));
            return cp;
        } else {
            return super.getCommand(request);

        }
    }

    /**
     * The NodeConnectionEditPart contain a reference to the diagram too. This function query the editpart and return
     * the PathGraph.
     * 
     * @return The PathGraph this NodeConnection is associated with.
     */
    private PathGraph getPathGraph() {
        return ((NodeConnectionEditPart) getHost()).getPathGraph();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
     */
    protected Command getDeleteDependantCommand(Request request) {
        return null;
    }

    /**
     * Overritten because we don't have an xylayout on the node connection and we don't really care about this right
     * now.
     * 
     * Returns {@link XYLayout#getOrigin(IFigure)}.
     * 
     * @see ConstrainedLayoutEditPolicy#getLayoutOrigin()
     */
    protected Point getLayoutOrigin() {
        return new Point(0, 0);
    }

}