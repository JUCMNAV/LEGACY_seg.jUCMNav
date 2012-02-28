package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

/**
 * Moves a strategy from one group to another. 
 * 
 * @author jkealey
 * 
 */
public class MoveScenarioCommand extends Command implements JUCMNavCommand {

    private ScenarioGroup group;
    private ScenarioGroup oldGroup;
    private ScenarioDef scenario;

    /**
     * 
     */
    public MoveScenarioCommand(ScenarioGroup targetGroup, ScenarioDef scenario) {
        this.scenario = scenario;
        this.group = targetGroup;
        this.oldGroup = scenario.getGroup();
        setLabel(Messages.getString("MoveScenarioCommand.MoveScenario")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return scenario!=null && group != oldGroup;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (oldGroup!=null)
            oldGroup.getScenarios().remove(scenario);
        if (group!=null)
            group.getScenarios().add(scenario);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert scenario != null : "pre not null"; //$NON-NLS-1$
        assert group==null || !group.getScenarios().contains(scenario) : "pre scenario not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert scenario !=null : "post not null"; //$NON-NLS-1$
        assert group == null || group.getScenarios().contains(scenario) : "post scenario not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (group!=null)
            group.getScenarios().remove(scenario);
        if (oldGroup!=null)
            oldGroup.getScenarios().add(scenario);
        testPreConditions();
    }

    public ScenarioDef getScenario() {
        return scenario;
    }

}
