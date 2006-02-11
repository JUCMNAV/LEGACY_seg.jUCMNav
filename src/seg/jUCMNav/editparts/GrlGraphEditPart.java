/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import seg.jUCMNav.editpolicies.layout.GrlGraphXYLayoutEditPolicy;
import urncore.IURNContainerRef;
import urncore.IURNNode;


/**
 * @author Jean-François Roy
 *
 */
public class GrlGraphEditPart extends URNDiagramEditPart {

    public GrlGraphEditPart(GRLGraph graph){
        super(graph);
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new GrlGraphXYLayoutEditPolicy());
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());

    }

    /**
     * @return the labels linked to nodes or componentrefs.
     */
    private List getLabels() {
        List list = new ArrayList();

        // put the labels on top because they are always over components.
        for (Iterator i = getDiagram().getNodes().iterator(); i.hasNext();) {
            IURNNode node = (IURNNode) i.next();
            if (node.getLabel() != null) {
                list.add(node.getLabel());
            }
        }

        for (Iterator i = getDiagram().getContRefs().iterator(); i.hasNext();) {
            IURNContainerRef component = (IURNContainerRef) i.next();
            if (component.getLabel() != null) {
                list.add(component.getLabel());
            }
        }

        return list;
    }
    
    /**
     * Returns the GrlGraph's children: IntentionalElementRefs
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */

    protected List getModelChildren() {
        List list = getComponents();
        list.addAll(getNodes());
        list.addAll(getLabels());
        return list;
    }

    /**
     * @return the GrlNode
     */
    private List getNodes() {
        List list = new ArrayList();

        for (Iterator i = getDiagram().getNodes().iterator(); i.hasNext();) {
            list.add(i.next());
        }

        return list;
    }

    /**
     * @return the IURNConnections
     */
    private List getConnections() {
        List list = new ArrayList();

        for (Iterator i = getDiagram().getConnections().iterator(); i.hasNext();) {
            list.add(i.next());
        }

        return list;
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshChildren();


    }
    
    /** 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
     */
    protected void registerVisuals() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        cLayer.setConnectionRouter(new BendpointConnectionRouter());

        super.registerVisuals();
    }

}
