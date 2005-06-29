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
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * EditPart for AndJoins and AndForks. Manages connection anchors for these two elements.
 * 
 * Could have been done in the figure, but wanted to decouple model from figure.
 * 
 * @author jkealey
 *  
 */
public class AndForkJoinEditPart extends PathNodeEditPart implements AnchorListener {

    HashMap extraAnchors;

    /**
     * @param model
     * @param diagram
     */
    public AndForkJoinEditPart(PathNode model, PathGraph diagram) {
        super(model, diagram);
        extraAnchors = new HashMap();
        getNodeFigure().getSourceConnectionAnchor().addAnchorListener(this);
        getNodeFigure().getTargetConnectionAnchor().addAnchorListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.PathNodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getAnchor((NodeConnection) connection.getModel());
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.PathNodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getAnchor((NodeConnection) connection.getModel());
    }

    /**
     * 
     * @param nc
     * @return the anchor associated to this node connection.
     */
    private AndForkJoinConnectionAnchor getAnchor(NodeConnection nc) {
        if (extraAnchors.get(nc) == null)
            extraAnchors.put(nc, new AndForkJoinConnectionAnchor(getFigure(), nc, (PathNode) getModel()));

        return (AndForkJoinConnectionAnchor) extraAnchors.get(nc);

    }

    /**
     * Refreshes all outgoing or incoming connections if the super classes's anchors are moved. 
     */
    public void anchorMoved(ConnectionAnchor anchor) {

        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        if (cLayer.getConnectionRouter() instanceof UCMConnectionRouter)
            ((UCMConnectionRouter) cLayer.getConnectionRouter()).refreshConnections();

        List toRefresh;
        if (getModel() instanceof AndFork)
            toRefresh = getModelSourceConnections();
        else
            toRefresh = getModelTargetConnections();

        for (Iterator iter = toRefresh.iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            AndForkJoinConnectionAnchor anch = ((AndForkJoinConnectionAnchor) getAnchor(element));
            if (anch != anchor)
                anch.ancestorMoved(getFigure());
        }

    }

}