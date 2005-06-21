/*
 * Created on 20-Jun-2005
 *
 */
package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Stack;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * @author jkealey
 * 
 * Query processor for returning all node connections in a spline, given a node connection on this spline.
 *  
 */
public class ConnectionSplineFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    private Vector _splinePath;

    public ConnectionSplineFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDSPLINE };
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        _splinePath = new Vector();

        if (((QFindSpline) q).getStartNodeConnection() != null) {
            // call recursive function processNode with the start node
            processNodeConnection(((QFindSpline) q).getStartNodeConnection());
        }

        // Return a response containing the visited node list
        RSpline r = new RSpline();
        r.setConnections(_splinePath);
        return r;
    }

    /**
     * We want to return an ordered sequence of NodeConnections that form a spline, containing n.
     * 
     * We route through simple nodes (1 in / 1 out) and PathNodes that might have connections / timeout paths. We don't investigate node connections that are
     * connected to Connect elements, as they have no visual representation.
     * 
     * @param n
     */
    private void processNodeConnection(NodeConnection n) {
        //        System.out.println("starts with: " + n.getSource());
        //        System.out.println("ends with: " + n.getTarget());

        if (n.getTarget() instanceof Connect || n.getSource() instanceof Connect)
            return;
        PathNode source = n.getSource();
        PathNode target = n.getTarget();
        NodeConnection nc = n;

        if (source == null || target == null)
            return;

        Stack s = new Stack();
        // navigate back to the start
        while (nc != null && source != null) {
            // add the connection
            s.push(nc);
            // if we can continue further
            if (source.getSucc().indexOf(nc) == 0 && source.getPred().size() > 0) {
                nc = (NodeConnection) source.getPred().get(0);
                source = nc.getSource();

                // prevent infinite loops
                if (s.contains(nc) || (source instanceof Connect))
                    nc = null;
            } else
                nc = null;
        }

        assert s.contains(n) : "missing initial connection";

        // we went through the spline in the inverse direction so we need to insert them in the opposite order using the stack
        while (s.size() > 0) {
            _splinePath.add(s.pop());
        }

        nc = n;

        while (nc != null && target != null) {

            // adding the connection
            //
            // checking because we don't want to add the middle one twice
            if (!_splinePath.contains(nc))
                _splinePath.add(nc);

            //if we can continue further
            if (target.getPred().indexOf(nc) == 0 && target.getSucc().size() > 0) {
                nc = (NodeConnection) target.getSucc().get(0);
                target = nc.getTarget();
                // prevent infinite loops
                if (_splinePath.contains(nc) || (target instanceof Connect))
                    nc = null;
            } else
                nc = null;

        }
        /*
         * while (target != null && target.getPred().indexOf(nc) == 0 && target.getSucc().size() > 0 && !(target instanceof Connect)) { if
         * (!_splinePath.contains(nc)) _splinePath.add(nc); nc = (NodeConnection) target.getSucc().get(0); target = nc.getTarget(); }
         * 
         * if (target != null && !(target instanceof Connect) && !_splinePath.contains(nc)) _splinePath.add(nc);
         */
        //        // build traversable sequence of node connections recursively, starting with n
        //        if (_visitedConnections.contains(n) || n.getTarget() instanceof Connect || n.getSource() instanceof Connect) {
        //            return;
        //        } else {
        //            _visitedConnections.add(n);
        //            if (n.getTarget() != null && n.getSource() != null) {
        //
        //                // add predecessors
        //                if (n.getTarget().getSucc().size() == 1 && n.getSource().getPred().size() == 1) {
        //                    //straight route
        //                    processNodeConnection((NodeConnection) n.getSource().getPred().get(0));
        //                } else if (n.getTarget().getSucc().size() == 1 && n.getSource().getPred().size() > 1 && n.getSource().getSucc().indexOf(n) == 0
        //                        && (n.getSource() instanceof EmptyPoint || n.getSource() instanceof WaitingPlace || n.getSource() instanceof Stub)) {
        //                    // could have connects, route through anyways
        //// processNodeConnection((NodeConnection) n.getSource().getPred().get(0));
        //                }
        //
        //                // visit this connection!
        //                // might have been modified recursively before
        //                if (!_splinePath.contains(n))
        //                    _splinePath.add(n);
        //
        //                // add successors
        //                if (n.getTarget().getSucc().size() == 1 && n.getSource().getPred().size() == 1) {
        //                    // straight route
        //                    processNodeConnection((NodeConnection) n.getTarget().getSucc().get(0));
        //                } else if (n.getSource().getPred().size() == 1 && n.getTarget().getSucc().size() > 1 && n.getTarget().getPred().indexOf(n) == 0
        //                        && (n.getTarget() instanceof EmptyPoint || n.getTarget() instanceof WaitingPlace || n.getTarget() instanceof Stub)) {
        //                    // could have connects or timeout paths, route through anyways
        //// processNodeConnection((NodeConnection) n.getTarget().getSucc().get(0));
        //                }
        //            }
        //        }
    }

    public class QFindSpline extends QueryRequest {
        // Finds reachable node connections starting with a NodeConnection
        NodeConnection _StartNodeConnection;

        public QFindSpline(NodeConnection nodeConnection) {
            this._queryType = QueryObject.FINDSPLINE;
            _StartNodeConnection = nodeConnection;
        }

        public NodeConnection getStartNodeConnection() {
            return _StartNodeConnection;
        }
    }

    public class RSpline extends QueryResponse {
        /* Data structure (query response) for passing a vector of connections */
        private Vector connections;

        public RSpline() {
            this._queryType = QueryObject.FINDSPLINE;
        }

        /**
         * @return Returns the nodes.
         */
        public Vector getConnections() {
            return connections;
        }

        /**
         * @param nodes
         *            The nodes to set.
         */
        public void setConnections(Vector connections) {
            this.connections = connections;
        }
    }

}