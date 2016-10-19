package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.DynamicContextComponentEditPolicy;
import seg.jUCMNav.views.property.ChangePropertySource;
import seg.jUCMNav.views.property.VariablePropertySource;
import ucm.scenario.Variable;
import urn.dyncontext.Change;

/**
 * TreeEditPart for Change in the Dynamic Context view
 * 
 * @author aprajita
 * 
 */
public class ChangeTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            Change
     */
    public ChangeTreeEditPart(Change model) {
        super(model);
    }
    
    /**
     * Listens to the model element.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {

        if (!isActive() && getChange() != null)
            getChange().eAdapters().add(this);
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DynamicContextComponentEditPolicy()); // deletion
    }

    /**
     * 
     * Stops listening to the model element and destroys image.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getChange() != null) {
            getChange().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon for a change
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/Change.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * 
     * @return the actual {@link Change}
     */
    public Change getChange() {
    	Change change = null;
        change = (Change) getModel();
        return change;
    }

    /**
     * Returns the change's name and sets the label as grayed out if it is inherited {@link #isInherited()}
     */
    protected String getText() {
    	if (widget != null && !widget.isDisposed()) {
        	((TreeItem) widget).setForeground(BLACK);
        }

        return super.getText();
    }
    
    /**
     * If selected, set the background color.
     */
    public void setSelected(boolean selected) {

        // bug 411
        if (!checkTreeItem())
            return;

        if (selected) {
            ((TreeItem) widget).setBackground(GRAY);
        } else {
            ((TreeItem) widget).setBackground(WHITE);
        }
        // refreshVisuals();
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
                propertySource = new ChangePropertySource((EObject) getModel());
        }
        return propertySource;

    }

}
