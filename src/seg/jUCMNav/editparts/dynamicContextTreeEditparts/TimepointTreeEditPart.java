package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.DynamicContextComponentEditPolicy;
import seg.jUCMNav.views.property.TimepointPropertySource;
import seg.jUCMNav.views.property.VariablePropertySource;
import ucm.scenario.Variable;
import urn.dyncontext.Timepoint;

/**
 * TreeEditPart for a timepoint in the dynamic context view
 * 
 * @author aprajita
 * 
 */
public class TimepointTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the timepoint
     */
    public TimepointTreeEditPart(Timepoint model) {
        super(model);
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
    	installEditPolicy(EditPolicy.COMPONENT_ROLE, new DynamicContextComponentEditPolicy());
    }

    /**
     * 
     * @return the timepoint
     */
    public Timepoint getTimepoint() {
        return (Timepoint) getModel();
    }


    /**
     * Returns the icon for the {@link Timepoint}
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/Timepoint.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
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
     * Returns the timepoint's name{@link #isInherited()}
     */
    protected String getText() {
        if (widget != null && !widget.isDisposed()) {
        	((TreeItem) widget).setForeground(BLACK);
        }
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String name = df.format(getTimepoint().getTimepoint());
         
        return name;
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
                propertySource = new TimepointPropertySource((EObject) getModel());
        }
        return propertySource;

    }
}
