/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editpolicies.UcmComponentEditPolicy;
import seg.jUCMNav.editpolicies.UcmNodeEditPolicy;
import seg.jUCMNav.emf.EObjectPropertySource;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.NodeFigure;
import seg.jUCMNav.figures.ResponsibilityFigure;
import seg.jUCMNav.figures.StartPointFigure;
import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Responsibility;
import seg.jUCMNav.model.ucm.StartPoint;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UcmNodeEditPart extends AbstractGraphicalEditPart implements Adapter {
	private IPropertySource propertySource = null;
	private Notifier target;
	
	
	public UcmNodeEditPart(Node model){
		setModel(model);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		NodeFigure figure = new NodeFigure();
		if(getModel() instanceof Responsibility)
			figure = new ResponsibilityFigure();
		else if(getModel() instanceof EndPoint)
			figure = new EndPointFigure();
		else if(getModel() instanceof StartPoint)
			figure = new StartPointFigure();
		else if(getModel() instanceof EndPoint)
			figure = new EndPointFigure();
//		((PathEditPart)getParent()).getFigure().add(figure);
		return figure;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// install the edit policy to handle connection creation
		installEditPolicy( EditPolicy.GRAPHICAL_NODE_ROLE, new UcmNodeEditPolicy() );
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new UcmComponentEditPolicy());
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
	
	private Node getNode(){
		return (Node)getModel();
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
		default:
			refreshVisuals();
		break;
		}
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
	
	private NodeFigure getNodeFigure(){
		return (NodeFigure)getFigure();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		Point location = new Point(getNode().getX(), getNode().getY());  // The position of the current figure
		Dimension size = new Dimension(-1, -1);
		Rectangle bounds = new Rectangle(location, size);
		figure.setBounds(bounds);
		figure.validate(); // Make the label recenter itself.
		// notify parent container of changed position & location
		// if this line is removed, the XYLayoutManager used by the parent container 
		// (the Figure of the ShapesDiagramEditPart), will not know the bounds of this figure
		// and will not draw it correctly.
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, figure, bounds);
	}
}
