package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  PathNode  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="PathNode">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element minOccurs="0" name="label" type="Label"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="pred" type="xsd:IDREF"/> <!-- NodeConnection -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="succ" type="xsd:IDREF"/> <!-- NodeConnection -->
//          <xsd:element minOccurs="0" name="contRef" type="xsd:IDREF"/> <!-- ComponentRef -->
//          <xsd:element minOccurs="0" name="pos" type="Position"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

//DONE

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.Label;
import seg.jUCMNav.importexport.z151.generated.PathNode;
import seg.jUCMNav.importexport.z151.generated.Position;

public class PathNodeMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.PathNode elem = (ucm.map.PathNode) o;
		String objId = elem.getId();
		PathNode elemZ = (PathNode) getObject(objId, target, "createPathNode"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (PathNode) super.handle(elem, elemZ, true);
			//label
			if (elem.getLabel() != null) {
				Label labelZ = of.createLabel();
				labelZ.setDeltaX(new BigInteger(Integer.toString(elem.getLabel().getDeltaX())));
				labelZ.setDeltaY(new BigInteger(Integer.toString(elem.getLabel().getDeltaY())));
				elemZ.setLabel(labelZ);
			}
			//pos
			Position positionZ = of.createPosition();
			positionZ.setX(new BigInteger(Integer.toString(elem.getX())));
			positionZ.setY(new BigInteger(Integer.toString(elem.getY())));
			elemZ.setPos(positionZ);

			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			
			//pred
			processList(elem.getPred(), elemZ.getPred(), "createPathNodePred", false); //$NON-NLS-1$
			//succ
			processList(elem.getSucc(), elemZ.getSucc(), "createPathNodeSucc", false); //$NON-NLS-1$
			//contRef
			if (elem.getContRef() != null)
				elemZ.setContRef(process(elem.getContRef(), null, false));
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
		}
		return elemZ;
	}
}
