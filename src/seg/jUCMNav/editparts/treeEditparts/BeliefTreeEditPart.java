package seg.jUCMNav.editparts.treeEditparts;

import grl.Belief;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.GRLNodeComponentEditPolicy;

/**
 * TreeEditPart for the Beliefs
 * 
 * @author Jean-François Roy
 * 
 */
public class BeliefTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            The belief
     */
    public BeliefTreeEditPart(Belief model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLNodeComponentEditPolicy());
    }

    /**
     * Returns an image representing the Intentional element.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/Belief16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }
}
