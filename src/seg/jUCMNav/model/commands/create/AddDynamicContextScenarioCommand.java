package seg.jUCMNav.model.commands.create;

import java.util.List;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.dyncontext.DynamicContext;

/**
 * This command adds a Scenario to the selected Dynamic Context
 * 
 * @author aprajita
 * 
 */
public class AddDynamicContextScenarioCommand extends Command implements JUCMNavCommand {
	
	private DynamicContext parent;
	private ScenarioDef child;
    boolean aborted = false;
    boolean isInCompoundCommand = false;

    /**
	 * Constructor
	 */
    public AddDynamicContextScenarioCommand(DynamicContext parent, ScenarioDef child) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("AddDynamicContextScenarioCommand.AddDynamicContextScenario")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public AddDynamicContextScenarioCommand(DynamicContext parent, ScenarioDef child, boolean isInCompoundCommand) {
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("AddDynamicContextScenarioCommand.AddDynamicContextScenario")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
    	List scenarios = getAllScenarios(parent);
        return parent != null && child != null && (isInCompoundCommand || scenarios.contains(child)) 
        		&& (parent.getScenario() == null);
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
            aborted = true; // another command in same compound command invalidated our preconditions
            return;
        }
        testPreConditions();
        if (parent.getScenario() == null)
        	this.parent.setScenario(child);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null : "post not null"; //$NON-NLS-1$
        assert  parent.getScenario() == child : "post scenario not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null : "pre not null"; //$NON-NLS-1$
        assert !( parent.getScenario() == child) : "pre scenario not added"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        this.parent.setScenario(null);
        testPreConditions();
    }
    
    /**
     * 
     * @param parent
     * 				the Dynamic Context
     * @return the list of all available Scenarios
     */
    private List getAllScenarios(DynamicContext parent){
    	List scenGroups = null;
    	if (parent != null)
    		scenGroups = parent.getUrnspec().getUcmspec().getScenarioGroups();
    	List scenarios = null;
    	if (scenGroups.size() > 0) {
	    	for (int i = 0; i < scenGroups.size(); i++){
	    		if (((ScenarioGroup) scenGroups.get(i)).getScenarios().size() > 0) {
	    			if (i == 0)
	    				scenarios = ((ScenarioGroup) scenGroups.get(i)).getScenarios();
	    			else
	    				scenarios.addAll(((ScenarioGroup) scenGroups.get(i)).getScenarios());
	    		}
	    			
	    	}
    	}
    	return scenarios;
    }

}
