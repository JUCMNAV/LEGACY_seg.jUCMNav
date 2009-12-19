package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.LinkRefBendpoint;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  LinkRefBendpoint  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="LinkRefBendpoint">
//  <xsd:sequence>
//    <xsd:element name="x" type="xsd:integer"/>
//    <xsd:element name="y" type="xsd:integer"/>
//  </xsd:sequence>
//</xsd:complexType>


public class LinkRefBendpointMHandler extends MHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.LinkRefBendpoint elem = (grl.LinkRefBendpoint) obj;
		LinkRefBendpoint elemZ = of.createLinkRefBendpoint();
		elemZ.setX(new BigInteger(Integer.toString(elem.getX())));
		elemZ.setY(new BigInteger(Integer.toString(elem.getY())));
		return elemZ;
	}

}
