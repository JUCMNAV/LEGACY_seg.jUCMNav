package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementRef;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  IntentionalElementRef  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="IntentionalElementRef">
//<xsd:complexContent>
//  <xsd:extension base="GRLNode">
//    <xsd:sequence>
//      <xsd:element name="def" type="xsd:IDREF"/> <!-- IntentionalElement -->
//    </xsd:sequence>
//  </xsd:extension>
//</xsd:complexContent>
//</xsd:complexType>

public class IntentionalElementRefMHandler extends GRLNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.IntentionalElementRef elem = (grl.IntentionalElementRef) o;
		String objId = elem.getId();
		IntentionalElementRef elemZ = (IntentionalElementRef) getObject(objId, target, "createIntentionalElementRef"); //$NON-NLS-1$
		elemZ.setDef((IntentionalElement) process(elem.getDef(), null, false));
		if (isFullConstruction) {			
			elemZ = (IntentionalElementRef) super.handle(elem, elemZ, true);
			elemZ.setDef((IntentionalElement) process(elem.getDef(), null, false));
		}
		return elemZ;
	}
}
