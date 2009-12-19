package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  UCMspec  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="UCMspec">
//    <xsd:sequence>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="enumerationTypes" type="EnumerationType"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="variables" type="Variable"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="scenarioGroups" type="ScenarioGroup"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="resources" type="GeneralResource"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="ucmMaps" type="UCMmap"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="components" type="Component"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="componentTypes" type="ComponentType"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="responsibilities" type="Responsibility"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="scenarioDefs" type="ScenarioDef"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.UCMmap;
import seg.jUCMNav.importexport.z151.generated.UCMspec;
import seg.jUCMNav.model.ModelCreationFactory;

public class UCMspecUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		UCMspec elemZ = (UCMspec) o;
		ucm.UCMspec elem = null;
		if (null == elem) {
			if (null == target)
				elem = (ucm.UCMspec) ModelCreationFactory.getNewObject(urn, ucm.UCMspec.class);
			else
				elem = (ucm.UCMspec) target;

		}
		if (isFullConstruction) {
			elem.setUrnspec(urn);
			// elem.getUrnspec();
			processList(elemZ.getScenarioGroups(), elem.getScenarioGroups(), true);
			processList(elemZ.getVariables(), elem.getVariables(), true);
			processList(elemZ.getEnumerationTypes(), elem.getEnumerationTypes(), true);
			processList(elemZ.getResources(), elem.getResources(), true);
			
			urncore.URNdefinition urnDef = urn.getUrndef();
			processList(elemZ.getResponsibilities(), urnDef.getResponsibilities(), true);
			processList(elemZ.getComponentTypes(), urnDef.getComponentTypes(), true);
			processList(elemZ.getComponents(), urnDef.getComponents(), true);
			//urnDefinitionUMHandler.handle(elemZ, urnDefinition, true);
			// elem.getClass();
//			elemZ.getUcmMaps()			
			for (UCMmap ucmMap :elemZ.getUcmMaps()){
				ucm.map.UCMmap tmp = (ucm.map.UCMmap) process(ucmMap, null, true);
				if (!urn.getUrndef().getSpecDiagrams().contains(tmp))
				urn.getUrndef().getSpecDiagrams().add(tmp);
			}

			//elemZ.getScenarioDefs() handled by ScenarioGroupUMHandler
			
			processList(elemZ.getVariables(), elem.getVariables(), true);
			setUcmspec(elem);
			
//			for (ScenarioGroup scenarioGroup : elemZ.getScenarioGroups()){
//				for (JAXBElement<Object> tmp : scenarioGroup.getScenarios()){
//					String objId = ((ScenarioDef) tmp.getValue()).getId();
//					ucm.scenario.ScenarioDef scenarioDef = (ucm.scenario.ScenarioDef) id2object.get(objId);
//					if (scenarioDef == null){
//						scenarioDef = (ucm.scenario.ScenarioDef) process(tmp.getValue(), null, true);
//						id2object.put(objId, scenarioDef);
//						scenarioGroup.getScenarios().add(scenarioDef);
//					}else{
//						scenarioGroup
//					}
//				}
//			}
		}
		return elem;
	}
	
	private void setUcmspec(ucm.UCMspec elem){
		for (Object item : elem.getEnumerationTypes()){
			((ucm.scenario.EnumerationType) item).setUcmspec(elem);
		}
		for (Object item : elem.getResources()){
			((ucm.performance.GeneralResource) item).setUcmspec(elem);
		}
		for (Object item: elem.getScenarioGroups()){
			((ucm.scenario.ScenarioGroup) item).setUcmspec(elem);
		}
		for (Object item: elem.getVariables()){
			((ucm.scenario.Variable) item).setUcmspec(elem);
		}
	}
}
