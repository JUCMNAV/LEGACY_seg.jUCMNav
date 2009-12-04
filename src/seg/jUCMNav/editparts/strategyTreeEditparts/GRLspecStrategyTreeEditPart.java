package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.GRLspec;
import grl.StrategiesGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.DelegatingElementComparator;

/**
 * This class is one of the top level edit parts for the stategies view.
 * 
 * @author Jean-François Roy
 * 
 */
public class GRLspecStrategyTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The GRLspec model
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
     * @return the icon associated with GRLspec
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of {@link StrategiesGroup}
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getGRLspec().getGroups());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * 
     * @return the GRLspec
     */
    private GRLspec getGRLspec() {
        return (GRLspec) getModel();
    }

    /**
     * @return the label for the folder containing evaluation strategies.
     */
    protected String getText() {
        return Messages.getString("GRLspecStrategyTreeEditPart.grlEvaluationStrategies"); //$NON-NLS-1$
    }
}
