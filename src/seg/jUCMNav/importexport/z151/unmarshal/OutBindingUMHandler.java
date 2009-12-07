package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  OutBinding  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="OutBinding">
//    <xsd:sequence>
//      <xsd:element name="endPoint" type="xsd:IDREF"/> <!-- EndPoint -->
//      <xsd:element name="stubExit" type="xsd:IDREF"/> <!-- NodeConnection -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class OutBindingUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		OutBinding elemZ = (OutBinding) o;
		String objId = elemZ.getId();
		ucm.map.OutBinding elem = (ucm.map.OutBinding) id2object.get(objId);
		if (null == elem) {
			if (null == target) {
				elem = (ucm.map.OutBinding) ModelCreationFactory.getNewObject(urn, ucm.map.OutBinding.class);
			} else
				elem = (ucm.map.OutBinding) target;
			id2object.put(objId, elem);
		}
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