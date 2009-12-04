package seg.jUCMNav.editpolicies.layout;

import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import seg.jUCMNav.model.commands.create.ConnectCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.ui.EditLabelCommand;
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
import urncore.Label;

/**
 * XYLayoutEditPolicy for PathNodes. Allows replacing empty points and direction arrows with other PathNodes when using the Palette. Furthermore, manages the
 * drag&drop behaviour of PathNodes on each other.
 * 
 * @author jkealey
 * 
 */
public class PathNodeXYLayoutEditPolicy extends XYLayoutEditPolicy {

    /**
     * Used to allow drag&drop of pathnodes on others. SafePathChecker is used profusely to make sure no illegal loops are formed.
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
                return new DividePathCommand(sel.getEndpoint(), sel.getEmptypoint(), true);
            }
            break;

        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getEmptypoint())) {
                return new DividePathCommand(sel.getStartpoint(), sel.getEmptypoint(), true);
            }
            break;
        case SelectionHelper.ENDPOINT_DIRECTIONARROW:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getDirectionarrow())) {
                return new DividePathCommand(sel.getEndpoint(), sel.getDirectionarrow(), true);
            }
            break;

        case SelectionHelper.STARTPOINT_DIRECTIONARROW:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getDirectionarrow())) {
                return new DividePathCommand(sel.getStartpoint(), sel.getDirectionarrow(), true);
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
                return new AttachBranchCommand(sel.getEndpoint(), sel.getStub());
            }
            break;

        case SelectionHelper.ENDPOINT_ORJOIN:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getOrjoin())) {
                return new AttachBranchCommand(sel.getEndpoint(), sel.getOrjoin());
            }
            break;

        case SelectionHelper.ENDPOINT_ANDJOIN:
            if (SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getAndjoin())) {
                return new AttachBranchCommand(sel.getEndpoint(), sel.getAndjoin());
            }
            break;

        case SelectionHelper.STARTPOINT_STUB:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getStub())) {
                return new AttachBranchCommand(sel.getStartpoint(), sel.getStub());
            }
            break;

        case SelectionHelper.STARTPOINT_ORFORK:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getOrfork())) {
                return new AttachBranchCommand(sel.getStartpoint(), sel.getOrfork());
            }
            break;

        case SelectionHelper.STARTPOINT_ANDFORK:
            if (SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getAndfork())) {
                return new AttachBranchCommand(sel.getStartpoint(), sel.getAndfork());
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

    /**
     * Not used.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        return null;
    }

    /**
     * Not used
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
     */
    protected EditPolicy createChildEditPolicy(EditPart child) {
        return null;
    }

    /**
     * Replaces empty points and direction arrows with other new node created in palette.
     * 
     * Adds branches on existing forks/joins if palette tool is path tool or fork/join.
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        // allows empty points to be replaced by direction arrows
        if ((getHost().getModel() instanceof EmptyPoint) && (request.getNewObject() instanceof DirectionArrow)) {
            return new ReplaceEmptyPointCommand((PathNode) getHost().getModel(), (PathNode) request.getNewObject());
        }
        // can replace with new object?
        if (isReplaceable(getHost().getModel()) && !(isPathTool(request) || isReplaceable(request.getNewObject()))) {
            // because we don't want forks/joins without only 1 in/out
            if (request.getNewObject() instanceof AndFork || request.getNewObject() instanceof OrFork || request.getNewObject() instanceof AndJoin
                    || request.getNewObject() instanceof OrJoin)
                return new DividePathCommand((PathNode) request.getNewObject(), (PathNode) getHost().getModel());
            else if (request.getNewObject() instanceof PathNode)
                return new ReplaceEmptyPointCommand((PathNode) getHost().getModel(), (PathNode) request.getNewObject());
            else
                return null;
        } else if (isForkOrJoin(getHost().getModel()) && (isForkOrJoin(request.getNewObject()) || isPathTool(request))) {
            return new AddBranchCommand((PathNode) getHost().getModel());
        } else if (getHost().getModel() instanceof Label) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            return new EditLabelCommand(editor, (LabelEditPart) getHost());
        } else
            return null;
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
     * Not used.
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getMoveChildrenCommand(org.eclipse.gef.Request)
     */
    protected Command getMoveChildrenCommand(Request request) {
        return null;
    }

    /**
     * 
     * @param pn
     *            the pathnode to check
     * @return true if pn is a fork or join
     */
    private boolean isForkOrJoin(Object pn) {
        return pn instanceof AndFork || pn instanceof AndJoin || pn instanceof OrFork || pn instanceof OrJoin;
    }

    /**
     * @param request
     *            the palette's CreateRequest
     * @return true if the request's new object is generated by the PathTool.
     */
    private boolean isPathTool(CreateRequest request) {
        return request.getNewObject() instanceof StartPoint || request.getNewObject() instanceof EndPoint;
    }

    /**
     * @param pn
     *            the pathnode to check
     * @return true if pn is an empty point or direction arrow
     */
    private boolean isReplaceable(Object pn) {
        return (pn instanceof EmptyPoint || pn instanceof DirectionArrow);
    }

    protected Point getLayoutOrigin() {
        if (getXYLayout() == null)
            return new Point(0, 0);
        else
            return getXYLayout().getOrigin(getLayoutContainer());
    }
}