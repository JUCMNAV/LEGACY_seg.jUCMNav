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
import seg.jUCMNav.model.commands.transformations.MoveDynamicContextCommand;
import seg.jUCMNav.model.commands.transformations.MoveScenarioCommand;
import seg.jUCMNav.model.commands.transformations.MoveStrategyCommand;
import seg.jUCMNav.model.commands.transformations.MoveTimepointCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

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
        }	else  if (newObjectType == DynamicContext.class && getHost()!=null && getHost().getTargetEditPart(request)!=null && getHost().getTargetEditPart(request).getModel() instanceof DynamicContextGroup){
            DynamicContextGroup group = (DynamicContextGroup) getHost().getTargetEditPart(request).getModel();
            Object obj = request.getNewObject();
            if (obj instanceof DynamicContext) {
                DynamicContext dyn = (DynamicContext)obj;
                return new MoveDynamicContextCommand(group, dyn);
            }
        }	else  if (newObjectType == Timepoint.class && getHost()!=null && getHost().getTargetEditPart(request)!=null && getHost().getTargetEditPart(request).getModel() instanceof TimepointGroup){
            TimepointGroup group = (TimepointGroup) getHost().getTargetEditPart(request).getModel();
            Object obj = request.getNewObject();
            if (obj instanceof Timepoint) {
                Timepoint tp = (Timepoint)obj;
                return new MoveTimepointCommand(group, tp);
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
