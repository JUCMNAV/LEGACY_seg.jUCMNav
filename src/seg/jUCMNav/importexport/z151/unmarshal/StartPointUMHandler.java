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

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.StartPoint;

public class StartPointUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		StartPoint elemZ = (StartPoint) o;
		String objId = elemZ.getId();
		ucm.map.StartPoint elem = (ucm.map.StartPoint) getObject(elemZ.getId(), target, ucm.map.StartPoint.class);
		if (isFullConstruction) {

			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav StartPoint local")){ //$NON-NLS-1$
					elem.setLocal(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav StartPoint failureKind")){ //$NON-NLS-1$
					elem.setFailureKind(ucm.map.FailureKind.get(item.getValue()));
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
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
