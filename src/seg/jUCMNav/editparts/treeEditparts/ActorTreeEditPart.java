/**
 * 
 */
package seg.jUCMNav.editparts.treeEditparts;

import grl.Actor;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ActorComponentEditPolicy;

/**
 * TreeEditPart for the actors
 * 
 * @author Jean-Fran�ois Roy
 *
 */
public class ActorTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *          the actor
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
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/GRLActor16.gif")).createImage()); //$NON-NLS-1$
        }
        return super.getImage();
    }
    
    /**
     * Sets unused definitions to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getActor().getContRefs().size() == 0)
            ((TreeItem) widget).setForeground(new Color(null, 150, 150, 150));
        else
            ((TreeItem) widget).setForeground(new Color(null, 0, 0, 0));

        super.refreshVisuals();
    }
}
