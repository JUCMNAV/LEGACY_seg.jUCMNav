package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.GRLspec;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pchen
 * 
 */
public class KPIViewGRLspecEditPart extends AbstractKPIViewEditPart {

    public KPIViewGRLspecEditPart(GRLspec element) {
        super(element);
    }

    // retrieve and return KPIViewObjects from the model element
    protected List createKPIViewObjects() {
        List kpiViewObjects = new ArrayList();
        Map indicatorMap = new HashMap();

        GRLspec model = getNode();
        IndicatorGroup[] indicatorGroups = new IndicatorGroup[0];
        Indicator[] indicators = new Indicator[0];

        indicatorGroups = (IndicatorGroup[]) model.getIndicatorGroup().toArray(new IndicatorGroup[0]);
        for (int i = 0; i < indicatorGroups.length; i++) {
            indicators = (Indicator[]) indicatorGroups[i].getIndicators().toArray(new Indicator[0]);

            for (int j = 0; j < indicators.length; j++) {
                indicatorMap.put(indicators[j].getId(), indicators[j]);
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

    /**
     * 
     * @return the KPI information element.
     */
    private GRLspec getNode() {
        return (GRLspec) getModel();
    }

}
