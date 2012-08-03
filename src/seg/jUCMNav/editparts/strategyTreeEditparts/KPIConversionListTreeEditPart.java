package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.GRLspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.DelegatingElementComparator;

/**
 * This class is the root edit part for the KPIConversion list, but actually listens to the GRLspec.
 * 
 * @author jkealey
 * 
 */
public class KPIConversionListTreeEditPart extends StrategyUrnModelElementTreeEditPart {


    /**
     * @param model
     *            The GRLspec model
     */
    public KPIConversionListTreeEditPart(GRLspec model) {
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
     * @return the icon associated with the variable list
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of Variables
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getGRLspec().getKPIConversion());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * @return the label for the folder containg variables or enumerations name.
     */
    protected String getText() {
        return "KPI Conversions";
    }

    /**
     * 
     * @return the GRLspec
     */
    private GRLspec getGRLspec() {
        return (GRLspec) getModel();
    }

    
}
