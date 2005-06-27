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
import seg.jUCMNav.model.commands.create.AddForkOnEmptyPointCommand;
import seg.jUCMNav.model.commands.create.AddJoinOnEmptyPointCommand;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import seg.jUCMNav.model.commands.transformations.ForkPathsCommand;
import seg.jUCMNav.model.commands.transformations.JoinEndToStubJoinCommand;
import seg.jUCMNav.model.commands.transformations.JoinPathsCommand;
import seg.jUCMNav.model.commands.transformations.JoinStartToStubForkCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
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
        if ((getHost().getModel() instanceof EmptyPoint || getHost().getModel() instanceof DirectionArrow)
                && request.getNewObject() instanceof PathNode
                && !(request.getNewObject() instanceof StartPoint || request.getNewObject() instanceof EndPoint || request.getNewObject() instanceof EmptyPoint)) {
            // because we don't want forks/joins without only 1 in/out
            if (request.getNewObject() instanceof AndFork || request.getNewObject() instanceof OrFork)
                return new AddForkOnEmptyPointCommand((PathNode) request.getNewObject(), ((PathNode) getHost().getModel()).getPathGraph(),
                        (PathNode) getHost().getModel());

            else if (request.getNewObject() instanceof AndJoin || request.getNewObject() instanceof OrJoin)
                return new AddJoinOnEmptyPointCommand((PathNode) request.getNewObject(), ((PathNode) getHost().getModel()).getPathGraph(),
                        (PathNode) getHost().getModel());
            else
                return new ReplaceEmptyPointCommand((PathNode) getHost().getModel(), (PathNode) request.getNewObject());
        } else
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
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {

        Vector selection = new Vector();
        selection.add(child);
        selection.add(getHost());

        SelectionHelper sel = new SelectionHelper(selection);
        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getEmptypoint())) {
                OrJoin join = (OrJoin) ModelCreationFactory.getNewObject(sel.getUrnspec(), OrJoin.class);
                return new JoinPathsCommand(sel.getEmptypoint(), sel.getEndpoint(), join);
            }
            break;

        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getEmptypoint())) {
                OrFork fork = (OrFork) ModelCreationFactory.getNewObject(sel.getUrnspec(), OrFork.class);
                return new ForkPathsCommand(sel.getEmptypoint(), sel.getStartpoint(), fork);
            }
            break;

        case SelectionHelper.STARTPOINT_ENDPOINT:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getStartpoint())) {
                Rectangle cons = getCurrentConstraintFor((GraphicalEditPart) getHost());
                return new MergeStartEndCommand(sel.getMap(), sel.getStartpoint(), sel.getEndpoint(), cons.x, cons.y);
            }
            break;

        case SelectionHelper.ENDPOINT_STUB:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getStub())) {
                return new JoinEndToStubJoinCommand(sel.getEndpoint(), sel.getStub());
            }
            break;

        case SelectionHelper.ENDPOINT_ORJOIN:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getOrjoin())) {
                return new JoinEndToStubJoinCommand(sel.getEndpoint(), sel.getOrjoin());
            }
            break;

        case SelectionHelper.ENDPOINT_ANDJOIN:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getAndjoin())) {
                return new JoinEndToStubJoinCommand(sel.getEndpoint(), sel.getAndjoin());
            }
            break;

        case SelectionHelper.STARTPOINT_STUB:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getStub())) {
                return new JoinStartToStubForkCommand(sel.getStartpoint(), sel.getStub());
            }
            break;

        case SelectionHelper.STARTPOINT_ORFORK:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getOrfork())) {
                return new JoinStartToStubForkCommand(sel.getStartpoint(), sel.getOrfork());
            }
            break;

        case SelectionHelper.STARTPOINT_ANDFORK:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getAndfork())) {
                return new JoinStartToStubForkCommand(sel.getStartpoint(), sel.getAndfork());
            }
            break;

        case SelectionHelper.EMPTYPOINT_TIMER:
            return new ConnectCommand(sel.getEmptypoint(), sel.getTimer());
        case SelectionHelper.EMPTYPOINT_WAITINGPLACE:
            return new ConnectCommand(sel.getEmptypoint(), sel.getWaitingPlace());
        case SelectionHelper.ENDPOINT_TIMER:
            return new ConnectCommand(sel.getEndpoint(), sel.getTimer());
        case SelectionHelper.ENDPOINT_WAITINGPLACE:
            return new ConnectCommand(sel.getEndpoint(), sel.getWaitingPlace());

        }

        // don't allow drop
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        return null;
    }
}