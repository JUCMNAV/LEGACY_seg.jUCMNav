package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GRLNode  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GRLNode">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="pred" type="xsd:IDREF"/> <!-- LinkRef -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="succ" type="xsd:IDREF"/> <!-- LinkRef -->
//          <xsd:element minOccurs="0" name="contRef" type="xsd:IDREF"/> <!-- ActorRef -->
//          <xsd:element name="pos" type="Position"/>
//          <xsd:element name="size" type="Size"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ActorRef;
import seg.jUCMNav.importexport.z151.generated.GRLNode;

public class GRLNodeUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GRLNode elemZ = (GRLNode) o;
		String objId = elemZ.getId();
		grl.GRLNode elem = (grl.GRLNode) getObject(objId, target, grl.GRLNode.class);
		if (isFullConstruction) {
			elem = (grl.GRLNode) super.handle(elemZ, elem, true);
			elem.setX(elemZ.getPos().getX().intValue());
			elem.setY(elemZ.getPos().getY().intValue());
//			elem.setDiagram(); //Handled in GRLGraphUMHandler
			elem.setContRef((grl.ActorRef) process((ActorRef) elemZ.getContRef(), null, false));
			
//			urncore.NodeLabel nodeLabel = (urncore.NodeLabel) ModelCreationFactory.getNewObject(urn, urncore.NodeLabel.class);
//			nodeLabel.setDeltaX(0);//set to default
//			nodeLabel.setDeltaY(0);//set to default
//			nodeLabel.setNode(elem);
//			elem.setLabel(nodeLabel);
			//elem.getContRef().setHeight(elemZ.getSize().getHeight().intValue());
			//elem.getContRef().setWidth(elemZ.getSize().getWidth().intValue());
			// elem.setId();
			 elem.setName(elemZ.getName());
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
			
//TODO			elemZ.getSize() 
		}
		return elem;
	}
}
