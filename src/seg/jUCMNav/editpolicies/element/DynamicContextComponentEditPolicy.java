package seg.jUCMNav.editpolicies.element;

import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import grl.ContributionContext;
import grl.EvaluationStrategy;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextLabelTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.model.commands.delete.DeleteDynamicContextCommand;
import seg.jUCMNav.model.commands.delete.DeleteDynamicContextContributionContextCommand;
import seg.jUCMNav.model.commands.delete.DeleteDynamicContextScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteDynamicContextStrategyCommand;
import seg.jUCMNav.model.commands.delete.DeleteIncludedDynamicContextCommand;
import seg.jUCMNav.model.commands.delete.DeleteTimepointCommand;
import ucm.scenario.ScenarioDef;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.Timepoint;

/**
 * ComponentEditPolicy for DynamicContext/Timepoint. Returns the delete command.
 * 
 * @author aprajita
 * 
 */
public class DynamicContextComponentEditPolicy extends ComponentEditPolicy {
	
	/**
     * Return a DeleteVariableCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof EvaluationStrategy) {

            EvaluationStrategy strategy = (EvaluationStrategy) obj;
            DeleteDynamicContextStrategyCommand deleteCommand = new DeleteDynamicContextStrategyCommand(((DynamicContextLabelTreeEditPart) getHost().getParent()).getDynamicContext(), strategy);
            return deleteCommand;
        } else if (obj instanceof ScenarioDef) {

            ScenarioDef scen = (ScenarioDef) obj;
            DeleteDynamicContextScenarioCommand deleteCommand = new DeleteDynamicContextScenarioCommand(((DynamicContextLabelTreeEditPart) getHost().getParent()).getDynamicContext(), scen);
            return deleteCommand;
        } else if (obj instanceof ContributionContext) {

            ContributionContext contriContext = (ContributionContext) obj;
            DeleteDynamicContextContributionContextCommand deleteCommand = new DeleteDynamicContextContributionContextCommand(((DynamicContextLabelTreeEditPart) getHost().getParent()).getDynamicContext(), contriContext);
            return deleteCommand;
        } else if (obj instanceof DynamicContext) {
            DynamicContext dyn = (DynamicContext) obj;
            if (getHost().getParent().getModel() instanceof String) {
                // included context.
                EditPart parentPart = (EditPart) getHost().getParent().getParent();
                DynamicContext parent = (DynamicContext) parentPart.getModel();
                
                Vector indexes = DynamicContextsUtils.getIndexesOfPrimaryDefinedIncludedContexts(parent);
                int index = getHost().getParent().getChildren().indexOf(getHost());
                
                for (int i=0;i<indexes.size();i++)
                {
                    if (((Integer)indexes.get(i)).intValue() == index)
                    {
                        return new DeleteIncludedDynamicContextCommand(parent, dyn);
                    }
                }
                // ignore included contexts. 
                return null;
            } else {
            	DeleteDynamicContextCommand deleteCommand = new DeleteDynamicContextCommand(dyn);
                return deleteCommand;
            }
        } else if (obj instanceof Timepoint) {
            Timepoint tp = (Timepoint) obj;
            DeleteTimepointCommand deleteCommand = new DeleteTimepointCommand(tp);
            return deleteCommand;
            
        }

        return null;
    }

}
