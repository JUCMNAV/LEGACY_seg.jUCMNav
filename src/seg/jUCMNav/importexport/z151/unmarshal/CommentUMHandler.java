package seg.jUCMNav.importexport.z151.unmarshal;

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

import seg.jUCMNav.importexport.z151.generated.Comment;
import seg.jUCMNav.model.ModelCreationFactory;

public class CommentUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Comment elemZ = (Comment) o;
		urncore.Comment elem = null;
		if (null == elem) {
			if (null == target) {
				elem = (urncore.Comment) ModelCreationFactory.getNewObject(urn, urncore.Comment.class);
			} else
				elem = (urncore.Comment) target;
		}
		if (isFullConstruction) {
			elem.setX(elemZ.getX().intValue());
			elem.setY(elemZ.getY().intValue());
			elem.setHeight(elemZ.getHeight().intValue());
			elem.setFillColor(elemZ.getFillColor());
			// elem.setDiagram(); //Handled in GRLGraphUMHandler
			elem.setWidth(elemZ.getWidth().intValue());
			elem.setDescription(elemZ.getDescription());

			// elem.getFillColor();
			// elem.getDiagram();
			// elem.getX();
			// elem.getY();
			// elem.getHeight();
			// elem.getWidth();
			// elem.getDescription();
			// elem.getClass();
		}
		return elem;

	}
}
