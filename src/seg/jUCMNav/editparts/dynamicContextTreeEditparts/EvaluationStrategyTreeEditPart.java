package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import grl.EvaluationStrategy;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.DynamicContextComponentEditPolicy;

/**
 * TreeEditPart for a strategy in the dynamic context view
 * 
 * @author aprajita
 * 
 */
public class EvaluationStrategyTreeEditPart extends DynamicContextUrnModelElementTreeEditPart {
	
	/**
     * @param model
     *            the evaluation strategy
     */
    public EvaluationStrategyTreeEditPart(EvaluationStrategy model) {
        super(model);
    }
    
    /**
     * Listens to the model element.
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {

        if (!isActive() && getEvaluationStrategy() != null)
            getEvaluationStrategy().eAdapters().add(this);
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new DynamicContextComponentEditPolicy()); // deletion
    }

    /**
     * 
     * Stops listening to the model element and destroys image.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive() && getEvaluationStrategy() != null) {
            getEvaluationStrategy().eAdapters().remove(this);
        }
        super.deactivate();
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

    /**
     * 
     * @return the actual {@link EvaluationStrategy}
     */
    private EvaluationStrategy getEvaluationStrategy() {
    	EvaluationStrategy strategy = null;
        strategy = (EvaluationStrategy) getModel();
        return strategy;
    }

    /**
     * Returns the strategy's name and sets the label as grayed out if it is inherited {@link #isInherited()}
     */
    protected String getText() {
        if (widget != null && !widget.isDisposed()) {
        	((TreeItem) widget).setForeground(BLACK);
        }

        return super.getText();
    }

}
