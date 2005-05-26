/*
 * Created on 2005-02-25
 *
 */
package seg.jUCMNav.editpolicies.layout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddForkOnConnectionCommand;
import seg.jUCMNav.model.commands.create.AddJoinOnConnectionCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.AndFork;
import ucm.map.DirectionArrow;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Stub;
import urn.URNspec;

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
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createAddCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    protected Command createAddCommand(EditPart child, Object constraint) {
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

        if (newObjectType == EmptyPoint.class || newObjectType == RespRef.class || newObjectType == DirectionArrow.class || newObjectType == Stub.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            //			if(oldLink.getSource() instanceof StartPoint || oldLink.getTarget() instanceof EndPoint)
            //				return null;

            createCommand = new SplitLinkCommand(getPathGraph(), (PathNode) request.getNewObject(), oldLink, constraint.x, constraint.y);
        } else if (newObjectType == OrFork.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            OrFork newOrFork = (OrFork) ModelCreationFactory.getNewObject((URNspec) getPathGraph().eContainer().eContainer().eContainer(), OrFork.class);
            createCommand = new AddForkOnConnectionCommand(newOrFork, getPathGraph(), oldLink, constraint.x, constraint.y);
        } else if (newObjectType == AndFork.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            AndFork newAndFork = (AndFork) ModelCreationFactory.getNewObject((URNspec) getPathGraph().eContainer().eContainer().eContainer(), AndFork.class);
            createCommand = new AddForkOnConnectionCommand(newAndFork, getPathGraph(), oldLink, constraint.x, constraint.y);
        } else if (newObjectType == OrJoin.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            OrJoin newOrJoin = (OrJoin) ModelCreationFactory.getNewObject((URNspec) getPathGraph().eContainer().eContainer().eContainer(), OrJoin.class);
            createCommand = new AddJoinOnConnectionCommand(newOrJoin, getPathGraph(), oldLink, constraint.x, constraint.y);
        } else if (newObjectType == AndJoin.class) {
            NodeConnection oldLink = (NodeConnection) this.getHost().getModel();
            AndJoin newAndJoin = (AndJoin) ModelCreationFactory.getNewObject((URNspec) getPathGraph().eContainer().eContainer().eContainer(), AndJoin.class);
            createCommand = new AddJoinOnConnectionCommand(newAndJoin, getPathGraph(), oldLink, constraint.x, constraint.y);
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
        // TODO Auto-generated method stub
        if (request.getType() == CutPathAction.CUTPATH_REQUEST) {
            CutPathCommand cp = new CutPathCommand(getPathGraph(), (NodeConnection)(getHost().getModel()));
            return cp;
        } else
            return super.getCommand(request);
    }

    /**
     * The NodeConnectionEditPart contain a reference to the diagram too. This function query the editpart and return the PathGraph.
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

}