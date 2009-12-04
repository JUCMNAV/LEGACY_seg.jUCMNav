package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import seg.jUCMNav.editpolicies.layout.MapXYLayoutEditPolicy;
import seg.jUCMNav.figures.router.UCMConnectionRouter;
import ucm.UcmPackage;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * 
 * Edit part for a UCMmap
 * 
 * @author Jean-François Roy
 * 
 */
public class UCMMapEditPart extends URNDiagramEditPart {

    /**
     * Creates an editpart for the map
     * 
     * @param map
     *            the use case map
     */
    public UCMMapEditPart(UCMmap map) {
        super(map);
    }

    /**
     * Creates our top level edit policies.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new MapXYLayoutEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
    }

    /**
     * 
     * @return the conditions linked to node connection, start points or end points.
     */
    private List getConditions() {
        List list = new ArrayList();

        for (Iterator i = getDiagram().getConnections().iterator(); i.hasNext();) {
            NodeConnection nc = (NodeConnection) i.next();
            if (nc.getCondition() != null) {
                list.add(nc.getCondition());
            }
        }
        for (Iterator i = getDiagram().getNodes().iterator(); i.hasNext();) {
            PathNode pn = (PathNode) i.next();
            if (pn instanceof StartPoint && ((StartPoint) pn).getPrecondition() != null) {
                list.add(((StartPoint) pn).getPrecondition());
            } else if (pn instanceof EndPoint && ((EndPoint) pn).getPostcondition() != null) {
                list.add(((EndPoint) pn).getPostcondition());
            }
        }

        return list;
    }

    /**
     * @return the labels linked to pathnodes or componentrefs.
     */
    private List getLabels() {
        List list = new ArrayList();

        // put the labels on top because they are always over components.
        for (Iterator i = getDiagram().getNodes().iterator(); i.hasNext();) {
            PathNode node = (PathNode) i.next();
            if (node.getLabel() != null) {
                list.add(node.getLabel());
            }
        }

        for (Iterator i = getDiagram().getContRefs().iterator(); i.hasNext();) {
            ComponentRef component = (ComponentRef) i.next();
            if (component.getLabel() != null) {
                list.add(component.getLabel());
            }
        }

        return list;
    }

    private List getComments() {
        List list = new ArrayList();
        for (Iterator iterator = getDiagram().getComments().iterator(); iterator.hasNext();) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Returns the map children: ComponentRefs and PathNodes and PathNode Labels, ordered in such a way that they don't interfere with each other on the board.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        List list = getComponents();
        list.addAll(getNodes());
        list.addAll(getLabels());
        list.addAll(getConditions());
        list.addAll(getComments());
        return list;
    }

    /**
     * @return the nodes in the diagram, except for Connects.
     */
    private List getNodes() {
        List list = new ArrayList();

        // put the nodes on top because they are always over components.
        for (Iterator i = getDiagram().getNodes().iterator(); i.hasNext();) {
            PathNode pn = (PathNode) i.next();
            if (!(pn instanceof Connect)) {
                list.add(pn);
            }
        }

        return list;
    }

    /**
     * Change listener. Has to handle when its children are changed and when we might have to reorder them.
     * 
     * This method should be reviewed for performance issues. We probably refresh too often.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (type) {
        case Notification.ADD:
        case Notification.ADD_MANY:
            // Don't call refreshChildren if a NodeConnection is sending a notification.
            if (!(notification.getNewValue() instanceof NodeConnection))
                refreshChildren();
            break;
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
            // Don't call refreshChildren if a NodeConnection is sending a notification.
            if (!(notification.getOldValue() instanceof NodeConnection))
                refreshChildren();
            break;
        case Notification.SET:
            switch (featureId) {
            case MapPackage.COMPONENT_REF__LABEL:
            case MapPackage.PATH_NODE__LABEL:
                refreshChildren();
                break;
            // duplicate with pn_label case MapPackage.COMPONENT_REF__WIDTH:
            case MapPackage.COMPONENT_REF__HEIGHT:
                if (notification.getNotifier() instanceof ComponentRef) {
                    // we might have to reorder the children so as to put the largest components in the back.
                    refreshChildren();
                }
                break;
            }
            // refreshVisuals();
            break;
        }

        refreshChildren();
    }

    /**
     * This function was overiden to include refresh of Stub's In and Out
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            AbstractGraphicalEditPart element = (AbstractGraphicalEditPart) iter.next();
            element.refresh();
            // refresh stub labels; doing this in its refreshVisuals crashed the app with a heap space error.
            if (element instanceof StubEditPart)
                ((StubEditPart) element).refreshInOuts();
        }
    }

    /**
     * 
     * Setup the connection router
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
     */
    protected void registerVisuals() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        cLayer.setConnectionRouter(new UCMConnectionRouter(getViewer().getEditPartRegistry(), (UCMmap) getDiagram()));

        super.registerVisuals();
    }

    protected void unregisterVisuals() {

        // disconnectRouter();

        super.unregisterVisuals();
    }

    public void dispose() {
        for (Iterator iterator = getChildren().iterator(); iterator.hasNext();) {
            EditPart part = (EditPart) iterator.next();
            part.removeNotify();
        }
        removeEditPolicy(EditPolicy.LAYOUT_ROLE);
        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        removeNotify();
        disconnectRouter();
    }

    public void disconnectRouter() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        if (cLayer != null) {
            if (cLayer.getConnectionRouter() instanceof UCMConnectionRouter) {
                UCMConnectionRouter router = (UCMConnectionRouter) cLayer.getConnectionRouter();
                router.dispose();

            }
            cLayer.setConnectionRouter(null);
        }
    }

}
