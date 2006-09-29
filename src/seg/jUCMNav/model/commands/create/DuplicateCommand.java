/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import urncore.Condition;

/**
 * Adds a scenario to a scenario group.
 * 
 * @author jkealey
 * 
 */
public class DuplicateCommand extends CompoundCommand {

	private ScenarioDef scenario;
	private EObject child;

	/**
	 * 
	 */
	public DuplicateCommand(ScenarioDef scenario, EObject child) {
		this.scenario = scenario;
		this.child = child;
		setLabel("Duplicate");
		
		if (this.child instanceof ScenarioStartPoint)
			add(new IncludePathNodeInScenarioCommand(scenario, ((ScenarioStartPoint)child).getStartPoint()));
		else if (this.child instanceof ScenarioEndPoint)
			add(new IncludePathNodeInScenarioCommand(scenario, ((ScenarioEndPoint)child).getEndPoint()));
		else if (this.child instanceof Condition) {
			add(new IncludeConditionInScenarioCommand(scenario, scenario.getPreconditions().contains(this.child), (Condition)this.child));
		}

	}



	
}
