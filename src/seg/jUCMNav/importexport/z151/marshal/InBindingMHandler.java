package seg.jUCMNav.importexport.z151.marshal;

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

public class InBindingMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.InBinding elem = (ucm.map.InBinding) o;
		String objId = this.getObjectId(elem);
		InBinding elemZ = (InBinding) id2object.get(objId);
		if (null == elemZ) {
			if (null == target) {
				elemZ = of.createInBinding();
				elemZ.setId(objId);
			} else
				elemZ = (InBinding) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ.setStartPoint(process(elem.getStartPoint(), null, false));
			elemZ.setStubEntry(process(elem.getStubEntry(), null, false));
			// elemZ.getStubEntry();
			// elemZ.getStartPoint();
			// elemZ.getClass();
		}
		return elemZ;
	}

	public int hashCode(Object obj) {
		int hash = 7;
		if (null != obj) {
			ucm.map.InBinding elem = (ucm.map.InBinding) obj;
			hash = 31 * hash + elem.getStartPoint().getId().hashCode();
			hash = 31 * hash + elem.getStubEntry().hashCode();
		} else {
			hash = 0;
		}
		return hash;
	}
}
