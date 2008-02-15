package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.GRLGraph;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ucm.map.ComponentRef;
import ucm.map.RespRef;
import ucm.map.UCMmap;
import urncore.Component;
import urncore.Responsibility;

/**
 * @author pchen
 * 
 */
public class KPIViewEditPartFactory implements EditPartFactory {

    public KPIViewEditPartFactory() {
    }

    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof IntentionalElement) {
            return new KPIViewIntentionalElementEditPart((IntentionalElement) model);
        } else if (model instanceof IntentionalElementRef) {
            return new KPIViewIntentionalElementEditPart((IntentionalElementRef) model);
        } else if (model instanceof IndicatorGroup) {
            return new KPIViewIndicatorGroupEditPart((IndicatorGroup) model);
        } else if (model instanceof KPIInformationElement) {
            return new KPIViewKPIInformationElementEditPart((KPIInformationElement) model);
        } else if (model instanceof KPIInformationElementRef) {
            return new KPIViewKPIInformationElementEditPart((KPIInformationElementRef) model);
        } else if (model instanceof GRLspec) {
            return new KPIViewGRLspecEditPart((GRLspec) model);
        } else if (model instanceof GRLGraph) {
            return new KPIViewGRLGraphEditPart((GRLGraph) model);
        } else if (model instanceof Responsibility) {
            return new KPIViewResponsibilityEditPart((Responsibility) model);
        } else if (model instanceof RespRef) {
            return new KPIViewResponsibilityEditPart((RespRef) model);
        } else if (model instanceof Component) {
            return new KPIViewComponentEditPart((Component) model);
        } else if (model instanceof ComponentRef) {
            return new KPIViewComponentEditPart((ComponentRef) model);
        } else if (model instanceof UCMmap) {
            return new KPIViewUCMmapEditPart((UCMmap) model);
        } else if (model instanceof KPIViewObject) {
            return new KPIViewObjectEditPart((KPIViewObject) model);
        } else {
            return new KPIViewRootEditPart(model);
        }
    }
}