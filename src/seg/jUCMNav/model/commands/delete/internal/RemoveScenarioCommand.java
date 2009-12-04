/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.UCMspec;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

/**
 * Removes a scenario from its group.
 * 
 * @author jkealey
 * 
 */
public class RemoveScenarioCommand extends Command implements JUCMNavCommand {

    private ScenarioDef scenario;
    private ScenarioGroup group;
    private UCMspec ucm;

    /**
     * 
     */
    public RemoveScenarioCommand(ScenarioDef scenario) {
        this.scenario = scenario;
        setLabel("RemoveScenarioCommand"); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        group = scenario.getGroup();
        ucm = group.getUcmspec();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        group.getScenarios().remove(scenario);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert ucm != null && scenario != null && group != null : "post something null"; //$NON-NLS-1$
        assert group.getScenarios().contains(scenario) : "pre scenario in group"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert ucm != null && scenario != null && group != null : "post something null"; //$NON-NLS-1$
        assert !group.getScenarios().contains(scenario) : "pre scenario in group"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        group.getScenarios().add(scenario);

        testPreConditions();
    }
}
