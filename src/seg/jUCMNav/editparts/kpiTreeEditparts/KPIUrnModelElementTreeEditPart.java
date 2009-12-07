package seg.jUCMNav.editparts.kpiTreeEditparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editparts.treeEditparts.UrnAbstractTreeEditPart;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.property.URNElementPropertySource;
import seg.jUCMNav.views.property.VariablePropertySource;
import ucm.scenario.Variable;
import urncore.URNmodelElement;

/**
 * TreeEditPart for all UrnModelElements. Baseclass for most other TreeEditParts.
 * 
 * Handles disposal of image.
 * 
 * @author pchen
 */
public class KPIUrnModelElementTreeEditPart extends UrnAbstractTreeEditPart implements Adapter {
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
    public KPIUrnModelElementTreeEditPart(Object model) {
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
        }
        super.deactivate();
    }

    /**
     * @see org.eclipse.gef.EditPart#getRoot()
     */
    public RootEditPart getRoot() {
        if (getParent() == null)
            return null;
        else
            return getParent().getRoot();
    }

    /**
     * Undoes any registration performed by {@link #register()}. The provided base classes will correctly unregister their visuals.
     */
    protected void unregister() {
        unregisterAccessibility();
        unregisterVisuals();
        if (getRoot() != null)
            unregisterModel();
    }

    /**
     * When something is changed, refresh. We are also refreshing the parent so that elements can be reordered if renamed
     * 
     */
    public void notifyChanged(Notification notification) {
        if (notification.getEventType() != Notification.REMOVING_ADAPTER && getRoot() != null) {
            
            if (getWidget().isDisposed()) return;
            try {
                refreshChildren();
            } catch (Exception ex) {
                // Bug 475: should be resolved but leaving code here as defense in depth.
                // seems to happen in very complex models after very quick changes. 
                // probably during the quick moment where the model is inconsistent.  
                System.out.println("quick ugly hack; trying to prevent weird happenings in UI "); //$NON-NLS-1$
                getChildren().clear();
                try {
                    refreshChildren();
                } catch (Exception ex2) {
                    System.out.println("Even our quick ugly hack didn't work."); //$NON-NLS-1$
                }
            }
            refreshVisuals();

            // get rid of elements that were deleted.
            if (getModel() instanceof EObject && ((EObject) getModel()).eContainer() == null)
                getParent().refresh();

            // refresh parent to reorder children if name changes.
            if (notification.getFeature() instanceof EAttributeImpl && ((EAttributeImpl) notification.getFeature()).getName().equals("name")) { //$NON-NLS-1$
                getParent().refresh();
            }

            // force a refresh.
            getText();
            getImage();
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
            if (getModel() instanceof Variable)
                propertySource = new VariablePropertySource((EObject) getModel());
            else
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

}
