/**
 * 
 */
package seg.jUCMNav.editparts.scenarioTreeEditparts;

import grl.EvaluationGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.EvaluationGroupComponentEditPolicy;
import seg.jUCMNav.model.util.EObjectClassNameComparator;

/**
 * TreeEditPart for Evaluation Group
 * 
 * @author Jean-François Roy
 *
 */
public class EvaluationGroupTreeEditPart extends ScenarioUrnModelElementTreeEditPart {

    /**
     * @param model
     */
    public EvaluationGroupTreeEditPart(EvaluationGroup model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new EvaluationGroupComponentEditPolicy());
    }
    
    public EvaluationGroup getEvaluationGroup(){
        return (EvaluationGroup)getModel();
    }
    
    /**
     * Returns the icon 
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
        list.addAll(getEvaluationGroup().getScenarios());
        Collections.sort(list, new EObjectClassNameComparator());
        return list;
    }
    
    /**
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getEvaluationGroup().getScenarios().size() == 0)
            ((TreeItem) widget).setForeground(new Color(null, 150, 150, 150));
        else
            ((TreeItem) widget).setForeground(new Color(null, 0, 0, 0));
        getImage();
        super.refreshVisuals();
    }
}
