/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import java.util.ArrayList;
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
import seg.jUCMNav.figures.router.BSplineConnectionRouter;
import ucm.map.Map;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * @author Etienne Tremblay
 */
public class MapAndPathGraphEditPart extends ModelElementEditPart {

    public MapAndPathGraphEditPart(Map map) {
        setModel(map);
        map.getPathGraph().eAdapters().add(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();
        layer.setLayoutManager(new FreeformLayout());
        layer.setBorder(new LineBorder(1));
        return layer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // This install a container policy
        //		installEditPolicy(EditPolicy.CONTAINER_ROLE, new UcmEditPolicy());
        // This install the layout edit policy. Wich commands are used for
        // create/move/resize etc...

        installEditPolicy(EditPolicy.LAYOUT_ROLE,
                new MapAndPathGraphXYLayoutEditPolicy());
        //		installEditPolicy(EditPolicy.NODE_ROLE, null);
        //		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
        //		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
        installEditPolicy(EditPolicy.COMPONENT_ROLE,
                new RootComponentEditPolicy());
    }

    private Map getMap() {
        return (Map) getModel();
    }

    private PathGraph getPathGraph() {
        return ((Map) getModel()).getPathGraph();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        List list = new ArrayList();
        Object o = new Object();

        for (int i=0;i<getMap().getCompRefs().size();i++)
            list.add(getMap().getCompRefs().get(i));

        for (int i=0;i<getPathGraph().getPathNodes().size();i++) {
            PathNode node = (PathNode) getPathGraph().getPathNodes().get(i);
            list.add(node);
            if(!(node.getLabel() == null))
                list.add(node.getLabel());
        }
                
        return list;
    }

    /*
     * (non-Javadoc)
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
            refreshVisuals();
            break;
        }
        
        refreshChildren();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
     */
    protected void registerVisuals() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        cLayer.setConnectionRouter(new BSplineConnectionRouter(getPathGraph()));

        super.registerVisuals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.ModelElementlEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            getPathGraph().eAdapters().add(this);
        super.activate();
    }

    /*
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