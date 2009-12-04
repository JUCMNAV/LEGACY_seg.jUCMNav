package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.WaitingPlace;

/**
 * Query processor for finding the next / previous responsibility on the path, including forks/joins.
 * 
 * One can define a set of node connections that must not be traversed to find additional node connections and a traversal direciton.
 * 
 * Behaves likes a ReachableNodeFinder, but stops at the next RespRef.
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class ResponsibilityFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    private Vector _visitedNodes;
    private Vector _visitedNodeConnections;

    // if true, stop at respref only. otherwise, stop at waiting place and timer too (in addition to start/end)
    private boolean bOnlyRespRef;
    private boolean bIncludeDirectionArrow;

    public ResponsibilityFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDRESPONSIBILITIES };
    }

    /**
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        _visitedNodes = new Vector();
        _visitedNodeConnections = new Vector();

        if (((QFindResponsibilities) q).getStartPathNode() != null) {
            // call recursive function processNode with the start node
            Set exclusions = ((QFindResponsibilities) q).getExclusionSet();
            this.bOnlyRespRef = ((QFindResponsibilities) q).getOnlyRespRef();
            this.bIncludeDirectionArrow = ((QFindResponsibilities) q).getIncludeDirectionArrows();
            if (exclusions == null)
                exclusions = Collections.EMPTY_SET;
            processNode(((QFindResponsibilities) q).getStartPathNode(), exclusions, ((QFindResponsibilities) q).getDirection());
        }

        // Return a response containing the visited node list
        RNextResponsibilities r = new RNextResponsibilities(this.bOnlyRespRef, this.bIncludeDirectionArrow);
        r.setNodes(_visitedNodes);
        r.setConnections(_visitedNodeConnections);
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

            // stop at resprefs, don't stop at first one !
            if ((n instanceof RespRef || (!this.bOnlyRespRef && (n instanceof WaitingPlace || n instanceof StartPoint || n instanceof EndPoint || (n instanceof DirectionArrow && bIncludeDirectionArrow))))
                    && _visitedNodes.size() > 1)
                return;

            Vector toVisit = new Vector();

            EList links = n.getPred();
            for (int i = 0; direction != QFindResponsibilities.DIRECTION_FORWARD && i < links.size(); i++) {
                // add the connection's source to the list
                PathNode node = (PathNode) ((NodeConnection) links.get(i)).getSource();
                if (!exclusions.contains(links.get(i)) && !(node instanceof Connect)) {
                    _visitedNodeConnections.add(links.get(i));
                    toVisit.add(node);
                }
            }

            links = n.getSucc();
            for (int i = 0; direction != QFindResponsibilities.DIRECTION_REVERSE && i < links.size(); i++) {
                // add the connection's target to the list
                PathNode node = (PathNode) ((NodeConnection) links.get(i)).getTarget();
                if (!exclusions.contains(links.get(i)) && !(node instanceof Connect)) {
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
     * Query to find reachable responsibilities (and possibily waiting places, timers, end points, and start points)
     * 
     * @author jkealey
     * 
     */
    public static class QFindResponsibilities extends QueryRequest {
        public static final int DIRECTION_BOTH = 0;
        public static final int DIRECTION_REVERSE = 1;
        public static final int DIRECTION_FORWARD = 2;
        // Finds reachable nodes from a PathNode
        private PathNode _StartPathNode;
        private Set _ExclusionSet;
        private int _Direction = 0;
        private boolean _onlyRespRef;
        private boolean _includeDirectionArrow;

        public QFindResponsibilities(PathNode startNode, boolean onlyRespRef, boolean includeDirectionArrow) {
            this._queryType = QueryObject.FINDRESPONSIBILITIES;
            _StartPathNode = startNode;
            _onlyRespRef = onlyRespRef;
            _includeDirectionArrow = includeDirectionArrow;
        }

        /**
         * 
         * @param startNode
         *            the starting point for traversal.
         * @param nodeConnectionExclusionSet
         *            A set of node connections that must not be traversed
         * @param direction
         *            the direction of traversal; both sides, following the directed graph or opposite the directed graph.
         * @param onlyRespRef
         *            if true, will only stop at respref; otherwise also stops at waiting place / timer.
         */
        public QFindResponsibilities(PathNode startNode, Set nodeConnectionExclusionSet, int direction, boolean onlyRespRef, boolean includeDirectionArrow) {
            this._queryType = QueryObject.FINDRESPONSIBILITIES;
            _StartPathNode = startNode;
            _ExclusionSet = nodeConnectionExclusionSet;
            _Direction = direction;
            _onlyRespRef = onlyRespRef;
            _includeDirectionArrow = includeDirectionArrow;
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

        public boolean getOnlyRespRef() {
            return _onlyRespRef;
        }

        public boolean getIncludeDirectionArrows() {
            return _includeDirectionArrow;
        }
    }

    /**
     * Response indicating the reached responsibilities (and possibily waiting places, timers, end points, and start points).
     * 
     * @author jkealey
     * 
     */
    public static class RNextResponsibilities extends QueryResponse {
        /* Data structure (query response) for passing a vector of nodes */
        private Vector nodes;
        private Vector connections;
        private boolean onlyRespRef;
        private boolean includeDirectionArrows;

        public RNextResponsibilities(boolean onlyRespRef, boolean includeDirectionArrows) {
            this._queryType = QueryObject.FINDRESPONSIBILITIES;
            this.onlyRespRef = onlyRespRef;
            this.includeDirectionArrows = includeDirectionArrows;
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
            this.nodes = new Vector();
            for (Iterator iter = nodes.iterator(); iter.hasNext();) {
                PathNode pn = (PathNode) iter.next();
                if (pn instanceof RespRef
                        || (!onlyRespRef && (pn instanceof WaitingPlace || pn instanceof EndPoint || pn instanceof StartPoint || (pn instanceof DirectionArrow && includeDirectionArrows))))
                    this.nodes.add(pn);

            }
        }

        public Vector getConnections() {
            return connections;
        }

        public void setConnections(Vector connections) {
            this.connections = connections;
        }
    }

}