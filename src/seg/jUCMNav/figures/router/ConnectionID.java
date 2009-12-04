package seg.jUCMNav.figures.router;

import seg.jUCMNav.figures.SplineConnection;
import ucm.map.PathNode;

/**
 * A class representing a connection identifier used by the UCMConnectionRouter. Basically the set of a SplineConnection between two PathNodes.
 * 
 * equals() and hashCode() are overridden to allow the ID to be used in HashMaps and other collections.
 * 
 * @author jkealey
 * 
 */
public class ConnectionID {

    // the id's elements.
    private SplineConnection connection;
    private PathNode source, target;

    /**
     * 
     * @param connection
     *            The spline connection figure
     * @param source
     *            The connection's source PathNode
     * @param target
     *            The connectoin's target PathNode
     */
    public ConnectionID(SplineConnection connection, PathNode source, PathNode target) {
        this.connection = connection;
        this.source = source;
        this.target = target;
    }

    /**
     * Overridden to allow collections to detect changes properly.
     */
    public int hashCode() {
        return connection.hashCode() + source.hashCode() + target.hashCode();
    }

    /**
     * Overridden to allow appropriate comparisons.
     * 
     * @param obj
     * @return boolean indicating equality
     */
    public boolean equals(Object obj) {
        if (obj instanceof ConnectionID) {
            ConnectionID prop = (ConnectionID) obj;
            return prop.getConnection().equals(connection) && prop.getSource() == source && prop.getTarget() == target;
        } else
            return false;
    }

    /**
     * 
     * @return the connection
     */
    public SplineConnection getConnection() {
        return connection;
    }

    /**
     * 
     * @param connection
     *            the connection
     */
    public void setConnection(SplineConnection connection) {
        this.connection = connection;
    }

    /**
     * 
     * @return the source PathNode
     */
    public PathNode getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *            the source PathNode
     */
    public void setSource(PathNode source) {
        this.source = source;
    }

    /**
     * 
     * @return the target PathNode
     */
    public PathNode getTarget() {
        return target;
    }

    /**
     * 
     * @param target
     *            the target PathNode
     */
    public void setTarget(PathNode target) {
        this.target = target;
    }
}