package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import urn.URNspec;
import urn.UrnPackage;
import urncore.Comment;
import urncore.UrncorePackage;

public class DescriptionPropertySection extends
		AbstractMultiLineStringPropertySection {

	protected EAttribute getFeature() {
		if(eObject instanceof URNspec)
			return UrnPackage.eINSTANCE.getURNspec_Description();
		else if(eObject instanceof Comment)
			return UrncorePackage.eINSTANCE.getComment_Description();
		else
			return UrncorePackage.eINSTANCE.getURNmodelElement_Description();
	}

	public String getLabelText() {
		return "Description: ";
	}
}
