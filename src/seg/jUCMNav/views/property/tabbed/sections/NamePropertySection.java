package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import seg.jUCMNav.Messages;
import urn.URNspec;
import urn.UrnPackage;
import urncore.UrncorePackage;

public class NamePropertySection extends AbstractStringPropertySection {

    protected EAttribute getFeature() {
        if (eObject instanceof URNspec)
            return UrnPackage.eINSTANCE.getURNspec_Name();
        else
            return UrncorePackage.eINSTANCE.getURNmodelElement_Name();
    }

    public String getLabelText() {
        return Messages.getString("NamePropertySection_Name"); //$NON-NLS-1$
    }
}
