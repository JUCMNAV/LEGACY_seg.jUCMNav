package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import urncore.UrncorePackage;

public class NamePropertySection extends AbstractStringPropertySection {

	protected EAttribute getFeature() {
		return UrncorePackage.eINSTANCE.getURNmodelElement_Name();
	}

	public String getLabelText() {
		return "Name:"; 
	}
}
