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

import seg.jUCMNav.importexport.z151.generated.EnumerationType;

public class EnumerationTypeMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.EnumerationType elem = (ucm.scenario.EnumerationType) o;
		String objId = elem.getId(); 
		EnumerationType elemZ = (EnumerationType) getObject(objId, target, "createEnumerationType"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (EnumerationType) super.handle(elem, elemZ, true);
			elemZ.setValues(elem.getValues());
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getInstances(), elemZ.getInstances(), "createEnumerationTypeInstances", false); //$NON-NLS-1$
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