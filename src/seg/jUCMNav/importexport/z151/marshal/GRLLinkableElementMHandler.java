package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.GRLLinkableElement;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  GRLLinkableElement  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="GRLLinkableElement">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="linksDest" type="xsd:IDREF"/> <!-- ElementLink -->
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="linksSrc" type="xsd:IDREF"/> <!-- ElementLink -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>


public class GRLLinkableElementMHandler extends GRLmodelElementMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.GRLLinkableElement elem = (grl.GRLLinkableElement) obj;
		String objId = elem.getId();
		GRLLinkableElement elemZ = (GRLLinkableElement) getObject(objId, target, "createGRLLinkableElement"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (GRLLinkableElement) super.handle(elem, elemZ, true);
			processList(elem.getLinksDest(), elemZ.getLinksDest(), "createGRLLinkableElementLinksDest", false); //$NON-NLS-1$
			processList(elem.getLinksSrc(), elemZ.getLinksSrc(), "createGRLLinkableElementLinksSrc", false); //$NON-NLS-1$
		}
		return elemZ;
	}
}
