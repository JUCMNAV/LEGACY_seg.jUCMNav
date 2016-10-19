package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.Timepoint;
import urncore.URNmodelElement;

/**
 * Editpart for textual strings that are children of DynamicContext.
 * 
 * @author aprajita
 */
public class DynamicContextLabelTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
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
     *            the DynamicContext
     */
    public DynamicContextLabelTreeEditPart(Object model, URNmodelElement root) {
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
     * @return the list of dynamic context children depending on the folder type. uses {@link DynamicContextsUtils}.
     */
    public List getModelChildren() {
         
        ArrayList list = new ArrayList();
        if (getRootElement() instanceof DynamicContext) {
            DynamicContext dyn = (DynamicContext) getRootElement();
            if (getLabel().equals(Messages.getString("DynamicContextLabelTreeEditPart.IncludedContexts"))) { //$NON-NLS-1$
                list.addAll(DynamicContextsUtils.getDefinedIncludedContexts(dyn));
            } else if (getLabel().equals(Messages.getString("DynamicContextLabelTreeEditPart.EvaluationStrategy"))) { //$NON-NLS-1$
            	if (DynamicContextsUtils.getDefinedEvaluationStrategy(dyn) != null) {
            		list.add(DynamicContextsUtils.getDefinedEvaluationStrategy(dyn));
            	}
            } else if (getLabel().equals(Messages.getString("DynamicContextLabelTreeEditPart.ScenarioDef"))) { //$NON-NLS-1$
            	if (DynamicContextsUtils.getDefinedScenario(dyn) != null) {
            		list.add(DynamicContextsUtils.getDefinedScenario(dyn));
            	}
            } else if (getLabel().equals(Messages.getString("DynamicContextLabelTreeEditPart.ContributionContexts"))) { //$NON-NLS-1$
            	if(DynamicContextsUtils.getDefinedContributionContext(dyn) != null) {
            		list.add(DynamicContextsUtils.getDefinedContributionContext(dyn));
            	}
            } else if (getLabel().equals(Messages.getString("DynamicContextLabelTreeEditPart.Changes"))) { //$NON-NLS-1$
            	list.addAll(DynamicContextsUtils.getDefinedChanges(dyn));
            	
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
    
    /**
     * @return the Dynamic Context
     */
    public DynamicContext getDynamicContext(){
    	DynamicContext dyn = null;
    	if (getRootElement() instanceof DynamicContext) {
            dyn = (DynamicContext) getRootElement();
    	}
        return dyn;
    }
    
    /**
     * 
     * @return the Timepoint
     */
    public Timepoint getTimepoint(){
    	Timepoint tp = null;
    	if (getRootElement() instanceof Timepoint) {
            tp = (Timepoint) getRootElement();
    	}
        return tp;
    }
}
