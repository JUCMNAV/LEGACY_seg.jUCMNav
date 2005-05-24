/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.views.property.ComponentPropertySource;
import seg.jUCMNav.views.property.ResponsibilityPropertySource;
import seg.jUCMNav.views.property.UCMElementPropertySource;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.UCMmodelElement;

/**
 * @author TremblaE
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class UcmModelElementTreeEditPart extends AbstractTreeEditPart implements Adapter {

    protected IPropertySource propertySource = null;
    private Notifier target;

    protected Image image;

    /**
     * @param model
     */
    public UcmModelElementTreeEditPart(Object model) {
        super(model);
    }

    /**
     *  
     */
    private UcmModelElementTreeEditPart() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            ((EObject) getModel()).eAdapters().add(this);
        super.activate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            ((EObject) getModel()).eAdapters().remove(this);
            if (image != null) {
                image.dispose();
                image=null;
            }
        }
        super.deactivate();
    }

    public void notifyChanged(Notification notification) {
        if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
            refreshChildren();
            refreshVisuals();
        }
    }

    protected String getText() {
        if (getModel() instanceof UCMmodelElement) {
            UCMmodelElement elem = (UCMmodelElement) getModel();
            return elem.getId() + ": " + elem.getName();
        } else
            return super.getText();
    }

    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            if (getModel() instanceof RespRef)
                propertySource = new ResponsibilityPropertySource((EObject) getModel());
            else if (getModel() instanceof ComponentRef)
                propertySource = new ComponentPropertySource((EObject) getModel());
            else
                propertySource = new UCMElementPropertySource((EObject) getModel());
        }
        return propertySource;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
    }

    /*
     * (non-Javadoc)
     * 
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

    public Map getContainingMap() {

        if (getModel() instanceof PathNode) {
            return (Map) (((PathNode) getModel()).eContainer().eContainer());
        } else if (getModel() instanceof ComponentRef) {
            return (Map) (((ComponentRef) getModel()).eContainer());
        } else if (getModel() instanceof Map) {
            return (Map) getModel();
        } else
            return null;
    }

    protected Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}