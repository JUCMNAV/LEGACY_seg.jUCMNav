package seg.jUCMNav.editparts.kpiTreeEditparts;

import grl.kpimodel.IndicatorGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.IndicatorGroupComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.util.DelegatingElementComparator;

/**
 * TreeEditPart for Indicator Group
 * 
 * @author pchen
 * 
 */
public class IndicatorGroupTreeEditPart extends KPIUrnModelElementTreeEditPart {
    /**
     * @param model
     */
    public IndicatorGroupTreeEditPart(IndicatorGroup model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new IndicatorGroupComponentEditPolicy());
    }

    public IndicatorGroup getIndicatorGroup() {
        return (IndicatorGroup) getModel();
    }

    /**
     * Returns the icon
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/folder16.gif")); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * @return the sorted list of Indicators
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getIndicatorGroup().getIndicators());
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * Sets unused group to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget != null && !widget.isDisposed()) {
            if (getIndicatorGroup().getIndicators().size() == 0)
                ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
            else
                ((TreeItem) widget).setForeground(ColorManager.BLACK);
        }
        getImage();
        super.refreshVisuals();
    }

    /**
     * 
     * @return the node.
     */
    private IndicatorGroup getNode() {
        return (IndicatorGroup) getModel();
    }

}