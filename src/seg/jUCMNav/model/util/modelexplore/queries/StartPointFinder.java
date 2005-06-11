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
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * @author jpdaigle
 *  
 */
public class StartPointFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    public StartPointFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLESTARTPOINTS };
    }

    public QueryResponse runImpl(QueryRequest qr) {
        PathNode startNode = ((QFindReachableStartPoints) qr).getStartPathNode();
        ReachableNodeFinder.QFindReachableNodes qrn = new ReachableNodeFinder().new QFindReachableNodes(startNode);
        ReachableNodeFinder.RReachableNodes reachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer
                .getInstance().run(qrn);

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

    public class QFindReachableStartPoints extends QueryRequest {
        // Finds reachable start points from a PathNode
        PathNode _StartPathNode;

        public QFindReachableStartPoints(PathNode startPathNode) {
            this._queryType = QueryObject.FINDREACHABLESTARTPOINTS;
            _StartPathNode = startPathNode;
        }

        public PathNode getStartPathNode() {
            return _StartPathNode;
        }

    }

    public class RReachableStartPoints extends QueryResponse {
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
