package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pchen
 * 
 */
public class KPIViewKPIInformationElementEditPart extends AbstractKPIViewEditPart {

    public KPIViewKPIInformationElementEditPart(KPIInformationElement element) {
        super(element);
    }

    public KPIViewKPIInformationElementEditPart(KPIInformationElementRef element) {
        super(element.getDef());
    }

    // retrieve and return KPIViewObjects from the model element
    protected List createKPIViewObjects() {
        List kpiViewObjects = new ArrayList();

        KPIInformationElement model = getNode();
        List kpiModelLinks = model.getKpiModelLinksSrc();
        int current_y = 0;
        for (int i = 0; i < kpiModelLinks.size(); i++) {
            KPIModelLink kpiModelLink = (KPIModelLink) kpiModelLinks.get(i);
            Indicator indicator = kpiModelLink.getIndDest();

            if (indicator != null) {
                KPIViewObject kpiViewObject = new KPIViewObject(indicator);
                kpiViewObject.setY(current_y);
                current_y = current_y + kpiViewObject.getHeight();

                kpiViewObjects.add(kpiViewObject);
            }
        }

        return kpiViewObjects;
    }

    /**
     * 
     * @return the KPI information element.
     */
    private KPIInformationElement getNode() {
        return (KPIInformationElement) getModel();
    }
}
