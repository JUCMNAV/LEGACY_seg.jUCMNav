package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import urn.URNspec;

/**
 * Editpart for textual strings used in the outline such as "Components" and "Responsibilities"
 * 
 * @author Etienne Tremblay
 */
public class LabelTreeEditPart extends UrnModelElementTreeEditPart {

    private URNspec root;

    /**
     * @param model
     */
    public LabelTreeEditPart(Object model, URNspec root) {
        super(model);
        this.root = root;
    }

    /**
     * Activate listeners
     */
    public void activate() {
        if (!isActive()) {
            ((EObject) root.getUrndef()).eAdapters().add(this);
            ((EObject) root.getGrlspec()).eAdapters().add(this);
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
            ((EObject) root.getUrndef()).eAdapters().remove(this);
            ((EObject) root.getGrlspec()).eAdapters().remove(this);
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
        if (getLabel().equals(Messages.getString("LabelTreeEditPart.components"))) //$NON-NLS-1$
            list.addAll(root.getUrndef().getComponents());
        else if (getLabel().equals(Messages.getString("LabelTreeEditPart.responsibilities"))) //$NON-NLS-1$
            list.addAll(root.getUrndef().getResponsibilities());
        else if (getLabel().equals(Messages.getString("LabelTreeEditPart.intentionalElementDefs"))) //$NON-NLS-1$
            list.addAll(root.getGrlspec().getIntElements());
        else if (getLabel().equals(Messages.getString("LabelTreeEditPart.actorDefs"))) //$NON-NLS-1$
            list.addAll(root.getGrlspec().getActors());
        else if (getLabel().equals(Messages.getString("LabelTreeEditPart.ucmDefs"))){ //$NON-NLS-1$
            list.add(Messages.getString("LabelTreeEditPart.components")); //$NON-NLS-1$
            list.add(Messages.getString("LabelTreeEditPart.responsibilities")); //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.grlDefs"))){ //$NON-NLS-1$
            list.add(Messages.getString("LabelTreeEditPart.actorDefs")); //$NON-NLS-1$
            list.add(Messages.getString("LabelTreeEditPart.intentionalElementDefs"));  //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.IncludedScenarios"))) { //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.StartPoints"))) { //$NON-NLS-1$

		} else if (getLabel().equals(Messages.getString("LabelTreeEditPart.Preconditions"))) { //$NON-NLS-1$

		} else if (getLabel().equals(Messages.getString("LabelTreeEditPart.EndPoints"))) { //$NON-NLS-1$

		} else if (getLabel().equals(Messages.getString("LabelTreeEditPart.Postconditions"))) { //$NON-NLS-1$

		}               
        
        Collections.sort(list, new EObjectClassNameComparator());
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
        if (getLabel().equals(Messages.getString("LabelTreeEditPart.components"))) { //$NON-NLS-1$
            return Messages.getString("LabelTreeEditPart.componentDef"); //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.responsibilities"))) { //$NON-NLS-1$
            return Messages.getString("LabelTreeEditPart.responsibilityDef"); //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.intentionalElementDefs"))){ //$NON-NLS-1$
            return Messages.getString("LabelTreeEditPart.intentionalElementDefs"); //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.actorDefs"))){ //$NON-NLS-1$
            return Messages.getString("LabelTreeEditPart.actorDefs"); //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.ucmDefs"))){ //$NON-NLS-1$
            return Messages.getString("LabelTreeEditPart.ucmDefs"); //$NON-NLS-1$
        } else if (getLabel().equals(Messages.getString("LabelTreeEditPart.grlDefs"))){ //$NON-NLS-1$
            return Messages.getString("LabelTreeEditPart.grlDefs"); //$NON-NLS-1$
        }
        else
            return null;

    }

    /**
     * Return icons associated with the textual strings.
     */
    protected Image getImage() {
        if (super.getImage() == null && getLabel().equals(Messages.getString("LabelTreeEditPart.components"))) //$NON-NLS-1$
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage()); //$NON-NLS-1$
        else if (super.getImage() == null && getLabel().equals(Messages.getString("LabelTreeEditPart.responsibilities"))) //$NON-NLS-1$
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage()); //$NON-NLS-1$
        else if (super.getImage() == null && getLabel().equals(Messages.getString("LabelTreeEditPart.grlDefs"))) //$NON-NLS-1$
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")).createImage()); //$NON-NLS-1$
        else if (super.getImage() == null && getLabel().equals(Messages.getString("LabelTreeEditPart.ucmDefs"))) //$NON-NLS-1$
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucm16.gif")).createImage()); //$NON-NLS-1$
        else if (super.getImage() == null && getLabel().equals(Messages.getString("LabelTreeEditPart.actorDefs"))) //$NON-NLS-1$
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/GRLActor16.gif")).createImage()); //$NON-NLS-1$
        else if (super.getImage() == null && getLabel().equals(Messages.getString("LabelTreeEditPart.intentionalElementDefs"))) //$NON-NLS-1$
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Softgoal16.gif")).createImage()); //$NON-NLS-1$

        return super.getImage();
    }
}