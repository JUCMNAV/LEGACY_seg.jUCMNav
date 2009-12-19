package seg.jUCMNav.importexport.z151.unmarshal;

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

public class ScenarioGroupUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ScenarioGroup elemZ = (ScenarioGroup) o;
		String objId = elemZ.getId();
		ucm.scenario.ScenarioGroup elem = (ucm.scenario.ScenarioGroup) getObject(elemZ.getId(), target, ucm.scenario.ScenarioGroup.class);
		if (isFullConstruction) {
		elem = (ucm.scenario.ScenarioGroup) super.handle(elemZ, elem, true);
//		elem.setUcmspec(); handled by UCMspecUMHandler
//		elem.setId();
//		elem.setName();
//		elem.setDescription();

//		elem.getUcmspec();
		processList(elemZ.getScenarios(), elem.getScenarios(), true); //handling UCMspecUMHandler elemZ.getScenarioDefs()
		for (Object item : elem.getScenarios()){
			((ucm.scenario.ScenarioDef) item).setGroup(elem);
		}
//		elem.getFromLinks();
//		elem.getToLinks();
//		elem.getMetadata();
//		elem.getName();
//		elem.getId();
//		elem.getDescription();
//		elem.getClass();
		}
		return elem;
	}
}
