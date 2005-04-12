package seg.jUCMNav.figures;

import org.eclipse.draw2d.PolylineConnection;

import ucm.map.NodeConnection;

/**
 * Created 2005-03-02
 * 
 * This figure represent a connection between two path nodes.
 * 
 * @author Etienne Tremblay
 */
public class SplineConnection extends PolylineConnection {
    /** This figure contains a final reference to the link it represent because the connection router needs this reference. 
     * The link is never modified since it's final.
     */
    private final NodeConnection link;
    
    public SplineConnection(NodeConnection link){
    	super();
        this.link = link;
    }

    /**
     * @return Returns the link.
     */
    public NodeConnection getLink() {
        return link;
    }
}