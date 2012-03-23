package seg.jUCMNav.editparts;

import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.GRLGraph;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import urncore.Comment;
import urncore.ConnectionLabel;
import urncore.Label;

/**
 * Edit Part factory for the GRL model elements.
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class GrlGraphicalEditPartFactory implements EditPartFactory {

    private GRLGraph graph;

    /**
     * 
     * @param graph
     *            the GRL graph
     */
    public GrlGraphicalEditPartFactory(GRLGraph graph) {
        this.graph = graph;
    }

    /**
     * Create new instances of edit part for the model elements
     * 
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof GRLGraph) {
            return new GrlGraphEditPart((GRLGraph) model);
        } else if (model instanceof IntentionalElementRef) {
            return new IntentionalElementEditPart((IntentionalElementRef) model);
        } else if (model instanceof KPIInformationElementRef) {
            return new KPIInformationElementEditPart((KPIInformationElementRef) model);
        } else if (model instanceof Belief) {
            return new BeliefEditPart((Belief) model);
        } else if (model instanceof ActorRef) {
            return new ActorRefEditPart((ActorRef) model);
        } 
        else if (model instanceof ConnectionLabel)
            return new ConnectionLabelEditPart((ConnectionLabel)model);
        else if (model instanceof Label) {
            return new LabelEditPart((Label) model);
        } 
        else if (model instanceof LinkRef) {
            return new LinkRefEditPart((LinkRef) model, graph);
        } else if (model instanceof KPIModelLinkRef) {
            return new KPIModelLinkRefEditPart((KPIModelLinkRef) model, graph);
        } else if (model instanceof BeliefLink) {
            return new BeliefLinkEditPart((BeliefLink) model, graph);
        } else if (model instanceof Comment) {
            return new CommentEditPart((Comment) model);
        } else {
            System.out.println("Unknown class in GrlGraphicalEditPartFactory.createEditPart();"); //$NON-NLS-1$
            assert false : "Unknown class in GrlGraphicalEditPartFactory.createEditPart();"; //$NON-NLS-1$
            return null;
        }
    }

}
