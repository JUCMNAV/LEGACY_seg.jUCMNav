package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editpolicies.element.DynamicContextComponentEditPolicy;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;

/**
 * TreeEditPart for DynamicContext in the DynamicContext view
 * 
 * @author aprajita
 * 
 */
public class DynamicContextTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the dynamic context
     */
    public DynamicContextTreeEditPart(DynamicContext model) {
        super(model);
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        if (!isInherited())
            installEditPolicy(EditPolicy.COMPONENT_ROLE, new DynamicContextComponentEditPolicy());
    }

    /**
     * 
     * @return the dynamic context
     */
    public DynamicContext getDynamicContext() {
        return (DynamicContext) getModel();
    }

    /**
     * Returns all the children of a dynamic context (folders for strategy, scenario, contribution context, changes, and included contexts).
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.add(Messages.getString("DynamicContextLabelTreeEditPart.IncludedContexts")); //$NON-NLS-1$
        list.add(Messages.getString("DynamicContextLabelTreeEditPart.EvaluationStrategy")); //$NON-NLS-1$
        list.add(Messages.getString("DynamicContextLabelTreeEditPart.ScenarioDef")); //$NON-NLS-1$
        list.add(Messages.getString("DynamicContextLabelTreeEditPart.ContributionContexts")); //$NON-NLS-1$
        list.add(Messages.getString("DynamicContextLabelTreeEditPart.Changes")); //$NON-NLS-1$
        return list;
    }

    /**
     * Returns the icon for the {@link DynamicContext}
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/urnstratscenon16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * If selected, set the background color.
     */
    public void setSelected(boolean selected) {

        // bug 411
        if (!checkTreeItem())
            return;

        if (selected) {
            ((TreeItem) widget).setBackground(GRAY);
        } else {
            ((TreeItem) widget).setBackground(WHITE);
        }
        // refreshVisuals();
    }

    /**
     * Is this context inherited from another context? This depends on the edit part and not the model instance; the model instance is not duplicated, the
     * edit part is.
     * 
     * @return Is this context inherited from another context?
     */
    public boolean isInherited() {
        if (getParent()==null || getParent().getModel() instanceof DynamicContextGroup)
            return false;
        else
        {
            DynamicContext dyn = ((DynamicContext) getParent().getParent().getModel());
            Vector indexes = DynamicContextsUtils.getIndexesOfPrimaryDefinedIncludedContexts(dyn);
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
     * Returns the context's name and sets the label as grayed out if it is inherited {@link #isInherited()}
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
