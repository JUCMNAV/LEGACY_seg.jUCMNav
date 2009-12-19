package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ComponentType  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ComponentType">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="instances" type="xsd:IDREF"/>  <!-- Component -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ComponentType;

public class ComponentTypeUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ComponentType elemZ = (ComponentType) o;
		String objId = elemZ.getId();
		urncore.ComponentType elem = (urncore.ComponentType) getObject(objId, target, urncore.ComponentType.class);
		if (isFullConstruction) {
			elem = (urncore.ComponentType) super.handle(elemZ, elem, false);
			elem.setUrndefinition(urn.getUrndef());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUrndefinition();
			processList(elemZ.getInstances(), elem.getInstances(), false);
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
