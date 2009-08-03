package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import urn.UrnPackage;

public class AuthorPropertySection extends AbstractStringPropertySection {

	protected EAttribute getFeature() {
		return UrnPackage.eINSTANCE.getURNspec_Author();
	}

	protected String getLabelText() {
		return "Author:";
	}

}
