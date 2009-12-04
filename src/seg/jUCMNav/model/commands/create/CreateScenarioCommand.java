/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

/**
 * Adds a scenario to a scenario group.
 * 
 * @author jkealey
 * 
 */
public class CreateScenarioCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private ScenarioGroup group;
    private ScenarioDef scenario;

    /**
	 * 
	 */
    public CreateScenarioCommand(URNspec urn, ScenarioGroup group) {
        this.urn = urn;
        this.group = group;
        setLabel(Messages.getString("CreateScenarioCommand.CreateScenario")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public CreateScenarioCommand(URNspec urn, ScenarioGroup group, boolean createNow) {
        this.urn = urn;
        this.group = group;
        setLabel(Messages.getString("CreateScenarioCommand.CreateScenario")); //$NON-NLS-1$
        if (createNow) {
            this.scenario = (ScenarioDef) ModelCreationFactory.getNewObject(urn, ScenarioDef.class);
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && group != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (this.scenario == null)
            this.scenario = (ScenarioDef) ModelCreationFactory.getNewObject(urn, ScenarioDef.class);
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        group.getScenarios().add(scenario);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && scenario != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !group.getScenarios().contains(scenario) : "pre scenario not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && scenario != null && group != null : "post not null"; //$NON-NLS-1$
        assert group.getScenarios().contains(scenario) : "post scenario not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        group.getScenarios().remove(scenario);
        testPreConditions();
    }

    public ScenarioDef getScenario() {
        return scenario;
    }

}
