package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Stack;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor;
import seg.jUCMNav.model.util.modelexplore.IQueryProcessorChain;
import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryRequest;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * Query processor for returning all node connections in a spline, given a node connection on this spline.
 * 
 * @author jkealey
 */
public class ConnectionSplineFinder extends AbstractQueryProcessor implements IQueryProcessorChain {

    public ConnectionSplineFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDSPLINE };
    }

    /**
     * @see seg.jUCMNav.model.util.modelexplore.AbstractQueryProcessor#runImpl(seg.jUCMNav.model.util.modelexplore.QueryRequest)
     */
    public QueryResponse runImpl(QueryRequest q) {
        Vector _splinePath = new Vector();

        if (((QFindSpline) q).getStartNodeConnection() != null) {
            // call recursive function processNode with the start node
            processNodeConnection(_splinePath, ((QFindSpline) q).getStartNodeConnection());
        }

        return getResponse(_splinePath);
    }

    /**
     * Subclassed to return inner class of subclass.
     * 
     * @return A response containing the visited node list.
     */
    protected QueryResponse getResponse(Vector _splinePath) {
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
     *            the node connection to start with
     */
    protected void processNodeConnection(Vector _splinePath, NodeConnection n) {
        // System.out.println("starts with: " + n.getSource());
        // System.out.println("ends with: " + n.getTarget());

        if (n.getTarget() instanceof Connect || n.getSource() instanceof Connect)
            return;
        PathNode source = (PathNode) n.getSource();
        PathNode target = (PathNode) n.getTarget();
        NodeConnection nc = n;

        if (source == null || target == null)
            return;

        Stack s = new Stack();
        // navigate back to the start
        while (nc != null && source != null) {
            // add the connection
            s.push(nc);

            // if we are adding a node connection connected to an and fork/join, stop processing further.
            if (isPathStopper((PathNode) n.getSource()))
                break;

            // if we can continue further
            if (source.getSucc().indexOf(nc) == 0 && source.getPred().size() > 0) {
                nc = (NodeConnection) source.getPred().get(0);
                source = (PathNode) nc.getSource();

                // prevent infinite loops
                if (s.contains(nc) || isPathStopper(source)) {
                    // if we are adding a node connection connected to an and fork/join, stop processing further but save the connection.
                    if (!s.contains(nc))
                        s.push(nc);
                    nc = null;
                }
            } else
                nc = null;
        }

        assert s.contains(n) : "missing initial connection"; //$NON-NLS-1$

        // we went through the spline in the inverse direction so we need to insert them in the opposite order using the stack
        while (s.size() > 0) {
            _splinePath.add(s.pop());
        }

        nc = n;
        while (nc != null && target != null && !isPathStopper(target)) {
            // adding the connection
            //
            // checking because we don't want to add the middle one twice
            if (!_splinePath.contains(nc))
                _splinePath.add(nc);

            // if we can continue further
            if (target.getPred().indexOf(nc) == 0 && target.getSucc().size() > 0) {
                nc = (NodeConnection) target.getSucc().get(0);
                target = (PathNode) nc.getTarget();
                // prevent infinite loops
                if (_splinePath.contains(nc) || isPathStopper(target)) {
                    // if we are adding a node connection connected to an and fork/join, stop processing further but save the connection.
                    if (!_splinePath.contains(nc))
                        _splinePath.add(nc);
                    nc = null;
                }
            } else
                nc = null;

        }

        // we have inconsistencies between different path stoppers. we want to include the NC going into an AndFork or AndJoin but not a Connect. Get rid of any
        // connect related elements.
        nc = (NodeConnection) _splinePath.firstElement();
        if (nc.getSource() instanceof Connect)
            _splinePath.remove(nc);

        nc = (NodeConnection) _splinePath.lastElement();
        if (nc.getTarget() instanceof Connect)
            _splinePath.remove(nc);

        // Debugging
        // System.out.println("**");
        // System.out.println("\t" + n.getSource() + "\n\t" + n.getTarget());
        // for (Iterator iter = _splinePath.iterator(); iter.hasNext();) {
        // NodeConnection elem = (NodeConnection) iter.next();
        // System.out.println(elem.getSource() + "\n" + elem.getTarget());
        //
        // }
    }

    /**
     * Connects/AndForks/AndJoins are path stoppers for regular splines.
     * 
     * @param node
     *            the node to check
     * @return true if path traversal should be stopped when hitting one of this node.
     */
    public boolean isPathStopper(PathNode node) {
        return (node instanceof Connect || node instanceof AndFork || node instanceof AndJoin);
    }

    /**
     * 
     * QueryRequest to find a spline, starting with a node connection.
     * 
     * @author jkealey
     * 
     */
    public class QFindSpline extends QueryRequest {
        // Finds reachable node connections starting with a NodeConnection
        NodeConnection _StartNodeConnection;

        /**
         * 
         * @param nodeConnection
         *            the starting point for the traversal
         */
        public QFindSpline(NodeConnection nodeConnection) {
            this._queryType = QueryObject.FINDSPLINE;
            _StartNodeConnection = nodeConnection;
        }

        /**
         * 
         * @return the starting point for the starting point.
         */
        public NodeConnection getStartNodeConnection() {
            return _StartNodeConnection;
        }
    }

    /**
     * 
     * QueryResponse containing a list of connections on a certain spline.
     * 
     * @author jkealey
     * 
     */
    public class RSpline extends QueryResponse {
        /* Data structure (query response) for passing a vector of connections */
        protected Vector connections;

        public RSpline() {
            this._queryType = QueryObject.FINDSPLINE;
        }

        /**
         * @return Returns the node connections.
         */
        public Vector getConnections() {
            return connections;
        }

        /**
         * @param connections
         *            The nodes connections to set.
         */
        public void setConnections(Vector connections) {
            this.connections = connections;
        }
    }

}