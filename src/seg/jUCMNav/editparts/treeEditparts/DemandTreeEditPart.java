package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.DemandComponentEditPolicy;
import ucm.performance.Demand;

/**
 * TreeEditPart for demands
 * 
 * @author jkealey
 */
public class DemandTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the demand
     */
    public DemandTreeEditPart(Demand model) {
        super(model);
    }

    /**
     * @return the demand
     */
    protected Demand getDemand() {
        return (Demand) getModel();
    }

    /**
     * Returns the icon appropriate for this component's kind
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/Resp16.gif"))); //$NON-NLS-1$
        return super.getImage();

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DemandComponentEditPolicy());
    }

}