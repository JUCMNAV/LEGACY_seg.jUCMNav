package seg.jUCMNav.importexport.z151.unmarshal;

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

import javax.xml.bind.JAXBElement;

import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.EndPoint;
import seg.jUCMNav.importexport.z151.generated.ScenarioDef;
import seg.jUCMNav.importexport.z151.generated.StartPoint;
import seg.jUCMNav.model.ModelCreationFactory;

public class ScenarioDefUMHandler extends UCMmodelElementUMHandler {
    public Object handle(Object o, Object target, boolean isFullConstruction) {
        ScenarioDef elemZ = (ScenarioDef) o;
        String objId = elemZ.getId();
        ucm.scenario.ScenarioDef elem = (ucm.scenario.ScenarioDef) getObject(elemZ.getId(), target, ucm.scenario.ScenarioDef.class);
        if (isFullConstruction) {
            elem = (ucm.scenario.ScenarioDef) super.handle(elemZ, elem, true);
            // elem.setGroup(); handled by ScenarioGroupUMHandler
            // elem.setId();
            // elem.setName();
            // elem.setDescription();

            // elem.getStartPoints();
            for (Object item : elemZ.getStartPoints()) {
                ucm.scenario.ScenarioStartPoint scenarioStartPoint = (ucm.scenario.ScenarioStartPoint) ModelCreationFactory.getNewObject(urn, ucm.scenario.ScenarioStartPoint.class);
                ucm.map.StartPoint startPoint = (ucm.map.StartPoint) process((StartPoint) item, null, false);
                scenarioStartPoint.setStartPoint(startPoint);
                if (!startPoint.getScenarioStartPoints().contains(scenarioStartPoint))
                    startPoint.getScenarioStartPoints().add(scenarioStartPoint);
                scenarioStartPoint.setScenarioDef(elem);
                scenarioStartPoint.setEnabled(true); // set to default
                elem.getStartPoints().add(scenarioStartPoint);
            }
            processList(elemZ.getParentScenarios(), elem.getParentScenarios(), false);
            processList(elemZ.getIncludedScenarios(), elem.getIncludedScenarios(), false);
            // elem.getEndPoints();

            for (JAXBElement<Object> item : elemZ.getEndPoints()) {
                ucm.scenario.ScenarioEndPoint scenarioEndPoint = (ucm.scenario.ScenarioEndPoint) ModelCreationFactory.getNewObject(urn, ucm.scenario.ScenarioEndPoint.class);
                ucm.map.EndPoint EndPoint = (ucm.map.EndPoint) process((EndPoint) item.getValue(), null, false);
                scenarioEndPoint.setEndPoint(EndPoint);
                if (!EndPoint.getScenarioEndPoints().contains(scenarioEndPoint))
                    EndPoint.getScenarioEndPoints().add(scenarioEndPoint);
                //scenarioEndPoint.setEndPoint((ucm.map.EndPoint) process((EndPoint) item.getValue(), null, false));
                scenarioEndPoint.setScenarioDef(elem);
                scenarioEndPoint.setMandatory(true); // set to default
                scenarioEndPoint.setEnabled(true); // set to default
                //elem.getEndPoints().add(scenarioEndPoint);
            }
            // scenarioEndPoint.getScenarioDef();
            // scenarioEndPoint.getEndPoint();
            // scenarioEndPoint.getClass();

            // elemZ.getPreconditions();
            for (Object conditionZ : elemZ.getPreconditions()) {
                if (null != conditionZ) {
                    // Label and Description will be lost.
                    urncore.Condition condition = (urncore.Condition) ModelCreationFactory.getNewObject(urn, urncore.Condition.class);
                    condition.setExpression(((Condition)conditionZ).getExpression());
                    condition.setDescription(""); //$NON-NLS-1$
                    condition.setScenarioDefPre(elem);
                }
            }

            // elem.getPostconditions();
            for (Object conditionZ : elemZ.getPostconditions()) {
                if (null != conditionZ) {
                    // Label and Description will be lost.
                    urncore.Condition condition = (urncore.Condition) ModelCreationFactory.getNewObject(urn, urncore.Condition.class);
                    condition.setExpression(((Condition)conditionZ).getExpression());
                    condition.setDescription(""); //$NON-NLS-1$
                    condition.setScenarioDefPost(elem);
                }
            }
            processList(elemZ.getInitializations(), elem.getInitializations(), true);
            for (Object item : elem.getInitializations()) {
                ((ucm.scenario.Initialization) item).setScenarioDef(elem);
            }
            // elem.getGroup();
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
