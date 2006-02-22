/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.GRLspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.EObjectClassNameComparator;

/**
 * This class is the root edit part for the Scenario view. 
 * 
 * @author Jean-François Roy
 *
 */
public class GRLspecScenarioTreeEditPart extends ScenarioUrnModelElementTreeEditPart {
    
    /**
     * @param editor
     *          The UCMNavMultiPageEditor
     */
    public GRLspecScenarioTreeEditPart(GRLspec model) {
        super(model);
    }

    /**
     * Listens to GRLspec
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getGRLspec().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * Stops listening to both the URNspec and the UCMspec.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getGRLspec().eAdapters().remove(this);
        }
        super.deactivate();
    }
    
    /**
     * @return the icon associated with URNspec
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")).createImage()); //$NON-NLS-1$
        return super.getImage();
    }
    
    /**
     * @return the sorted list of Scenario Group
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getGRLspec().getEvaluationGroups());
        Collections.sort(list, new EObjectClassNameComparator());
        return list;
    }

    private GRLspec getGRLspec(){
        return (GRLspec)getModel();
    }
    
    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return "GRL Scenarios";
    }
}
