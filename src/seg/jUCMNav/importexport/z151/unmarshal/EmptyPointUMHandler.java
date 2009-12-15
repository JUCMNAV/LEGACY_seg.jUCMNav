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
				ucm.map.FailurePoint elem = (ucm.map.FailurePoint) getObject(objId, target, ucm.map.FailurePoint.class);
				elem.setExpression(item.getValue());
				metaDataList.remove(item);
				if (isFullConstruction) {
					elem = (ucm.map.FailurePoint) super.handle(elemZ, elem, true);
				}
				return elem;
			}
			if (item.getName().equals("jUCMNav Anything")){
				String objId = elemZ.getId();
				ucm.map.Anything elem = (ucm.map.Anything) getObject(objId, target, ucm.map.Anything.class);
				metaDataList.remove(item);
				if (isFullConstruction) {
					elem = (ucm.map.Anything) super.handle(elemZ, elem, true);
				}
				return elem;
			}
		}

		String objId = elemZ.getId();
		ucm.map.EmptyPoint elem = (ucm.map.EmptyPoint) getObject(objId, target, ucm.map.EmptyPoint.class);
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
