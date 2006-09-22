package seg.jUCMNav.editparts.strategyTreeEditparts;

import ucm.scenario.ScenarioDef;

/**
 * Class that contains a scenario so that we have another type of model element
 * for the strategy treeview. Would recursively build scenarios with children
 * otherwise.
 * 
 * Or do we want that behaviour? hmmm
 * 
 * @author jkealey
 * 
 */
public class IncludedScenario {

	private ScenarioDef scenario;

	public IncludedScenario(ScenarioDef scenario) {
		this.scenario = scenario;
	}

	public ScenarioDef getScenario() {
		return scenario;
	}

	public void setScenario(ScenarioDef scenario) {
		this.scenario = scenario;
	}

}
