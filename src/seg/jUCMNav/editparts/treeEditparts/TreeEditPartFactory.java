package seg.jUCMNav.editparts.treeEditparts;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.ElementLink;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.wrappers.ComponentTreeWrapper;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.UCMmap;
import ucm.performance.Demand;
import ucm.performance.GeneralResource;
import urn.URNspec;
import urncore.Component;
import urncore.Concern;
import urncore.Responsibility;

/**
 * The EditPartFactory associated with the outline treeview.
 * 
 * @author jkealey, TremblaE, gunterm, pchen
 * 
 */
public class TreeEditPartFactory implements EditPartFactory {

    // the urn spec being edited.
    protected URNspec urn;
    protected boolean onlyDefinitions;

    /**
     * @param urn
     *            the urnspec to display
     * @param onlyDefinitions
     *            do we show only the grl/ucm definitions?
     */
    public TreeEditPartFactory(URNspec urn, boolean onlyDefinitions) {
        this.urn = urn;
        this.onlyDefinitions = onlyDefinitions;
    }

    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor)
            return new OutlineRootEditPart((UCMNavMultiPageEditor) model);
        else if (model instanceof URNspec)
            return new URNspecTreeEditPart((URNspec) model, onlyDefinitions);
        else if (model instanceof UCMmap)
            return new MapTreeEditPart((UCMmap) model);
        else if (model instanceof ComponentRef)
            return new ComponentRefTreeEditPart((ComponentRef) model);
        else if (model instanceof RespRef)
            return new RespRefTreeEditPart((RespRef) model);
        else if (model instanceof Stub)
            return new StubTreeEditPart((Stub) model);
        else if (model instanceof PathNode)
            return new PathNodeTreeEditPart((PathNode) model);
        else if (model instanceof String)
            return new LabelTreeEditPart(model, urn);
        else if (model instanceof Component)
            return new ComponentTreeEditPart((Component) model);
        else if (model instanceof ComponentTreeWrapper)
            return new ComponentTreeEditPart((ComponentTreeWrapper) model);
        else if (model instanceof Responsibility)
            return new ResponsibilityTreeEditPart((Responsibility) model);
        else if (model instanceof GRLGraph)
            return new GrlGraphTreeEditPart((GRLGraph) model);
        else if (model instanceof IntentionalElementRef)
            return new IntentionalElementRefTreeEditPart((IntentionalElementRef) model);
        else if (model instanceof IntentionalElement)
            return new IntentionalElementTreeEditPart(model);
        else if (model instanceof KPIInformationElementRef)
            return new KPIInformationElementRefTreeEditPart((KPIInformationElementRef) model);
        else if (model instanceof KPIInformationElement)
            return new KPIInformationElementTreeEditPart(model);
        else if (model instanceof Belief)
            return new BeliefTreeEditPart((Belief) model);
        else if (model instanceof ActorRef)
            return new ActorRefTreeEditPart((ActorRef) model);
        else if (model instanceof Actor)
            return new ActorTreeEditPart((Actor) model);
        else if (model instanceof ElementLink)
            return new ElementLinkTreeEditPart(model);
        else if (model instanceof KPIModelLink)
            return new KPIModelLinkTreeEditPart(model);
        else if (model instanceof GeneralResource)
            return new GeneralResourceTreeEditPart((GeneralResource) model);
        else if (model instanceof Demand)
            return new DemandTreeEditPart((Demand) model);
        else if (model instanceof Concern)
            return new ConcernTreeEditPart((Concern) model);
        else
            return null;
    }

}