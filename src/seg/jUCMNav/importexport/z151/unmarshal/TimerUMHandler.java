package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Timer  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Timer">
//    <xsd:complexContent>
//      <xsd:extension base="WaitingPlace">
//        <xsd:sequence>
//          <xsd:element minOccurs="0" name="timeoutPath" type="xsd:IDREF"/> <!-- NodeConnection -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class TimerUMHandler extends WaitingPlaceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Timer elemZ = (Timer) o;
		String objId = elemZ.getId();
		ucm.map.Timer elem = (ucm.map.Timer) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.map.Timer) ModelCreationFactory.getNewObject(urn, ucm.map.Timer.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.map.Timer) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.map.Timer) super.handle(elemZ, elem, true);
			elem.setTimeoutPath((ucm.map.NodeConnection) process(elemZ.getTimeoutPath(), null, false)); 
			// elem.setWaitType();
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getTimeoutPath();
			// elem.getWaitType();
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
