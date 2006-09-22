/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioDef;

/**
 * Removes a scenario's included scenario. 
 * 
 * @author jkealey
 * 
 */
public class DeleteIncludedScenarioCommand extends Command implements JUCMNavCommand {

	private ScenarioDef parent, child;
	private int index;

	/**
	 * Remove child from the parent's included scenario list. 
	 */
	public DeleteIncludedScenarioCommand(ScenarioDef parent, ScenarioDef child) {
		this.parent = parent;
		this.child = child;
		setLabel("Remove Included Scenario");

	}

	/**
	 * Only if no strategy/scenario in it
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return parent!=null && child!=null && parent.getIncludedScenarios().contains(child);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		index = parent.getIncludedScenarios().indexOf(child);
		redo();
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();

		parent.getIncludedScenarios().remove(child);

		testPostConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert parent!=null && child!=null : "pre something is null"; //$NON-NLS-1$
		assert parent.getIncludedScenarios().contains(child) : "pre scenario included"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert parent!=null && child!=null: "post something is null"; //$NON-NLS-1$
		assert !parent.getIncludedScenarios().contains(child) : "post scenario included"; //$NON-NLS-1$	
	}
		

	/**
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		parent.getIncludedScenarios().add(index, child);

		testPreConditions();
	}
}
