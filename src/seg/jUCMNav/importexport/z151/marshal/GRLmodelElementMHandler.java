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
		GRLmodelElement elemZ = (GRLmodelElement) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = (GRLmodelElement) of.createGRLLinkableElement();
			}else
				elemZ = (GRLmodelElement) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			super.handle(elem,elemZ, true);
		}
		return elemZ;
	}
}
