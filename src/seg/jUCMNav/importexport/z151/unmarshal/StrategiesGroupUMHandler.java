package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  StrategiesGroup  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="StrategiesGroup">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="strategies" type="xsd:IDREF"/> <!-- EvaluationStrategy -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.StrategiesGroup;

public class StrategiesGroupUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		StrategiesGroup elemZ = (StrategiesGroup) o;
		String objId = elemZ.getId();
		grl.StrategiesGroup elem = (grl.StrategiesGroup) getObject(elemZ.getId(), target, grl.StrategiesGroup.class);
		if (isFullConstruction) {
			elem = (grl.StrategiesGroup) super.handle(elemZ, elem, true);
			//elem.setGrlspec(); //Handled in GRLspecUMHandler
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getGrlspec();
			processList(elemZ.getStrategies(), elem.getStrategies(), false);
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
