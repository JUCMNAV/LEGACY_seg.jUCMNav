/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.StrategiesGroup;

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

/**
 * TreeEditPart for Strategies Group
 * 
 * @author Jean-François Roy
 * 
 */
public class StrategiesGroupTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            the strategy group
     */
    public StrategiesGroupTreeEditPart(StrategiesGroup model) {
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
     * @return the strategy group
     */
    public StrategiesGroup getStrategiesGroup() {
        return (StrategiesGroup) getModel();
    }

    /**
     * Returns the icon
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * @return the sorted list of Strategy Group
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getStrategiesGroup().getStrategies());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget != null && !widget.isDisposed()) {
            if (getStrategiesGroup().getStrategies().size() == 0)
                ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
            else
                ((TreeItem) widget).setForeground(ColorManager.BLACK);
        }
        getImage();
        super.refreshVisuals();
    }
}
