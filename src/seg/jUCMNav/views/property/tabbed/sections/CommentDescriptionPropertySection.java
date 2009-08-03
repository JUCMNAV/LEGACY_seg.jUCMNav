package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;

import urncore.UrncorePackage;

public class CommentDescriptionPropertySection extends
		DescriptionPropertySection {

	protected EAttribute getFeature() {
		return UrncorePackage.eINSTANCE.getComment_Description();
	}

}
