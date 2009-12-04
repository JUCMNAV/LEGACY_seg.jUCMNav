/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
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
    private boolean aborted; // can happen if deleting multiple scenarios at a time

    /**
     * Remove child from the parent's included scenario list.
     */
    public DeleteIncludedScenarioCommand(ScenarioDef parent, ScenarioDef child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("DeleteIncludedScenarioCommand.RemoveIncludedScenarios")); //$NON-NLS-1$

    }

    /**
     * Only if no strategy/scenario in it
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && parent.getIncludedScenarios().contains(child);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (!parent.getIncludedScenarios().contains(child)) {
            aborted = true;
        }

        index = parent.getIncludedScenarios().indexOf(child);

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

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
        assert parent != null && child != null : "pre something is null"; //$NON-NLS-1$
        assert parent.getIncludedScenarios().contains(child) : "pre scenario included"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post something is null"; //$NON-NLS-1$
        assert !parent.getIncludedScenarios().contains(child) : "post scenario included"; //$NON-NLS-1$	
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        parent.getIncludedScenarios().add(index, child);

        testPreConditions();
    }
}
