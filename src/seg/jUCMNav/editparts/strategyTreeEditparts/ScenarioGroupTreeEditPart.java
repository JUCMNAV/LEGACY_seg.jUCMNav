/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.StrategiesGroupComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import ucm.scenario.ScenarioGroup;

/**
 * TreeEditPart for Scenarios Group
 * 
 * @author jkealey
 *
 */
public class ScenarioGroupTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model the group
     */
    public ScenarioGroupTreeEditPart(ScenarioGroup model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new StrategiesGroupComponentEditPolicy());
    }
    
    /**
     * 
     * @return the scenario group
     */
    public ScenarioGroup getScenarioGroup(){
        return (ScenarioGroup)getModel();
    }
    
    /**
     * Returns the icon for a scenario group. 
     */
    protected Image getImage() {
        if (super.getImage() == null) {       
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")).createImage()); //$NON-NLS-1$
        }
        return super.getImage();
    }
    
    /**
     * @return the sorted list of Scenario Group
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getScenarioGroup().getScenarios());
        Collections.sort(list, new EObjectClassNameComparator());
        return list;
    }
    
    /**
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getScenarioGroup().getScenarios().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
