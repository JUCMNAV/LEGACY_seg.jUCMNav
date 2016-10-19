package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioDef;
import urn.dyncontext.DynamicContext;

/**
 * Command to delete Scenario of the selected DynamicContext
 * 
 * @author aprajita
 * 
 */
public class DeleteDynamicContextScenarioCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext parent;
	private ScenarioDef child;
    private boolean aborted;

    /**
     * Removes scenario from the parent DynamicContext
     */
    public DeleteDynamicContextScenarioCommand(DynamicContext parent, ScenarioDef child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("DeleteDynamicContextScenarioCommand.DeleteDynamicContextScenario")); //$NON-NLS-1$

    }

    /**
     * Only if the child is the included Scenario of the parent
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && child != null && parent.getScenario() == child;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (!(parent.getScenario() == child)) {
            aborted = true;
        }

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

        testPreConditions();

        parent.setScenario(null);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre something is null"; //$NON-NLS-1$
        assert parent.getScenario() == child : "pre scenario included"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post something is null"; //$NON-NLS-1$
        assert !(parent.getScenario() == child) : "post scenario included"; //$NON-NLS-1$	
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        parent.setScenario(child);

        testPreConditions();
    }

}
