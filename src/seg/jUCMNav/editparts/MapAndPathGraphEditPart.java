package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import seg.jUCMNav.editpolicies.MapAndPathGraphXYLayoutEditPolicy;
import seg.jUCMNav.emf.ComponentRefAreaComparator;
import seg.jUCMNav.figures.router.BSplineConnectionRouter;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * Top level edit part. Contains the drawing board where everything is inserted.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class MapAndPathGraphEditPart extends ModelElementEditPart {

    public MapAndPathGraphEditPart(Map map) {
        setModel(map);
        map.getPathGraph().eAdapters().add(this);
    }

    /**
     * ( Creates the freeform layout
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();
        layer.setLayoutManager(new FreeformLayout());
        layer.setBorder(new LineBorder(1));
        return layer;
    }

    /**
     * Creates our top level edit policities.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // This install a container policy
        //		installEditPolicy(EditPolicy.CONTAINER_ROLE, new UcmEditPolicy());
        // This install the layout edit policy. Wich commands are used for
        // create/move/resize etc...

        installEditPolicy(EditPolicy.LAYOUT_ROLE, new MapAndPathGraphXYLayoutEditPolicy());
        //		installEditPolicy(EditPolicy.NODE_ROLE, null);
        //		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
        //		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
    }

    /**
     * 
     * @return the top level ucm.map.Map used to store all information. Contains ComponentRefs and a PathGraph.
     */
    private Map getMap() {
        return (Map) getModel();
    }

    /**
     * 
     * @return The Map's path graph, used to insert nodes and connections.
     */
    private PathGraph getPathGraph() {
        return ((Map) getModel()).getPathGraph();
    }

    /**
     * Returns the map&pathgraph's children: ComponentRefs and PathNodes and PathNode Labels, ordered in such a way that they don't interfere with each other on
     * the board.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        List list = new ArrayList();
        Object o = new Object();

        // get all component references
        for (int i = 0; i < getMap().getCompRefs().size(); i++)
            list.add(getMap().getCompRefs().get(i));

        // sort them by ascending area
        Collections.sort(list, new ComponentRefAreaComparator());
        // reverse the list so that our largest components are in the back.
        Collections.reverse(list);

        // put the nodes on top because they are always over components.
        for (int i = 0; i < getPathGraph().getPathNodes().size(); i++) {
            PathNode node = (PathNode) getPathGraph().getPathNodes().get(i);
            list.add(node);
            // if we have a label on a path node, we also want to add it.
            if (!(node.getLabel() == null))
                list.add(node.getLabel());
        }

        return list;
    }

    /**
     * Change listener. Has to handle when its children are changed and when we might have to reorder them.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        switch (type) {
        case Notification.ADD:
        case Notification.ADD_MANY:
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
            refreshChildren();
            break;
        case Notification.SET:
            if (notification.getNotifier() instanceof ComponentRef) {
                // we might have to reorder the children so as to put the largest components in the back.
                refreshChildren();
            }
            refreshVisuals();
            break;
        }

        refreshChildren();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
     */
    protected void registerVisuals() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        cLayer.setConnectionRouter(new BSplineConnectionRouter(getPathGraph()));

        super.registerVisuals();
    }

    /**
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.ModelElementlEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            getPathGraph().eAdapters().add(this);
        super.activate();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive())
            getPathGraph().eAdapters().remove(this);
        super.deactivate();
    }

}