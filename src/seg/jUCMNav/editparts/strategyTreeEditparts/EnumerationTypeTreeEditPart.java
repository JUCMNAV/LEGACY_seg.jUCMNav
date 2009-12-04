package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.VariableComponentEditPolicy;
import ucm.scenario.EnumerationType;

/**
 * This class is the root edit part for an enumeration type.
 * 
 * @author jkealey
 * 
 */
public class EnumerationTypeTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The EnumerationType model
     */
    public EnumerationTypeTreeEditPart(EnumerationType model) {
        super(model);
    }

    /**
     * Listens to an enumeration type.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getEnumerationType().eAdapters().add(this);
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
     * Stops listening to the enumeration type.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getEnumerationType().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the enumeration type
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/Enumeration16.gif")); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * Enumeration Types have no children.
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 
     * @return the enumeration type
     */
    private EnumerationType getEnumerationType() {
        return (EnumerationType) getModel();
    }

    /**
     * @return the enumeration type name.
     */
    protected String getText() {
        return getEnumerationType().getName();
    }
}
