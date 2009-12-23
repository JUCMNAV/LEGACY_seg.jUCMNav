package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  UCMspec  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="UCMspec">
//    <xsd:sequence>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="enumerationTypes" type="EnumerationType"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="variables" type="Variable"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="scenarioGroups" type="ScenarioGroup"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="resources" type="GeneralResource"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="ucmMaps" type="UCMmap"/> jUCMNav does not have. 
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="components" type="Component"/> 
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="componentTypes" type="ComponentType"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="responsibilities" type="Responsibility"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="scenarioDefs" type="ScenarioDef"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ScenarioDef;
import seg.jUCMNav.importexport.z151.generated.UCMspec;

public class UCMspecMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.UCMspec elem = (ucm.UCMspec) o;
		UCMspec elemZ = null;
		if (null == target)
			elemZ = of.createUCMspec();
		else
			elemZ = (UCMspec) target;
		if (isFullConstruction) {
			//enumerationTypes
			processList(elem.getEnumerationTypes(), elemZ.getEnumerationTypes(), true);
			//variables
			processList(elem.getVariables(), elemZ.getVariables(), true);
			//scenarioGroups
			processList(elem.getScenarioGroups(), elemZ.getScenarioGroups(), true);
			// elemZ.getUcmMaps() handled by URNspecMHandler
			processList(elem.getUrnspec().getUrndef().getComponentTypes(), elemZ.getComponentTypes(), true); 
			// need to confirm																											// to
			processList(elem.getUrnspec().getUrndef().getResponsibilities(), elemZ.getResponsibilities(), true);
            // need to confirm                                                                                                          // to

			for (Object scenarioGroup : elem.getScenarioGroups()) {
				for (Object scenarioDef : ((ucm.scenario.ScenarioGroup) scenarioGroup).getScenarios()) {
					ScenarioDef scenarioDefZ = (ScenarioDef) process((ucm.scenario.ScenarioDef) scenarioDef, null, true);
					if (!elemZ.getScenarioDefs().contains(scenarioDefZ))
						elemZ.getScenarioDefs().add(scenarioDefZ);
				}
			}
			//resources
			processList(elem.getResources(), elemZ.getResources(), true); 
			
			//components
			processList(elem.getUrnspec().getUrndef().getComponents(), elemZ.getComponents(), true);
		}
		return elemZ;
	}
}