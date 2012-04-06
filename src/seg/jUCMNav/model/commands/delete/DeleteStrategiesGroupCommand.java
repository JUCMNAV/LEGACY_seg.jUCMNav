/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.ContributionContextGroup;
import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

/**
 * Delete a strategy/scenario/contribution group. It should have no strategy/scenario/contribs in it.
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteStrategiesGroupCommand extends Command implements JUCMNavCommand {

    private Object group;

    // the URNspec in which it is contained
    private URNspec urn;

    /**
	 * 
	 */
    public DeleteStrategiesGroupCommand(StrategiesGroup group) {
        this.group = group;
        setLabel(Messages.getString("DeleteStrategiesGroupCommand.deleteStrategiesGroup")); //$NON-NLS-1$

    }

    /**
	 * 
	 */
    public DeleteStrategiesGroupCommand(ScenarioGroup group) {
        this.group = group;
        setLabel(Messages.getString("DeleteStrategiesGroupCommand.DeleteScenarioGroup")); //$NON-NLS-1$
    }
    
    public DeleteStrategiesGroupCommand(ContributionContextGroup group) {
        this.group = group;
        setLabel(Messages.getString("DeleteStrategiesGroupCommand.DeleteContributionContextGroup")); //$NON-NLS-1$
    }

    private ScenarioGroup getScenarioGroup() {
        return (ScenarioGroup) group;
    }

    private StrategiesGroup getStrategyGroup() {

        return (StrategiesGroup) group;
    }

    private ContributionContextGroup getContributionContextGroup() {

        return (ContributionContextGroup) group;
    }

    
    /**
     * Only if no strategy/scenario/contrib in it
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return group != null
                && ((group instanceof StrategiesGroup && getStrategyGroup().getStrategies().size() == 0) || 
                        (group instanceof ScenarioGroup && getScenarioGroup().getScenarios().size() == 0) ||
                        (group instanceof ContributionContextGroup && getContributionContextGroup().getContribs().size() == 0)
                        );
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (group instanceof StrategiesGroup)
            urn = getStrategyGroup().getGrlspec().getUrnspec();
        else if (group instanceof ContributionContextGroup)
            urn = getContributionContextGroup().getGrlspec().getUrnspec();
        else
            urn = getScenarioGroup().getUcmspec().getUrnspec();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (group instanceof StrategiesGroup)
            urn.getGrlspec().getGroups().remove(group);
        else if (group instanceof ContributionContextGroup)
            urn.getGrlspec().getContributionGroups().remove(group);
        else
            urn.getUcmspec().getScenarioGroups().remove(group);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert group != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert (group instanceof StrategiesGroup && getStrategyGroup().getStrategies().size() == 0)
                || (group instanceof ScenarioGroup && getScenarioGroup().getScenarios().size() == 0)
                || (group instanceof ContributionContextGroup && getContributionContextGroup().getContribs().size() == 0)
                : "pre can't delete if still strategies / scenarios / contrib."; //$NON-NLS-1$
        assert urn.getGrlspec().getGroups().contains(group) || 
                urn.getUcmspec().getScenarioGroups().contains(group) || 
                urn.getGrlspec().getContributionGroups().contains(group): "pre group in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert group != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert (group instanceof StrategiesGroup && getStrategyGroup().getStrategies().size() == 0)
                || (group instanceof ScenarioGroup && getScenarioGroup().getScenarios().size() == 0)
                || (group instanceof ContributionContextGroup && getContributionContextGroup().getContribs().size() == 0)
                : "post can't delete if still strategies."; //$NON-NLS-1$
        assert !urn.getGrlspec().getGroups().contains(group) 
                && !urn.getUcmspec().getScenarioGroups().contains(group)
                && !urn.getGrlspec().getContributionGroups().contains(group): "post group in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add group
        if (group instanceof StrategiesGroup)
            urn.getGrlspec().getGroups().add(group);
        else if (group instanceof ContributionContextGroup)
            urn.getGrlspec().getContributionGroups().add(group);
        else
            urn.getUcmspec().getScenarioGroups().add(group);

        testPreConditions();
    }
}
