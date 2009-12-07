package seg.jUCMNav.editparts.strategyTreeEditparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.views.property.URNElementPropertySource;
import seg.jUCMNav.views.property.VariablePropertySource;
import ucm.scenario.Variable;

/**
 * TreeEditPart for all UrnModelElements. Baseclass for most other TreeEditParts.
 * 
 * Handles disposal of image.
 * 
 * @author Jean-François Roy
 */
public class StrategyUrnModelElementTreeEditPart extends UrnModelElementTreeEditPart implements Adapter {

    public final Color DARKGRAY = ColorManager.DARKGRAY;
    public final Color BLACK = ColorManager.BLACK;
    public final Color GRAY = ColorManager.GRAY;
    public final Color WHITE = ColorManager.WHITE;

    // The property source associated with this model element.
    protected IPropertySource propertySource = null;

    /**
     * @param model
     *            the model element being edited.
     */
    public StrategyUrnModelElementTreeEditPart(Object model) {
        super(model);
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

            if (getWidget().isDisposed())
                return;

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

            // force a refresh of colors.
            getText();

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

}