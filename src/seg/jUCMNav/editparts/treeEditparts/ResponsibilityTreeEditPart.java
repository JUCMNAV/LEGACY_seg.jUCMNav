package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ResponsibilityComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import urncore.Responsibility;

/**
 * TreeEditPart for a responsibility definition.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ResponsibilityTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the responsibility definition
     */
    public ResponsibilityTreeEditPart(Responsibility model) {
        super(model);
    }

    /**
     * 
     * @return the responsibility definition
     */
    protected Responsibility getResponsibility() {
        return (Responsibility) getModel();
    }

    /**
     * @return the icon associated with responsibilities
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/Resp16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ResponsibilityComponentEditPolicy());
    }

    /**
     * Unreferenced responsibilities are displayed in a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget == null)
            return;
        if (getResponsibility().getRespRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);

        super.refreshVisuals();
    }
}