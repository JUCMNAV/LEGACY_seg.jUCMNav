/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import seg.jUCMNav.figures.GrlNodeFigure;
import seg.jUCMNav.figures.IntentionalElementFigure;
import urncore.SpecificationNode;

/**
 * EditPart for all IntentialElementRef.
 * They listen for changes in the references and the definitions
 * 
 * @author Jean-François Roy
 *
 */
public class GrlNodeEditPart extends ModelElementEditPart implements NodeEditPart{

    private GRLGraph diagram;
    private static final int DEFAULT_HEIGHT = 50;
    private static final int DEFAULT_WIDTH = 50;

    /**
     * 
     * @param model the component ref to draw
     * @param graph the graph in which it is contained. 
     */
    public GrlNodeEditPart(SpecificationNode model, GRLGraph graph) {
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
        //TODO create edit policies for GRLNode

    }

    /** 
     * Create the GrlNode figure
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        return new IntentionalElementFigure();
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

    private SpecificationNode getNode(){
        return (SpecificationNode) getModel();
    }

    /**
     * @return The node's figure
     */
    public GrlNodeFigure getNodeFigure() {
        return (GrlNodeFigure) getFigure();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getSourceConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return getNodeFigure().getSourceConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return getNodeFigure().getTargetConnectionAnchor();
    }

    /**
     * 
     * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return getNodeFigure().getTargetConnectionAnchor();
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        refreshVisuals();

    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        GrlNodeFigure nodeFigure = getNodeFigure();
        
        // position the figure
        Dimension dim = nodeFigure.getPreferredSize().getCopy();
        Point location = new Point(getNode().getX() - (dim.width / 2), getNode().getY() - (dim.height / 2)); // The
        // position of the current figure
        Rectangle bounds = new Rectangle(location, dim);
        figure.setBounds(bounds);
        figure.setLocation(location);

        // set information for specific drawing
        if (getNode() instanceof IntentionalElementRef) {
            IntentionalElement elem = (IntentionalElement) ((IntentionalElementRef)getNode()).getDef();
            ((IntentionalElementFigure) figure).setType(elem.getType().getValue());
        }

        figure.validate();
        // notify parent container of changed position & location
        // if this line is removed, the XYLayoutManager used by the parent container
        // (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
        // and will not draw it correctly.
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
    }
    
    public void setDiagram(GRLGraph graph){
        diagram = graph;
    }
}
