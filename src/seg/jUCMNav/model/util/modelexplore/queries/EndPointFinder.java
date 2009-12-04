package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Set;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.EndPoint;
import ucm.map.PathNode;

/**
 * Given a PathNode on a certain set of splines, find all reachable end points. Note that this returns EndPoints only, not Stubs that behave as end points.
 * 
 * If modifications are made here, might need to be made in StartPointFinder as well.
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class EndPointFinder extends AbstractQueryProcessor implements IQueryProcessorChain {
    public EndPointFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLEENDPOINTS };
    }

    public QueryResponse runImpl(QueryRequest qr) {
        PathNode startNode = ((QFindReachableEndPoints) qr).getStartPathNode();
        Set exclusions = ((QFindReachableEndPoints) qr).getExclusions();
        int direction = ((QFindReachableEndPoints) qr).getDirection();

        ReachableNodeFinder.QFindReachableNodes qrn = new ReachableNodeFinder.QFindReachableNodes(startNode, exclusions, direction);
        ReachableNodeFinder.RReachableNodes reachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qrn);

        // extract vector of all reachable nodes
        Vector vReachableNodes = reachableNodes.getNodes();

        // extract StartPoints from this
        Vector vEndPoints = new Vector();
        for (int i = 0; i < vReachableNodes.size(); i++) {
            PathNode node = (PathNode) vReachableNodes.get(i);
            if (node instanceof EndPoint)
                vEndPoints.add(node);
        }

        // Return a response containing the node list
        RReachableEndPoints r = new RReachableEndPoints();
        r.setNodes(vEndPoints);
        return r;
    }

    /**
     * Request to find the reachable end points.
     * 
     * @author jkealey
     * 
     */
    public static class QFindReachableEndPoints extends QueryRequest {
        // Finds reachable start points from a PathNode
        PathNode _StartPathNode;
        Set _exclusions;
        int _direction;

        public QFindReachableEndPoints(PathNode startPathNode, Set exclusions, int direction) {
            this._queryType = QueryObject.FINDREACHABLEENDPOINTS;
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
     * Response that indicates which end points were reached.
     * 
     * @author jkealey
     * 
     */
    public static class RReachableEndPoints extends QueryResponse {
        /* Data structure (query response) for passing a vector of nodes */
        private Vector nodes;

        public RReachableEndPoints() {
            this._queryType = QueryObject.FINDREACHABLEENDPOINTS;
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