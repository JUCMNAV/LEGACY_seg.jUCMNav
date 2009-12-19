package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ScenarioGroup  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ScenarioGroup">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="scenarios" type="xsd:IDREF"/> <!-- ScenarioDef -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ScenarioGroup;

public class ScenarioGroupMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.ScenarioGroup elem = (ucm.scenario.ScenarioGroup) o;
		String objId = elem.getId();
		ScenarioGroup elemZ = (ScenarioGroup) getObject(objId, target, "createScenarioGroup"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (ScenarioGroup) super.handle(elem, elemZ, true);
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getScenarios(), elemZ.getScenarios(), "createScenarioGroupScenarios", false); //$NON-NLS-1$
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
