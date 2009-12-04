package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.ElementLink;
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
public class KPIViewIntentionalElementEditPart extends AbstractKPIViewEditPart {

    public KPIViewIntentionalElementEditPart(IntentionalElement element) {
        super(element);
    }

    public KPIViewIntentionalElementEditPart(IntentionalElementRef ref) {
        super(ref.getDef());
    }

    // retrieve and return KPIViewObjects from the model element
    protected List createKPIViewObjects() {
        List kpiViewObjects = new ArrayList();
        Indicator[] indicators = new Indicator[0];

        IntentionalElement model = getNode();
        if (model instanceof Indicator) {
            indicators = new Indicator[1];
            indicators[0] = (Indicator) model;
        } else {
            Map indicatorMap = new HashMap();
            findAllIndicators(model, indicatorMap);

            indicators = (Indicator[]) indicatorMap.values().toArray(new Indicator[0]);
        }

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
    private IntentionalElement getNode() {
        return (IntentionalElement) getModel();
    }

}
