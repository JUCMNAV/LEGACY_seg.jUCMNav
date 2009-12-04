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
 * This class is the root edit part for the variables/enumeration lists, but actually listens to the UCMspec.
 * 
 * @author jkealey
 * 
 */
public class VariableListTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    private boolean isEnumerations;

    /**
     * @param model
     *            The UCMspec model
     * @param isEnumerations
     *            is this the enumeration list (true) or the variable list (false)
     */
    public VariableListTreeEditPart(UCMspec model, boolean isEnumerations) {
        super(model);
        this.isEnumerations = isEnumerations;
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
        if (isEnumerations)
            list.addAll(getUCMspec().getEnumerationTypes());
        else
            list.addAll(getUCMspec().getVariables());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * @return the label for the folder containg variables or enumerations name.
     */
    protected String getText() {
        if (isEnumerations)
            return Messages.getString("VariableListTreeEditPart.Enumerations"); //$NON-NLS-1$
        else
            return Messages.getString("VariableListTreeEditPart.Variables"); //$NON-NLS-1$
    }

    /**
     * 
     * @return the UCMspec
     */
    private UCMspec getUCMspec() {
        return (UCMspec) getModel();
    }

    /**
     * 
     * @return is this the enumeration list (true) or the variable list (false)
     */
    public boolean isEnumerations() {
        return isEnumerations;
    }
}
