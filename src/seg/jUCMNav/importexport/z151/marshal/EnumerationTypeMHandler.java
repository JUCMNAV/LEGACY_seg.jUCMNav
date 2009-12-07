package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EnumerationType  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EnumerationType">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element name="values" type="xsd:string"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="instances" type="xsd:IDREF"/> <!-- Variable -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;

public class EnumerationTypeMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.EnumerationType elem = (ucm.scenario.EnumerationType) o;
		String objId = elem.getId();
		EnumerationType elemZ = (EnumerationType) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createEnumerationType();
			}else
				elemZ = (EnumerationType) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (EnumerationType) super.handle(elem, elemZ, true);
			elemZ.setValues(elem.getValues());
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getInstances(), elemZ.getInstances(), "createEnumerationTypeInstances", false);
			// elemZ.getValues();
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
		}
		return elemZ;
	}
}