/**
 * 
 */
package seg.jUCMNav.editparts;

import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.GRLGraph;
import grl.IntentionalElementRef;
import grl.LinkRef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import urncore.Label;

/**
 * @author Jean-François Roy
 *
 */
public class GrlGraphicalEditPartFactory implements EditPartFactory {

    private GRLGraph graph;
    
    public GrlGraphicalEditPartFactory(){
        super();
    }
    
    public GrlGraphicalEditPartFactory(GRLGraph graph){
        this.graph = graph;
    }
    
    /* 
     * Create new instances of edit part for the model elements
     * 
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof GRLGraph){
            return new GrlGraphEditPart((GRLGraph)model);
        }
        else if(model instanceof IntentionalElementRef){
            return new IntentionalElementEditPart((IntentionalElementRef)model, graph);
        }
        else if (model instanceof Belief){
            return new BeliefEditPart((Belief)model,graph);
        }
        else if (model instanceof ActorRef){
            return new ActorRefEditPart((ActorRef)model,graph);
        }
        else if(model instanceof Label){
            return new LabelEditPart((Label)model);
        } 
        else if (model instanceof LinkRef) {
            return new LinkRefEditPart((LinkRef)model, graph);
        }
        else if (model instanceof BeliefLink) {
            return new BeliefLinkEditPart((BeliefLink)model, graph);
        }
        else {  
            System.out.println("Unknown class in GrlGraphicalEditPartFactory.createEditPart();"); //$NON-NLS-1$
            assert false : "Unknown class in GrlGraphicalEditPartFactory.createEditPart();"; //$NON-NLS-1$
            return null;
        }
    }

}
