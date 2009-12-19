package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  UCMmodelElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="UCMmodelElement">
//    <xsd:complexContent>
//      <xsd:extension base="URNmodelElement"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.UCMmodelElement;

public class UCMmodelElementUMHandler extends URNmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		UCMmodelElement elemZ = (UCMmodelElement) o;
		String objId = elemZ.getId();
		urncore.UCMmodelElement elem = (urncore.UCMmodelElement) getObject(elemZ.getId(), target, urncore.UCMmodelElement.class);
		if (isFullConstruction) {
			elem = (urncore.UCMmodelElement) super.handle(elemZ, elem, true);
			// elem.setId();
			// elem.setName();
			// elem.setDescription();
			//
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
