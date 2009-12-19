package seg.jUCMNav.importexport.z151.marshal;

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

import seg.jUCMNav.importexport.z151.generated.RespRef;
import seg.jUCMNav.importexport.z151.generated.Responsibility;

public class RespRefMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.RespRef elem = (ucm.map.RespRef) o;
		String objId = elem.getId();
		RespRef elemZ = (RespRef) getObject(objId, target, "createRespRef"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (RespRef) super.handle(elem, elemZ, true);
			elemZ.setRepetitionCount(elem.getRepetitionCount());
			elemZ.setHostDemand(elem.getHostDemand());
			elemZ.setRespDef((Responsibility) process(elem.getRespDef(), null, false));
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			//
			// elemZ.getRepetitionCount();
			// elemZ.getHostDemand();
			// elemZ.getRespDef();
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
