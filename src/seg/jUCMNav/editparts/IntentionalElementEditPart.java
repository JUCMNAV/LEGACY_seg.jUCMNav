 /**
 * 
 */
package seg.jUCMNav.editparts;

import grl.Decomposition;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.directEditPolicy.GrlNodeDirectEditPolicy;
import seg.jUCMNav.editpolicies.directEditPolicy.IntentionalElementNodeEditPolicy;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.GrlNodeFeedbackEditPolicy;
import seg.jUCMNav.figures.IntentionalElementFigure;
import seg.jUCMNav.views.property.IntentionalElementPropertySource;
import urncore.IURNNode;

/**
 * EditPart for all IntentialElementRef.
 * It listen for changes in the references and the definitions
 * 
 * @author Jean-François Roy
 *
 */
public class IntentionalElementEditPart extends GrlNodeEditPart implements NodeEditPart{

    private GRLGraph diagram;
    
    /**
     * 
     * @param model the intentional element ref to draw
     * @param graph the graph in which it is contained. 
     */
    public IntentionalElementEditPart(IURNNode model, GRLGraph graph) {
        super();
        setModel(model);
        this.diagram = graph;
    }

    /**
     * We need to listen for the reference and the definition for intentionalElement
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getNode() instanceof IntentionalElementRef && ((IntentionalElementRef) getNode()).getDef() != null){
            ((IntentionalElementRef)getNode()).getDef().eAdapters().add(this);
        }
        
        // listen to reference
        super.activate();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new GrlNodeFeedbackEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new IntentionalElementNodeEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new GrlNodeDirectEditPolicy());
    }

    /** 
     * Create the GrlNode figure
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        IntentionalElementFigure fig = new IntentionalElementFigure();
        return fig;
    }


    /**
     * Overriding because we also have to listen to the Component definition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getNode() instanceof IntentionalElementRef && ((IntentionalElementRef) getNode()).getDef() != null)
            ((IntentionalElementRef) getNode()).getDef().eAdapters().remove(this);
        super.deactivate();

    }
    
    /**
     * When nodes are dragged in GEF, they explictly remove connections from being possible drop targets. By overriding DragEditPartsTracker, we allow this
     * behaviour.
     * 
     * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return new DragPathNodeTracker(this);
    }

    private IntentionalElementRef getNode(){
        return (IntentionalElementRef) getModel();
    }

    /**
     * @return The node's figure
     */
    public IntentionalElementFigure getNodeFigure() {
        return (IntentionalElementFigure) getFigure();
    }

    /**
     * @return a IntentionalElementPropertySource 
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new IntentionalElementPropertySource((EObject) getModel());
        }
        return propertySource;
    }
   
    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    protected List getModelSourceConnections() {
        return getNode().getSucc();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
     */
    protected List getModelTargetConnections() {
        return getNode().getPred();
    }
    
    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        
        return getNodeFigure().getConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        
        if (connection instanceof LinkRefEditPart && (((LinkRefEditPart)connection).getLinkRef().getLink() instanceof Decomposition)) {
            return getNodeFigure().getDecompositionTarget();
        }
        else {
            return getNodeFigure().getConnectionAnchor();
        }
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeFigure().getConnectionAnchor();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshTargetConnections();
        refreshSourceConnections();
        refreshVisuals();
        
        int featureId = notification.getFeatureID(GrlPackage.class);
        if (featureId == GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE){
            for (Iterator iter = getNode().getDef().getLinksDest().iterator(); iter.hasNext();) {
                ElementLink decomp = (ElementLink) iter.next();
                if (decomp instanceof Decomposition){
                    for (Iterator iRef = decomp.getRefs().iterator();iRef.hasNext();){
                        LinkRef ref = (LinkRef)iRef.next();
                        LinkRefEditPart refEditPart = (LinkRefEditPart) getViewer().getEditPartRegistry().get(ref);
                        if (refEditPart != null) {
                            refEditPart.refreshVisuals();
                        }
                    }
                }
            }
        }
        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }
    
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getNode().getX(), getNode().getY());
        // its size (the width of the elements should always be 2 the height of them
        Dimension size = getNodeFigure().getSize().getCopy(); 
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.setLocation(location);
        
        setText();

        // set information for specific drawing
        if (((IntentionalElementRef)getNode()).getDef() != null && 
                (((IntentionalElementRef)getNode()).getDef() instanceof IntentionalElement)) {
            IntentionalElement elem = ((IntentionalElementRef)getNode()).getDef();
            ((IntentionalElementFigure) figure).setType(elem.getType().getValue());
            ((IntentionalElementFigure) figure).setColors(getNode().getDef().getLineColor(), getNode().getDef().getFillColor(), getNode().getDef().isFilled());
        }

        
        
        //   Make the label recenter itself.
        figure.validate(); 

        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
//        if (getParent() != null)
//            (getLayer(URNRootEditPart.COMPONENT_LAYER)).setConstraint(figure, bounds);
    }
    
        
    public void setDiagram(GRLGraph graph){
        diagram = graph;
    }
    
    /**
     * Sets the label's text, given its referenced model element.
     */
    private void setText() {
        if (getNode().getDef() != null){
            getNodeFigure().setText(getNode().getDef().getName());
        }
    }
}
