package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import urncore.UrncorePackage;

public class DescriptionPropertySection extends
		AbstractMultiLineStringPropertySection {

	protected EAttribute getFeature() {
		return UrncorePackage.eINSTANCE.getURNmodelElement_Description();
	}

	protected String getLabelText() {
		return "Description: ";
	}
}
