package seg.jUCMNav.editpolicies.layout;

import java.util.Vector;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.ForkPathsCommand;
import seg.jUCMNav.model.commands.transformations.JoinEndToStubJoinCommand;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import seg.jUCMNav.model.commands.transformations.JoinStartToStubForkCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.EndPointFinder;
import seg.jUCMNav.model.util.modelexplore.queries.StartPointFinder;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Created on 25-May-2005
 * 
 * @author jkealey
 *  
 */
public class PathNodeXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
     */
    protected EditPolicy createChildEditPolicy(EditPart child) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
     */
    protected Command getDeleteDependantCommand(Request request) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getMoveChildrenCommand(org.eclipse.gef.Request)
     */
    protected Command getMoveChildrenCommand(Request request) {
        return null;
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
        StartPoint checkStartPoint;
        EndPoint checkEndPoint;
        StartPointFinder.RReachableStartPoints rReachableStarts;
        StartPointFinder.QFindReachableStartPoints qReachableStarts;
        EndPointFinder.QFindReachableEndPoints qReachableEnds;
        EndPointFinder.RReachableEndPoints rReachableEnds;

        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
            // JP: use new querying capabilities to make sure the endpoint being dropped is not already reachable from
            // this EmptyPoint.
            // If it's reachable, abort because this is an illegal action (destruction of endpoint / creation of loops).
            checkNode = sel.getEmptypoint();
            checkEndPoint = sel.getEndpoint();
            qReachableEnds = new EndPointFinder().new QFindReachableEndPoints(checkNode);
            rReachableEnds = (EndPointFinder.RReachableEndPoints) GraphExplorer.getInstance().run(qReachableEnds);
            vReachable = rReachableEnds.getNodes();
            if (!vReachable.contains(checkEndPoint)) {
                OrJoin join = (OrJoin) ModelCreationFactory.getNewObject(sel.getUrnspec(), OrJoin.class);
                return new JoinPathsCommand(sel.getEmptypoint(), sel.getEndpoint(), join);
            }
            break; // This is just for style, yo :)

        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            // JP: use new querying capabilities to make sure the startpoint being dropped is not already reachable from
            // this node.
            // If it's reachable, abort because this is an illegal action (destruction of startpoint / creation of
            // loops).
            checkNode = sel.getEmptypoint();
            checkStartPoint = sel.getStartpoint();
            qReachableStarts = new StartPointFinder().new QFindReachableStartPoints(checkNode);
            rReachableStarts = (StartPointFinder.RReachableStartPoints) GraphExplorer.getInstance().run(
                    qReachableStarts);
            vReachable = rReachableStarts.getNodes();
            if (!vReachable.contains(checkStartPoint)) {
                OrFork fork = (OrFork) ModelCreationFactory.getNewObject(sel.getUrnspec(), OrFork.class);
                return new ForkPathsCommand(sel.getEmptypoint(), sel.getStartpoint(), fork);
            }
            break;

        case SelectionHelper.STARTPOINT_ENDPOINT:
            // disable creation of loops by checking that the SP is not already reachable from the EP
            checkNode = sel.getEndpoint();
            checkStartPoint = sel.getStartpoint();
            qReachableStarts = new StartPointFinder().new QFindReachableStartPoints(checkNode);
            rReachableStarts = (StartPointFinder.RReachableStartPoints) GraphExplorer.getInstance().run(
                    qReachableStarts);
            vReachable = rReachableStarts.getNodes();
            if (!vReachable.contains(checkStartPoint)) {
                Rectangle cons = getCurrentConstraintFor((GraphicalEditPart) getHost());
                return new MergeStartEndCommand(sel.getMap(), sel.getStartpoint(), sel.getEndpoint(), cons.x, cons.y);
            }
            break;

        case SelectionHelper.ENDPOINT_STUB:
            return new JoinEndToStubJoinCommand(sel.getEndpoint(), sel.getStub());
        case SelectionHelper.ENDPOINT_ORJOIN:
            return new JoinEndToStubJoinCommand(sel.getEndpoint(), sel.getOrjoin());
        case SelectionHelper.ENDPOINT_ANDJOIN:
            return new JoinEndToStubJoinCommand(sel.getEndpoint(), sel.getAndjoin());
        case SelectionHelper.STARTPOINT_STUB:
            return new JoinStartToStubForkCommand(sel.getStartpoint(), sel.getStub());
        case SelectionHelper.STARTPOINT_ORFORK:
            return new JoinStartToStubForkCommand(sel.getStartpoint(), sel.getOrfork());
        case SelectionHelper.STARTPOINT_ANDFORK:
            return new JoinStartToStubForkCommand(sel.getStartpoint(), sel.getAndfork());
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
}