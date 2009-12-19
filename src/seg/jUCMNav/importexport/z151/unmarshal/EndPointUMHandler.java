package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EndPoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EndPoint">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode">
//        <xsd:sequence>
//		<xsd:element maxOccurs="unbounded" minOccurs="0" name="outBindings" type="xsd:IDREF"/> <!-- OutBinding -->
//          <xsd:element minOccurs="0" name="postcondition" type="Condition"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.EndPoint;
import seg.jUCMNav.importexport.z151.generated.Metadata;

public class EndPointUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		EndPoint elemZ = (EndPoint) o;
		String objId = elemZ.getId();
		ucm.map.EndPoint elem = (ucm.map.EndPoint) getObject(objId, target, ucm.map.EndPoint.class);
		if (isFullConstruction) {
			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav EndPoint local")){ //$NON-NLS-1$
					elem.setLocal(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (ucm.map.EndPoint) super.handle(elemZ, elem, true);
			if (elem.getPostcondition() != null) {
				urncore.Condition condition = (urncore.Condition) process(elemZ.getPostcondition(), null, true);
				elem.setPostcondition(condition);
				condition.setEndPoint(elem);
			}
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getOutBindings(), elem.getOutBindings(), false);
			// elem.getPostcondition();
			// elem.getScenarioEndPoints() handled by ScenarioDefUMHandler
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
