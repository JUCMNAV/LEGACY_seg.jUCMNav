package seg.jUCMNav.importexport.z151.marshal;

import java.util.List;
import javax.xml.bind.JAXBElement;
import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;

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

/*** Done! ***/

public class IntentionalElementRefMHandler extends GRLNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.IntentionalElementRef elem = (grl.IntentionalElementRef) o;
		String objId = elem.getId();
		IntentionalElementRef elemZ = (IntentionalElementRef) id2object.get(objId);
		if (elemZ == null) {
			if (null == target) {
				elemZ = of.createIntentionalElementRef();
			} else
				elemZ = (IntentionalElementRef) target;
			this.id2object.put(objId, elemZ);
		}
		elemZ.setDef((IntentionalElement) process(elem.getDef(), null, false));
		if (isFullConstruction) {			
			elemZ = (IntentionalElementRef) super.handle(elem, elemZ, true);
			//elemZ.setName(""); //This is fixed by removing label in GRLNodeUMHandler.java. No longer need this to get rid of extra words
			elemZ.setDef((IntentionalElement) process(elem.getDef(), null, false));
		}
		return elemZ;
	}
}
