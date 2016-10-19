package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextUrnModelElementTreeEditPart;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import urn.URNspec;

/**
 * This class is the root edit part for the timepoint group list, but actually listens to the URNspec.
 * 
 * @author aprajita
 * 
 */
public class TimepointGroupListTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	/**
     * @param model
     *            The URNspec model
     */
    public TimepointGroupListTreeEditPart(URNspec model) {
        super(model);
    }

    /**
     * Listens to URNspec
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
        	getURNspec().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * Stops listening to the URNspec
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getURNspec().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the folder
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of groups
     */
    protected List getModelChildren() {
    	ArrayList list = new ArrayList();
        list.addAll(getURNspec().getTimepointGroups());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * @return the label for the folder containing the groups 
     */
    protected String getText() {
        return Messages.getString("TimepointGroupListTreeEditPart.Timepoints"); //$NON-NLS-1$
    }

    /**
     * 
     * @return the URNspec
     */
    private URNspec getURNspec() {
        return (URNspec) getModel();
    }
}
