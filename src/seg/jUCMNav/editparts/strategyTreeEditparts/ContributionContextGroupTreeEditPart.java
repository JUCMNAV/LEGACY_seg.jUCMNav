/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.ContributionContextGroup;

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
 * TreeEditPart for Contribution Context Group
 * 
 * @author jkealey
 * 
 */
public class ContributionContextGroupTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            the group
     */
    public ContributionContextGroupTreeEditPart(ContributionContextGroup model) {
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
     * @return the group
     */
    public ContributionContextGroup getGroup() {
        return (ContributionContextGroup) getModel();
    }

    /**
     * Returns the icon for a group.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * @return the sorted list of Scenario Group
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getGroup().getContribs());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
/*        if (getScenarioGroup().getScenarios().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else*/
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
