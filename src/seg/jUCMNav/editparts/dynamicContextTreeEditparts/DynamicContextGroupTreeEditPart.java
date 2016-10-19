package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.StrategiesGroupComponentEditPolicy;
import seg.jUCMNav.editpolicies.layout.StrategiesGroupLayoutEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import urn.dyncontext.DynamicContextGroup;

/**
 * TreeEditPart for DynamicContextGroup in the Dynamic Context view
 * 
 * @author aprajita
 * 
 */
public class DynamicContextGroupTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the group
     */
    public DynamicContextGroupTreeEditPart(DynamicContextGroup model) {
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
     * @return the dynamic context group
     */
    public DynamicContextGroup getDynamicContextGroup() {
        return (DynamicContextGroup) getModel();
    }

    /**
     * Returns the icon for a dynamic context group.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * @return the sorted list of Dynamic Context Group
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getDynamicContextGroup().getContexts());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getDynamicContextGroup().getContexts().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
