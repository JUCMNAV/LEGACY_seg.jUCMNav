/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.Variable;

/**
 * This command adds a variable initialization
 * 
 * @author jkealey
 * 
 */
public class CreateVariableInitializationCommand extends Command implements JUCMNavCommand {

    private Variable var;
    private ScenarioDef scenario;
    private String value;
    private Initialization init;
    private String varName; // for late binding

    /**
     * 
     */
    public CreateVariableInitializationCommand(Variable var, ScenarioDef scenario, String value) {
        this.var = var;
        this.scenario = scenario;
        this.value = value;
        setLabel(Messages.getString("CreateVariableInitializationCommand.CreateVariableInitialization")); //$NON-NLS-1$
    }

    /**
     * 
     */
    public CreateVariableInitializationCommand(String var, ScenarioDef scenario, String value) {
        this.varName = var;
        this.scenario = scenario;
        this.value = value;
        setLabel(Messages.getString("CreateVariableInitializationCommand.CreateVariableInitialization")); //$NON-NLS-1$
    }

    /**
     * 
     */
    public CreateVariableInitializationCommand(Variable var, ScenarioDef scenario) {
        this.var = var;
        this.scenario = scenario;
        setLabel(Messages.getString("CreateVariableInitializationCommand.CreateVariableInitialization")); //$NON-NLS-1$
    }

    /**
     * 
     */
    public CreateVariableInitializationCommand(String var, ScenarioDef scenario) {
        this.varName = var;
        this.scenario = scenario;
        setLabel(Messages.getString("CreateVariableInitializationCommand.CreateVariableInitialization")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return scenario != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (var == null) {
            for (Iterator iter = scenario.getGroup().getUcmspec().getVariables().iterator(); iter.hasNext();) {
                Variable v = (Variable) iter.next();
                if (v.getName().equalsIgnoreCase(varName)) {
                    var = v;
                    break;
                }

            }
        }

        assert var != null : "unable to find variable " + varName; //$NON-NLS-1$

        init = (Initialization) ModelCreationFactory.getNewObject(var.getUcmspec().getUrnspec(), Initialization.class);

        if (this.value == null) {
            if (var.getType().equals(ScenarioUtils.sTypeBoolean))
                init.setValue("false"); //$NON-NLS-1$
            else if (var.getType().equals(ScenarioUtils.sTypeInteger))
                init.setValue("0"); //$NON-NLS-1$
            else {
                init.setValue(var.getEnumerationType().getValues().split(",")[0]); //$NON-NLS-1$
            }
        } else {
            init.setValue(this.value);
        }

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        init.setVariable(var);
        init.setScenarioDef(scenario);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert var != null && var.getUcmspec() != null && scenario != null && init != null : "post not null"; //$NON-NLS-1$
        assert init.getScenarioDef() != null && init.getVariable() != null : "post init not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert var != null && var.getUcmspec() != null && scenario != null && init != null : "pre not null"; //$NON-NLS-1$
        assert init.getScenarioDef() == null && init.getVariable() == null : "pre init not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        init.setVariable(null);
        init.setScenarioDef(null);
        testPreConditions();
    }

    public Initialization getInitialization() {
        return init;
    }
}
