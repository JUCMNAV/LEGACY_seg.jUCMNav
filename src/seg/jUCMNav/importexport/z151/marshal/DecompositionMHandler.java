package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.Decomposition;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Decomposition  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Decomposition">
//  <xsd:complexContent>
//    <xsd:extension base="ElementLink"/>
//  </xsd:complexContent>
//</xsd:complexType>

public class DecompositionMHandler extends ElementLinkMHandler {
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.Decomposition elem = (grl.Decomposition) obj;
		String objId = elem.getId();
		Decomposition elemZ = (Decomposition) getObject(objId, target, "createDecomposition"); //$NON-NLS-1$
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
		}
		return elemZ;
	}
}
