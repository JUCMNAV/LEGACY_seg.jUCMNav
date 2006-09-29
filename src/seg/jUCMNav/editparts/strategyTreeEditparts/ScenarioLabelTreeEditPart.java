package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.scenario.ScenarioDef;

/**
 * Editpart for textual strings that are children of Scenarios. 
 * 
 * @author jkealey
 */
public class ScenarioLabelTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    private ScenarioDef root;

    /**
     * @param model
     */
    public ScenarioLabelTreeEditPart(Object model, ScenarioDef root) {
        super(model);
        this.root = root;
    }

    /**
     * Activate listeners
     */
    public void activate() {
        if (!isActive()) {
            ((ScenarioDef) root).eAdapters().add(this);
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
            ((ScenarioDef) root).eAdapters().add(this);
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
     * Return the sorted list of component or responsibility definitions.
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        if (getLabel().equals("Included scenarios")) {
        	list.addAll(root.getIncludedScenarios());
        } else if (getLabel().equals("Start points")) {
        	list.addAll(root.getStartPoints());
		} else if (getLabel().equals("Preconditions")) {
			list.addAll(root.getInitializations());
			list.addAll(root.getPreconditions());
		} else if (getLabel().equals("End points")) {
			list.addAll(root.getEndPoints());
		} else if (getLabel().equals("Postconditions")) {
			list.addAll(root.getPostconditions());
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
//        if (getLabel().equals(Messages.getString("LabelTreeEditPart.components"))) { //$NON-NLS-1$
//            return Messages.getString("LabelTreeEditPart.componentDef"); //$NON-NLS-1$
//        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.responsibilities"))) { //$NON-NLS-1$
//            return Messages.getString("LabelTreeEditPart.responsibilityDef"); //$NON-NLS-1$
//        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.intentionalElementDefs"))){ //$NON-NLS-1$
//            return Messages.getString("LabelTreeEditPart.intentionalElementDefs"); //$NON-NLS-1$
//        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.actorDefs"))){ //$NON-NLS-1$
//            return Messages.getString("LabelTreeEditPart.actorDefs"); //$NON-NLS-1$
//        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.ucmDefs"))){ //$NON-NLS-1$
//            return Messages.getString("LabelTreeEditPart.ucmDefs"); //$NON-NLS-1$
//        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.grlDefs"))){ //$NON-NLS-1$
//            return Messages.getString("LabelTreeEditPart.grlDefs"); //$NON-NLS-1$
//        }
//        else
//            return null;

    }

    /**
     * Returns the icon 
     */
    protected Image getImage() {
        if (super.getImage() == null) {       
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/folder16.gif")).createImage()); //$NON-NLS-1$
        }
        return super.getImage();
    }
}