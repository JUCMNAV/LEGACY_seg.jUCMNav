package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.StrategiesGroupComponentEditPolicy;
import seg.jUCMNav.editpolicies.layout.StrategiesGroupLayoutEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * TreeEditPart for a timepoint group in the dynamic context view
 * 
 * @author aprajita
 * 
 */
public class TimepointGroupTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the group
     */
    public TimepointGroupTreeEditPart(TimepointGroup model) {
        super(model);
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new StrategiesGroupComponentEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new StrategiesGroupLayoutEditPolicy());
    }

    /**
     * 
     * @return the timepoint group
     */
    public TimepointGroup getTimepointGroup() {
        return (TimepointGroup) getModel();
    }

    /**
     * Returns the icon for a timepoint group.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * @return the sorted list of Timepoint
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getTimepointGroup().getTimepoints());
        Collections.sort(list, new Comparator<Timepoint>() {
        	@Override
        	public int compare(Timepoint t1, Timepoint t2) {
                return t1.getTimepoint().compareTo(t2.getTimepoint());
            }
		});
        return list;
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
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
