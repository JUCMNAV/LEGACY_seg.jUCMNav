package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Demand  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Demand">
//    <xsd:sequence>
//      <xsd:element name="quantity" type="xsd:string"/>
//      <xsd:element name="resource" type="xsd:IDREF"/> <!-- ExternalOperation -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Demand;
import seg.jUCMNav.importexport.z151.generated.ExternalOperation;
import seg.jUCMNav.model.ModelCreationFactory;

public class DemandUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Demand elemZ = (Demand) o;
		ucm.performance.Demand elem = null;
		if (null == elem) {
			if (null == target) {
				elem = (ucm.performance.Demand) ModelCreationFactory.getNewObject(urn, ucm.performance.Demand.class);
			} else
				elem = (ucm.performance.Demand) target;
		}
		if (isFullConstruction) {
			elem.setResource((ucm.performance.ExternalOperation) process((ExternalOperation) elemZ.getResource(), null, true));
			elem.setQuantity(elemZ.getQuantity());
			// elem.setResponsibility(); handled by ResponsibilityUMHandler

			// elem.getResponsibility();
			// elem.getQuantity();
			// elem.getResource();
			// elem.getClass();
		}
		return elem;

	}
}
