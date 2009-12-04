package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Set;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Given a PathNode on a certain set of splines, find all reachable start points. Note that this returns StartPoints only, not Stubs that behave as start
 * points.
 * 
 * If modifications are made here, might need to be made in EndPointFinder as well.
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class StartPointFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    public StartPointFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLESTARTPOINTS };
    }

    public QueryResponse runImpl(QueryRequest qr) {
        PathNode startNode = ((QFindReachableStartPoints) qr).getStartPathNode();
        Set exclusions = ((QFindReachableStartPoints) qr).getExclusions();
        int direction = ((QFindReachableStartPoints) qr).getDirection();

        ReachableNodeFinder.QFindReachableNodes qrn = new ReachableNodeFinder.QFindReachableNodes(startNode, exclusions, direction);
        ReachableNodeFinder.RReachableNodes reachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qrn);

        // extract vector of all reachable nodes
        Vector vReachableNodes = reachableNodes.getNodes();

        // extract StartPoints from this
        Vector vStartPoints = new Vector();
        for (int i = 0; i < vReachableNodes.size(); i++) {
            PathNode node = (PathNode) vReachableNodes.get(i);
            if (node instanceof StartPoint)
                vStartPoints.add(node);
        }

        // Return a response containing the node list
        RReachableStartPoints r = new RReachableStartPoints();
        r.setNodes(vStartPoints);
        return r;
    }

    /**
     * Query to find all reachable start points.
     * 
     * @author jkealey
     * 
     */
    public static class QFindReachableStartPoints extends QueryRequest {
        // Finds reachable start points from a PathNode
        PathNode _StartPathNode;
        Set _exclusions;
        int _direction;

        public QFindReachableStartPoints(PathNode startPathNode, Set exclusions, int direction) {
            this._queryType = QueryObject.FINDREACHABLESTARTPOINTS;
            _StartPathNode = startPathNode;
            _exclusions = exclusions;
            _direction = direction;
        }

        public PathNode getStartPathNode() {
            return _StartPathNode;
        }

        public int getDirection() {
            return _direction;
        }

        public Set getExclusions() {
            return _exclusions;
        }

    }

    /**
     * Response indicating which end points were reached.
     * 
     * @author jkealey
     * 
     */
    public static class RReachableStartPoints extends QueryResponse {
        /* Data structure (query response) for passing a vector of nodes */
        private Vector nodes;

        public RReachableStartPoints() {
            this._queryType = QueryObject.FINDREACHABLESTARTPOINTS;
        }

        /**
         * @return Returns the nodes.
         */
        public Vector getNodes() {
            return nodes;
        }

        /**
         * @param nodes
         *            The nodes to set.
         */
        public void setNodes(Vector nodes) {
            this.nodes = nodes;
        }

    }
}