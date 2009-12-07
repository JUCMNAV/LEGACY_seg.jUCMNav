package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  InBinding  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="InBinding">
//    <xsd:sequence>
//      <xsd:element name="startPoint" type="xsd:IDREF"/>  <!-- StartPoint -->
//      <xsd:element name="stubEntry" type="xsd:IDREF"/>  <!-- NodeConnection -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class InBindingUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		InBinding elemZ = (InBinding) o;
		String objId = elemZ.getId();
		ucm.map.InBinding elem = (ucm.map.InBinding) id2object.get(objId);
		;
		if (null == elem) {
			if (null == target) {
				elem = (ucm.map.InBinding) ModelCreationFactory.getNewObject(urn, ucm.map.InBinding.class);
			} else
				elem = (ucm.map.InBinding) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem.setStartPoint((ucm.map.StartPoint) process((StartPoint) elemZ.getStartPoint(), null, false));
			// elem.setBinding(); handled by PluginBindingUMHandler
			elem.setStubEntry((ucm.map.NodeConnection) process(elem.getStubEntry(), null, false));

			// elem.getBinding();
			// elem.getStubEntry();
			// elem.getStartPoint();
			// elem.getClass();
		}
		return elem;
	}
}
