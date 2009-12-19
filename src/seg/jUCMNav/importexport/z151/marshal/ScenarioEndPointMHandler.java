package seg.jUCMNav.importexport.z151.marshal;

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

//DONE

import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.EndPoint;

public class ScenarioEndPointMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.ScenarioEndPoint elem = (ucm.scenario.ScenarioEndPoint) o;
		ucm.map.EndPoint endPoint = elem.getEndPoint();
		String objId = endPoint.getId();
		EndPoint elemZ = (EndPoint) getObject(objId, target, "createEndPoint"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (EndPoint) super.handle(endPoint, elemZ, true);
			// elemZ.setPostcondition();
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(endPoint.getOutBindings(), elemZ.getOutBindings(), "createEndPointOutBindings", false); //$NON-NLS-1$
			elemZ.setPostcondition((Condition) process(endPoint.getPostcondition(), null, true));
			// elemZ.getPred();
			// elemZ.getSucc();
			// elemZ.getContRef();
			// elemZ.getLabel();
			// elemZ.getPos();
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
