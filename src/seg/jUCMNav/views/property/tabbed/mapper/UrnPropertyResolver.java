package seg.jUCMNav.views.property.tabbed.mapper;

import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.kpimodel.KPIInformationElementRef;
import ucm.map.RespRef;
import urncore.Condition;
import urncore.IURNContainerRef;
import urncore.Label;

public class UrnPropertyResolver implements IPropertyDataResolver {

    public Object getData(Object obj) {
        if (obj instanceof Label && !(obj instanceof Condition))
            obj = ((Label) obj).eContainer(); // get referenced object.

        if (obj instanceof RespRef)
            obj = ((RespRef) obj).getRespDef();
        else if (obj instanceof IntentionalElementRef)
            obj = ((IntentionalElementRef) obj).getDef();
        else if (obj instanceof KPIInformationElementRef)
            obj = ((KPIInformationElementRef) obj).getDef();
        else if (obj instanceof IURNContainerRef)
            obj = ((IURNContainerRef) obj).getContDef();
        else if (obj instanceof LinkRef)
            obj = ((LinkRef) obj).getLink();

        return obj;
    }
}
