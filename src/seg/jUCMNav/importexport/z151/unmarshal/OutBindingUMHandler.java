package seg.jUCMNav.importexport.z151.unmarshal;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  OutBinding  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="OutBinding">
//	<xsd:sequence>
//		<xsd:element name="id" type="xsd:ID" /> <!-- ADDED because OutBinding is not a URNmodelElement (no ID) -->
//		<xsd:element name="endPoint" type="xsd:IDREF" /> <!-- EndPoint -->
//		<xsd:element name="stubExit" type="xsd:IDREF" /> <!-- NodeConnection -->
//	</xsd:sequence>
//</xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.EndPoint;
import seg.jUCMNav.importexport.z151.generated.NodeConnection;
import seg.jUCMNav.importexport.z151.generated.OutBinding;

public class OutBindingUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		OutBinding elemZ = (OutBinding) o;
		ucm.map.OutBinding elem = (ucm.map.OutBinding) getObject(elemZ.getId(), target, ucm.map.OutBinding.class);
		if (isFullConstruction) {
			elem.setEndPoint((ucm.map.EndPoint) process((EndPoint) elemZ.getEndPoint(), null, false));
			// elem.setBinding(); handled by PluginBindingUMHandler
			elem.setStubExit((ucm.map.NodeConnection) process((NodeConnection) elemZ.getStubExit(), null, false));

			// elem.getBinding();
			// elem.getStubExit();
			// elem.getEndPoint();
			// elem.getClass();
		}
		return elem;
	}
}