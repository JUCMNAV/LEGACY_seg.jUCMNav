/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.Belief;
import grl.GRLGraph;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import seg.jUCMNav.editpolicies.directEditPolicy.BeliefNodeEditPolicy;
import seg.jUCMNav.editpolicies.directEditPolicy.GrlNodeDirectEditPolicy;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.GrlNodeFeedbackEditPolicy;
import seg.jUCMNav.figures.BeliefFigure;
import seg.jUCMNav.figures.GrlNodeFigure;
import urncore.IURNNode;

/**
 * Edit Part for a GRL Belief
 * 
 * @author Jean-François Roy
 *
 */
public class BeliefEditPart extends GrlNodeEditPart implements NodeEditPart {

    private GRLGraph diagram;
    
    /**
     * Constructor for the Belief edit part
     * @param model the Belief to draw
     * @param graph the graph in which it is contained. 
     */
    public BeliefEditPart(IURNNode model, GRLGraph graph) {
        super();
        setModel(model);
        this.diagram = graph;
    }


    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new GrlNodeFeedbackEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new GrlNodeDirectEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new BeliefNodeEditPolicy());
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#createFigure()
     */
    protected IFigure createFigure() {
        return new BeliefFigure();
    }

    /**
     * @return the model cast as a belief
     */
    private Belief getBelief(){
        return (Belief) getModel();
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

    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
     */
    protected List getModelSourceConnections() {
        return getBelief().getSucc();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
     */
    protected List getModelTargetConnections() {
        return getBelief().getPred();
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
        return getNodeFigure().getConnectionAnchor();
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
        
        // we want the top level editpart to refresh its children so that the largest components are always in the back.
        if (notification.getEventType() == Notification.SET && getParent() != null)
            ((URNDiagramEditPart) getParent()).notifyChanged(notification);
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        // The position of the current figure
        Point location = new Point(getBelief().getX(), getBelief().getY());
        // its size (the width of the elements should always be 2 the height of them
        Dimension size = getNodeFigure().getSize().getCopy(); //new Dimension(getBelief().getWidth(), getBelief().getHeight());
        Rectangle bounds = new Rectangle(location, size);
        figure.setBounds(bounds);
        figure.setLocation(location);
 
        setText();

        if (((GrlConnectionOnBottomRootEditPart) getRoot()).isStrategyView()){
            ((BeliefFigure) figure).setColors("75,75,75", "0,0,0", false);
        } else{
            ((BeliefFigure) figure).setColors("0,0,0", "0,0,0", false);
        }

        // set information for specific drawing
        //Line color for belief is gray
        //((BeliefFigure) figure).setColors("165,165,165", "0,0,0", true);

        //   Make the label recenter itself.
        figure.validate(); 

    }

    /**
     * Sets the label's text, given its referenced model element.
     */
    private void setText() {
        getNodeFigure().setText(getBelief().getDescription());
    }
}
