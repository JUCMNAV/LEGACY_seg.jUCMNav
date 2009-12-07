package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ExternalOperation  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ExternalOperation">
//    <xsd:complexContent>
//      <xsd:extension base="ActiveResource">
//        <xsd:sequence>
//		<xsd:element maxOccurs="unbounded" minOccurs="0" name="demands" type="xsd:IDREF"/> <!-- Demand -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;

public class ExternalOperationMHandler extends ActiveResourceMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.ExternalOperation elem = (ucm.performance.ExternalOperation) o;
		String objId = elem.getId();
		ExternalOperation elemZ = (ExternalOperation) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createExternalOperation();
			}else
				elemZ = (ExternalOperation) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (ExternalOperation) super.handle(elem, elemZ, true);
			// elemZ.setOpTime();
			// elemZ.setUnit();
			// elemZ.setMultiplicity();
			// elemZ.setSchedPolicy();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getDemands(), elemZ.getDemands(), "createExternalOperationDemands", false);
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
