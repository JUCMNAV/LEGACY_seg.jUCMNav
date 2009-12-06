package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Collections;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * Query processor for finding reachable nodes given a start point in the graph.
 * 
 * One can define a set of node connections that must not be traversed to find additional node connections and a traversal direciton.
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class ReachableNodeFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    private Vector _visitedNodes;
    private Vector _visitedNodeConnections;
    private boolean _allowConnect;
    
    public ReachableNodeFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLENODES };
    }

    /**
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        _visitedNodes = new Vector();
        _visitedNodeConnections = new Vector();
        if (((QFindReachableNodes) q).getStartPathNode() != null) {
            _allowConnect = ((QFindReachableNodes) q).allowConnect();
            
            // call recursive function processNode with the start node
            Set exclusions = ((QFindReachableNodes) q).getExclusionSet();
            if (exclusions == null)
                exclusions = Collections.EMPTY_SET;
            processNode(((QFindReachableNodes) q).getStartPathNode(), exclusions, ((QFindReachableNodes) q).getDirection());
        }

        // Return a response containing the visited node list
        RReachableNodes r = new RReachableNodes();
        r.setNodes(_visitedNodes);
        r.setConnections(_visitedNodeConnections);
        
        _visitedNodeConnections=null;
        _visitedNodes=null;
        return r;
    }

    /**
     * 
     * @param n
     *            the starting point for traversal.
     * @param exclusions
     *            A set of node connections that must not be traversed
     * @param direction
     *            the direction of traversal; both sides, following the directed graph or opposite the directed graph.
     */
    private void processNode(PathNode n, Set exclusions, int direction) {
        // visit nodes, call on next, fill vector

        if (_visitedNodes.contains(n)) {
            return;
        } else {
            // visit this node!
            _visitedNodes.add(n);

            Vector toVisit = new Vector();

            EList links = n.getPred();
            for (int i = 0; direction != QFindReachableNodes.DIRECTION_FORWARD && i < links.size(); i++) {
                // add the connection's source to the list
                PathNode node = (PathNode) ((NodeConnection) links.get(i)).getSource();
                if (!exclusions.contains(links.get(i)) && (_allowConnect || !(node instanceof Connect))) {
                    _visitedNodeConnections.add(links.get(i));
                    toVisit.add(node);
                }
            }

            links = n.getSucc();
            for (int i = 0; direction != QFindReachableNodes.DIRECTION_REVERSE && i < links.size(); i++) {
                // add the connection's target to the list
                PathNode node = (PathNode) ((NodeConnection) links.get(i)).getTarget();
                if (!exclusions.contains(links.get(i)) && (_allowConnect || !(node instanceof Connect))) {
                    _visitedNodeConnections.add(links.get(i));
                    toVisit.add(node);
                }
            }

            // recursive call to process all nodes in the list to visit
            for (int i = 0; i < toVisit.size(); i++) {
                processNode((PathNode) toVisit.get(i), exclusions, direction);
            }
        }
    }

    /**
     * Query to find the reachable nodes.
     * 
     * @author jkealey
     * 
     */
    public static class QFindReachableNodes extends QueryRequest {
        public static final int DIRECTION_BOTH = 0;
        public static final int DIRECTION_REVERSE = 1;
        public static final int DIRECTION_FORWARD = 2;
        // Finds reachable nodes from a PathNode
        private PathNode _StartPathNode;
        private Set _ExclusionSet;
        private int _Direction = 0;
        private boolean _allowConnect=false;

        public QFindReachableNodes(PathNode startNode) {
            this._queryType = QueryObject.FINDREACHABLENODES;
            _StartPathNode = startNode;
        }

        /**
         * 
         * @param startNode
         *            the starting point for traversal.
         * @param nodeConnectionExclusionSet
         *            A set of node connections that must not be traversed
         * @param direction
         *            the direction of traversal; both sides, following the directed graph or opposite the directed graph.
         */
        public QFindReachableNodes(PathNode startNode, Set nodeConnectionExclusionSet, int direction) {
            this._queryType = QueryObject.FINDREACHABLENODES;
            _StartPathNode = startNode;
            _ExclusionSet = nodeConnectionExclusionSet;
            _Direction = direction;
        }
        
        /**
         * 
         * @param startNode
         *            the starting point for traversal.
         * @param nodeConnectionExclusionSet
         *            A set of node connections that must not be traversed
         * @param direction
         *            the direction of traversal; both sides, following the directed graph or opposite the directed graph.
         * @param allowConnect
         *         allow traversing through connect elements?            
         */
        public QFindReachableNodes(PathNode startNode, Set nodeConnectionExclusionSet, int direction, boolean allowConnect) {
            this._queryType = QueryObject.FINDREACHABLENODES;
            _StartPathNode = startNode;
            _ExclusionSet = nodeConnectionExclusionSet;
            _Direction = direction;
            _allowConnect = allowConnect;
        }        

        public PathNode getStartPathNode() {
            return _StartPathNode;
        }

        public Set getExclusionSet() {
            return _ExclusionSet;
        }

        public int getDirection() {
            return _Direction;
        }
        
        public boolean allowConnect()
        {
            return _allowConnect;
        }
    }

    /**
     * Response that indicates the reached nodes and node connections.
     * 
     * @author jkealey
     * 
     */
    public static class RReachableNodes extends QueryResponse {
        /* Data structure (query response) for passing a vector of nodes */
        private Vector nodes;
        private Vector connections;

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

        public Vector getConnections() {
            return connections;
        }

        public void setConnections(Vector connections) {
            this.connections = connections;
        }
    }

}