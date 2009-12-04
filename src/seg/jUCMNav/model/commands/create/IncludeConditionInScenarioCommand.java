/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import urncore.Condition;

/**
 * This command includes a pre/post condition inside a scenario.
 * 
 * @author jkealey
 * 
 */
public class IncludeConditionInScenarioCommand extends Command implements JUCMNavCommand {

    private ScenarioDef parent;
    private boolean bIsPreCondition;
    private Condition condition;
    private URNspec urn;
    private Condition clone;

    /**
     * 
     * @return the added condition.
     */
    public Condition getCondition() {
        return condition;
    }

    /**
	 * 
	 */
    public IncludeConditionInScenarioCommand(ScenarioDef parent, boolean bIsPreCondition) {
        this.parent = parent;
        this.bIsPreCondition = bIsPreCondition;
        setLabel(Messages.getString("IncludeConditionInScenarioCommand.IncludePrePostInScenario")); //$NON-NLS-1$
        urn = parent.getGroup().getUcmspec().getUrnspec();
    }

    public IncludeConditionInScenarioCommand(ScenarioDef parent, boolean bIsPreCondition, Condition clone) {
        this.parent = parent;
        this.bIsPreCondition = bIsPreCondition;
        setLabel(Messages.getString("IncludeConditionInScenarioCommand.IncludePrePostInScenario")); //$NON-NLS-1$
        urn = parent.getGroup().getUcmspec().getUrnspec();
        this.clone = clone;
    }

    public IncludeConditionInScenarioCommand(ScenarioDef parent, boolean bIsPreCondition, Condition clone, URNspec urn) {
        this.parent = parent;
        this.bIsPreCondition = bIsPreCondition;
        setLabel(Messages.getString("IncludeConditionInScenarioCommand.IncludePrePostInScenario")); //$NON-NLS-1$
        this.urn = urn;
        this.clone = clone;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && urn != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        condition = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
        if (clone != null) {
            condition.setLabel(clone.getLabel());
            condition.setExpression(clone.getExpression());
        } else {
            // if (bIsPreCondition)
            //				condition.setLabel(Messages.getString("IncludeConditionInScenarioCommand.Precondition")); //$NON-NLS-1$
            // else
            //				condition.setLabel(Messages.getString("IncludeConditionInScenarioCommand.Postcondition")); //$NON-NLS-1$
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (bIsPreCondition) {
            this.parent.getPreconditions().add(condition);
        } else {
            this.parent.getPostconditions().add(condition);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && condition != null && urn != null : "post not null"; //$NON-NLS-1$
        assert (parent.getPreconditions().contains(condition) && bIsPreCondition) || (!bIsPreCondition && parent.getPostconditions().contains(condition)) : "post scenario not updated"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && condition != null && urn != null : "pre not null"; //$NON-NLS-1$
        assert (!parent.getPreconditions().contains(condition) && bIsPreCondition) || (!bIsPreCondition && !parent.getPostconditions().contains(condition)) : "pre scenario not updated"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (bIsPreCondition) {
            this.parent.getPreconditions().remove(condition);
        } else {
            this.parent.getPostconditions().remove(condition);
        }

        testPreConditions();
    }
}
