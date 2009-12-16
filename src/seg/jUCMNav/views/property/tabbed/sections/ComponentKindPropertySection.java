package seg.jUCMNav.views.property.tabbed.sections;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;

import seg.jUCMNav.Messages;
import urncore.Component;
import urncore.ComponentKind;
import urncore.UrncorePackage;

public class ComponentKindPropertySection extends AbstractEnumerationPropertySection {

    protected String[] getEnumerationFeatureValues() {
        List values = ComponentKind.VALUES;
        String[] result = new String[values.size()];

        for (int i = 0; i < result.length; i++)
            result[i] = ((ComponentKind) values.get(i)).getName();

        return result;
    }

    protected EAttribute getFeature() {
        return UrncorePackage.eINSTANCE.getComponent_Kind();
    }

    protected String getFeatureAsText() {
        return ((Component) eObject).getKind().getName();
    }

    protected Object getFeatureValue(int index) {
        return ComponentKind.VALUES.get(index);
    }

    public String getLabelText() {
        return Messages.getString("ComponentKindPropertySection_Kind"); //$NON-NLS-1$
    }

    protected boolean isEqual(int index) {
        return ((Component) eObject).getKind().equals(ComponentKind.VALUES.get(index));
    }
}
