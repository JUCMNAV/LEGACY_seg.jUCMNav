package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.GRLmodelElement;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  GRLmodelElement  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="GRLmodelElement">
//  <xsd:complexContent>
//    <xsd:extension base="URNmodelElement"/>
//  </xsd:complexContent>
//</xsd:complexType>

public class GRLmodelElementMHandler extends URNmodelElementMHandler{
	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		urncore.GRLmodelElement elem = (urncore.GRLmodelElement) obj;
		String objId = elem.getId();
		GRLmodelElement elemZ = (GRLmodelElement) getObject(objId, target, "createGRLmodelElement"); //$NON-NLS-1$
		if (isFullConstruction) {
			super.handle(elem,elemZ, true);
		}
		return elemZ;
	}
}
