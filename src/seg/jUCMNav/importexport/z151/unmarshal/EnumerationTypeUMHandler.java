package seg.jUCMNav.importexport.z151.unmarshal;

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

public class EnumerationTypeUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		EnumerationType elemZ = (EnumerationType) o;
		String objId = elemZ.getId();
		ucm.scenario.EnumerationType elem = (ucm.scenario.EnumerationType) getObject(objId, target, ucm.scenario.EnumerationType.class);
		if (isFullConstruction) {
			elem = (ucm.scenario.EnumerationType) super.handle(elemZ, elem, true);
			//elem.setUcmspec(); handled by UCMspecUMHandler
			elem.setValues(elemZ.getValues());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUcmspec();
			processList(elemZ.getInstances(), elem.getInstances(), false);
			// elem.getValues();
			// elem.getFromLinks();
			// elem.getToLinks();
			// elem.getMetadata();
			// elem.getName();
			// elem.getId();
			// elem.getDescription();
			// elem.getClass();
		}
		return elem;
		
	}
}