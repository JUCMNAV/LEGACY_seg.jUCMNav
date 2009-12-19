package seg.jUCMNav.importexport.z151.marshal;

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

public class ComponentTypeMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.ComponentType elem = (urncore.ComponentType) o;
		String objId = elem.getId();
		ComponentType elemZ = (ComponentType) getObject(objId, target, "createComponentType"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (ComponentType) super.handle(elem, elemZ, true);
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getInstances(), elemZ.getInstances(), "createComponentTypeInstances", false); //$NON-NLS-1$
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
