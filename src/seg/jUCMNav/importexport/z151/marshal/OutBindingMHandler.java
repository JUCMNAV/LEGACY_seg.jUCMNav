package seg.jUCMNav.importexport.z151.marshal;

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

public class OutBindingMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.OutBinding elem = (ucm.map.OutBinding) o;
		String objId = this.getObjectId(elem);
		OutBinding elemZ = (OutBinding) id2object.get(objId);
		if (null == elemZ) {
			if (null == target) {
				elemZ = of.createOutBinding();
				elemZ.setId(objId);
			} else
				elemZ = (OutBinding) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ.setEndPoint(process(elem.getEndPoint(), null, false));
			elemZ.setStubExit(process(elem.getStubExit(), null, false));
		}
		return elemZ;
	}

	public int hashCode(Object obj) {
		int hash = 7;
		if (null != obj) {
			ucm.map.OutBinding elem = (ucm.map.OutBinding) obj;
			hash = 31 * hash + elem.getEndPoint().getId().hashCode();
			hash = 31 * hash + elem.getStubExit().hashCode();
		} else {
			hash = 0;
		}
		return hash;
	}
}
