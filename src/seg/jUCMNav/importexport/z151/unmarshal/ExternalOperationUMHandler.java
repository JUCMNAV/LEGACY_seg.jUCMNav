package seg.jUCMNav.importexport.z151.unmarshal;

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

public class ExternalOperationUMHandler extends ActiveResourceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ExternalOperation elemZ = (ExternalOperation) o;
		String objId = elemZ.getId();
		ucm.performance.ExternalOperation elem = (ucm.performance.ExternalOperation) getObject(objId, target, ucm.performance.ExternalOperation.class);
		if (isFullConstruction) {
			elem = (ucm.performance.ExternalOperation) super.handle(elemZ,
					elem, true);
			elem.setOpTime(elemZ.getOpTime());
			// elem.setUcmspec();
			// elem.setMultiplicity();
			// elem.setSchedPolicy();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getDemands(), elem.getDemands(), false);
			// elem.getOpTime();
			// elem.getUcmspec();
			// elem.getMultiplicity();
			// elem.getSchedPolicy();
			// elem.getFromLinks();
			// elem.getToLinks();
			// elem.getMetadata();
			// elem.getName();
			// elem.getId();
			// elem.getDescription();
			// elem.getClass();
		}
		return elem;
	}
}
