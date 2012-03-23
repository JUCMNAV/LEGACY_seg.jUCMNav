package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ContributionChangeComponentEditPolicy;
import seg.jUCMNav.model.util.URNNamingHelper;

/**
 * This class is the edit part for a contribution change.
 * 
 * @author jkealey
 * 
 */
public class ContributionChangeTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The ContributionChange model
     */
    public ContributionChangeTreeEditPart(ContributionChange model) {
        super(model);
    }

    /**
     * Listens to ContributionChange
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getContributionChange().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        if (!isInherited())
            installEditPolicy(EditPolicy.COMPONENT_ROLE, new ContributionChangeComponentEditPolicy());
    }

    /**
     * Stops listening to the ContributionChange.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getContributionChange().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the ContributionChange
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/info16.gif")); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * Variables ContributionChange have no children.
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 
     * @return the ContributionChange
     */
    private ContributionChange getContributionChange() {
        return (ContributionChange) getModel();
    }

    /**
     * 
     * @return the Contribution associated with the ContributionChange
     */
    private Contribution getContribution() {
        return getContributionChange().getContribution();
    }

    /**
     * Is this element inherited from another contribution context? This depends on the edit part and not the model instance; the model instance is not duplicated, the edit
     * part is.
     * 
     * @return Is this element inherited from another contribution context?
     */
    private boolean isInherited() {
        if (getParent() == null || getParent().getParent() == null)
            return false;
        return !((ContributionContext) getParent().getParent().getModel()).getChanges().contains(getModel());
    }

    /**
     * @return the ContributionChange name. Inherited elements are grayed out {@link #isInherited()}
     */
    protected String getText() {

        if (widget == null)
            return ""; //$NON-NLS-1$

        if (isInherited())
            ((TreeItem) widget).setForeground(DARKGRAY);
        else
            ((TreeItem) widget).setForeground(BLACK);

        if (getContributionChange() == null)
            return ""; //$NON-NLS-1$
        else
        {
            return URNNamingHelper.getName(getContributionChange());
        }
    }
}
