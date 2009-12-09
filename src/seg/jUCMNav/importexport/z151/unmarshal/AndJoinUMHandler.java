package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  AndJoin  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="AndJoin">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class AndJoinUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		AndJoin elemZ = (AndJoin) o;
		String objId = elemZ.getId();
		ucm.map.AndJoin elem = (ucm.map.AndJoin) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.map.AndJoin) ModelCreationFactory.getNewObject(urn,
						ucm.map.AndJoin.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.map.AndJoin) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {			
			elem = (ucm.map.AndJoin) super.handle(elemZ, elem, true);
			elem.setOrientation(null); //ORIENTATION_EDEFAULT
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getOrientation();
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