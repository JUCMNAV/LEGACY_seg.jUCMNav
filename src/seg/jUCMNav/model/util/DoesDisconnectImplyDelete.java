package seg.jUCMNav.model.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;

/**
 * This is a helper class that helps decide whether or not the disconnection of the passed branches from the fork/join/stub/timer should cause it to be deleted
 * or downgraded into an empty point.
 * 
 * Originally in DeleteMultiNodeCommand
 * 
 * @author jkealey
 * 
 */
public class DoesDisconnectImplyDelete {
    /**
     * 
     * Because other commands get rid of connects, we need to get rid of a few node connections in verifyShouldDelete() because the command must be run prior to
     * the execution of the previous commands.
     * 
     * @param connections
     *            a vector of NodeConnections to check for Connects
     */
    public static void trimConnectNodeConnections(Vector connections) {
        Vector v = new Vector();
        for (Iterator iter = connections.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            if (nc.getSource() instanceof Connect || nc.getTarget() instanceof Connect)
                v.add(nc);
        }

        // done in two passes because we're using iterators.
        for (Iterator iter = v.iterator(); iter.hasNext();) {
            connections.remove(iter.next());
        }
    }
    // if shouldReplaceWithEmpty, this is the node connection on which the empty point should be inserted
    private NodeConnection insertionConnection;

    // list of incoming and outgoing node connections
    private Vector ncInBefore, ncOutBefore;

    private Vector ncInToRemove, ncOutToRemove;

    // if this is false, we're only removing branches.
    private boolean shouldDeleteNode = true;

    // if this is true, we should replace the toDelete with an empty point
    private boolean shouldReplaceWithEmpty = false;

    // the pathnode to be deleted.
    private PathNode toDelete;

    /**
     * @param toDelete
     *            The PathNode from which node connections must be removed.
     * @param ncIn
     *            The node connections that enter this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * @param ncOut
     *            The node connections that exist this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * 
     */
    public DoesDisconnectImplyDelete(PathNode toDelete, List ncIn, List ncOut) {
        this.toDelete = toDelete;
        this.ncInToRemove = new Vector(ncIn);
        this.ncOutToRemove = new Vector(ncOut);
        this.shouldDeleteNode = false;
        trimConnectNodeConnections(ncInToRemove);
        trimConnectNodeConnections(ncOutToRemove);
        verifyShouldDelete();
        verifyShouldDowngrade();
    }

    /**
     * If shouldReplaceWithEmpty(), this indicates where to insert the empty point.
     * 
     * @return wheter to inset the empty point.
     */
    public NodeConnection getInsertionConnection() {
        return insertionConnection;
    }

    /**
     * 
     * @return incoming node connections, before deletion.
     */
    public Vector getNcInBefore() {
        return ncInBefore;
    }

    /**
     * 
     * @return incoming node connections that should be removed by deletion
     */
    public Vector getNcInToRemove() {
        return ncInToRemove;
    }

    /**
     * 
     * @return outgoing node connections, before deletion.
     */
    public Vector getNcOutBefore() {
        return ncOutBefore;
    }

    /**
     * 
     * @return outgoing node connections that should be removed by deletion
     */
    public Vector getNcOutToRemove() {
        return ncOutToRemove;
    }

    /**
     * Marks toDelete for deletion: all branches must be disconnected and shouldDeleteNode becomes true.
     * 
     */
    private void markNodeForDeletion() {
        this.ncInBefore = new Vector(toDelete.getPred());
        this.ncOutBefore = new Vector(toDelete.getSucc());

        ncOutToRemove = new Vector();
        ncOutToRemove.addAll(toDelete.getSucc());

        ncInToRemove = new Vector();
        ncInToRemove.addAll(toDelete.getPred());

        shouldDeleteNode = true;
    }

    /**
     * Defines ncInBefore and ncOutBefore by taking the pred/succ of the pathnode and removing any connects from the list.
     */
    private void setCleanBeforeLists() {
        this.ncInBefore = new Vector(toDelete.getPred());
        this.ncOutBefore = new Vector(toDelete.getSucc());

        // we don't care about connections but we know they might still be in the lists because the DisconnectCommand might be in the same CompoundCommand as
        // this command.
        trimConnectNodeConnections(ncInBefore);
        trimConnectNodeConnections(ncOutBefore);
    }

    /**
     * 
     * @return should this node be deleted from the pathgraph because of the disconnection
     */
    public boolean shouldDeleteNode() {
        return shouldDeleteNode;
    }

    /**
     * 
     * @return should this node be replaced with an emtpy point after its deletion
     */
    public boolean shouldReplaceWithEmpty() {
        return shouldReplaceWithEmpty;
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
            exclusions.addAll(ncOutToRemove);
            direction = QFindReachableNodes.DIRECTION_FORWARD;
        } else {
            // this is a join, we haven't marked it for deletion yet so we have deleted some (but not all) its inputs and have not touched its
            // outputs.
            // we want to verify that by going backwards in the graph, without going through the connections that are marked for deletion, if we can
            // find a start point
            exclusions.addAll(ncInToRemove);
            direction = QFindReachableNodes.DIRECTION_REVERSE;
        }

        // run the query of reachable nodes.
        QFindReachableNodes qry = new ReachableNodeFinder.QFindReachableNodes(toDelete, exclusions, direction);
        ReachableNodeFinder.RReachableNodes resp = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qry);
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
     * 
     */
    private void verifyShouldDelete() {
        setCleanBeforeLists();

        Vector ncInAfter = (Vector) ncInBefore.clone();
        ncInAfter.removeAll(ncInToRemove);

        Vector ncOutAfter = (Vector) ncOutBefore.clone();
        ncOutAfter.removeAll(ncOutToRemove);

        if (toDelete instanceof Stub) {
            if (ncInAfter.size() == 0 && ncOutAfter.size() == 0) {
                // stubs are deleted only when they have neither inputs nor outputs
                markNodeForDeletion();
            }
        } else if (ncInAfter.size() == 0 || ncOutAfter.size() == 0) {
            // delete element if has no elements on one side or the other.
            markNodeForDeletion();
        } else if (toDelete instanceof Timer && ncOutAfter.size() == 1 && ncOutBefore.indexOf(ncOutAfter.get(0)) == 1) {
            // if trying to delete the normal path of a timer, must disconnect its timeout path as well.
            markNodeForDeletion();
        } else if (toDelete instanceof AndFork || toDelete instanceof OrFork || toDelete instanceof AndJoin || toDelete instanceof OrJoin) {
            boolean foundStartOrEnd = verifyCausesLoop();

            // if we can't find one, we made a bad loop: delete it.
            if (!foundStartOrEnd)
                markNodeForDeletion();
            // else if 1 in, 1 out -> convert to empty point. logic moved elsewhere.
        }

    }

    /**
     * Checks to see if the fork/join should be downgraded into an empty point. (If it only has one in/out remaining after disconnection)
     * 
     */
    private void verifyShouldDowngrade() {
        // check to see if we must downgrade to an empty point.
        if (toDelete instanceof AndFork || toDelete instanceof OrFork || toDelete instanceof AndJoin || toDelete instanceof OrJoin) {
            if (ncInBefore.size() - ncInToRemove.size() == 1 && ncOutBefore.size() - ncOutToRemove.size() == 1) {
                shouldReplaceWithEmpty = true;
                Vector v;
                if (toDelete instanceof AndFork || toDelete instanceof OrFork) {
                    v = new Vector(ncInBefore);
                    v.removeAll(ncInToRemove);
                } else { // joins
                    v = new Vector(ncOutBefore);
                    v.removeAll(ncOutToRemove);
                }
                insertionConnection = (NodeConnection) v.firstElement();
            }
        }

    }

}
