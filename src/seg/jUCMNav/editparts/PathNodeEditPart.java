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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.ComponentRefEditPolicy;
import seg.jUCMNav.editpolicies.PathNodeEditPolicy;
import seg.jUCMNav.editpolicies.PathNodeNonRezizableEditPolicy;
import seg.jUCMNav.emf.EObjectPropertySource;
import seg.jUCMNav.figures.EmptyPointFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.PathNodeFigure;
import seg.jUCMNav.figures.ResponsibilityFigure;
import seg.jUCMNav.figures.StartPointFigure;
import ucm.UcmPackage;
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;

/**
 * @author Etienne Tremblay
 *
 */
public class PathNodeEditPart extends AbstractGraphicalEditPart implements Adapter, NodeEditPart {
	private IPropertySource propertySource = null;
	private Notifier target;
	
	private PathGraph diagram;
	
	
	public PathNodeEditPart(PathNode model, PathGraph diagram){
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
		return figure;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// install the edit policy to handle connection creation
		installEditPolicy( EditPolicy.GRAPHICAL_NODE_ROLE, new PathNodeEditPolicy() );
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentRefEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PathNodeNonRezizableEditPolicy());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		if(!isActive())
			getNode().eAdapters().add(this);
		super.activate();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		if(isActive())
			getNode().eAdapters().remove(this);
		super.deactivate();
	}
	
	private PathNode getNode(){
		return (PathNode)getModel();
	}
	
	protected IPropertySource getPropertySource() {
		if( propertySource == null ) {
			propertySource = new EObjectPropertySource( getNode() );
		}
		return propertySource;
		
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
		default:
			refreshVisuals();
		break;
		}
	}
	
	public PathNodeFigure getNodeFigure(){
		return (PathNodeFigure)getFigure();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		Dimension dim = getNodeFigure().getPreferredSize().getCopy();
		Point location = new Point(getNode().getX()-(dim.width/2), getNode().getY()-(dim.height/2));  // The position of the current figure
		Rectangle bounds = new Rectangle(location, dim);
		figure.setBounds(bounds);
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent container 
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
		// and will not draw it correctly.
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type.equals( getModel().getClass() );
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		/* override the default behavior defined in AbstractEditPart
		*  which would expect the model to be a property sourced. 
		*  instead the editpart can provide a property source
		*/
		if (IPropertySource.class == key) {
			return getPropertySource();
		}
		return super.getAdapter( key );
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
}
