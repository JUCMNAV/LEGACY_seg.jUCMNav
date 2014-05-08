package seg.jUCMNav.editparts.treeEditparts;

import fm.Feature;
import grl.IntentionalElement;
import grl.IntentionalElementType;
import grl.kpimodel.Indicator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.IntentionalElementComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for the intentional elements
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class IntentionalElementTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the intentionalElement definition
     */
    public IntentionalElementTreeEditPart(Object model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new IntentionalElementComponentEditPolicy());
    }

    /**
     * @return the intentional element definition
     */
    protected IntentionalElement getElement() {
        return (IntentionalElement) getModel();
    }

    /**
     * Returns the icon appropriate for this element type
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            if (getElement().getType().getValue() == IntentionalElementType.GOAL)
                setImage((JUCMNavPlugin.getImage("icons/Goal16.gif"))); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.SOFTGOAL)
                setImage((JUCMNavPlugin.getImage("icons/Softgoal16.gif"))); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.TASK)
            	if (getElement() instanceof Feature)
            		setImage((JUCMNavPlugin.getImage("icons/Feature16.gif"))); //$NON-NLS-1$
            	else
            		setImage((JUCMNavPlugin.getImage("icons/Task16.gif"))); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.RESSOURCE)
                setImage((JUCMNavPlugin.getImage("icons/Resource16.gif"))); //$NON-NLS-1$
            else if (getElement().getType().getValue() == IntentionalElementType.INDICATOR)
                setImage((JUCMNavPlugin.getImage("icons/Indicator16.gif"))); //$NON-NLS-1$
        }

        return super.getImage();
    }

    /**
     * @return the sorted list of maps and the component and responsibility definition labels
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getElement().getLinksSrc());
        list.addAll(getElement().getLinksDest());

        if (getElement() instanceof Indicator) {
            list.addAll(((Indicator) getElement()).getKpiModelLinksDest());
        }

        return list;

    }

    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget == null)
            return;
        if (getElement().getRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
