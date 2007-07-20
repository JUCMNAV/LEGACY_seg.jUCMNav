package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pchen
 * 
 */
public class KPIViewIndicatorGroupEditPart extends AbstractKPIViewEditPart {

    public KPIViewIndicatorGroupEditPart(IndicatorGroup element) {
        super(element);
    }

    // retrieve and return KPIViewObjects from the model element
    protected List createKPIViewObjects() {
        List kpiViewObjects = new ArrayList();
        Indicator[] indicators = new Indicator[0];

        IndicatorGroup model = getNode();
        indicators = (Indicator[]) model.getIndicators().toArray(new Indicator[0]);

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
    private IndicatorGroup getNode() {
        return (IndicatorGroup) getModel();
    }

}
