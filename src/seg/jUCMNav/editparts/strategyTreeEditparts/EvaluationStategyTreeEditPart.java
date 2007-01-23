package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.EvaluationStrategyComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for Strategy in the strategies view
 * 
 * @author Jean-François Roy
 * 
 */
public class EvaluationStategyTreeEditPart extends StrategyUrnModelElementTreeEditPart {


	/**
	 * @param model
	 */
	public EvaluationStategyTreeEditPart(EvaluationStrategy model) {
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
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")).createImage()); //$NON-NLS-1$
		}
		return super.getImage();
	}

	/** 
     * If selected, set the element in bold.
	 */
	public void setSelected(boolean selected) {
		// bug 411
		if (widget == null)
			return;
		if (selected) {
			((TreeItem) widget).setBackground(ColorManager.LIGHTGRAY);
		} else {
			((TreeItem) widget).setBackground(ColorManager.WHITE);
		}
		// refreshVisuals();
	}
}
