package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.ElementLink;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pchen
 * 
 */
public class KPIViewGRLGraphEditPart extends AbstractKPIViewEditPart {

    public KPIViewGRLGraphEditPart(GRLGraph element) {
        super(element);
    }

    // retrieve and return KPIViewObjects from the model element
    protected List createKPIViewObjects() {
        List kpiViewObjects = new ArrayList();
        Indicator[] indicators = new Indicator[0];

        GRLGraph model = getNode();
        Map indicatorMap = new HashMap();
        List urnNodes = model.getNodes();
        for (int i = 0; i < urnNodes.size(); i++) {
            if (urnNodes.get(i) instanceof IntentionalElementRef) {
                IntentionalElement intElem = ((IntentionalElementRef) urnNodes.get(i)).getDef();

                if (intElem instanceof Indicator) {
                    indicatorMap.put(((Indicator) intElem).getId(), (Indicator) intElem);
                } else {
                    findAllIndicators(intElem, indicatorMap);
                }
            }
        }

        indicators = (Indicator[]) indicatorMap.values().toArray(new Indicator[0]);
        int current_y = 0;
        for (int i = 0; i < indicators.length; i++) {
            KPIViewObject kpiViewObject = new KPIViewObject((Indicator) indicators[i]);
            kpiViewObject.setY(current_y);
            current_y = current_y + kpiViewObject.getHeight();

            kpiViewObjects.add(kpiViewObject);
        }

        return kpiViewObjects;
    }

    private void findAllIndicators(IntentionalElement model, Map indicatorMap) {
        if (model == null) return; // bug 780
        ElementLink[] elementLinks = (ElementLink[]) model.getLinksDest().toArray(new ElementLink[0]);
        if (elementLinks != null) {
            for (int i = 0; i < elementLinks.length; i++) {
                // TODO: Make sure this GRLLinkableElement is an IntentionalElement
                IntentionalElement intElem = (IntentionalElement) elementLinks[i].getSrc();
                if (intElem instanceof Indicator) {
                    indicatorMap.put(((Indicator) intElem).getId(), (Indicator) intElem);
                } else {
                    findAllIndicators(intElem, indicatorMap);
                }
            }
        }

    }

    /**
     * 
     * @return the KPI information element.
     */
    private GRLGraph getNode() {
        return (GRLGraph) getModel();
    }

}
