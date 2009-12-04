/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.ScenarioDef;

/**
 * This command includes a scenario in another one.
 * 
 * @author jkealey
 * 
 */
public class IncludeScenarioCommand extends Command implements JUCMNavCommand {

    private ScenarioDef parent, child;
    boolean aborted = false;
    boolean isInCompoundCommand = false;

    /**
	 * 
	 */
    public IncludeScenarioCommand(ScenarioDef parent, ScenarioDef child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludeScenarioCommand.IncludeScenario")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public IncludeScenarioCommand(ScenarioDef parent, ScenarioDef child, boolean isInCompoundCommand) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("IncludeScenarioCommand.IncludeScenario")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && (isInCompoundCommand || ScenarioUtils.getPossibleIncludedScenarios(parent).contains(child));
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
        if (!canExecute()) {
            aborted = true; // another command in same compoundcommand invalided our preconditions
            return;
        }
        testPreConditions();
        this.parent.getIncludedScenarios().add(child);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post not null"; //$NON-NLS-1$
        assert parent.getIncludedScenarios().contains(child) : "post child not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre not null"; //$NON-NLS-1$
        assert !parent.getIncludedScenarios().contains(child) : "pre child not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        this.parent.getIncludedScenarios().remove(child);
        testPreConditions();
    }
}
