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
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.EObjectClassNameComparator;

/**
 * This class is the root edit part for the stategies view. 
 * 
 * @author Jean-François Roy
 *
 */
public class GRLspecStrategyTreeEditPart extends StrategyUrnModelElementTreeEditPart {
    
    /**
     * @param model
     *          The GRLspec model
     */
    public GRLspecStrategyTreeEditPart(GRLspec model) {
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
     * @return the sorted list of Strategy Group
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getGRLspec().getGroups());
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
        return Messages.getString("GRLspecStrategyTreeEditPart.grlEvaluationStrategies"); //$NON-NLS-1$
    }
}
