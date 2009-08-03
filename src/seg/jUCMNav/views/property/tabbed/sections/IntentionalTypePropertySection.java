package seg.jUCMNav.views.property.tabbed.sections;

import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementType;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;

public class IntentionalTypePropertySection extends
		AbstractEnumerationPropertySection {

	protected String[] getEnumerationFeatureValues() {
		List values = IntentionalElementType.VALUES;
		String[] result = new String[values.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ((IntentionalElementType)values.get(i)).getName();
		}
		
		return result;
	}

	protected EAttribute getFeature() {
		return GrlPackage.eINSTANCE.getIntentionalElement_Type();
	}

	protected String getFeatureAsText() {
		return ((IntentionalElement)eObject).getType().getName();
	}

	
	protected Object getFeatureValue(int index) {
		return IntentionalElementType.VALUES.get(index);
	}

	protected String getLabelText() {
		return "Type:";
	}

	protected boolean isEqual(int index) {
		return ((IntentionalElement)eObject).getType().equals(IntentionalElementType.VALUES.get(index));
	}

}
