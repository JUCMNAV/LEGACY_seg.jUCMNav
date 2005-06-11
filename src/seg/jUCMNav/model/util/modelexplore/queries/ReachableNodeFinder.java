/*
 * Created on 11-Jun-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Vector;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * @author jpdaigle
 * 
 * Query processor for finding reachable nodes given a start point in the graph.
 */
public class ReachableNodeFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    private Vector _visitedNodes;

    public ReachableNodeFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLENODES };
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        _visitedNodes = new Vector();

        if (((QFindReachableNodes) q).getStartPathNode() != null) {
            // call recursive function processNode with the start node
            processNode(((QFindReachableNodes) q).getStartPathNode());
        }

        // Return a response containing the visited node list
        RReachableNodes r = new RReachableNodes();
        r.setNodes(_visitedNodes);
        return r;
    }

    private void processNode(PathNode n) {
        // visit nodes, call on next, fill vector

        if (_visitedNodes.contains(n)) {
            return;
        } else {
            // visit this node!
            _visitedNodes.add(n);

            Vector toVisit = new Vector();

            EList links = n.getPred();
            for (int i = 0; i < links.size(); i++) {
                // add the connection's source to the list
                toVisit.add(((NodeConnection) links.get(i)).getSource());
            }

            links = n.getSucc();
            for (int i = 0; i < links.size(); i++) {
                // add the connection's target to the list
                toVisit.add(((NodeConnection) links.get(i)).getTarget());
            }

            // recursive call to process all nodes in the list to visit
            for (int i = 0; i < toVisit.size(); i++) {
                processNode((PathNode) toVisit.get(i));
            }
        }
    }

    public class QFindReachableNodes extends QueryRequest {
        // Finds reachable nodes from a PathNode
        PathNode _StartPathNode;

        public QFindReachableNodes(PathNode startNode) {
            this._queryType = QueryObject.FINDREACHABLENODES;
            _StartPathNode = startNode;
        }

        public PathNode getStartPathNode() {
            return _StartPathNode;
        }
    }

    public class RReachableNodes extends QueryResponse {
        /* Data structure (query response) for passing a vector of nodes */
        private Vector nodes;

        public RReachableNodes() {
            this._queryType = QueryObject.FINDREACHABLENODES;
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
