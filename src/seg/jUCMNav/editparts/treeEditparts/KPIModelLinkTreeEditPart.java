package seg.jUCMNav.editparts.treeEditparts;

import grl.kpimodel.KPIModelLink;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.KPIModelLinkComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for the KPIModelLink.
 * 
 * @author pchen
 * 
 */
public class KPIModelLinkTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the KPIModelLink
     */
    public KPIModelLinkTreeEditPart(Object model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new KPIModelLinkComponentEditPolicy());
    }

    /**
     * @return the KPIModelLink definition
     */
    protected KPIModelLink getKPIModelLink() {
        return (KPIModelLink) getModel();
    }

    /**
     * Returns the icon appropriate for this element type
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            if (getKPIModelLink() instanceof KPIModelLink) {
                KPIModelLink kpiModelLink = (KPIModelLink) getKPIModelLink();
                if (getParent().getModel() == kpiModelLink.getKpiInformationElementSrc()) {
                    setImage(JUCMNavPlugin.getImage("icons/KPIModelLinkSrc16.gif")); //$NON-NLS-1$
                } else if (getParent().getModel() == kpiModelLink.getIndDest()) {
                    setImage(JUCMNavPlugin.getImage("icons/KPIModelLinkDest16.gif")); //$NON-NLS-1$
                } else {
                    setImage(JUCMNavPlugin.getImage("icons/KPIModelLink16.gif")); //$NON-NLS-1$
                }
            }
        }

        return super.getImage();
    }

    /**
     * Sets unused KPIModelLink to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget == null)
            return;
        if (getKPIModelLink().getRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
