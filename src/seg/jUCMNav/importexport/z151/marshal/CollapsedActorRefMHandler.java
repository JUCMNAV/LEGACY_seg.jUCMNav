package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.CollapsedActorRef;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  CollapsedActorRef  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="CollapsedActorRef">
//  <xsd:complexContent>
//    <xsd:extension base="GRLNode">
//      <xsd:sequence>
//		<xsd:element name="actor" type="xsd:IDREF"/> <!-- Actor -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>


public class CollapsedActorRefMHandler extends GRLNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.CollapsedActorRef elem = (grl.CollapsedActorRef) o;
		String objId = elem.getId();
		CollapsedActorRef elemZ = (CollapsedActorRef) getObject(objId, target, "createCollapsedActorRef"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ.setActor((grl.Actor) process(elem.getActor(), null, false));
		}
		return elemZ;
	}
}
