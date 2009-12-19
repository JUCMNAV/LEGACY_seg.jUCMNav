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

import seg.jUCMNav.importexport.z151.generated.ExternalOperation;

public class ExternalOperationMHandler extends ActiveResourceMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.ExternalOperation elem = (ucm.performance.ExternalOperation) o;
		String objId = elem.getId();
		ExternalOperation elemZ = (ExternalOperation) getObject(objId, target, "createExternalOperation"); //$NON-NLS-1$
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

			processList(elem.getDemands(), elemZ.getDemands(), "createExternalOperationDemands", false); //$NON-NLS-1$
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
