package seg.jUCMNav.model.util.modelexplore.queries;

import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.model.util.modelexplore.QueryObject;
import seg.jUCMNav.model.util.modelexplore.QueryResponse;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.Stub;

/**
 * Created on 21-Jun-2005
 * 
 * @author jkealey
 *  
 */
public class DeletionPathFinder extends ConnectionSplineFinder {

    /**
     *  
     */
    public DeletionPathFinder() {
        this._answerQueryTypes = new String[] { QueryObject.FINDDELETIONPATH };
    }

    /**
     * Subclassed to return response subclass.
     * 
     * @return returns the query response using the _splinePath.
     */
    protected QueryResponse getResponse() {
        // Return a response containing the visited node list
        RSpline r = new RSpline();
        r.setConnections(_splinePath);
        return r;
    }

    /**
     * @param node
     * @return returns true if path traversal should be stopped when hitting one of this node.
     */
    public boolean isPathStopper(PathNode node) {
        return (node instanceof Connect) || (node instanceof AndFork) || (node instanceof AndJoin) || (node instanceof OrFork) || (node instanceof OrJoin)
                || (node instanceof Stub);
    }

    public class QFindSpline extends ConnectionSplineFinder.QFindSpline {

        public QFindSpline(NodeConnection nodeConnection) {
            super(nodeConnection);
            this._queryType = QueryObject.FINDDELETIONPATH;
        }

    }

    public class RSpline extends ConnectionSplineFinder.RSpline {

        public RSpline() {
            this._queryType = QueryObject.FINDDELETIONPATH;
        }

        /**
         * 
         * @return Returns the nodes on path
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