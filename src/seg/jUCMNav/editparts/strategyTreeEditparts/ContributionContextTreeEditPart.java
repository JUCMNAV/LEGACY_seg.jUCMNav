package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.ContributionContext;
import grl.ContributionContextGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editpolicies.element.EvaluationStrategyComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * TreeEditPart for Contribution Context in the strategies view
 * 
 * @author jkealey
 * 
 */
public class ContributionContextTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    public static final String INCLUDED_CONTRIBUTION_CONTEXTS = Messages.getString("ContributionContextTreeEditPart.IncludedContributionContexts"); //$NON-NLS-1$
    public static final String CONTRIBUTION_CHANGES = Messages.getString("ContributionContextTreeEditPart.ContributionChanges"); //$NON-NLS-1$

    /**
     * @param model
     */
    public ContributionContextTreeEditPart(ContributionContext model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new EvaluationStrategyComponentEditPolicy());
    }

    /**
     * 
     * @return the evaluation strategy
     */
    public ContributionContext getContributionContext() {
        return (ContributionContext) getModel();
    }

    /**
     * @return the icon for an contribution context
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/ContributionContext16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }
    
  
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.add(CONTRIBUTION_CHANGES);
        list.add(INCLUDED_CONTRIBUTION_CONTEXTS);
        return list;
    }

    /**
     * If selected, set the element in bold.
     */
    public void setSelected(boolean selected) {
        // bug 411
        if (widget == null || widget.isDisposed())
            return;
        if (selected) {
            ((TreeItem) widget).setBackground(ColorManager.LIGHTGRAY);
        } else {
            ((TreeItem) widget).setBackground(ColorManager.WHITE);
        }
        // refreshVisuals();
    }
    
    /**
     * Is this context  inherited from another context? This depends on the edit part and not the model instance; the model instance is not duplicated, the
     * edit part is.
     * 
     * @return Is this scenario inherited from another scenario?
     */
    public boolean isInherited() {
        if (getParent()==null || getParent().getModel() instanceof ContributionContextGroup)
            return false;
        else
        {
            ContributionContext def = ((ContributionContext) getParent().getParent().getModel());
            Vector indexes = EvaluationStrategyManager.getIndexesOfPrimaryDefinedIncludedContributionContexts(def);
            int index = getParent().getChildren().indexOf(this);
            
            boolean isInherited = true;
            for (int i=0;i<indexes.size();i++)
            {
                if (((Integer)indexes.get(i)).intValue() == index)
                    isInherited = false;
            }
            // this is a hack because we don't seem to be listening to the selection anymore
            // is only needed when we delete a scenario which is included elsewhere, then undo that deletion. 
            checkForegroundColor(isInherited);
            
            return isInherited;
            
        }
    }
    
    /**
     * Returns the scenario's name and sets the label as grayed out if it is inherited {@link #isInherited()}
     */
    protected String getText() {
        if (widget != null && !widget.isDisposed()) {
            checkForegroundColor(isInherited());
        }

        return super.getText();
    }

    private void checkForegroundColor(boolean isInherited) {
        if (widget != null && !widget.isDisposed()) {
            if (isInherited)
                ((TreeItem) widget).setForeground(DARKGRAY);
            else
                ((TreeItem) widget).setForeground(BLACK);
        }
    }

}
