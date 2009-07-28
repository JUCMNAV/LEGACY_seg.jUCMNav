package seg.jUCMNav.editparts.kpiTreeEditparts;

import grl.GRLspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.DelegatingElementComparator;

/**
 * This class is the GRLspec edit part for the KPI view.
 * 
 * @author pchen
 * 
 */
public class GRLspecKPITreeEditPart extends KPIUrnModelElementTreeEditPart {
    /**
     * @param model
     *            The GRLspec model
     */
    public GRLspecKPITreeEditPart(GRLspec model) {
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
     * Stops listening to the GRLspec.
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
            setImage(JUCMNavPlugin.getImage("icons/folder16.gif")); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of Indicator Groups
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getGRLspec().getIndicatorGroup());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    private GRLspec getGRLspec() {
        return (GRLspec) getModel();
    }

    /**
     * @return the GRLspec name.
     */
    protected String getText() {
        return Messages.getString("GRLspecKPITreeEditPart.grlKPIs"); //$NON-NLS-1$
    }

    /**
     * 
     * @return the node.
     */
    private GRLspec getNode() {
        return (GRLspec) getModel();
    }
}
