package seg.jUCMNav.editparts.treeEditparts;

import grl.kpimodel.KPIInformationElement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.KPIInformationElementComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for the KPIInformationElements
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the KPIInformationElement definition
     */
    public KPIInformationElementTreeEditPart(Object model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new KPIInformationElementComponentEditPolicy());
    }

    /**
     * @return the KPIInformationElement definition
     */
    protected KPIInformationElement getElement() {
        return (KPIInformationElement) getModel();
    }

    /**
     * Returns the icon appropriate for this element type
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/Dimension16.gif")); //$NON-NLS-1$
        }

        return super.getImage();
    }

    /**
     * @return the sorted list of maps and the component and responsibility definition labels
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.addAll(getElement().getKpiModelLinksSrc());

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
