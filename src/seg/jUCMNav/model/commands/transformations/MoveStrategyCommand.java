package seg.jUCMNav.model.commands.transformations;

import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Moves a strategy from one group to another. 
 * 
 * @author jkealey
 * 
 */
public class MoveStrategyCommand extends Command implements JUCMNavCommand {

    private StrategiesGroup group;
    private StrategiesGroup oldGroup;
    private EvaluationStrategy scenario;

    /**
     * 
     */
    public MoveStrategyCommand(StrategiesGroup targetGroup, EvaluationStrategy scenario) {
        this.scenario = scenario;
        this.group = targetGroup;
        this.oldGroup = scenario.getGroup();
        setLabel(Messages.getString("MoveStrategyCommand.MoveStrategy")); //$NON-NLS-1$
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
            oldGroup.getStrategies().remove(scenario);
        if (group!=null)
            group.getStrategies().add(scenario);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert scenario != null : "pre not null"; //$NON-NLS-1$
        assert group==null || !group.getStrategies().contains(scenario) : "pre strategy not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert scenario !=null : "post not null"; //$NON-NLS-1$
        assert group == null || group.getStrategies().contains(scenario) : "post strategy not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (group!=null)
            group.getStrategies().remove(scenario);
        if (oldGroup!=null)
            oldGroup.getStrategies().add(scenario);
        testPreConditions();
    }

    public EvaluationStrategy getScenario() {
        return scenario;
    }

}
