package seg.jUCMNav.figures;

import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import ucm.map.NodeConnection;

/**
 * This figure represent a connection between two path nodes.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class SplineConnection extends PolylineConnection {
    /**
     * This figure contains a final reference to the link it represent because the connection router needs this reference. The link is never modified since it's
     * final.
     */
    private final NodeConnection link;

    /**
     * 
     * @param link
     *            the node connection
     */
    public SplineConnection(NodeConnection link) {
        super();
        setAntialias(GeneralPreferencePage.getAntialiasingPref()); // recommended by @pushmatrix :)
        this.link = link;
    }

    /**
     * @return Returns the link.
     */
    public NodeConnection getLink() {
        return link;
    }

    /**
     * Overridden to add a check to avoid crashing when spline has not yet been routed and thus has no points.
     * 
     * Lays out this Figure using its {@link LayoutManager}.
     * 
     * @since 2.0
     */
    public void layout() {
        if (getSourceAnchor() != null && getTargetAnchor() != null)
            getConnectionRouter().route(this);

        Rectangle oldBounds = bounds;
        if (this.getPoints().size() > 0)
            super.layout();
        bounds = null;

        if (!getBounds().contains(oldBounds)) {
            getParent().translateToParent(oldBounds);
            getUpdateManager().addDirtyRegion(getParent(), oldBounds);
        }

        repaint();
        fireFigureMoved(); // Used to be fireMoved(), now deprecated
    }
}