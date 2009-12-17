package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  URNmodelElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="URNmodelElement">
//    <xsd:sequence>
//      <xsd:element name="id" type="xsd:ID"/>
//      <xsd:element name="name" type="xsd:string"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="metadata" type="Metadata"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="toLinks" type="xsd:IDREF"/> <!-- URNlink -->
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="fromLinks" type="xsd:IDREF"/> <!-- URNlink -->
//      <xsd:element minOccurs="0" name="desc" type="Description"/>
//      <xsd:element minOccurs="0" name="concern" type="xsd:IDREF"/> <!-- Concern -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.URNmodelElement;

public class URNmodelElementUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		URNmodelElement elemZ = (URNmodelElement) o;
		String objId = elemZ.getId();
		urncore.URNmodelElement elem = (urncore.URNmodelElement) getObject(elemZ.getId(), target, urncore.URNmodelElement.class);
		if (isFullConstruction) {
			elem.setId(elemZ.getId());
			elem.setName(elemZ.getName());
			if (elemZ.getDesc() != null)
				elem.setDescription(elemZ.getDesc().getDescription());
			// elemZ.getConcern() handled by GRLGraphUMHandler
			processList(elemZ.getFromLinks(), elem.getFromLinks(), false);
			processList(elemZ.getToLinks(), elem.getToLinks(), false);
			processList(elemZ.getMetadata(), elem.getMetadata(), true);
			// elem.getName();
			// elem.getId();
			// elem.getDescription();
			// elem.getClass();
		}
		return elem;
	}
}