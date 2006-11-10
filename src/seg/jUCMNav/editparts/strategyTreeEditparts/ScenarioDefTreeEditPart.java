/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editpolicies.element.EvaluationStrategyComponentEditPolicy;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

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
    	if (!isInherited()) 
    		installEditPolicy(EditPolicy.COMPONENT_ROLE, new EvaluationStrategyComponentEditPolicy());
	}

	public ScenarioDef getScenarioDef() {
		return (ScenarioDef) getModel();
	}

	protected List getModelChildren() {
        ArrayList list = new ArrayList();
        list.add(Messages.getString("ScenarioLabelTreeEditPart.IncludedScenarios")); //$NON-NLS-1$
        list.add(Messages.getString("ScenarioLabelTreeEditPart.StartPoints")); //$NON-NLS-1$
        list.add(Messages.getString("ScenarioLabelTreeEditPart.Initializations")); //$NON-NLS-1$
        list.add(Messages.getString("ScenarioLabelTreeEditPart.Preconditions")); //$NON-NLS-1$
        list.add(Messages.getString("ScenarioLabelTreeEditPart.EndPoints")); //$NON-NLS-1$
        list.add(Messages.getString("ScenarioLabelTreeEditPart.Postconditions")); //$NON-NLS-1$
        return list;
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
			((TreeItem) widget).setBackground(GRAY);
		} else {
			((TreeItem) widget).setBackground(WHITE);
		}
		// refreshVisuals();
	}
	

	private boolean isInherited() {
		if (getParent().getModel() instanceof ScenarioGroup)
			return false;
		else 
			return getParent().getChildren().indexOf(this) < ((ScenarioLabelTreeEditPart)getParent()).getModelChildren().size()-((ScenarioDef) getParent().getParent().getModel()).getIncludedScenarios().size();
	}

	protected String getText() {
		if (widget!=null && !widget.isDisposed()) {
	    	if (isInherited()) 
	    		((TreeItem) widget).setForeground(DARKGRAY);
	    	else
	    		((TreeItem) widget).setForeground(BLACK);
		}
    	
    	return super.getText();
	}


}
