package seg.jUCMNav.model.util;

import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.DeletionPathFinder;
import seg.jUCMNav.model.util.modelexplore.queries.DeletionPathFinder.QFindSpline;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * 
 * Check if a proposed fusion of two nodes is <i>safe </i>, that is, will not cause an illegal loop. joinFromPathNode is expected to be a StartPoint or
 * EndPoint, but not enforced.
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class SafePathChecker {

    /**
     * One can loop a start/end with a stub on the same deletion path if they are not already directly joined.
     * 
     * @param nc
     *            the node connection
     * @param nodes
     *            the nodes on the path segment
     * @return true/false showing whether loopback to stub is allowed
     */
    private static boolean isLoopBackToStubAllowed(NodeConnection nc, Vector nodes) {
        return (nodes.firstElement() instanceof Stub && !nc.getSource().equals(nodes.firstElement()) && !nc.getTarget().equals(nodes.firstElement()))
                || (nodes.lastElement() instanceof Stub && !nc.getSource().equals(nodes.lastElement()) && !nc.getTarget().equals(nodes.lastElement()));
    }

    /**
     * Is it safe to put this pathnode onto this node connection; might cause forks/joins to be created
     * 
     * @param joinFromPathNode
     *            the pathnode
     * @param joinToNodeConnection
     *            the node connection
     * @return true if safe
     */
    public static boolean isSafeFusion(PathNode joinFromPathNode, NodeConnection joinToNodeConnection) {
        PathNode toNode = (PathNode) joinToNodeConnection.getTarget();
        boolean b = isSafeFusion(joinFromPathNode, toNode);
        if (!b && (joinFromPathNode instanceof EndPoint && toNode instanceof EndPoint)) {
            toNode = (PathNode) joinToNodeConnection.getSource();
            b = isSafeFusion(joinFromPathNode, toNode);
        }
        return b;
    }

    /**
     * Is it safe to combine these two pathnodes? Might replace a start/end with an empty point.
     * 
     * @param joinFromPathNode
     *            the first pathnode
     * @param joinToPathNode
     *            the second pathnode
     * @return true if safe; no illegal loops caused
     */
    public static boolean isSafeFusion(PathNode joinFromPathNode, PathNode joinToPathNode) {

        if (joinFromPathNode == joinToPathNode)
            return false;
        // Query for reachable nodes starting from joinToPathNode: if joinFromPathNode is not included in there,
        // the join won't cause a loop.

        QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(joinToPathNode);
        ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
        Vector vReachable = rReachableNodes.getNodes();

        ReachableNodeFinder.QFindReachableNodes qReachableNodes2 = new ReachableNodeFinder.QFindReachableNodes(joinFromPathNode);
        ReachableNodeFinder.RReachableNodes rReachableNodes2 = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes2);
        Vector vReachable2 = rReachableNodes2.getNodes();

        if (!vReachable.containsAll(vReachable2)) {
            // different sets -> can always fusion
            // don't verify opposite; trust query returns same vector but in different order (equals doesn't work here)
            return true;
        }

        // remove passed start/ends from reachable pathnodes
        while (vReachable.contains(joinFromPathNode) && (joinFromPathNode instanceof StartPoint || joinFromPathNode instanceof EndPoint))
            vReachable.remove(joinFromPathNode);
        while (vReachable.contains(joinToPathNode) && (joinToPathNode instanceof StartPoint || joinToPathNode instanceof EndPoint))
            vReachable.remove(joinToPathNode);

        // count remaining start/ends
        int iStartPoints, iEndPoints;
        iStartPoints = iEndPoints = 0;

        for (Iterator iter = vReachable.iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof StartPoint)
                iStartPoints++;
            else if (pn instanceof EndPoint)
                iEndPoints++;
        }

        // verify that merger will leave at least one start and end point in the path
        if (iStartPoints > 0 && iEndPoints > 0) {

            // find the node connection following the start or preceeding the end.
            NodeConnection nc = null;
            if (joinFromPathNode instanceof StartPoint)
                nc = (NodeConnection) joinFromPathNode.getSucc().get(0);
            else if (joinFromPathNode instanceof EndPoint)
                nc = (NodeConnection) joinFromPathNode.getPred().get(0);
            else if (joinToPathNode instanceof StartPoint)
                nc = (NodeConnection) joinToPathNode.getSucc().get(0);
            else if (joinToPathNode instanceof EndPoint)
                nc = (NodeConnection) joinToPathNode.getPred().get(0);
            else
                assert false : "incorrect call to isSafeFusion"; //$NON-NLS-1$

            // we want to make sure we aren't merging elements on the same deletion path to cause illegal loops.
            QFindSpline qry = new DeletionPathFinder().new QFindSpline(nc);
            DeletionPathFinder.RSpline resp = (DeletionPathFinder.RSpline) GraphExplorer.run(qry);
            Vector nodes = resp.getPathNodes();

            if (joinFromPathNode instanceof StartPoint || joinFromPathNode instanceof EndPoint)
                return !nodes.contains(joinToPathNode) || (joinToPathNode instanceof Stub && isLoopBackToStubAllowed(nc, nodes));
            else
                return !nodes.contains(joinFromPathNode) || (joinFromPathNode instanceof Stub && isLoopBackToStubAllowed(nc, nodes));

        } else
            return false;

    }

}