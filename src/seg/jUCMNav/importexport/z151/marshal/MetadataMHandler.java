package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.Metadata;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Metadata  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Metadata">
//  <xsd:sequence>
//    <xsd:element name="name" type="xsd:string"/>
//    <xsd:element name="value" type="xsd:string"/>
//  </xsd:sequence>
//</xsd:complexType>

public class MetadataMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.Metadata md = (urncore.Metadata) o;
		Metadata mdZ = of.createMetadata();
		if (isFullConstruction) {
			mdZ.setName(md.getName());
			mdZ.setValue(md.getValue());
		}
		return mdZ;
	}
}
