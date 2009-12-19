package seg.jUCMNav.importexport.z151.marshal;

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

public class DemandMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.Demand elem = (ucm.performance.Demand) o;
		Demand elemZ = null;
		if (null == target)
			elemZ = of.createDemand();
		else
			elemZ = (Demand) target;
		if (isFullConstruction) {
			elemZ.setResource(process(elem.getResource(), null, false));
			elemZ.setQuantity(elem.getQuantity());
			// elemZ.getQuantity();
			// elemZ.getResource();
			// elemZ.getClass();
		}
		return elemZ;
	}
}
