package seg.jUCMNav.importexport.z151.unmarshal;

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

import seg.jUCMNav.importexport.z151.generated.ComponentRef;
import seg.jUCMNav.importexport.z151.generated.PathNode;
import seg.jUCMNav.model.ModelCreationFactory;

public class PathNodeUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		PathNode elemZ = (PathNode) o;
		String objId = elemZ.getId();
		ucm.map.PathNode elem = (ucm.map.PathNode) getObject(elemZ.getId(), target, ucm.map.PathNode.class);
		if (isFullConstruction) {
			elem = (ucm.map.PathNode) super.handle(elemZ, elem, true);
			if (elemZ.getPos() != null) {
				elem.setX(elemZ.getPos().getX().intValue());
				elem.setY(elemZ.getPos().getY().intValue());
			}
			// elem.setDiagram(); handled by UCMmapUMHandler
			elem.setContRef((ucm.map.ComponentRef) process((ComponentRef) elemZ.getContRef(), null, false));
			if (elemZ.getLabel() != null) {
				urncore.NodeLabel nodeLabel = (urncore.NodeLabel) ModelCreationFactory.getNewObject(urn, urncore.NodeLabel.class);
				nodeLabel.setDeltaX(elemZ.getLabel().getDeltaX().intValue());
				nodeLabel.setDeltaY(elemZ.getLabel().getDeltaY().intValue());
				nodeLabel.setNode(elem);
				elem.setLabel(nodeLabel);
			}
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getDiagram();
			// elem.getContRef();
			processList(elemZ.getSucc(), elem.getSucc(), false);
			processList(elemZ.getPred(), elem.getPred(), false);
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
