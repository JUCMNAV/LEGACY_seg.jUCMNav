/*
 * Created on 2005-02-15
 *
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.emf.EObjectPropertySource;
import seg.jUCMNav.model.ucm.Component;

/**
 * Created 2005-02-15
 * 
 * @author Etienne Tremblay
 */
public class ComponentEditPart extends AbstractGraphicalEditPart implements
		Adapter {
	
	private IPropertySource propertySource = null;
	private Notifier target;

	/**
	 * 
	 */
	public ComponentEditPart(Component model) {
		super();
		setModel(model);
	}
	
	private Component getComponent(){
		return (Component)getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		if(!isActive())
			getComponent().eAdapters().add(this);
		super.activate();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		if(isActive())
			getComponent().eAdapters().remove(this);
		super.deactivate();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		RectangleFigure rect = new RectangleFigure();
		rect.setFill(false);
		return rect;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		refreshVisuals();
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
	
	protected IPropertySource getPropertySource() {
		if( propertySource == null ) {
			propertySource = new EObjectPropertySource( getComponent() );
		}
		return propertySource;
		
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
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		Point location = new Point(getComponent().getX(), getComponent().getY());  // The position of the current figure
		Dimension size = new Dimension(getComponent().getWidth(), getComponent().getHeight());
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
