package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ScenarioDef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ScenarioDef">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="initializations" type="Initialization"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="postconditions" type="Condition"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="preconditions" type="Condition"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="parentScenarios" type="xsd:IDREF"/>  <!-- ScenarioDef -->
//          <xsd:element minOccurs="0" name="includedScenarios" type="xsd:IDREFS"/>  <!-- ScenarioDef {ordered} -->
//	  <xsd:element maxOccurs="unbounded" name="groups" type="xsd:IDREF"/> <!-- ScenarioGroup -->
//	  <xsd:element minOccurs="0" name="startPoints" type="xsd:IDREFS"/>  <!-- StartPoint {ordered} -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="endPoints" type="xsd:IDREF"/>  <!-- EndPoint -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ScenarioDef;

public class ScenarioDefMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.ScenarioDef elem = (ucm.scenario.ScenarioDef) o;
		String objId = elem.getId();
		ScenarioDef elemZ = (ScenarioDef) getObject(objId, target, "createScenarioDef"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (ScenarioDef) super.handle(elem, elemZ, true);
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			elemZ.getGroups().add(process(elem.getGroup(), null, "createScenarioDefGroups", false)); //$NON-NLS-1$
			processList(elem.getInitializations(), elemZ.getInitializations(), true);
			processList(elem.getPostconditions(), elemZ.getPostconditions(), true);
			processList(elem.getPreconditions(), elemZ.getPreconditions(), true);
			processList(elem.getParentScenarios(), elemZ.getParentScenarios(), "createScenarioDefParentScenarios",false); //$NON-NLS-1$
			processList(elem.getIncludedScenarios(), elemZ.getIncludedScenarios(),false);
			processList(elem.getStartPoints(), elemZ.getStartPoints(), false);
			processList(elem.getEndPoints(), elemZ.getEndPoints(),"createScenarioDefEndPoints", false); //$NON-NLS-1$
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
