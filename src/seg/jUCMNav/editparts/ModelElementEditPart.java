package seg.jUCMNav.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.views.property.ResponsibilityPropertySource;
import seg.jUCMNav.views.property.URNElementPropertySource;
import ucm.map.RespRef;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Label;

/**
 * This is a top level EditPart that defines the behaviour that needs to be adhered to in order to function properly. All jUCMNav EditParts should subclass this
 * EditPart.
 * 
 * @author Etienne Tremblay
 */
public abstract class ModelElementEditPart extends AbstractGraphicalEditPart implements Adapter {
    protected IPropertySource propertySource = null;
    private Notifier target;

    /**
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            ((EObject) getModel()).eAdapters().add(this);
        super.activate();
    }

    /**
     * Create the editpolicies for this editpart.
     */
    protected abstract void createEditPolicies();

    /**
     * Each editparts has to provide a way to create their figures.
     * 
     * @return The figure for this editpart.
     */
    protected abstract IFigure createFigure();

    /**
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive())
            ((EObject) getModel()).eAdapters().remove(this);
        super.deactivate();
    }

    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class key) {
        /*
         * override the default behavior defined in AbstractEditPart which would expect the model to be a property sourced. instead the editpart can provide a
         * property source
         */
        if (IPropertySource.class == key) {
            return getPropertySource();
        }
        return super.getAdapter(key);
    }

    /**
     * Returns a ResponsibilityPropertySource if the model element is a RespRef, UCMElementPropertySource otherwise.
     * 
     * @return a property source for our object
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            if (getModel() instanceof RespRef)
                propertySource = new ResponsibilityPropertySource((EObject) getModel());
            else
                propertySource = new URNElementPropertySource((EObject) getModel());
        }
        return propertySource;

    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
    }

    /**
     * This method is called when the model element is changed. The edit part has to update the visuals of the change.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public abstract void notifyChanged(Notification notification);

    /**
     * Called when the figure needs to be refreshed.
     */
    protected abstract void refreshVisuals();

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
            if (getModel() instanceof IURNNode || getModel() instanceof IURNConnection || getModel() instanceof IURNContainerRef) {
                Label label = null;
                if (getModel() instanceof IURNNode)
                    label = ((IURNNode) getModel()).getLabel();
                else if (getModel() instanceof IURNConnection)
                    label = ((IURNConnection) getModel()).getLabel();
                else if (getModel() instanceof IURNContainerRef)
                    label = ((IURNContainerRef) getModel()).getLabel();

                if (label != null) {
                    EditPart editpart = (EditPart) getViewer().getEditPartRegistry().get(label);
                    if (editpart != null)
                        editpart.performRequest(req);
                }
            }
        }

        super.performRequest(req);
    }
}