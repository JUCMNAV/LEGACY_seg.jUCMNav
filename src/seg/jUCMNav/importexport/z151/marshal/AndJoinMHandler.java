package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  AndJoin  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="AndJoin">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode"/>
//    </xsd:complexContent>
//  </xsd:complexType>
//DONE
import seg.jUCMNav.importexport.z151.generated.AndJoin;

public class AndJoinMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.AndJoin elem = (ucm.map.AndJoin) o;
		String objId = elem.getId();
		AndJoin elemZ = (AndJoin) getObject(objId, target, "createAndJoin"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (AndJoin) super.handle(elem, elemZ, true);
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			//
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
