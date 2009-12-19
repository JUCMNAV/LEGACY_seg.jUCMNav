package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EmptyPoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EmptyPoint">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode"/>
//    </xsd:complexContent>
//  </xsd:complexType>

//DONE

import seg.jUCMNav.importexport.z151.generated.EmptyPoint;
import seg.jUCMNav.importexport.z151.generated.Metadata;

public class AnythingMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.Anything elem = (ucm.map.Anything) o;
		String objId = elem.getId();
		EmptyPoint elemZ = (EmptyPoint) getObject(objId, target, "createEmptyPoint"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (EmptyPoint) super.handle(elem, elemZ, true);
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
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav Anything"); //$NON-NLS-1$
			mdZ.setValue(""); //$NON-NLS-1$
			elemZ.getMetadata().add(mdZ);
		}
		return elemZ;
	}
}
