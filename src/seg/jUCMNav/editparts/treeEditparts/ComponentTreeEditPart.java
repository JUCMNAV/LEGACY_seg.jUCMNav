package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ComponentComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.wrappers.ComponentTreeWrapper;
import urncore.Component;
import urncore.ComponentKind;

/**
 * TreeEditPart for Components.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ComponentTreeEditPart extends UrnModelElementTreeEditPart {

    private ComponentTreeWrapper wrapper;

    /**
     * @param model
     *            the component definition
     */
    public ComponentTreeEditPart(Component model) {
        super(model);
    }

    /**
     * 
     * @param model
     *            the resx-component wrapper
     */
    public ComponentTreeEditPart(ComponentTreeWrapper model) {
        super(model.getComp());
        this.wrapper = model;
    }

    /**
     * @return the component definition
     */
    protected Component getComp() {
        return (Component) getModel();
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
            return (JUCMNavPlugin.getImage("icons/Agent16.gif")); //$NON-NLS-1$
        case ComponentKind.ACTOR:
            return ((JUCMNavPlugin.getImage("icons/Actor16.gif"))); //$NON-NLS-1$
        case ComponentKind.OBJECT:
            return ((JUCMNavPlugin.getImage("icons/Object16.gif"))); //$NON-NLS-1$
        case ComponentKind.PROCESS:
            return ((JUCMNavPlugin.getImage("icons/Process16.gif"))); //$NON-NLS-1$
        default:
            return ((JUCMNavPlugin.getImage("icons/Component16.gif"))); //$NON-NLS-1$
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentComponentEditPolicy(wrapper));
    }

    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget == null)
            return;
        if (getComp().getContRefs().size() == 0 && wrapper == null)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);

        super.refreshVisuals();
    }
}