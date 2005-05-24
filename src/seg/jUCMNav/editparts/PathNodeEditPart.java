/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.editparts;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import seg.jUCMNav.editpolicies.element.PathNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.PathNodeNonResizableEditPolicy;
import seg.jUCMNav.figures.AndForkFigure;
import seg.jUCMNav.figures.EmptyPointFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.OrForkFigure;
import seg.jUCMNav.figures.PathNodeFigure;
import seg.jUCMNav.figures.ResponsibilityFigure;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.figures.StartPointFigure;
import seg.jUCMNav.figures.StubFigure;
import ucm.UcmPackage;
import ucm.map.AndFork;
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.OrFork;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * @author Etienne Tremblay
 *
 */
public class PathNodeEditPart extends ModelElementEditPart implements NodeEditPart {
	
	private PathGraph diagram;
	
	
	public PathNodeEditPart(PathNode model, PathGraph diagram){
		super();
		setModel(model);
		this.diagram = diagram;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		PathNodeFigure figure = new EmptyPointFigure();
		if(getModel() instanceof RespRef)
			figure = new ResponsibilityFigure();
		else if(getModel() instanceof EndPoint)
			figure = new EndPointFigure();
		else if(getModel() instanceof StartPoint)
			figure = new StartPointFigure();
		else if(getModel() instanceof OrFork)
		    figure = new OrForkFigure();
		else if (getModel() instanceof AndFork)
			figure = new AndForkFigure();
		else if (getModel() instanceof Stub){
			Stub stub = (Stub)getModel();
			figure = new StubFigure(stub.isDynamic());
		}
		return figure;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// install the edit policy to handle connection creation
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathNodeComponentEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PathNodeNonResizableEditPolicy());
	}
	
	protected PathNode getNode(){
		return (PathNode)getModel();
	}
	
	public PathNodeFigure getNodeFigure(){
		return (PathNodeFigure)getFigure();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		int featureId = notification.getFeatureID( UcmPackage.class );
		switch( featureId ) {
		case MapPackage.PATH_NODE__PRED:
			refreshTargetConnections();
		break;		
		case MapPackage.PATH_NODE__SUCC:
			refreshSourceConnections();
		break;
		case MapPackage.PATH_NODE__LABEL:
			((MapAndPathGraphEditPart)getParent()).notifyChanged(notification);
		break;
		default:
			//refreshVisuals();
		break;
		}
		
		refreshVisuals();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	public void refreshVisuals() {
		PathNodeFigure nodeFigure = getNodeFigure();
		Dimension dim = nodeFigure.getPreferredSize().getCopy();
		Point location = new Point(getNode().getX()-(dim.width/2), getNode().getY()-(dim.height/2));  // The position of the current figure
		Rectangle bounds = new Rectangle(location, dim);
		figure.setBounds(bounds);
		if (getModel() instanceof EndPoint && ((EndPoint) getModel()).getPred().size()>0) {
            NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(((EndPoint) getModel()).getPred().get(0));
            if (nc!=null) {
                                
            	SplineConnection sp = (SplineConnection) nc.getFigure();
            	//sp.layout();
            	if(sp != null) {
            		PointList list = sp.getPoints();
            		if(list != null) {
            			
            			Ray r;

            			if(list.size() > 2) {
            				r = new Ray(list.getPoint(list.size()-2), list.getLastPoint());
            			} else {
            				r = new Ray(list.getMidpoint(), list.getLastPoint());
            			}
        //    			r = new Ray(sp.getPoints().getPoint(sp.getPoints().size()-2),list.getLastPoint());
            			
            			//System.out.println(r);

            			((EndPointFigure) nodeFigure).setEntryRay(r);
            		}
            	}
            }
		}
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent container 
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
		// and will not draw it correctly.
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
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
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getNodeFigure().getSourceConnectionAnchor();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getNodeFigure().getTargetConnectionAnchor();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getNodeFigure().getSourceConnectionAnchor();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getNodeFigure().getTargetConnectionAnchor();
	}
	/**
	 * @return Returns the diagram.
	 */
	public PathGraph getDiagram() {
		return diagram;
	}
	/**
	 * @param diagram The diagram to set.
	 */
	public void setDiagram(PathGraph diagram) {
		this.diagram = diagram;
	}
	

    /** 
     * Overriding because we also have to listen to the responsibility definition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive() && getNode() instanceof RespRef && ((RespRef)getNode()).getRespDef()!=null)
            ((RespRef)getNode()).getRespDef().eAdapters().add(this);

        super.activate();
    }

    /**
     * Overriding because we also have to listen to the responsibility definition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getNode() instanceof RespRef && ((RespRef)getNode()).getRespDef()!=null)
            ((RespRef)getNode()).getRespDef().eAdapters().remove(this);
        super.deactivate();        
        
    }
}
