package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  LinkRefBendpoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="LinkRefBendpoint">
//    <xsd:sequence>
//      <xsd:element name="x" type="xsd:integer"/>
//      <xsd:element name="y" type="xsd:integer"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.LinkRefBendpoint;
import seg.jUCMNav.model.ModelCreationFactory;

public class LinkRefBendpointUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		LinkRefBendpoint elemZ = (LinkRefBendpoint) o;
		grl.LinkRefBendpoint elem = null;
		if (null == elem) {
			if (null == target)
				elem = (grl.LinkRefBendpoint) ModelCreationFactory
						.getNewObject(urn, grl.LinkRefBendpoint.class);
			else
				elem = (grl.LinkRefBendpoint) target;
		}
		if (isFullConstruction) {
			elem.setX(elemZ.getX().intValue());
			elem.setY(elemZ.getY().intValue());
			//elem.setLinkref(); handled by LinkRefUMHandler

			// elem.getLinkref();
			// elem.getX();
			// elem.getY();
			// elem.getClass();
		}
		return elem;
	}
}
