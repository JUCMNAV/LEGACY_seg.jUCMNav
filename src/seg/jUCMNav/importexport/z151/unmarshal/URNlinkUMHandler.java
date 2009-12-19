package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  URNlink  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="URNlink">
//    <xsd:complexContent>
//      <xsd:extension base="URNmodelElement">
//        <xsd:sequence>
//          <xsd:element name="type" type="xsd:string"/>
//          <xsd:element name="toElem" type="xsd:IDREF"/> <!-- URNmodelElement -->
//          <xsd:element name="fromElem" type="xsd:IDREF"/> <!-- URNmodelElement -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.URNlink;
import seg.jUCMNav.importexport.z151.generated.URNmodelElement;

public class URNlinkUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		URNlink elemZ = (URNlink) o;
		String objId = elemZ.getId();
		urn.URNlink elem = (urn.URNlink) getObject(elemZ.getId(), target, urn.URNlink.class);
		if (isFullConstruction) {
			elem.setUrnspec(urn);
			elem.setFromElem((urncore.URNmodelElement) process((URNmodelElement) elemZ.getFromElem(), null, false));
			elem.setToElem((urncore.URNmodelElement) process((URNmodelElement) elemZ.getToElem(), null, false));
			elem.setType(elemZ.getType());

			processList(elemZ.getMetadata(), elem.getMetadata(), true);
			// elem.getUrnspec();
			// elem.getFromElem();
			// elem.getToElem();
			// elem.getType();
			// elem.getClass();
			// TODO elemZ.getConcern()
			// TODO elemZ.getDesc()
			// TODO elemZ.getFromLinks()
			// TODO elemZ.getName()
			// TODO elemZ.getToLinks()
		}
		return elem;
	}
}