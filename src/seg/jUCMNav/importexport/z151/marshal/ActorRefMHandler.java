package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.Actor;
import seg.jUCMNav.importexport.z151.generated.ActorRef;
import seg.jUCMNav.importexport.z151.generated.Label;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.Position;
import seg.jUCMNav.importexport.z151.generated.Size;

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
		ActorRef elemZ = (ActorRef) getObject(objId, target, "createActorRef"); //$NON-NLS-1$
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
			processList(elem.getNodes(), elemZ.getNodes(), "createActorRefNodes", false); //$NON-NLS-1$

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

			EList list = elem.getChildren();
			if (list != null && list.size() > 0) {
				for (Object item : list) {
					((grl.ActorRef) item).getId();
					Metadata mdZ = of.createMetadata();
					mdZ.setName("jUCMNav ActorRef children"); //$NON-NLS-1$
					mdZ.setValue(((grl.ActorRef) item).getId());
					elemZ.getMetadata().add(mdZ);
				}
			}
			grl.ActorRef item = (grl.ActorRef) elem.getParent();
			if (item != null) {
				Metadata mdZ = of.createMetadata();
				mdZ.setName("jUCMNav ActorRef parent"); //$NON-NLS-1$
				mdZ.setValue(item.getId());
				elemZ.getMetadata().add(mdZ);
			}
		}
		return elemZ;
	}

}
