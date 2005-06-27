package seg.jUCMNav.model.commands.delete;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.OutBinding;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.Condition;

/**
 * Created on 29-May-2005
 * 
 * This class implements the deletion for a PathNode that has multiple inputs or outputs.
 * 
 * We create new start and end points to truncate the paths that enter or exit this PathNode.
 * 
 * It is important to be noted that one can pass a subset of the node connections in the constructor and only these will be deleted. Therefore, depending on the
 * constructor parameters, this command doesn't necessarily delete the actual node, it might just disconnect branches. Furthermore, note that even though a
 * subset of branches is passed, an internal decision might be taken to delete the pathnode. For example, deleting the incoming branch of a fork will delete the
 * fork itself.
 * 
 * This command handles the deletion of PluginBindings, InBindings and OutBindings from Stubs.
 * 
 * @author jkealey
 *  
 */
public class DeleteMultiNodeCommand extends CompoundCommand implements JUCMNavCommand {

    // if the node has already been deleted, this will prevent the command from
    // being done/undone without breaking the stack.
    private boolean aborted = false;

    // needed to get access to the SplineConnection of incoming / outgoing node
    // connections.
    private Map editpartregistry;

    // if the pathnode was downgraded to an empty point, this is it.
    private EmptyPoint empty;

    // list of incoming and outgoing node connections
    private List ncIn, ncOut;

    // list of new start points and end points.
    private List newStart, newEnd;

    // when deleting an orfork, remember its out conditions.
    private HashMap outConditions;

    // if the node is bound to a parent
    private ComponentRef parent;

    // the pathgraph that contains the node.
    private PathGraph pg;

    // if this is false, we're only removing branches.
    private boolean shouldDeleteNode = true;

    // the pathnode to be deleted.
    private PathNode toDelete;

    // the URNspec which contains all the elements
    private URNspec urn;

    /**
     * Deletes a PathNode and replaces the specified incoming and outgoing node connections with start points and end points.
     * 
     * The node connection lists will not be respected if they imply the node must be completely removed.
     * 
     * @param toDelete
     *            The PathNode to be deleted.
     * @param ncIn
     *            The node connections that enter this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * @param ncOut
     *            The node connections that exist this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * 
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     *  
     */
    public DeleteMultiNodeCommand(PathNode toDelete, List ncIn, List ncOut, Map editpartregistry) {
        this.toDelete = toDelete;
        this.ncIn = ncIn;
        this.ncOut = ncOut;
        this.editpartregistry = editpartregistry;
        this.shouldDeleteNode = false;
    }

    /**
     * Deletes a PathNode and replaces its incoming and outgoing node connections with start points and end points.
     * 
     * @param toDelete
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     *  
     */
    public DeleteMultiNodeCommand(PathNode toDelete, Map editpartregistry) {
        this.toDelete = toDelete;
        this.editpartregistry = editpartregistry;
        this.shouldDeleteNode = true;
    }

    /**
     * Delete bindings if any exist.
     */
    private void addDeletePluginBindingCommands() {
        if (toDelete instanceof Stub) {
            Stub stub = (Stub) toDelete;

            if (shouldDeleteNode) {
                // remove the plugin binding and its bindings
                for (Iterator i = stub.getBindings().iterator(); i.hasNext();) {
                    PluginBinding plugin = (PluginBinding) i.next();
                    DeletePluginCommand del = new DeletePluginCommand(plugin);
                    add(del);
                }
            } else {
                // simply delete the affected bindings
                for (Iterator iter = ncIn.iterator(); iter.hasNext();) {
                    NodeConnection nc = (NodeConnection) iter.next();
                    for (Iterator iterator = nc.getInBindings().iterator(); iterator.hasNext();) {
                        InBinding binding = (InBinding) iterator.next();
                        add(new DeleteInBindingCommand(binding));
                    }
                }
                for (Iterator iter = ncOut.iterator(); iter.hasNext();) {
                    NodeConnection nc = (NodeConnection) iter.next();
                    for (Iterator iterator = nc.getOutBindings().iterator(); iterator.hasNext();) {
                        OutBinding binding = (OutBinding) iterator.next();
                        add(new DeleteOutBindingCommand(binding));
                    }
                }
            }
        }
    }

    /**
     * Removes the connect to this pathnode if necessary.
     */
    private void addDisconnectCommand() {
        if (shouldDeleteNode) {
            DisconnectCommand cmd = new DisconnectCommand(toDelete);
            if (cmd.canExecute()) {
                add(cmd);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return toDelete != null && toDelete.getPathGraph() != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canUndo()
     */
    public boolean canUndo() {
        // Make sure we can undo even if we don't have any added commands
        if (getCommands().size() == 0)
            return true;
        return super.canUndo();
    }

    /**
     *  
     */
    private void doRedo() {
        if (shouldDeleteNode) {
            // remove the current node from the map
            toDelete.setCompRef(null);
            pg.getPathNodes().remove(toDelete);
        }

        // rewire incoming and outgoing links.
        for (int i = 0; i < ncIn.size(); i++) {
            NodeConnection nc = (NodeConnection) ncIn.get(i);
            PathNode pn = (PathNode) newEnd.get(i);
            nc.setTarget(pn);
            pg.getPathNodes().add(pn);
            pn.setCompRef(ParentFinder.getPossibleParent(pn));
        }

        for (int i = 0; i < ncOut.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOut.get(i);
            PathNode pn = (PathNode) newStart.get(i);
            nc.setSource(pn);
            pg.getPathNodes().add(pn);
            pn.setCompRef(ParentFinder.getPossibleParent(pn));
            nc.setCondition(null);
        }

        if (!shouldDeleteNode && empty != null) {
            // must downgrade to empty point.
            ((NodeConnection) toDelete.getPred().get(0)).setTarget(empty);
            ((NodeConnection) toDelete.getSucc().get(0)).setCondition(null);
            ((NodeConnection) toDelete.getSucc().get(0)).setSource(empty);
            empty.setCompRef(toDelete.getCompRef());
            toDelete.setCompRef(null);
            pg.getPathNodes().remove(toDelete);
            pg.getPathNodes().add(empty);

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // this could happen when multiple nodes are selected for deletion and
        // this one has already been deleted.
        if (!canExecute()) {
            aborted = true;
            return;
        }
        verifyShouldDelete();

        initialize();

        testPreConditions();

        super.execute();
        doRedo();

        testPostConditions();
    }

    /**
     *  
     */
    private void initEmptyPoint() {
        // need to downgrade pathnode to empty point.
        empty = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
        empty.setX(toDelete.getX());
        empty.setY(toDelete.getY());
    }

    /**
     * Given the list of incoming node connections, create end points and set them at the right place (mid node connection).
     */
    private void initEndPoints() {
        // create and initialize all new end points.
        for (Iterator iter = ncIn.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            EndPoint ep = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);
            NodeConnectionEditPart nodePart = (NodeConnectionEditPart) editpartregistry.get(nc);
            if (nodePart != null) {
                SplineConnection spline = (SplineConnection) nodePart.getFigure();

                // if we don't do this, when deleting multiple path elements,
                // the spline might not have been refreshed and the mid point
                // ends up being in the
                // top
                // left corner.
                spline.getConnectionRouter().route(spline);
                if (spline.getPoints().size() > 0) {
                    midPoint = spline.getPoints().getMidpoint();
                    ep.setX(midPoint.x);
                    ep.setY(midPoint.y);
                } else {
                    if (nc.getSource() != null && nc.getSource() != toDelete) {
                        ep.setX(nc.getSource().getX());
                        ep.setY(nc.getSource().getY());
                    }
                    if (nc.getTarget() != null && nc.getTarget() != toDelete) {
                        ep.setX(nc.getTarget().getX());
                        ep.setY(nc.getTarget().getY());
                    }
                }
            }
            newEnd.add(ep);
        }
    }

    /**
     * Initializes all the internal variables, given a properly define list of in/outs to be deleted.
     */
    private void initialize() {
        addDeletePluginBindingCommands();
        addDisconnectCommand();
        trimConnectNodeConnections();
        initVariables();
        initStartPoints();
        initEndPoints();
        initOutConditions();

    }

    /**
     * Store the list of conditions on outgoing node connections. Since there are no conditions on incoming connections, don't need the inverse.
     */
    private void initOutConditions() {
        outConditions = new HashMap();
        for (Iterator iter = toDelete.getSucc().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            outConditions.put(nc, nc.getCondition());
        }
    }

    /**
     * Given the list of outgoing node connections, create start points and set them at the right place (mid node connection).
     */
    private void initStartPoints() {
        // create and initialize all new start points.
        for (Iterator iter = ncOut.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            StartPoint sp = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);

            NodeConnectionEditPart nodePart = (NodeConnectionEditPart) editpartregistry.get(nc);
            if (nodePart != null) {
                SplineConnection spline = (SplineConnection) nodePart.getFigure();
                // if we don't do this, when deleting multiple path elements,
                // the spline might not have been refreshed and the mid point
                // ends up being in the top left corner

                spline.getConnectionRouter().route(spline);
                if (spline.getPoints().size() > 0) {
                    midPoint = spline.getPoints().getMidpoint();
                    sp.setX(midPoint.x);
                    sp.setY(midPoint.y);
                }
            }
            newStart.add(sp);
        }
    }

    /**
     * save pathgraph/urn and other simple variables
     */
    private void initVariables() {
        pg = toDelete.getPathGraph();
        urn = toDelete.getPathGraph().getMap().getUcmspec().getUrnspec();
        parent = toDelete.getCompRef();
    }

    /**
     * Make sure we delete all in/out connects and disconnect any connects from the element.
     */
    private void markNodeForDeletion() {
        ncOut = new Vector();
        ncIn = new Vector();
        ncOut.addAll(toDelete.getSucc());
        ncIn.addAll(toDelete.getPred());

        shouldDeleteNode = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

        super.redo();

        testPreConditions();

        doRedo();

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && pg != null && toDelete != null && ncIn != null && ncOut != null && newStart != null && newEnd != null : "post something is null"; //$NON-NLS-1$
        assert ncIn.size() == newEnd.size() && ncOut.size() == newStart.size() : "post invalid sizes"; //$NON-NLS-1$
        if (shouldDeleteNode)
            assert !pg.getPathNodes().contains(toDelete) : "post node not in model"; //$NON-NLS-1$

        for (int i = 0; i < ncIn.size(); i++) {
            assert !toDelete.getPred().contains(ncIn.get(i)) : "post pred nc not in pred " + i; //$NON-NLS-1$
            assert pg.getPathNodes().contains(newEnd.get(i)) : "post new end not in graph " + i; //$NON-NLS-1$
        }
        for (int i = 0; i < ncOut.size(); i++) {
            assert !toDelete.getSucc().contains(ncOut.get(i)) : "post succ nc not in succ " + i; //$NON-NLS-1$
            assert pg.getPathNodes().contains(newStart.get(i)) : "post new start not in graph " + i; //$NON-NLS-1$
        }

        /*
         * if (toDelete instanceof Stub) { Stub stub = (Stub) toDelete;
         * 
         * assert stub.getBindings().size() == 0 : "Post Stub PluginBindings count is not 0."; //$NON-NLS-1$ for (Iterator i = plugins.iterator(); i.hasNext();) {
         * PluginBinding plugin = (PluginBinding) i.next();
         * 
         * assert plugin.getPlugin() == null : "Post the map associated with the PluginBinding is not null."; //$NON-NLS-1$
         * 
         * assert plugin.getIn().size() == 0 : "Post number of InBinding is not 0."; //$NON-NLS-1$ assert plugin.getOut().size() == 0 : "Post number of
         * OutBinding is not 0."; //$NON-NLS-1$
         * 
         * for (Iterator j = plugin.getIn().iterator(); j.hasNext();) { InBinding in = (InBinding) j.next(); assert in.getStartPoint() == null : "Post the
         * StartPoint for this InBinding is not null."; //$NON-NLS-1$ assert in.getStubEntry() == null : "Post the entry for this InBinding is not null.";
         * //$NON-NLS-1$ }
         * 
         * for (Iterator j = plugin.getOut().iterator(); j.hasNext();) { OutBinding out = (OutBinding) j.next(); assert out.getEndPoint() == null : "Post the
         * EndPoint for this OutBinding is not null."; //$NON-NLS-1$ assert out.getStubExit() == null : "Post the exit for this OutBinding is not null.";
         * //$NON-NLS-1$ } } }
         */
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && pg != null && toDelete != null && ncIn != null && ncOut != null && newStart != null && newEnd != null : "pre something is null"; //$NON-NLS-1$
        assert ncIn.size() == newEnd.size() && ncOut.size() == newStart.size() : "pre invalid sizes"; //$NON-NLS-1$
        if (shouldDeleteNode)
            assert pg.getPathNodes().contains(toDelete) : "pre node not in model"; //$NON-NLS-1$
        for (int i = 0; i < ncIn.size(); i++) {
            assert toDelete.getPred().contains(ncIn.get(i)) : "pre pred nc not in pred " + i; //$NON-NLS-1$
            assert !pg.getPathNodes().contains(newEnd.get(i)) : "pre new end not in graph " + i; //$NON-NLS-1$
        }
        for (int i = 0; i < ncOut.size(); i++) {
            assert toDelete.getSucc().contains(ncOut.get(i)) : "pre succ nc not in succ " + i; //$NON-NLS-1$
            assert !pg.getPathNodes().contains(newStart.get(i)) : "pre new start not in graph " + i; //$NON-NLS-1$
        }
        /*
         * if (toDelete instanceof Stub) { Stub stub = (Stub) toDelete;
         * 
         * assert stub.getBindings().size() == plugins.size() : "Pre Stub PluginBindings count is different."; //$NON-NLS-1$ for (Iterator i =
         * stub.getBindings().iterator(); i.hasNext();) { PluginBinding plugin = (PluginBinding) i.next();
         * 
         * assert plugin.getPlugin() == maps.get(plugin) : "Pre the map associated with the PluginBinding is not the same as before."; //$NON-NLS-1$
         * 
         * assert plugin.getIn().size() == ((ArrayList) inBindings.get(plugin)).size() : "Pre number of InBinding is not the same for this PluginBinding";
         * //$NON-NLS-1$ assert plugin.getOut().size() == ((ArrayList) outBindings.get(plugin)).size() : "Pre number of OutBinding is not the same for this
         * PluginBinding"; //$NON-NLS-1$
         * 
         * for (Iterator j = plugin.getIn().iterator(); j.hasNext();) { InBinding in = (InBinding) j.next(); assert in.getStartPoint() == starts.get(in) : "Pre
         * the StartPoint for this InBinding is not the same as before."; //$NON-NLS-1$ assert in.getStubEntry() == entry.get(in) : "Pre the entry for this
         * InBinding is not the same as before."; //$NON-NLS-1$ }
         * 
         * for (Iterator j = plugin.getOut().iterator(); j.hasNext();) { OutBinding out = (OutBinding) j.next(); assert out.getEndPoint() == starts.get(out) :
         * "Pre the EndPoint for this OutBinding is not the same as before."; //$NON-NLS-1$ assert out.getStubExit() == entry.get(out) : "Pre the exit for this
         * OutBinding is not the same as before."; //$NON-NLS-1$ } } }
         */

    }

    /**
     * Because other commands get rid of connects, we need to get rid of a few node connections.
     */
    private void trimConnectNodeConnections() {
        Vector v = new Vector();
        for (Iterator iter = ncIn.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            if (nc.getSource() instanceof Connect)
                v.add(nc);
        }
        for (Iterator iter = v.iterator(); iter.hasNext();) {
            ncIn.remove(iter.next());
        }
        v = new Vector();
        for (Iterator iter = ncOut.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            if (nc.getTarget() instanceof Connect)
                v.add(nc);
        }
        for (Iterator iter = v.iterator(); iter.hasNext();) {
            ncOut.remove(iter.next());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        if (!shouldDeleteNode && empty != null) {
            ((NodeConnection) empty.getPred().get(0)).setTarget(toDelete);
            ((NodeConnection) empty.getSucc().get(0)).setSource(toDelete);
            toDelete.setCompRef(empty.getCompRef());
            empty.setCompRef(null);
            pg.getPathNodes().remove(empty);
            pg.getPathNodes().add(toDelete);
        }

        // rewire the incoming and outgoing connections.
        for (int i = 0; i < ncIn.size(); i++) {
            NodeConnection nc = (NodeConnection) ncIn.get(i);
            nc.setTarget(toDelete);
            PathNode pn = (PathNode) newEnd.get(i);
            pg.getPathNodes().remove(pn);
            pn.setCompRef(null);
        }

        for (int i = 0; i < ncOut.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOut.get(i);
            nc.setSource(toDelete);
            PathNode pn = (PathNode) newStart.get(i);
            pg.getPathNodes().remove(pn);
            pn.setCompRef(null);
        }

        for (Iterator iter = outConditions.keySet().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            if (outConditions.get(nc) != nc.getCondition())
                nc.setCondition((Condition) outConditions.get(nc));
        }

        if (shouldDeleteNode) {
            // add the pathnode to the graph
            toDelete.setCompRef(parent);
            pg.getPathNodes().add(toDelete);
        }

        testPreConditions();

        super.undo();

    }

    /**
     * @return true if the deletion of these branches on a fork/join would cause a loop.
     */
    private boolean verifyCausesLoop() {
        HashSet exclusions = new HashSet();

        int direction = 0;

        if (toDelete instanceof AndFork || toDelete instanceof OrFork) {
            // this is a fork, we haven't marked it for deletion yet so we have deleted some (but not all) its outputs and have not touched its
            // input.
            // we want to verify that by going backwards in the graph, without going through the connections that are marked for deletion, if we can
            // find an end point
            exclusions.addAll(ncOut);
            direction = QFindReachableNodes.DIRECTION_FORWARD;
        } else {
            // this is a join, we haven't marked it for deletion yet so we have deleted some (but not all) its inputs and have not touched its
            // outputs.
            // we want to verify that by going backwards in the graph, without going through the connections that are marked for deletion, if we can
            // find a start point
            exclusions.addAll(ncIn);
            direction = QFindReachableNodes.DIRECTION_REVERSE;
        }

        // run the query of reachable nodes.
        QFindReachableNodes qry = new ReachableNodeFinder().new QFindReachableNodes(toDelete, exclusions, direction);
        ReachableNodeFinder.RReachableNodes resp = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qry);
        Vector vReachable = resp.getNodes();

        // iterate through the collection, looking to see if we can reach a start/end.
        boolean foundStartOrEnd = false;
        for (Iterator iter = vReachable.iterator(); iter.hasNext() && !foundStartOrEnd;) {
            PathNode element = (PathNode) iter.next();

            // paths can start with startpoints or stubs.
            if (direction == QFindReachableNodes.DIRECTION_REVERSE && element instanceof StartPoint || element instanceof Stub)
                foundStartOrEnd = true;

            // paths can end with endpoints, stubs or timeout paths (that have yet to be inserted).
            if (direction == QFindReachableNodes.DIRECTION_FORWARD && element instanceof EndPoint || element instanceof Stub
                    || (element instanceof Timer && element.getSucc().size() == 1))
                foundStartOrEnd = true;
        }
        return foundStartOrEnd;
    }

    /**
     * We must verify that the given node connections to be deleted imply the path node must be deleted.
     * 
     * For example, a timer must be deleted if we remove its primary input/output but is still legal if we delete its timeoutpath.
     */
    private void verifyShouldDelete() {
        // create new vectors.
        newStart = new Vector();
        newEnd = new Vector();

        // save current state.
        if (ncOut == null) {
            ncOut = new Vector();
            ncOut.addAll(toDelete.getSucc());
        }

        if (ncIn == null) {
            ncIn = new Vector();
            ncIn.addAll(toDelete.getPred());
        }

        // make sure it is valid to simply break the connection.
        if (!shouldDeleteNode) {
            int minIn = 0;
            int minOut = 0;

            if (!(toDelete instanceof Stub) && ((toDelete.getSucc().size() - ncOut.size() == 0) || (toDelete.getPred().size() - ncIn.size() == 0))) {
                // delete element if has no elements on one side or the other.
                markNodeForDeletion();
            } else if (toDelete instanceof Timer && ncOut.size() == 1 && toDelete.getSucc().indexOf(ncOut.get(0)) == 0) {
                // if trying to delete the normal path of a timer, must disconnect its timeout path as well.
                markNodeForDeletion();
            } else if ((new DisconnectCommand(toDelete)).canExecute()) {
                // is this pathnode connected to a connect?
                if (toDelete instanceof EmptyPoint && ncOut.contains(toDelete.getSucc().get(0))) {
                    // must delete if we delete the out that isn't a connect.
                    markNodeForDeletion();
                } else if (toDelete instanceof WaitingPlace && ncIn.contains(toDelete.getPred().get(0))) {
                    // must delete if we delete the in that isn't a connect. (remember timer subclass of waitingplace)
                    markNodeForDeletion();
                }
            } else if (toDelete instanceof Stub) {
                // stubs are deleted only when they have neither inputs nor outputs
                if ((toDelete.getSucc().size() - ncOut.size() == 0) && (toDelete.getPred().size() - ncIn.size() == 0)) {
                    markNodeForDeletion();
                }
            } else {
                if (toDelete instanceof AndFork || toDelete instanceof OrFork || toDelete instanceof AndJoin || toDelete instanceof OrJoin) {
                    boolean foundStartOrEnd = verifyCausesLoop();

                    // if we can't find one, we made a bad loop: delete it.
                    if (!foundStartOrEnd)
                        markNodeForDeletion();
                    else if ((toDelete.getSucc().size() - ncOut.size() == 1) && (toDelete.getPred().size() - ncIn.size() == 1)) {
                        // we downgrade forks/joins to empty points when they have only 1 in/out
                        initEmptyPoint();
                    }
                }
            }

        }
    }
}