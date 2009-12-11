package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Concern  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Concern">
//  <xsd:complexContent>
//    <xsd:extension base="URNmodelElement">
//      <xsd:sequence>
//        <xsd:element minOccurs="0" name="condition" type="xsd:IDREF"/>  <!-- Condition -->
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="elements" type="Condition"/>  <!-- URNmodelElement -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>


public class ConcernMHandler extends URNmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.Concern elem = (urncore.Concern) o;
		String objId = elem.getId();
		Concern elemZ = (Concern) getObject(objId, target, "createConcern");
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
			elemZ.setCondition((Condition) process(elem.getCondition(), null, false));
			//elemZ.getElements() handled by GRLGraphMHandler, UCMmapMHandler. This only cover concerns for GRLGraph and UCMmap
		}
		return elemZ;
	}
}
