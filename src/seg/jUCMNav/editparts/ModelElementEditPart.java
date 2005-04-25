/*
 * Created on 2005-02-23
 *
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.views.EObjectPropertySource;

/**
 * Created 2005-02-23
 * 
 * @author Etienne Tremblay
 */
public abstract class ModelElementEditPart extends AbstractGraphicalEditPart implements Adapter {
	private IPropertySource propertySource = null;
	private Notifier target;

	/**
	 * 
	 */
	public ModelElementEditPart() {
		super();
	}
	
	/**
	 * Each editparts has to provide a way to create their figures.
	 * @return The figure for this editpart.
	 */
	protected abstract IFigure createFigure();
	
	/**
	 * Create the editpolicies for this editpart.
	 */
	protected abstract void createEditPolicies();
	
	protected abstract void refreshVisuals();

	/**
	 * This method is called when the model element is changed.  The edit part has to update the visuals of the change.
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public abstract void notifyChanged(Notification notification);
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		if(!isActive())
			((EObject)getModel()).eAdapters().add(this);
		super.activate();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		if(isActive())
			((EObject)getModel()).eAdapters().remove(this);
		super.deactivate();
	}
		
	protected IPropertySource getPropertySource() {
		if( propertySource == null ) {
			propertySource = new EObjectPropertySource( (EObject)getModel() );
		}
		return propertySource;
		
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
}
