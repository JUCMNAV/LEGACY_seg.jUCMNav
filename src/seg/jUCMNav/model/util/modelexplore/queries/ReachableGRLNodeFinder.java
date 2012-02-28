package seg.jUCMNav.model.util.modelexplore.queries;

import grl.GRLNode;

import java.util.Collections;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import urncore.IURNConnection;
import urncore.IURNNode;

/**
 * Query processor for finding reachable nodes given a start point in the graph.
 * 
 * One can define a set of node connections that must not be traversed to find additional node connections and a traversal direciton.
 * 
 * @author etremblay
 * 
 */
public class ReachableGRLNodeFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    private Vector _visitedNodes;
    private Vector _visitedNodeConnections;
    
    public ReachableGRLNodeFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDREACHABLEGRLNODES };
    }

    /**
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        _visitedNodes = new Vector();
        _visitedNodeConnections = new Vector();
        if (((QFindReachableNodes) q).getStartGRLNode() != null) {
            
            // call recursive function processNode with the start node
            Set exclusions = ((QFindReachableNodes) q).getExclusionSet();
            if (exclusions == null)
                exclusions = Collections.EMPTY_SET;
            processNode(((QFindReachableNodes) q).getStartGRLNode(), exclusions, ((QFindReachableNodes) q).getDirection());
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
    private void processNode(IURNNode n, Set exclusions, int direction) {
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
                IURNNode node = (IURNNode) ((IURNConnection) links.get(i)).getSource();
                if (!exclusions.contains(links.get(i))) {
                    _visitedNodeConnections.add(links.get(i));
                    toVisit.add(node);
                }
            }

            links = n.getSucc();
            for (int i = 0; direction != QFindReachableNodes.DIRECTION_REVERSE && i < links.size(); i++) {
                // add the connection's target to the list
                IURNNode node = (IURNNode) ((IURNConnection) links.get(i)).getTarget();
                if (!exclusions.contains(links.get(i))) {
                    _visitedNodeConnections.add(links.get(i));
                    toVisit.add(node);
                }
            }

            // recursive call to process all nodes in the list to visit
            for (int i = 0; i < toVisit.size(); i++) {
                processNode((IURNNode) toVisit.get(i), exclusions, direction);
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
        // Finds reachable nodes from a GRLNode
        private GRLNode _StartGRLNode;
        private Set _ExclusionSet;
        private int _Direction = 0;

        public QFindReachableNodes(GRLNode startNode) {
            this._queryType = QueryObject.FINDREACHABLEGRLNODES;
            _StartGRLNode = startNode;
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
        public QFindReachableNodes(GRLNode startNode, Set nodeConnectionExclusionSet, int direction) {
            this._queryType = QueryObject.FINDREACHABLEGRLNODES;
            _StartGRLNode = startNode;
            _ExclusionSet = nodeConnectionExclusionSet;
            _Direction = direction;
        }      

        public GRLNode getStartGRLNode() {
            return _StartGRLNode;
        }

        public Set getExclusionSet() {
            return _ExclusionSet;
        }

        public int getDirection() {
            return _Direction;
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
            this._queryType = QueryObject.FINDREACHABLEGRLNODES;
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