package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GeneralResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GeneralResource">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element default="1" name="multiplicity" type="xsd:nonNegativeInteger"/>
//          <xsd:element name="schedPolicy" type="xsd:string"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.GeneralResource;

public class GeneralResourceUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GeneralResource elemZ = (GeneralResource) o;
		String objId = elemZ.getId();
		ucm.performance.GeneralResource elem = (ucm.performance.GeneralResource) getObject(objId, target, ucm.performance.GeneralResource.class);
		if (isFullConstruction) {
			elem = (ucm.performance.GeneralResource) super.handle(elemZ, elem, true);
			//elem.setUcmspec(); handled by UCMspecUMHandler
			elem.setMultiplicity(elemZ.getMultiplicity().toString());
			elem.setSchedPolicy(elemZ.getSchedPolicy());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUcmspec();
			// elem.getMultiplicity();
			// elem.getSchedPolicy();
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