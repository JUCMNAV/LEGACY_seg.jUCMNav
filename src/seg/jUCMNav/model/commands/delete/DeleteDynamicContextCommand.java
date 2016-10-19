package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import grl.ContributionContext;
import grl.EvaluationStrategy;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemoveDynamicContextCommand;
import ucm.scenario.ScenarioDef;
import urn.dyncontext.DynamicContext;

/**
 * Command to delete the selected DynamicContext
 * 
 * @author aprajita
 * 
 */
public class DeleteDynamicContextCommand extends CompoundCommand {
	
	/**
	 * 
	 * @param dyn
	 * 			The DynamicContext to be deleted
	 */
	public DeleteDynamicContextCommand(DynamicContext dyn) {
        setLabel(Messages.getString("DeleteDynamicContextCommand.DeleteDynamicContext")); //$NON-NLS-1$
        
        //Delete all included DynamicContexts, ContributionContext, Scenario, and EvaluationStrategy of the selected DynamicContext
        for (Iterator iter = dyn.getIncludedContexts().iterator(); iter.hasNext();) {
            DynamicContext child = (DynamicContext) iter.next();
            add(new DeleteIncludedDynamicContextCommand(dyn, child));
        }
        for (Iterator iter = dyn.getParentContexts().iterator(); iter.hasNext();) {
            DynamicContext parent = (DynamicContext) iter.next();
            add(new DeleteIncludedDynamicContextCommand(parent, dyn));
        }
        
        EvaluationStrategy strategy = dyn.getStrategy();
        if (strategy != null){
        	add(new DeleteDynamicContextStrategyCommand(dyn, strategy));
        }
        
        ScenarioDef scenario = dyn.getScenario();
        if (scenario != null){
        	add(new DeleteDynamicContextScenarioCommand(dyn, scenario));
        }
        
        ContributionContext contrib = dyn.getContributionContext();
        if (contrib != null){
        	add(new DeleteDynamicContextContributionContextCommand(dyn, contrib));
        }
        
        //Remove the DynamicContext from the group
        add(new RemoveDynamicContextCommand(dyn));
    }

}
