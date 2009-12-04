package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.ElementLink;
import grl.IntentionalElement;
import grl.kpimodel.Indicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ucm.map.RespRef;
import urn.URNlink;
import urncore.Responsibility;

/**
 * @author pchen
 * 
 */
public class KPIViewResponsibilityEditPart extends AbstractKPIViewEditPart {

    public KPIViewResponsibilityEditPart(Responsibility element) {
        super(element);
    }

    public KPIViewResponsibilityEditPart(RespRef ref) {
        super(ref.getRespDef());
    }

    // retrieve and return KPIViewObjects from the model element
    protected List createKPIViewObjects() {
        List kpiViewObjects = new ArrayList();
        Indicator[] indicators = new Indicator[0];

        Map indicatorMap = new HashMap();
        Responsibility model = getNode();
        List urnLinks = model.getToLinks();
        for (int i = 0; i < urnLinks.size(); i++) {
            if (((URNlink) urnLinks.get(i)).getFromElem() instanceof IntentionalElement) {
                IntentionalElement intElem = (IntentionalElement) ((URNlink) urnLinks.get(i)).getFromElem();
                findAllIndicators(intElem, indicatorMap);
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
    private Responsibility getNode() {
        return (Responsibility) getModel();
    }

}
