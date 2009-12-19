package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Comment  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Comment">
//    <xsd:sequence>
//      <xsd:element name="description" type="xsd:string"/>
//      <xsd:element name="x" type="xsd:integer"/>
//      <xsd:element name="y" type="xsd:integer"/>
//      <xsd:element name="width" type="xsd:integer"/>
//      <xsd:element name="height" type="xsd:integer"/>
//      <xsd:element name="fillColor" type="xsd:string"/>
//    </xsd:sequence>
//  </xsd:complexType>

//DONE

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.Comment;

public class CommentMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.Comment elem = (urncore.Comment) o;
		Comment elemZ = null;
		if (null == target)
			elemZ = of.createComment();
		else
			elemZ = (Comment) target;
		if (isFullConstruction) {
			elemZ.setFillColor(elem.getFillColor());
			elemZ.setX(new BigInteger(Integer.toString(elem.getX())));
			elemZ.setY(new BigInteger(Integer.toString(elem.getY())));
			elemZ.setHeight(new BigInteger(Integer.toString(elem.getHeight())));
			elemZ.setWidth(new BigInteger(Integer.toString(elem.getWidth())));
			elemZ.setDescription(elem.getDescription());
		}
		return elemZ;
	}
}
