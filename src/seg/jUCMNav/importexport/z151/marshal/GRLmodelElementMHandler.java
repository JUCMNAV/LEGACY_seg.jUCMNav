package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  GRLmodelElement  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="GRLmodelElement">
//  <xsd:complexContent>
//    <xsd:extension base="URNmodelElement"/>
//  </xsd:complexContent>
//</xsd:complexType>

/***Done!***/

public class GRLmodelElementMHandler extends URNmodelElementMHandler{
	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		urncore.GRLmodelElement elem = (urncore.GRLmodelElement) obj;
		String objId = elem.getId();
		GRLmodelElement elemZ = (GRLmodelElement) getObject(objId, target, "createGRLmodelElement");
		if (isFullConstruction) {
			super.handle(elem,elemZ, true);
		}
		return elemZ;
	}
}
