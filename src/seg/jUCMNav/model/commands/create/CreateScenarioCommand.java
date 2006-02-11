/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.EvaluationGroup;
import grl.EvaluationScenario;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * @author Jean-François Roy
 *
 */
public class CreateScenarioCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private EvaluationGroup group;
    private EvaluationScenario scenario;
    
    /**
     * 
     */
    public CreateScenarioCommand(URNspec urn, EvaluationGroup group) {
        this.urn = urn;
        this.group = group;
        scenario = (EvaluationScenario)ModelCreationFactory.getNewObject(urn, EvaluationScenario.class);
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
        urn.getGrlspec().getScenarios().add(scenario);
        group.getScenarios().add(scenario);
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && scenario != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !group.getScenarios().contains(scenario) : "pre scenario not in model"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
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
        urn.getGrlspec().getScenarios().remove(scenario);
        group.getScenarios().remove(scenario);
        testPreConditions();
    }
}
