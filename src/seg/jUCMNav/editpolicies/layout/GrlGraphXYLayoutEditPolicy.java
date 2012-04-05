/**
 * 
 */
package seg.jUCMNav.editpolicies.layout;

import grl.ActorRef;
import grl.Belief;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElementRef;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.editparts.GrlNodeEditPart;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintGrlNodeCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.AddKPIInformationElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableGRLNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableGRLNodeFinder.QFindReachableNodes;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableGRLNodeFinder.RReachableNodes;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.Comment;
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
        }
        else {
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
        SetConstraintGrlNodeCommand move = new SetConstraintGrlNodeCommand(node, constraint.x, constraint.y, constraint.width, constraint.height, false);

        // after creation, move and resize the node;
        if (create != null)
            createCommand = create.chain(move);

        return createCommand;
    }

    public static Command buildCreateKPIInformationElementCommand(GRLGraph graph, KPIInformationElementRef info) {
        return  buildCreateKPIInformationElementCommand(graph, info, null);
    }
    public static Command buildCreateKPIInformationElementCommand(GRLGraph graph, KPIInformationElementRef info, Vector forceAutoAddLinksToTheseElements ) {
        Command create;
        create = new AddKPIInformationElementRefCommand(graph, info);

        if (GeneralPreferencePage.getGrlAutoAddLinks())
            forceAutoAddLinksToTheseElements = null; // copy all, don't filter in this case. 

        
        if (forceAutoAddLinksToTheseElements!=null || GeneralPreferencePage.getGrlAutoAddLinks()) {
            if (info.getDef().getKpiModelLinksSrc().size() > 0) {
                // dragged existing element from outline.

                CreateAllKPIModelLinkRefCommand createCmd = new CreateAllKPIModelLinkRefCommand(graph, info, forceAutoAddLinksToTheseElements );
                if (createCmd.canExecute())
                    create = create.chain(createCmd);

            }
        }
        return create;
    }

    public static Command buildCreateGrlNodeCommand(GRLGraph graph, IntentionalElementRef node)
    {
        return buildCreateGrlNodeCommand(graph, node, null);
    }
    public static Command buildCreateGrlNodeCommand(GRLGraph graph, IntentionalElementRef node, Vector forceAutoAddLinksToTheseElements) {
        Command create;
        create = new AddIntentionalElementRefCommand(graph, node);

        if (GeneralPreferencePage.getGrlAutoAddLinks())
            forceAutoAddLinksToTheseElements = null; // copy all, don't filter in this case. 
        if (forceAutoAddLinksToTheseElements!=null || GeneralPreferencePage.getGrlAutoAddLinks()) {
            if ((node).getDef().getLinksSrc().size() + (node).getDef().getLinksDest().size() > 0) {
                // dragged existing element from outline.

                CreateAllLinkRefCommand createCmd = new CreateAllLinkRefCommand(graph, node, forceAutoAddLinksToTheseElements);
                if (createCmd.canExecute())
                    create = create.chain(createCmd);

            }

            if (node instanceof Indicator && ((Indicator) (node).getDef()).getKpiModelLinksDest().size() > 0) {
                // dragged existing element from outline.

                CreateAllKPIModelLinkRefCommand createCmd = new CreateAllKPIModelLinkRefCommand(graph, node, forceAutoAddLinksToTheseElements);
                if (createCmd.canExecute())
                    create = create.chain(createCmd);

            }
        }
        return create;
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
        
        boolean multipleNodeMoved = isMultipleSelected(node, getSelectedModel(part.getViewer()));

        return new SetConstraintGrlNodeCommand(node, rect.getLocation().x, rect.getLocation().y, rect.width, rect.height, multipleNodeMoved);
    }

    @Override
    protected boolean isMultipleSelected(List nodes, List selected) {
        boolean multipleNodeMoved = false;
        
        for (Iterator i = nodes.iterator(); i.hasNext();) {
            GRLNode node = (GRLNode) i.next();
            multipleNodeMoved |= isMultipleSelected(node, selected);
        }
        
        return multipleNodeMoved;
    }


    /**
     * For a given GRLNode, find if any of the selected nodes in the UI can be reached via connections from the initial GRLNode.
     * This will affect how we move connection bendpoints in the model
     * 
     * @param node
     * @param selectedNodes
     * @return true if 
     */
    protected boolean isMultipleSelected(GRLNode node, List selectedNodes) {
        HashSet ncs = new HashSet();
        
        QFindReachableNodes qReachableNodes = new ReachableGRLNodeFinder.QFindReachableNodes(node);
        RReachableNodes rReachableNodes = (RReachableNodes) GraphExplorer.run(qReachableNodes);
        Vector vReachable = rReachableNodes.getNodes();
        
        boolean contains = false;
        
        for (Iterator i = vReachable.iterator(); i.hasNext();) {
            Object next = i.next();
            contains |= (selectedNodes.contains(next) && next != node);
            if(contains)
                break;
        }
        
        return contains;
    }

}
