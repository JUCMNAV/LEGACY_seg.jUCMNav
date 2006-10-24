/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
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
		setLabel(Messages.getString("DuplicateCommand.Duplicate")); //$NON-NLS-1$
		
		if (this.child instanceof ScenarioStartPoint)
			add(new IncludePathNodeInScenarioCommand(scenario, ((ScenarioStartPoint)child).getStartPoint()));
		else if (this.child instanceof ScenarioEndPoint)
			add(new IncludePathNodeInScenarioCommand(scenario, ((ScenarioEndPoint)child).getEndPoint()));
		else if (this.child instanceof Condition) {
			
			add(new IncludeConditionInScenarioCommand(scenario, ((Condition)this.child).getScenarioDefPost()==null, (Condition)this.child));
		}

	}



	
}
