package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import ucm.UCMspec;

/**
 * This class is the root edit part for the scenarios view.
 * 
 * @author jkealey
 * 
 */
public class UCMspecScenarioTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The UCMspec model
     */
    public UCMspecScenarioTreeEditPart(UCMspec model) {
        super(model);
    }

    /**
     * Listens to UCMspec
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getUCMspec().eAdapters().add(this);
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
            getUCMspec().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the UCMspec
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of Scenario Groups
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getUCMspec().getScenarioGroups());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * 
     * @return the UCMspec
     */
    private UCMspec getUCMspec() {
        return (UCMspec) getModel();
    }

    /**
     * @return the UCMspec name.
     */
    protected String getText() {
        return Messages.getString("UCMspecScenarioTreeEditPart.UcmScenarios"); //$NON-NLS-1$
    }
}
