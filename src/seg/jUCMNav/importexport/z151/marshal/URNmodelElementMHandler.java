package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.Description;
import seg.jUCMNav.importexport.z151.generated.URNmodelElement;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  URNmodelElement  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="URNmodelElement">
//  <xsd:sequence>
//    <xsd:element name="id" type="xsd:ID"/>
//    <xsd:element name="name" type="xsd:string"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="metadata" type="Metadata"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="toLinks" type="xsd:IDREF"/> <!-- URNlink -->
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="fromLinks" type="xsd:IDREF"/> <!-- URNlink -->
//    <xsd:element minOccurs="0" name="desc" type="Description"/>
//    <xsd:element minOccurs="0" name="concern" type="xsd:IDREF"/> <!-- Concern -->
//  </xsd:sequence>
//</xsd:complexType>


public abstract class URNmodelElementMHandler extends MHandler {
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		urncore.URNmodelElement elem = (urncore.URNmodelElement) obj;
		URNmodelElement elemZ;
		if (null == target)
			elemZ = of.createURNmodelElement();
		else
			elemZ = (URNmodelElement) target;

		if (isFullConstruction) {
			elemZ.setId(elem.getId());
			elemZ.setName(elem.getName());
			processList(elem.getMetadata(), elemZ.getMetadata(), true);
			processList(elem.getToLinks(), elemZ.getToLinks(), "createURNmodelElementFromLinks", false); //$NON-NLS-1$
			processList(elem.getFromLinks(), elemZ.getFromLinks(), "createURNmodelElementToLinks", false); //$NON-NLS-1$
			if (null != elem.getDescription()) {
				Description desc = of.createDescription();
				desc.setDescription(elem.getDescription());
				elemZ.setDesc(desc);
			}
			//elemZ.getConcern() handled by GRLGraphMHandler and UCMmapMHandler
		}		
		return elemZ;
	}
}
