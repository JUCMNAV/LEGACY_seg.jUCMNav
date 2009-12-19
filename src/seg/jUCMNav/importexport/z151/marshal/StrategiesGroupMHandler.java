package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.StrategiesGroup;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  StrategiesGroup  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="StrategiesGroup">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="strategies" type="xsd:IDREF"/> <!-- EvaluationStrategy -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>


public class StrategiesGroupMHandler extends GRLmodelElementMHandler {
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.StrategiesGroup elem = (grl.StrategiesGroup) obj;
		String objId = elem.getId();
		StrategiesGroup elemZ = (StrategiesGroup) getObject(objId, target, "createStrategiesGroup"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (StrategiesGroup) super.handle(elem, elemZ, true);
			processList(elem.getStrategies(), elemZ.getStrategies(), "createStrategiesGroupStrategies", false); //$NON-NLS-1$
		}
		return elemZ;
	}

}
