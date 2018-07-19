package seg.jUCMNav.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import asd.ASDiagram;
import asd.Aim;
import asd.Community;
import asd.DivisionOfLabour;
import asd.Outcome;
import asd.Rule;
import asd.Subject;
import asd.Tool;

/**
 * Edit Part factory for the GRL model elements.
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class AsdGraphicalEditPartFactory implements EditPartFactory {

    private ASDiagram asdiagram;
    

    /**
     * 
     * @param graph
     *            the GRL graph
     */
    public AsdGraphicalEditPartFactory(ASDiagram asdiagram) 
    {
        this.asdiagram = asdiagram;
         
        
    }

    /**
     * Create new instances of edit part for the model elements
     * 
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof ASDiagram) {
            return new ASDiagramEditPart((ASDiagram) model);
        }
        else if (model instanceof Tool) {
            return new ToolEditPart((Tool) model,asdiagram);
        } 
        else if(model instanceof Community)
        {
        	return new CommunityEditPart((Community) model, asdiagram);
        }
        else if(model instanceof Rule)
        {
        	return new RuleEditPart((Rule) model, asdiagram);
        }
        else if(model instanceof DivisionOfLabour)
        {
        	return new DivisionOfLabourEditPart((DivisionOfLabour) model, asdiagram);
        }
        else if(model instanceof Aim)
        {
        	return new AimEditPart((Aim) model, asdiagram);
        }
        else if(model instanceof Subject)
        {
        	return new SubjectEditPart((Subject) model, asdiagram);
        }
        else if(model instanceof Outcome)
        {
        	return new OutcomeEditPart((Outcome) model, asdiagram);
        }
        else {
            System.out.println("Unknown class in AsdGraphicalEditPartFactory.createEditPart();"); //$NON-NLS-1$
            assert false : "Unknown class in AsdGraphicalEditPartFactory.createEditPart();"; //$NON-NLS-1$
            return null;
        }
    }

}
