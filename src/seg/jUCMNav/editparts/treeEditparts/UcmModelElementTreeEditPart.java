package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.views.property.UCMElementPropertySource;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;

/**
 * TreeEditPart for all UcmModelElements. Baseclass for most other TreeEditParts.
 * 
 * Handles disposal of image.
 * 
 * @author TremblaE
 */
public class UcmModelElementTreeEditPart extends AbstractTreeEditPart implements Adapter {

    // The property source associated with this model element.
    protected IPropertySource propertySource = null;

    // for impleneting Adapter
    private Notifier target;

    // the image associated with this TreeEditPart.
    protected Image image;

    /**
     * @param model
     *            the model element being edited.
     */
    public UcmModelElementTreeEditPart(Object model) {
        super(model);
    }

    /**
     * Listens to the model element.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive())
            ((EObject) getModel()).eAdapters().add(this);
        super.activate();
    }

    /**
     * 
     * Stops listening to the model element and destroys image.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            ((EObject) getModel()).eAdapters().remove(this);
            if (image != null) {
                image.dispose();
                image = null;
            }
        }
        super.deactivate();
    }

    /**
     * When something is changed, refresh. We are also refreshing the parent so that elements can be reordered if renamed
     *  
     */
    public void notifyChanged(Notification notification) {
        if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
            refreshChildren();
            refreshVisuals();

            // refresh parent to reorder children if name changes.
            if (notification.getFeature() instanceof EAttributeImpl && ((EAttributeImpl) notification.getFeature()).getName().equals("name")) { //$NON-NLS-1$
                getParent().refresh();
            }

        }
    }

    /**
     * Returns the textual string associated with this element.
     * 
     * @see seg.jUCMNav.model.util.EObjectClassNameComparator
     */
    protected String getText() {
        return EObjectClassNameComparator.getSortableElementName((EObject) getModel());
    }

    /**
     * 
     * @return the property source associated with this element.
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new UCMElementPropertySource((EObject) getModel());
        }
        return propertySource;

    }

    /**
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /**
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /**
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
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
     * Convenience method to return the map that containst his element.
     * 
     * @return the map containing this element. 
     */
    public Map getContainingMap() {
        if (getModel() instanceof PathNode) {
            return (((PathNode) getModel()).getPathGraph().getMap());
        } else if (getModel() instanceof ComponentRef) {
            return (((ComponentRef) getModel()).getMap());
        } else if (getModel() instanceof Map) {
            return (Map) getModel();
        } else
            return null;
    }

    /**
     * @return The icon associated with this model element. 
     */
    protected Image getImage() {
        return image;
    }

    /**
     * @param image the icon associated with this model element. 
     */
    public void setImage(Image image) {
        this.image = image;
    }
}