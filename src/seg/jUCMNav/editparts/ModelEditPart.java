/*
 * Created on 2005-02-23
 *
 */
package seg.jUCMNav.editparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * Created 2005-02-23
 * 
 * @author Etienne Tremblay
 */
public abstract class ModelEditPart extends AbstractGraphicalEditPart implements Adapter {

	/**
	 * 
	 */
	public ModelEditPart() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		// TODO Implement Method
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		// TODO Implement Method

	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		// TODO Implement method
		return false;
	}

}
