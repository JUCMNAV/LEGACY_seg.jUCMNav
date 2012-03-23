package seg.jUCMNav.editpolicies.layout;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.model.commands.transformations.MoveContributionContextCommand;
import seg.jUCMNav.model.commands.transformations.MoveScenarioCommand;
import seg.jUCMNav.model.commands.transformations.MoveStrategyCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;

/**
 * 
 * XYLayoutEditPolicy for the MapAndPathGraphEditPart. Handles creation of new elements and moving/resizing of existing ones.
 * 
 * @author etremblay, jkealey
 * 
 */
public class StrategiesGroupLayoutEditPolicy extends LayoutEditPolicy {

    /**
     * Returns a command to be executed when the palette tries to create something on the MapAndPathGraphEditPart.
     * 
     * Extends path for PathTool and creates ComponentRefs. PathNodes are created on NodeConnections so are not handled here.
     * 
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        Object newObjectType = null;
        if (request.getNewObject() != null)
            newObjectType = request.getNewObjectType();

        if (newObjectType == ScenarioDef.class && getHost()!=null && getHost().getTargetEditPart(request)!=null && getHost().getTargetEditPart(request).getModel() instanceof ScenarioGroup){
            ScenarioGroup group = (ScenarioGroup) getHost().getTargetEditPart(request).getModel();
            Object obj = request.getNewObject();
            if (obj instanceof ScenarioDef) {
                ScenarioDef def = (ScenarioDef)obj;
                return new MoveScenarioCommand(group, def);
            }
        }
        else  if (newObjectType == EvaluationStrategy.class && getHost()!=null && getHost().getTargetEditPart(request)!=null && getHost().getTargetEditPart(request).getModel() instanceof StrategiesGroup){
            StrategiesGroup group = (StrategiesGroup) getHost().getTargetEditPart(request).getModel();
            Object obj = request.getNewObject();
            if (obj instanceof EvaluationStrategy) {
                EvaluationStrategy def = (EvaluationStrategy)obj;
                return new MoveStrategyCommand(group, def);
            }
        }  else  if (newObjectType == ContributionContext.class && getHost()!=null && getHost().getTargetEditPart(request)!=null && getHost().getTargetEditPart(request).getModel() instanceof ContributionContextGroup){
            ContributionContextGroup group = (ContributionContextGroup) getHost().getTargetEditPart(request).getModel();
            Object obj = request.getNewObject();
            if (obj instanceof ContributionContext) {
                ContributionContext def = (ContributionContext)obj;
                return new MoveContributionContextCommand(group, def);
            }
        }

        return null;
    }

   
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Command getMoveChildrenCommand(Request request) {
        // TODO Auto-generated method stub
        return null;
    }

}
