package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;
import grl.StrategiesGroup;

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
 * TreeEditPart for Strategy in the strategies view
 * 
 * @author Jean-François Roy
 * 
 */
public class EvaluationStrategyTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     */
    public EvaluationStrategyTreeEditPart(EvaluationStrategy model) {
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
    public EvaluationStrategy getEvaluationStrategy() {
        return (EvaluationStrategy) getModel();
    }

    /**
     * @return the icon for an evaluation strategy.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage((JUCMNavPlugin.getImage("icons/grlstrat16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }
    
  
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.add(Messages.getString("EvaluationStrategyTreeEditPart.IncludedStrategies")); //$NON-NLS-1$
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
     * Is this scenario inherited from another scenario? This depends on the edit part and not the model instance; the model instance is not duplicated, the
     * edit part is.
     * 
     * @return Is this scenario inherited from another scenario?
     */
    public boolean isInherited() {
        if (getParent()==null || getParent().getModel() instanceof StrategiesGroup)
            return false;
        else
        {
            EvaluationStrategy def = ((EvaluationStrategy) getParent().getParent().getModel());
            Vector indexes = EvaluationStrategyManager.getIndexesOfPrimaryDefinedIncludedStrategies(def);
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
