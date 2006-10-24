package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ComponentElementComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;

/**
 * TreeEditPart for ComponentElements.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ComponentTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the component definition
     */
    public ComponentTreeEditPart(ComponentElement model) {
        super(model);
    }

    /**
     * @return the component definition
     */
    protected ComponentElement getComp() {
        return (ComponentElement) getModel();
    }

    /**
     * Returns the icon appropriate for this component's kind
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            if (getComp() instanceof Component) {
                setImage(getComponentImage(((Component) getComp()).getKind()));
            } else {
                setImage(getComponentImage(ComponentKind.TEAM_LITERAL));
            }

        }
        return super.getImage();
    }

    /**
     * Used by both definitions and their references
     * 
     * @param kind
     *            the ComponentKind for which to obtain the image
     * @return the icon associated with the ComponentKind
     */
    protected static Image getComponentImage(ComponentKind kind) {
        switch (kind.getValue()) {
        case ComponentKind.AGENT:
            return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Agent16.gif")).createImage(); //$NON-NLS-1$
        case ComponentKind.ACTOR:
            return ((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Actor16.gif")).createImage()); //$NON-NLS-1$
        case ComponentKind.OBJECT:
            return ((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Object16.gif")).createImage()); //$NON-NLS-1$
        case ComponentKind.PROCESS:
            return ((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Process16.gif")).createImage()); //$NON-NLS-1$
        default:
            return ((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage()); //$NON-NLS-1$
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentElementComponentEditPolicy());
    }

    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getComp().getContRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);

        super.refreshVisuals();
    }
}