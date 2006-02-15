/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.EvaluationGroup;
import grl.EvaluationScenario;
import grl.GRLspec;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * @author Jean-François Roy
 *
 */
public class RemoveEvaluationScenarioCommand extends Command implements JUCMNavCommand {

    private EvaluationScenario scenario;
    private EvaluationGroup group;
    private GRLspec grl;
    
    /**
     * 
     */
    public RemoveEvaluationScenarioCommand(EvaluationScenario scenario) {
        this.scenario = scenario;
        setLabel("RemoveEvaluationScenarioCommand");       
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        grl = scenario.getGrlspec();
        group = scenario.getGroup();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        EvaluationGroup element;
        
        group.getScenarios().remove(scenario);
        
        grl.getScenarios().remove(scenario);

        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert grl != null && scenario != null && group !=null: "pre something null"; //$NON-NLS-1$
        assert grl.getScenarios().contains(scenario): "pre scenario in grl"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert grl != null && scenario != null && group !=null: "post something null"; //$NON-NLS-1$
        assert !grl.getScenarios().contains(scenario): "post scenario in grl"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        group.getScenarios().add(scenario);
        grl.getScenarios().add(scenario);
                
        testPreConditions();
    }
}
