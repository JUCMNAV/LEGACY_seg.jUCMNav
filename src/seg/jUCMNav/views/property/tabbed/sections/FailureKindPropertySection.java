package seg.jUCMNav.views.property.tabbed.sections;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;

import seg.jUCMNav.Messages;
import ucm.map.FailureKind;
import ucm.map.MapPackage;
import ucm.map.StartPoint;

public class FailureKindPropertySection extends AbstractEnumerationPropertySection {

    protected String[] getEnumerationFeatureValues() {
        List values = FailureKind.VALUES;
        String[] result = new String[values.size()];

        for (int i = 0; i < result.length; i++)
            result[i] = ((FailureKind) values.get(i)).getName();

        return result;
    }

    protected EAttribute getFeature() {
        return MapPackage.eINSTANCE.getStartPoint_FailureKind();
    }

    protected String getFeatureAsText() {
        return ((StartPoint) eObject).getFailureKind().getName();
    }

    protected Object getFeatureValue(int index) {
        return FailureKind.VALUES.get(index);
    }

    public String getLabelText() {
        return Messages.getString("FailureKindPropertySection_Kind"); //$NON-NLS-1$
    }

    protected boolean isEqual(int index) {
        return ((StartPoint) eObject).getFailureKind().equals(FailureKind.VALUES.get(index));
    }
}
