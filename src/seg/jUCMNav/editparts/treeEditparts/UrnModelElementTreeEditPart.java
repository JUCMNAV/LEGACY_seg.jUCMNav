package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.property.URNElementPropertySource;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * TreeEditPart for all UrnModelElements. Baseclass for most other TreeEditParts.
 * 
 * Handles disposal of image.
 * 
 * @author TremblaE, gunterm
 */
public class UrnModelElementTreeEditPart extends UrnAbstractTreeEditPart implements Adapter {

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
    public UrnModelElementTreeEditPart(Object model) {
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
            // if (image != null) {
            // image.dispose();
            // image = null;
            // }
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
            if (notification.getFeature() instanceof EAttributeImpl
                    && ((EAttributeImpl) notification.getFeature()).getName().equals("name") && getParent() != null) { //$NON-NLS-1$
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
        // Set the text depending on the outline preferences
        if (DisplayPreferences.getInstance().getShowNodeNumber()) {
            // This class return the ID between () at the element name
            return EObjectClassNameComparator.getSortableElementName((EObject) getModel());
        } else {
            return URNNamingHelper.getName((URNmodelElement) getModel());
        }
    }

    /**
     * 
     * @return the property source associated with this element.
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null) {
            propertySource = new URNElementPropertySource((EObject) getModel());
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
    public IURNDiagram getContainingMap() {
        if (getModel() instanceof IURNNode) {
            return (((IURNNode) getModel()).getDiagram());
        } else if (getModel() instanceof IURNContainerRef) {
            return (((IURNContainerRef) getModel()).getDiagram());
        } else if (getModel() instanceof IURNDiagram) {
            return (IURNDiagram) getModel();
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
     * @param image
     *            the icon associated with this model element.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @param ancestor
     *            is the {@link UrnModelElementTreeEditPart} for which it should be determined whether it is an ancestor in the tree hierachy above and
     *            including this {@link UrnModelElementTreeEditPart} (i.e. whether the model of the ancestor appears in the tree hierarchy)
     * @return true if the model of the ancestor can be found in the tree hierarchy, false if it cannot be found
     */
    public boolean isAncestor(EditPart ancestor) {
        if (ancestor.getModel() == this.getModel())
            return true;
        else {
            // the second clause makes sure that we are not checking beyond the part of the tree that contains
            // only UrnModelElementTreeEditParts (because the root of the tree has a different type)
            if (getParent() == null || !(getParent() instanceof UrnModelElementTreeEditPart))
                return false;
            else
                return ((UrnModelElementTreeEditPart) getParent()).isAncestor(ancestor);
        }
    }

    protected void removeChildVisual(EditPart childEditPart) {
        TreeEditPart treeEditPart = (TreeEditPart) childEditPart;
        if (treeEditPart.getWidget() != null) {
            treeEditPart.getWidget().dispose();
            treeEditPart.setWidget(null);
        }
    }

}