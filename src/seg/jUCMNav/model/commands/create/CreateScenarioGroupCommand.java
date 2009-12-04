/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

/**
 * This command add an Scenario Group to the model (for Scenarios)
 * 
 * @author jkealey
 * 
 */
public class CreateScenarioGroupCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private ScenarioGroup group;

    /**
     * 
     */
    public CreateScenarioGroupCommand(URNspec urn, ScenarioGroup group) {
        this.urn = urn;
        this.group = group;
        setLabel(Messages.getString("CreateScenarioGroupCommand.CreateScenarioGroup")); //$NON-NLS-1$
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
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUcmspec().getScenarioGroups().add(group);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUcmspec() != null && group != null : "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getScenarioGroups().contains(group) : "post group not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUcmspec() != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getScenarioGroups().contains(group) : "pre groups not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUcmspec().getScenarioGroups().remove(group);
        testPreConditions();
    }
}
