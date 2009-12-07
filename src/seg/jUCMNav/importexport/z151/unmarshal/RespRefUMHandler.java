package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  RespRef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="RespRef">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode">
//        <xsd:sequence>
//          <xsd:element name="repetitionCount" type="xsd:string"/>
//          <xsd:element name="hostDemand" type="xsd:string"/>
//	  <xsd:element name="respDef" type="xsd:IDREF"/> <!-- Responsibility -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class RespRefUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		RespRef elemZ = (RespRef) o;
		String objId = elemZ.getId();
		ucm.map.RespRef elem = (ucm.map.RespRef) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.map.RespRef) ModelCreationFactory.getNewObject(urn, ucm.map.RespRef.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.map.RespRef) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.map.RespRef) super.handle(elemZ, elem, true);
			elem.setRepetitionCount(elemZ.getRepetitionCount());
			elem.setHostDemand(elemZ.getHostDemand());
			elem.setRespDef((urncore.Responsibility) process((Responsibility) elemZ.getRespDef(), null, false));
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getRepetitionCount();
			// elem.getHostDemand();
			// elem.getRespDef();
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
