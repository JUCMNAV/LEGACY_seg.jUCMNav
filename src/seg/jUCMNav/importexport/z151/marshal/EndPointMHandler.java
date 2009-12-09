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

import seg.jUCMNav.importexport.z151.generated.*;

public class EndPointMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.EndPoint elem = (ucm.map.EndPoint) o;
		String objId = elem.getId();
		EndPoint elemZ = (EndPoint) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createEndPoint();
			}else
				elemZ = (EndPoint) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (EndPoint) super.handle(elem, elemZ, true);
			// elemZ.setPostcondition();
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getOutBindings(), elemZ.getOutBindings(), "createEndPointOutBindings", false);
			elemZ.setPostcondition((Condition) process(elem.getPostcondition(), null, true));
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
			
			boolean local = elem.isLocal();
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav EndPoint local");
			mdZ.setValue(Boolean.toString(local));
			elemZ.getMetadata().add(mdZ);
		}
		return elemZ;
	}
}
