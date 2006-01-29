package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ResponsibilityComponentEditPolicy;
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
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage()); //$NON-NLS-1$
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
        if (getResponsibility().getRespRefs().size() == 0)
            ((TreeItem) widget).setForeground(new Color(null, 150, 150, 150));
        else
            ((TreeItem) widget).setForeground(new Color(null, 0, 0, 0));

        super.refreshVisuals();
    }
}