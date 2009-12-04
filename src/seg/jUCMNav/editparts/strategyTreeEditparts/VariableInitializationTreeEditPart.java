package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editpolicies.element.VariableComponentEditPolicy;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.Variable;

/**
 * This class is the edit part for a variable initialization.
 * 
 * @author jkealey
 * 
 */
public class VariableInitializationTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The Initialization model
     */
    public VariableInitializationTreeEditPart(Initialization model) {
        super(model);
    }

    /**
     * Listens to Initialization
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getInitialization().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        if (!isInherited())
            installEditPolicy(EditPolicy.COMPONENT_ROLE, new VariableComponentEditPolicy());
    }

    /**
     * Stops listening to the Initialization.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getInitialization().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the Initialization
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
     * Variables Initialization have no children.
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 
     * @return the Initialization
     */
    private Initialization getInitialization() {
        return (Initialization) getModel();
    }

    /**
     * 
     * @return the Variable associated with the Initialization
     */
    private Variable getVariable() {
        return getInitialization().getVariable();
    }

    /**
     * Is this element inherited from another scenario? This depends on the edit part and not the model instance; the model instance is not duplicated, the edit
     * part is.
     * 
     * @return Is this element inherited from another scenario?
     */
    private boolean isInherited() {
        if (getParent() == null || getParent().getParent() == null)
            return false;
        return !((ScenarioDef) getParent().getParent().getModel()).getInitializations().contains(getModel());
    }

    /**
     * @return the Initialization name. Inherited elements are grayed out {@link #isInherited()}
     */
    protected String getText() {

        if (widget == null)
            return ""; //$NON-NLS-1$

        if (isInherited())
            ((TreeItem) widget).setForeground(DARKGRAY);
        else
            ((TreeItem) widget).setForeground(BLACK);

        if (getVariable() == null)
            return ""; //$NON-NLS-1$
        else
            return getVariable().getName() + Messages.getString("VariableInitializationTreeEditPart.Equals") + getInitialization().getValue(); //$NON-NLS-1$
    }
}
