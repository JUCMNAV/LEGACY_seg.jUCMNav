package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EmptyPoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EmptyPoint">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.List;

import seg.jUCMNav.importexport.z151.generated.EmptyPoint;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.PathNode;

public class EmptyPointUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		PathNode elemZ = (EmptyPoint) o;
		ucm.map.PathNode elem = null;
        String objId = elemZ.getId();
        boolean isPlainEmptyPoint=true;

		List<Metadata> metaDataList = elemZ.getMetadata();
		for(Metadata item: metaDataList){
			if (item.getName().equals("jUCMNav FailurePoint expression")){ //$NON-NLS-1$
				elem = (ucm.map.FailurePoint) getObject(objId, target, ucm.map.FailurePoint.class);
				((ucm.map.FailurePoint) elem).setExpression(item.getValue());
				if (isFullConstruction) {
					elem = (ucm.map.FailurePoint) super.handle(elemZ, elem, true);
	                //Cannot remove this metadata here... Would cause a type casting error later
				}
				isPlainEmptyPoint=false;
				return elem;
			}
			if (item.getName().equals("jUCMNav Anything")){ //$NON-NLS-1$
				elem = (ucm.map.Anything) getObject(objId, target, ucm.map.Anything.class);
				if (isFullConstruction) {
					elem = (ucm.map.Anything) super.handle(elemZ, elem, true);
                    //Cannot remove this metadata here... Would cause a type casting error later
				}
                isPlainEmptyPoint=false;
                return elem;
			}
		}

		if (isPlainEmptyPoint){
	        elem = (ucm.map.EmptyPoint) getObject(objId, target, ucm.map.EmptyPoint.class);
	        if (isFullConstruction) {
	            elem = (ucm.map.EmptyPoint) super.handle(elemZ, elem, true);
	        }		    
		}

		if (isFullConstruction) {
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
