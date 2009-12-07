package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ProcessingResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ProcessingResource">
//    <xsd:complexContent>
//      <xsd:extension base="ActiveResource">
//        <xsd:sequence>
//          <xsd:element default="Processor" name="kind" type="DeviceKind"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="components" type="xsd:IDREF"/> <!-- Component -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;

public class ProcessingResourceMHandler extends ActiveResourceMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.ProcessingResource elem = (ucm.performance.ProcessingResource) o;
		String objId = elem.getId();
		ProcessingResource elemZ = (ProcessingResource) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createProcessingResource();
			}else
				elemZ = (ProcessingResource) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (ProcessingResource) super.handle(elem, elemZ, true);
			elemZ.setKind(getDeviceKind(elem.getKind()));
			// elemZ.setOpTime();
			// elemZ.setUnit();
			// elemZ.setMultiplicity();
			// elemZ.setSchedPolicy();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			//
			// elemZ.getKind();
			processList(elem.getComponents(), elemZ.getComponents(), "createProcessingResourceComponents", false);
			// elemZ.getOpTime();
			// elemZ.getUnit();
			// elemZ.getMultiplicity();
			// elemZ.getSchedPolicy();
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
		}
		return elemZ;
	}
}
