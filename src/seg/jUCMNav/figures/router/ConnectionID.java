package seg.jUCMNav.figures.router;

import seg.jUCMNav.figures.SplineConnection;
import ucm.map.PathNode;

public class ConnectionID {

    private SplineConnection connection;
    private PathNode source, target;

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

    public SplineConnection getConnection() {
        return connection;
    }

    public void setConnection(SplineConnection connection) {
        this.connection = connection;
    }

    public PathNode getSource() {
        return source;
    }

    public void setSource(PathNode source) {
        this.source = source;
    }

    public PathNode getTarget() {
        return target;
    }

    public void setTarget(PathNode target) {
        this.target = target;
    }
}