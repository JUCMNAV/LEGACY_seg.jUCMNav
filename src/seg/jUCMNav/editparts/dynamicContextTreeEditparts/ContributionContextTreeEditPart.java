package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import grl.ContributionContext;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.DynamicContextComponentEditPolicy;

/**
 * TreeEditPart for Contribution Context in the Dynamic Context view
 * 
 * @author aprajita
 * 
 */
public class ContributionContextTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the contribution context
     */
    public ContributionContextTreeEditPart(ContributionContext model) {
        super(model);
    }
    
    /**
     * Listens to the model element.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {

        if (!isActive() && getContributionContext() != null)
            getContributionContext().eAdapters().add(this);
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DynamicContextComponentEditPolicy()); // deletion
    }

    /**
     * 
     * Stops listening to the model element and destroys image.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getContributionContext() != null) {
            getContributionContext().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon for a contribution context.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/grlstrat16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * 
     * @return the actual {@link ContributionContext}
     */
    private ContributionContext getContributionContext() {
    	ContributionContext contriContext = null;
    	contriContext = (ContributionContext) getModel();
        return contriContext;
    }

}
