/**
 * 
 */
package seg.jUCMNav.editpolicies.layout;

import grl.ActorRef;
import grl.Belief;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElementRef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.editparts.GrlNodeEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintGrlNodeCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.AddKPIInformationElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.Comment;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Label;

/**
 * XYLayoutEditPolicy for the GrlGraphEditPart. Handles creation of new elements and moving/resizing of existing ones.
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class GrlGraphXYLayoutEditPolicy extends AbstractDiagramXYLayoutEditPolicy {

    /**
     * Returns a command to be executed when the palette tries to create something
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        Object newObjectType = null;
        if (request.getNewObject() != null)
            newObjectType = request.getNewObjectType();

        // converts relative to absolute positions (so that zooms work properly)
        Rectangle constraint = (Rectangle) getConstraintFor(request);
        Command createCommand = null;

        if ((newObjectType == IntentionalElementRef.class) || (newObjectType == Belief.class) || (newObjectType == KPIInformationElementRef.class)) {
            createCommand = handleCreateGrlNode(request, constraint);
        } else if (newObjectType == ActorRef.class) {
            createCommand = handleCreateActorRef(request, constraint);
        } else if (newObjectType == Comment.class) {
            createCommand = handleCreateComment(request, constraint);
        }
        return createCommand;
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
     * Convenience method to prevent casting
     * 
     * @return the graph
     */
    protected GRLGraph getGraph() {
        return (GRLGraph) getHost().getModel();
    }

    /**
     * Handles moving/resizing of Graph children.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (child.getModel() instanceof IURNNode) {
            return handleMoveGRLNode(child, constraint);
        } else if (child.getModel() instanceof Label) {
            return handleMoveLabel(child, constraint);
        } else if (child.getModel() instanceof IURNContainerRef) {
            return handleMoveResizeContainerRef(child, constraint);
        } else if (child.getModel() instanceof Comment) {
            return handleMoveResizeComment(child, constraint);
        } else {
            System.out.println("Unknown model element"); //$NON-NLS-1$
            return null;
        }

    }

    /**
     * @param request
     *            the CreateRequest containing the new ActorRef
     * @param constraint
     *            where the new object should be created
     * @return a command that adds a new ActorRef on the Map and moves/resizes it into place.
     */
    private Command handleCreateActorRef(CreateRequest request, Rectangle constraint) {
        Command createCommand;
        ActorRef actorRef = (ActorRef) request.getNewObject();

        AddContainerRefCommand create = new AddContainerRefCommand(getGraph(), actorRef);
        SetConstraintBoundContainerRefCompoundCommand moveResize = new SetConstraintBoundContainerRefCompoundCommand(actorRef, constraint.x, constraint.y,
                constraint.width, constraint.height);

        // after creation, move and resize the component;
        createCommand = create.chain(moveResize);
        return createCommand;
    }

    /**
     * @param request
     *            the CreateRequest containing the new ComponentRef
     * @param constraint
     *            where the new object should be created
     * @return a command that adds a new IntentionalElementRef or KPIInformationElementRef on the Graph and moves/resizes it into place.
     */
    private Command handleCreateGrlNode(CreateRequest request, Rectangle constraint) {
        Command createCommand = null;
        Command create = null;
        GRLNode node = null;
        if (request.getNewObject() instanceof IntentionalElementRef) {
            node = (IntentionalElementRef) request.getNewObject();
            create = buildCreateGrlNodeCommand(getGraph(), (IntentionalElementRef) node);
        } else if (request.getNewObject() instanceof KPIInformationElementRef) {
            node = (KPIInformationElementRef) request.getNewObject();
            KPIInformationElementRef info = (KPIInformationElementRef) node;
            create = buildCreateKPIInformationElementCommand(getGraph(), info);
        } else if (request.getNewObject() instanceof Belief) {
            node = (Belief) request.getNewObject();
            create = new AddBeliefCommand(getGraph(), (Belief) node);
        }
        SetConstraintCommand move = new SetConstraintCommand(node, constraint.x, constraint.y);

        // after creation, move and resize the node;
        if (create != null)
            createCommand = create.chain(move);

        return createCommand;
    }

    public static Command buildCreateKPIInformationElementCommand(GRLGraph graph, KPIInformationElementRef info) {
        Command create;
        create = new AddKPIInformationElementRefCommand(graph, info);

        if (GeneralPreferencePage.getGrlAutoAddLinks()) {
            if (info.getDef().getKpiModelLinksSrc().size() > 0) {
                // dragged existing element from outline.

                CreateAllKPIModelLinkRefCommand createCmd = new CreateAllKPIModelLinkRefCommand(graph, info);
                if (createCmd.canExecute())
                    create = create.chain(createCmd);

            }
        }
        return create;
    }

    public static Command buildCreateGrlNodeCommand(GRLGraph graph, IntentionalElementRef node) {
        Command create;
        create = new AddIntentionalElementRefCommand(graph, node);

        if (GeneralPreferencePage.getGrlAutoAddLinks()) {
            if ((node).getDef().getLinksSrc().size() + (node).getDef().getLinksDest().size() > 0) {
                // dragged existing element from outline.

                CreateAllLinkRefCommand createCmd = new CreateAllLinkRefCommand(graph, node);
                if (createCmd.canExecute())
                    create = create.chain(createCmd);

            }

            if (node instanceof Indicator && ((Indicator) (node).getDef()).getKpiModelLinksDest().size() > 0) {
                // dragged existing element from outline.

                CreateAllKPIModelLinkRefCommand createCmd = new CreateAllKPIModelLinkRefCommand(graph, node);
                if (createCmd.canExecute())
                    create = create.chain(createCmd);

            }
        }
        return create;
    }

    protected List getMoveBendpointCommands(EList list, Rectangle rect, boolean isNext, Map registry) {
        List commands = new ArrayList();
        for (Iterator i = list.iterator(); i.hasNext();) {
            IURNConnection type = (IURNConnection) i.next();

            if (type instanceof LinkRef)
                commands.addAll(getMoveBendpointCommands((LinkRef) type, rect, isNext, registry));
        }
        return commands;
    }

    protected List getMoveBendpointCommands(LinkRef ref, Rectangle rect, boolean isNext, Map registry) {
        List commands = new ArrayList();

        if (ref.getBendpoints().size() > 0) {
            LinkRefEditPart part = (LinkRefEditPart) registry.get(ref);
            List bendpoints = (List) part.getConnectionFigure().getRoutingConstraint();

            Point diff;

            if (isNext)
                diff = rect.getLocation().getTranslated((new Point(ref.getSource().getX(), ref.getSource().getY())).getNegated());
            else
                diff = rect.getLocation().getTranslated((new Point(ref.getTarget().getX(), ref.getTarget().getY())).getNegated());

            boolean multipleSelected = part.getViewer().getSelectedEditParts().size() > 1;

            int index = 0;
            for (Iterator y = bendpoints.iterator(); y.hasNext();) {
                AbsoluteBendpoint bendpoint = (AbsoluteBendpoint) y.next();
                float weight = 1;

                if (!multipleSelected) {
                    // Calculate how moving the node should affect the bendpoints.
                    // The closer the bendpoint is to the moving node, the more it is affected by the move.
                    // This simulate the behavior of RelativeBendpoint in eclipse.
                    weight = (index + 1) / ((float) bendpoints.size() + 1);
                    if (isNext)
                        weight = ((bendpoints.size() - index) + 1) / ((float) bendpoints.size() + 1);

                    // Bendpoints directly next to another node are not affected when we move the other node of the connection
                    if ((index == 0 && !isNext) || (index == (bendpoints.size() - 1) && isNext))
                        weight = 0;
                    else if ((index == (bendpoints.size() - 1) && !isNext) || (index == 0 && isNext))
                        weight = 1;
                }

                // Simlate a translation of the bendpoint location by the delta movement of the node.  Affect the bendpoint by the weight calculated above.
                Point location = bendpoint.getLocation().getTranslated(new Point(Math.round(diff.x * weight), Math.round(diff.y * weight)));

                BendpointRequest request = new BendpointRequest();
                request.setIndex(index);
                request.setLocation(location);
                request.setSource(part);
                request.setType(RequestConstants.REQ_MOVE_BENDPOINT);

                commands.add(part.getCommand(request));

                index++;
            }
        }
        return commands;
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
    private Command handleMoveGRLNode(EditPart child, Object constraint) {
        Rectangle rect = (Rectangle) constraint;
        GRLNode node = (GRLNode) child.getModel();
        GrlNodeEditPart part = (GrlNodeEditPart) child;

        Map registry = child.getViewer().getEditPartRegistry();

        List commands = new ArrayList();

        commands.addAll(getMoveBendpointCommands(node.getPred(), rect, false, registry));
        commands.addAll(getMoveBendpointCommands(node.getSucc(), rect, true, registry));

        return new SetConstraintGrlNodeCommand(node, rect.getLocation().x, rect.getLocation().y, commands);
    }
}
