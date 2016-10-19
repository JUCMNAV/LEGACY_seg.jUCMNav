package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import urn.URNspec;
import urn.dyncontext.DynamicContextGroup;

/**
 * This class is one of the top level edit parts for the dynamic context view.
 * 
 * @author aprajita
 * 
 */
public class URNspecDynamicContextTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            The URNspec model
     */
    public URNspecDynamicContextTreeEditPart(URNspec model) {
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
     * Stops listening to both the URNspec and the UCMspec.
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
     * @return the icon associated with URNspec
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of {@link DynamicContextGroup}
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getURNspec().getDynamicContextGroups());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * 
     * @return the URNspec
     */
    private URNspec getURNspec() {
        return (URNspec) getModel();
    }

    /**
     * @return the label for the folder containing dynamic contexts.
     */
    protected String getText() {
        return Messages.getString("URNspecDynamicContextTreeEditPart.urnDynamicContexts"); //$NON-NLS-1$
    }
}
