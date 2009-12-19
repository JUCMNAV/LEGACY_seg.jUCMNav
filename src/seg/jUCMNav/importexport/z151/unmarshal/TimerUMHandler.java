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

import seg.jUCMNav.importexport.z151.generated.Timer;

public class TimerUMHandler extends WaitingPlaceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Timer elemZ = (Timer) o;
		ucm.map.Timer elem = (ucm.map.Timer) getObject(elemZ.getId(), target, ucm.map.Timer.class);
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
