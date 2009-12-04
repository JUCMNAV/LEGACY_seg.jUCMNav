package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.Stub;
import ucm.map.Timer;

/**
 * Special kind of ConnectionSplineFinder with more path stoppers. Used to determine deletion paths.
 * 
 * @author jkealey
 * 
 */
public class DeletionPathFinder extends ConnectionSplineFinder {

    public DeletionPathFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDDELETIONPATH };
    }

    /**
     * Subclassed to return response subclass.
     * 
     * @return returns the query response using the _splinePath.
     */
    protected QueryResponse getResponse(Vector _splinePath) {
        // Return a response containing the visited node list
        RSpline r = new RSpline();
        r.setConnections(_splinePath);
        return r;
    }

    /**
     * Deletion path stoppers are same as parent + OrForks/OrJoins and stubs.
     * 
     * @param node
     *            the node to evaluate
     * @return true if path traversal should be stopped when hitting one of this node.
     */
    public boolean isPathStopper(PathNode node) {
        return super.isPathStopper(node) || (node instanceof OrFork) || (node instanceof OrJoin) || (node instanceof Stub)
                || (node instanceof Timer && node.getSucc().size() > 1);
    }

    /**
     * 
     * Must subclass inner class; same behaviour has superclass with different querytype.
     * 
     * @author jkealey
     * 
     */
    public class QFindSpline extends ConnectionSplineFinder.QFindSpline {

        /**
         * 
         * @param nodeConnection
         *            the connection to start the traversal
         */
        public QFindSpline(NodeConnection nodeConnection) {
            super(nodeConnection);
            this._queryType = QueryObject.FINDDELETIONPATH;
        }

    }

    /**
     * 
     * QueryResponse for deletion paths. Same as superclass but can also return the path as a list of nodes.
     * 
     * @author jkealey
     * 
     */
    public class RSpline extends ConnectionSplineFinder.RSpline {

        public RSpline() {
            this._queryType = QueryObject.FINDDELETIONPATH;
        }

        /**
         * 
         * @return the nodes on path
         */
        public Vector getPathNodes() {
            Vector v = new Vector();

            NodeConnection nc = null;
            for (Iterator iter = getConnections().iterator(); iter.hasNext();) {
                nc = (NodeConnection) iter.next();
                v.add(nc.getSource());
            }
            if (nc != null)
                v.add(nc.getTarget());

            return v;
        }

    }

}