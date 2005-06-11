/*
 * Created on 31-May-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore.queries;

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
 * @author jpdaigle
 *  
 */
public class EndPointFinder extends AbstractQueryProcessor implements IQueryProcessorChain {
    public EndPointFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLEENDPOINTS };
    }

    public QueryResponse runImpl(QueryRequest qr) {
        PathNode startNode = ((QFindReachableEndPoints) qr).getStartPathNode();
        ReachableNodeFinder.QFindReachableNodes qrn = new ReachableNodeFinder().new QFindReachableNodes(startNode);
        ReachableNodeFinder.RReachableNodes reachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer
                .getInstance().run(qrn);

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

    public class QFindReachableEndPoints extends QueryRequest {
        // Finds reachable start points from a PathNode
        PathNode _StartPathNode;

        public QFindReachableEndPoints(PathNode startPathNode) {
            this._queryType = QueryObject.FINDREACHABLEENDPOINTS;
            _StartPathNode = startPathNode;
        }

        public PathNode getStartPathNode() {
            return _StartPathNode;
        }

    }

    public class RReachableEndPoints extends QueryResponse {
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
