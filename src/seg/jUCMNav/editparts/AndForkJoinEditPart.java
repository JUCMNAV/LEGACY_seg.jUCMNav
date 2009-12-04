package seg.jUCMNav.editparts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.LayerConstants;

import seg.jUCMNav.figures.anchors.AndForkJoinConnectionAnchor;
import seg.jUCMNav.figures.router.UCMConnectionRouter;
import ucm.map.AndFork;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * EditPart for AndJoins and AndForks. Manages connection anchors for these two elements.
 * 
 * Could have been done in the figure, but wanted to decouple model from figure.
 * 
 * @author jkealey
 * 
 */
public class AndForkJoinEditPart extends PathNodeEditPart implements AnchorListener {

    // although this is called extraAnchors; we're generating all the anchors in here.
    private HashMap extraAnchors;

    /**
     * @param model
     *            the AndFork/AndJoin
     * @param diagram
     *            its parent.
     */
    public AndForkJoinEditPart(PathNode model, UCMmap diagram) {
        super(model, diagram);
        extraAnchors = new HashMap();
    }

    public void activate() {
        getNodeFigure().getSourceConnectionAnchor().addAnchorListener(this);
        getNodeFigure().getTargetConnectionAnchor().addAnchorListener(this);

        super.activate();
    }

    public void deactivate() {
        getNodeFigure().getSourceConnectionAnchor().removeAnchorListener(this);
        getNodeFigure().getTargetConnectionAnchor().removeAnchorListener(this);

        super.deactivate();
    }

    /**
     * Refreshes all outgoing or incoming connections if the super classes's anchors are moved.
     */
    public void anchorMoved(ConnectionAnchor anchor) {

        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);

        // jk (later): I'm not sure why we are refreshing all the connections; might want to try to reduce the scope.
        if (cLayer.getConnectionRouter() instanceof UCMConnectionRouter)
            ((UCMConnectionRouter) cLayer.getConnectionRouter()).refreshConnections();

        List toRefresh;
        // and forks have their outputs rearranged; therefore the connection's source are rearranged.
        if (getModel() instanceof AndFork)
            toRefresh = getModelSourceConnections();
        else
            toRefresh = getModelTargetConnections();

        // when one anchor is moved, we must inform the others as we are using mutually exclusive locations.
        for (Iterator iter = toRefresh.iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            AndForkJoinConnectionAnchor anch = (getAnchor(element));
            if (anch != anchor)
                anch.ancestorMoved(getFigure());
        }

    }

    /**
     * Returns an anchor associated with this node connection.
     * 
     * @param nc
     *            the node connection
     * @return the anchor associated to this node connection.
     */
    private AndForkJoinConnectionAnchor getAnchor(NodeConnection nc) {
        if (extraAnchors.get(nc) == null)
            extraAnchors.put(nc, new AndForkJoinConnectionAnchor(getFigure(), nc, (PathNode) getModel()));

        return (AndForkJoinConnectionAnchor) extraAnchors.get(nc);

    }

    /**
     * @param connection
     *            for which to obtain the anchor
     * @return the anchor on the source side of the connection
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getAnchor((NodeConnection) connection.getModel());
    }

    /**
     * @param connection
     *            for which to obtain the anchor
     * @return the anchor on the target side of the connection
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getAnchor((NodeConnection) connection.getModel());
    }

}