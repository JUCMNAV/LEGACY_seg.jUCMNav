package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GRLmodelElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GRLmodelElement">
//    <xsd:complexContent>
//      <xsd:extension base="URNmodelElement"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.GRLmodelElement;

public class GRLmodelElementUMHandler extends URNmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GRLmodelElement elemZ = (GRLmodelElement) o;
		String objId = elemZ.getId();
		urncore.GRLmodelElement elem = (urncore.GRLmodelElement) getObject(objId, target, urncore.GRLmodelElement.class);
		if (isFullConstruction) {
			elem = (urncore.GRLmodelElement) super.handle(elemZ, elem, true);
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