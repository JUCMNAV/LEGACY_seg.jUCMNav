package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import seg.jUCMNav.Messages;
import ucm.map.MapPackage;
import ucm.map.RespRef;
import urncore.NodeLabel;

public class RepetitionPropertySection extends AbstractStringPropertySection {

    protected EAttribute getFeature() {
        return MapPackage.eINSTANCE.getRespRef_RepetitionCount();
    }

    public String getLabelText() {
        return Messages.getString("RepetitionPropertySection_RepCount"); //$NON-NLS-1$
    }

    protected Object getDataForSection(Object obj) {
        if (obj instanceof RespRef)
            return obj;
        else if (obj instanceof NodeLabel) {
            if (((NodeLabel) obj).eContainer() instanceof RespRef)
                return ((NodeLabel) obj).eContainer();
        }

        return super.getDataForSection(obj);
    }
}
