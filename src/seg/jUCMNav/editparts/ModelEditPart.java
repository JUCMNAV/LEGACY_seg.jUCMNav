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

import seg.jUCMNav.emf.EObjectPropertySource;

/**
 * Created 2005-02-23
 * 
 * @author Etienne Tremblay
 */
public abstract class ModelEditPart extends AbstractGraphicalEditPart implements Adapter {
	private IPropertySource propertySource = null;
	private Notifier target;

	/**
	 * 
	 */
	public ModelEditPart() {
		super();
	}
	
	/**
	 * This method is used to return the model object of this editpart
	 * @return The model object this edit part represent.
	 */
	public abstract EObject getModelObj();
	
	/**
	 * Each editparts has to provide a way to create their figures.
	 * @return The figure for this editpart.
	 */
	protected abstract IFigure createFigure();
	
	/**
	 * Create the editpolicies for this editpart.
	 */
	protected abstract void createEditPolicies();
	
	public abstract void activate();

	public abstract void deactivate();
	
	protected abstract void refreshVisuals();
	
	protected IPropertySource getPropertySource() {
		if( propertySource == null ) {
			propertySource = new EObjectPropertySource( getModelObj() );
		}
		return propertySource;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public abstract void notifyChanged(Notification notification);

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
