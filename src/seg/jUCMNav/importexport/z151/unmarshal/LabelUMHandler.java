package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Label  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Label">
//    <xsd:sequence>
//      <xsd:element name="deltaX" type="xsd:integer"/>
//      <xsd:element name="deltaY" type="xsd:integer"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Label;
import seg.jUCMNav.model.ModelCreationFactory;

public class LabelUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Label elemZ = (Label) o;
		urncore.Label elem = null;
		if (null == elem) {
			if (null == target)
				elem = (urncore.Label) ModelCreationFactory.getNewObject(urn,
						urncore.Label.class);
			else
				elem = (urncore.Label) target;

		}
		if (isFullConstruction) {
			elem.setDeltaX(elemZ.getDeltaX().intValue());
			elem.setDeltaY(elemZ.getDeltaY().intValue());

			// elem.getDeltaX();
			// elem.getDeltaY();
			// elem.getClass();
		}
		return elem;
	}
}
