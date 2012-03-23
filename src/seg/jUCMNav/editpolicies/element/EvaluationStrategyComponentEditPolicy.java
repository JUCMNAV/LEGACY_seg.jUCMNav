package seg.jUCMNav.editpolicies.element;

import grl.ContributionContext;
import grl.EvaluationStrategy;

import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteContributionContextCommand;
import seg.jUCMNav.model.commands.delete.DeleteIncludedContributionContextCommand;
import seg.jUCMNav.model.commands.delete.DeleteIncludedScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteIncludedStrategyCommand;
import seg.jUCMNav.model.commands.delete.DeleteScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategyCommand;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import ucm.scenario.ScenarioDef;

/**
 * ComponentEditPolicy for EvaluationStrategy/ScenarioDef/ContributionContext. Return the delete command for a strategy
 * 
 * @author Jean-François Roy, jkealey
 * 
 */
public class EvaluationStrategyComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteEvaluationStrategyCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof EvaluationStrategy) {
            EvaluationStrategy strategy = (EvaluationStrategy) obj;
            if (getHost().getParent().getModel() instanceof String) {
                // included scenario.
                EditPart parentPart = (EditPart) getHost().getParent().getParent();
                EvaluationStrategy parent = (EvaluationStrategy) parentPart.getModel();
                
                Vector indexes = EvaluationStrategyManager.getIndexesOfPrimaryDefinedIncludedStrategies(parent);
                int index = getHost().getParent().getChildren().indexOf(getHost());
                
                for (int i=0;i<indexes.size();i++)
                {
                    if (((Integer)indexes.get(i)).intValue() == index)
                    {
                        return new DeleteIncludedStrategyCommand(parent, strategy);
                    }
                }
                // ignore included scenarios. 
                return null;
            } 
            else {
                DeleteStrategyCommand deleteCommand = new DeleteStrategyCommand(strategy);
                return deleteCommand;
            }
        } else  if (obj instanceof ContributionContext) {
            ContributionContext context = (ContributionContext) obj;
            if (getHost().getParent().getModel() instanceof String) {
                // included contexts.
                EditPart parentPart = (EditPart) getHost().getParent().getParent();
                ContributionContext parent = (ContributionContext) parentPart.getModel();
                
                Vector indexes = EvaluationStrategyManager.getIndexesOfPrimaryDefinedIncludedContributionContexts(parent);
                int index = getHost().getParent().getChildren().indexOf(getHost());
                
                for (int i=0;i<indexes.size();i++)
                {
                    if (((Integer)indexes.get(i)).intValue() == index)
                    {
                        return new DeleteIncludedContributionContextCommand(parent, context);
                    }
                }
                // ignore included contexts. 
                return null;
            } 
            else {
                DeleteContributionContextCommand deleteCommand = new DeleteContributionContextCommand(context);
                return deleteCommand;
            }
        } 
        else if (obj instanceof ScenarioDef) {
            ScenarioDef scenario = (ScenarioDef) obj;
            if (getHost().getParent().getModel() instanceof String) {
                // included scenario.
                EditPart parentPart = (EditPart) getHost().getParent().getParent();
                ScenarioDef parent = (ScenarioDef) parentPart.getModel();
                
                Vector indexes = ScenarioUtils.getIndexesOfPrimaryDefinedIncludedScenarios(parent);
                int index = getHost().getParent().getChildren().indexOf(getHost());
                
                for (int i=0;i<indexes.size();i++)
                {
                    if (((Integer)indexes.get(i)).intValue() == index)
                    {
                        return new DeleteIncludedScenarioCommand(parent, scenario);
                    }
                }
                // ignore included scenarios. 
                return null;
            } else {
                DeleteScenarioCommand deleteCommand = new DeleteScenarioCommand(scenario);
                return deleteCommand;
            }
        }

        return null;
    }

}
