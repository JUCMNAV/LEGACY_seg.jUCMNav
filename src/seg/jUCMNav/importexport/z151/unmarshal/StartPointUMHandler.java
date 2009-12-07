package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  StartPoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="StartPoint">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode">
//        <xsd:sequence>
//          <xsd:element minOccurs="0" name="workload" type="Workload"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="inBindings" type="xsd:IDREF"/> <!-- InBinding -->
//          <xsd:element minOccurs="0" name="precondition" type="Condition"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class StartPointUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		StartPoint elemZ = (StartPoint) o;
		String objId = elemZ.getId();
		ucm.map.StartPoint elem = (ucm.map.StartPoint) id2object.get(objId);
		if (null == elem) {
			if (null == target) {
				elem = (ucm.map.StartPoint) ModelCreationFactory.getNewObject(urn, ucm.map.StartPoint.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId) < Integer.valueOf(objId))
					globelId = objId;
			} else
				elem = (ucm.map.StartPoint) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.map.StartPoint) super.handle(elemZ, elem, true);
			if (elemZ.getWorkload() != null) {
				ucm.performance.Workload workload = (ucm.performance.Workload) process(elemZ.getWorkload(), null, true);
				workload.setStartPoint(elem);
				elem.setWorkload(workload);
			}

			if (elem.getPrecondition() != null) {
				urncore.Condition condition = (urncore.Condition) process(elemZ.getPrecondition(), null, true);
				elem.setPrecondition(condition);
				condition.setStartPoint(elem);
			}
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getWorkload();
			processList(elemZ.getInBindings(), elem.getInBindings(), false);
			// elem.getPrecondition();
			// elem.getScenarioStartPoints() handled by ScenarioDefUMHandler
			// elem.getDiagram();
			// elem.getContRef();
			// elem.getSucc();
			// elem.getPred();
			// elem.getX();
			// elem.getY();
			// elem.getLabel();
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
