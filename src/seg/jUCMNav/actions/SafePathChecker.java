/*
 * Created on 11-Jun-2005
 *
 */
package seg.jUCMNav.actions;

import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * @author jpdaigle
 *
 * Check if a proposed join is <i>safe</i>, that is, will not cause a loop.
 * joinFromPathNode is expected to be a StartPoint or EndPoint, but not enforced.
 */
public class SafePathChecker {

    public static boolean isSafeJoin(PathNode joinFromPathNode, NodeConnection joinToNodeConnection) {
        PathNode toNode = joinToNodeConnection.getTarget();
        return isSafeJoin(joinFromPathNode, toNode);
    }

    public static boolean isSafeJoin(PathNode joinFromPathNode, PathNode joinToPathNode) {
        // Query for reachable nodes starting from joinToPathNode: if joinFromPathNode is not included in there,
        // the join won't cause a loop.
        
        ReachableNodeFinder.QFindReachableNodes qReachableNodes = new ReachableNodeFinder().new QFindReachableNodes(
                joinToPathNode);
        ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer
                .getInstance().run(qReachableNodes);
        Vector vReachable = rReachableNodes.getNodes();
        return (!vReachable.contains(joinFromPathNode));
    }

}
