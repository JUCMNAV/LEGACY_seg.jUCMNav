package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ScenarioConditionComponentEditPolicy;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.scenario.ScenarioDef;
import urncore.Condition;

/**
 * This class is the root edit part for a pre/post condition.
 * 
 * @author jkealey
 * 
 */
public class ConditionTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            the pre/post condition
     */
    public ConditionTreeEditPart(Condition model) {
        super(model);
    }

    /**
     * Listens to a condition
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getCondition().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        if (!isInherited())
            installEditPolicy(EditPolicy.COMPONENT_ROLE, new ScenarioConditionComponentEditPolicy());
    }

    /**
     * Stops listening to the condition
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getCondition().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with condition
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/condition.gif")); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * Conditions have no children.
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 
     * @return the condition
     */
    private Condition getCondition() {
        return (Condition) getModel();
    }

    /**
     * Is this condition inherited from another scenario? This depends on the edit part and not the model instance; the model instance is not duplicated, the
     * edit part is.
     * 
     * @return Is this condition inherited from another scenario?
     */
    public boolean isInherited() {
        if (getParent() == null)
            return false;
        return !((ScenarioDef) getParent().getParent().getModel()).getPreconditions().contains(getModel())
                && !((ScenarioDef) getParent().getParent().getModel()).getPostconditions().contains(getModel());
    }

    /**
     * Uses {@link URNNamingHelper} to return the condition's label. Inherited (@see {@link #isInherited()} elements are grayed out.
     * 
     * @return the condition label.
     */
    protected String getText() {
        if (widget != null) {
            if (isInherited())
                ((TreeItem) widget).setForeground(DARKGRAY);
            else
                ((TreeItem) widget).setForeground(BLACK);
        }
        return URNNamingHelper.getName(getCondition());
    }

}
