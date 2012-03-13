package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.ScenarioDef;
import urncore.URNmodelElement;

/**
 * Editpart for textual strings that are children of Scenarios.
 * 
 * @author jkealey
 */
public class ScenarioLabelTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * The parent.
     */
    private URNmodelElement root;
    protected URNmodelElement getRootElement() { return root; }
    protected void setRootElement(URNmodelElement val) { this.root = val;  }
    
    /**
     * @param model
     *            the child
     * @param root
     *            the scenario
     */
    public ScenarioLabelTreeEditPart(Object model, URNmodelElement root) {
        super(model);
        setRootElement(root);
    }

    /**
     * Activate listeners
     */
    public void activate() {
        if (!isActive()) {
            getRootElement().eAdapters().add(this);
        }

        setFlag(FLAG_ACTIVE, true);

        activateEditPolicies();

        List c = getChildren();
        for (int i = 0; i < c.size(); i++)
            if (c.get(i) instanceof EditPart)
                ((EditPart) c.get(i)).activate();

        fireActivated();
    }

    /**
     * Deactivate listeners
     */
    public void deactivate() {
        if (isActive()) {
            getRootElement().eAdapters().remove(this);
        }
        List c = getChildren();
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i) instanceof EditPart)
                ((EditPart) c.get(i)).deactivate();
        }

        deactivateEditPolicies();

        setFlag(FLAG_ACTIVE, false);
        fireDeactivated();
    }

    /**
     * Labels don't have any properties.
     */
    protected IPropertySource getPropertySource() {
        return null;
    }

    /**
     * @return the list of scenario children depending on the folder type. uses {@link ScenarioUtils}.
     */
    public List getModelChildren() {
         
        ArrayList list = new ArrayList();
        if (getRootElement() instanceof ScenarioDef) {
            ScenarioDef def = (ScenarioDef) getRootElement();
            if (getLabel().equals(Messages.getString("ScenarioLabelTreeEditPart.IncludedScenarios"))) { //$NON-NLS-1$
                list.addAll(ScenarioUtils.getDefinedIncludedScenarios(def));
            } else if (getLabel().equals(Messages.getString("ScenarioLabelTreeEditPart.StartPoints"))) { //$NON-NLS-1$
                list.addAll(ScenarioUtils.getDefinedStartPoints(def));
            } else if (getLabel().equals(Messages.getString("ScenarioLabelTreeEditPart.Preconditions"))) { //$NON-NLS-1$
                list.addAll(ScenarioUtils.getDefinedPreconditions(def));
            } else if (getLabel().equals(Messages.getString("ScenarioLabelTreeEditPart.Initializations"))) { //$NON-NLS-1$
                list.addAll(ScenarioUtils.getDefinedInitializations(def));
            } else if (getLabel().equals(Messages.getString("ScenarioLabelTreeEditPart.EndPoints"))) { //$NON-NLS-1$
                list.addAll(ScenarioUtils.getDefinedEndPoints(def));
            } else if (getLabel().equals(Messages.getString("ScenarioLabelTreeEditPart.Postconditions"))) { //$NON-NLS-1$
                list.addAll(ScenarioUtils.getDefinedPostconditions(def));
            }
            }

        return list;
    }

    /**
     * Their label is their model string.
     * 
     * @return the label associated with this string.
     */
    protected String getLabel() {
        return (String) getModel();
    }

    /**
     * Their label text is inferred from the model element. (Mapping)
     */
    protected String getText() {
        return (String) getModel();
    }

    /**
     * Returns the icon for the folder.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/folder16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }
}