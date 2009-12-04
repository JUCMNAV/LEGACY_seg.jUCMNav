package seg.jUCMNav.editparts.treeEditparts;

import grl.Actor;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ActorComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for the actors
 * 
 * @author Jean-François Roy
 * 
 */
public class ActorTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the actor
     */
    public ActorTreeEditPart(Actor model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActorComponentEditPolicy());
    }

    /**
     * @return the actor definition
     */
    protected Actor getActor() {
        return (Actor) getModel();
    }

    /**
     * Returns the icon
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/GRLActor16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (widget == null)
            return;
        if (getActor().getContRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);

        super.refreshVisuals();
    }
}
