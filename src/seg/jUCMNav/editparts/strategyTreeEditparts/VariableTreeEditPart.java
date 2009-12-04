package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.VariableComponentEditPolicy;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.Variable;

/**
 * This class is the root edit part for a variable.
 * 
 * @author jkealey
 * 
 */
public class VariableTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The Variable model
     */
    public VariableTreeEditPart(Variable model) {
        super(model);
    }

    /**
     * Listens to Variable
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getVariable().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new VariableComponentEditPolicy());
    }

    /**
     * Stops listening to the variable.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getVariable().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the variable.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            if (ScenarioUtils.sTypeBoolean.equals(getVariable().getType())) {
                setImage(JUCMNavPlugin.getImage("icons/Boolean16.gif")); //$NON-NLS-1$
            } else if (ScenarioUtils.sTypeInteger.equals(getVariable().getType())) {
                setImage(JUCMNavPlugin.getImage("icons/Integer16.gif")); //$NON-NLS-1$
            } else {
                setImage(JUCMNavPlugin.getImage("icons/Enumeration16.gif")); //$NON-NLS-1$
            }
        }
        return super.getImage();
    }

    /**
     * Variables have no children.
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 
     * @return the variable
     */
    private Variable getVariable() {
        return (Variable) getModel();
    }

    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return getVariable().getName();
    }
}
