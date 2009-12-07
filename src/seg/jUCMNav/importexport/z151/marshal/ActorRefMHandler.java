package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ActorRef  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ActorRef">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element name="label" type="Label"/>
//	      <xsd:element name="actorDef" type="xsd:IDREF"/> <!-- Actor -->
//	      <xsd:element maxOccurs="unbounded" minOccurs="0" name="nodes" type="xsd:IDREF"/> <!-- GRLNode -->
//        <xsd:element name="pos" type="Position"/>
//        <xsd:element name="size" type="Size"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

/*** Done! ***/

public class ActorRefMHandler extends GRLmodelElementMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.ActorRef elem = (grl.ActorRef) obj;
		String objId = elem.getId();
		ActorRef elemZ = (ActorRef) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createActorRef();
			}else
				elemZ = (ActorRef) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (ActorRef) super.handle(elem, elemZ, true);

			// label
			Label lZ = of.createLabel();
			int deltaX = elem.getLabel().getDeltaX();
			int deltaY = elem.getLabel().getDeltaY();
			lZ.setDeltaX(new BigInteger(Integer.toString(deltaX)));
			lZ.setDeltaY(new BigInteger(Integer.toString(deltaY)));
			elemZ.setLabel(lZ);

			// actorDef
			elemZ.setActorDef((Actor) process(elem.getContDef(), null, false));

			// nodes
			processList(elem.getNodes(), elemZ.getNodes(), "createActorRefNodes", false);

			// pos
			Position posZ = of.createPosition();
			posZ.setX(new BigInteger(Integer.toString(elem.getX())));
			posZ.setY(new BigInteger(Integer.toString(elem.getY())));
			elemZ.setPos(posZ);

			// size
			Size sZ = of.createSize();
			sZ.setHeight(new BigInteger(Integer.toString(elem.getHeight())));
			sZ.setWidth((new BigInteger(Integer.toString(elem.getWidth()))));
			elemZ.setSize(sZ);
		}
		return elemZ;
	}

}
