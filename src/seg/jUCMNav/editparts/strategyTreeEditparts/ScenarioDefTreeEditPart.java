/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.EvaluationStrategyComponentEditPolicy;
import ucm.scenario.ScenarioDef;

/**
 * TreeEditPart for Scenarios in the strategies view
 * 
 * @author jkealey
 * 
 */
public class ScenarioDefTreeEditPart extends StrategyUrnModelElementTreeEditPart {

	private boolean selected;

	/**
	 * @param model
	 */
	public ScenarioDefTreeEditPart(ScenarioDef model) {
		super(model);
		selected = false;
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new EvaluationStrategyComponentEditPolicy());
	}

	public ScenarioDef getScenarioDef() {
		return (ScenarioDef) getModel();
	}

	/**
	 * Returns the icon
	 */
	protected Image getImage() {
		if (super.getImage() == null) {
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucm16.gif")).createImage()); //$NON-NLS-1$
		}
		return super.getImage();
	}

	// If selected, set the element in bold.
	public void setSelected(boolean selected) {
		// bug 411
		if (widget == null)
			return;
		this.selected = selected;
		if (selected) {
			((TreeItem) widget).setBackground(new Color(null, 200, 200, 200));
		} else {
			((TreeItem) widget).setBackground(new Color(null, 255, 255, 255));
		}
		// refreshVisuals();
	}
}
