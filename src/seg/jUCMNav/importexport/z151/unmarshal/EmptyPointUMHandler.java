package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EmptyPoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EmptyPoint">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class EmptyPointUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		PathNode elemZ = (EmptyPoint) o;
		List<Metadata> metaDataList = elemZ.getMetadata();
		for(Metadata item: metaDataList){
			if (item.getName().equals("jUCMNav FailurePoint expression")){
				String objId = elemZ.getId();
				ucm.map.FailurePoint elem = (ucm.map.FailurePoint) id2object.get(objId);
				if (null == elem) {
				if (null == target){
						elem = (ucm.map.FailurePoint) ModelCreationFactory.getNewObject(
								urn, ucm.map.FailurePoint.class);
							elem.setId(objId);
						if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
					}
					else
						elem = (ucm.map.FailurePoint) target;
					id2object.put(objId, elem);
				}
				elem.setExpression(item.getValue());
				metaDataList.remove(item);
				if (isFullConstruction) {
					elem = (ucm.map.FailurePoint) super.handle(elemZ, elem, true);
				}
				return elem;
			}
			if (item.getName().equals("jUCMNav Anything")){
				String objId = elemZ.getId();
				ucm.map.Anything elem = (ucm.map.Anything) id2object.get(objId);
				if (null == elem) {
				if (null == target){
						elem = (ucm.map.Anything) ModelCreationFactory.getNewObject(
								urn, ucm.map.Anything.class);
							elem.setId(objId);
						if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
					}
					else
						elem = (ucm.map.Anything) target;
					id2object.put(objId, elem);
				}
				metaDataList.remove(item);
				if (isFullConstruction) {
					elem = (ucm.map.Anything) super.handle(elemZ, elem, true);
				}
				return elem;
			}
		}

		String objId = elemZ.getId();
		ucm.map.EmptyPoint elem = (ucm.map.EmptyPoint) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.map.EmptyPoint) ModelCreationFactory.getNewObject(
						urn, ucm.map.EmptyPoint.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.map.EmptyPoint) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.map.EmptyPoint) super.handle(elemZ, elem, true);
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

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
