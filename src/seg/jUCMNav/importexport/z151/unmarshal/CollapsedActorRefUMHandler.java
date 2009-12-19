package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  CollapsedActorRef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="CollapsedActorRef">
//    <xsd:complexContent>
//      <xsd:extension base="GRLNode">
//        <xsd:sequence>
//		<xsd:element name="actor" type="xsd:IDREF"/> <!-- Actor -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.CollapsedActorRef;

public class CollapsedActorRefUMHandler extends GRLNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		CollapsedActorRef elemZ = (CollapsedActorRef) o;
		String objId = elemZ.getId();
		grl.CollapsedActorRef elem = (grl.CollapsedActorRef) getObject(objId, target, grl.CollapsedActorRef.class);
		if (isFullConstruction) {
			elem = (grl.CollapsedActorRef) super.handle(elemZ, elem, true);
			// elem.setActor(); //Handled in ActorUMHandler
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getActor();
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
