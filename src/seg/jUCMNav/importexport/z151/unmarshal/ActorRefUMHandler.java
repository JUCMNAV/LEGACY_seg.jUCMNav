package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ActorRef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ActorRef">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element name="label" type="Label"/>
//	  <xsd:element name="actorDef" type="xsd:IDREF"/> <!-- Actor -->
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="nodes" type="xsd:IDREF"/> <!-- GRLNode -->
//          <xsd:element name="pos" type="Position"/>
//          <xsd:element name="size" type="Size"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class ActorRefUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ActorRef elemZ = (ActorRef) o;
		String objId = elemZ.getId();
		grl.ActorRef elem = (grl.ActorRef) id2object.get(objId);
		if (null == elem) {
			if (null == target) {
				elem = (grl.ActorRef) ModelCreationFactory.getNewObject(urn, grl.ActorRef.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId) < Integer.valueOf(objId))
					globelId = objId;
			} else
				elem = (grl.ActorRef) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (grl.ActorRef) super.handle(elemZ, elem, true);
			elem.setX(elemZ.getPos().getX().intValue());
			elem.setY(elemZ.getPos().getY().intValue());
			elem.setHeight(elemZ.getSize().getHeight().intValue());
			elem.setFixed(false);
			// elem.setDiagram(); //Handled in GRLGraphUMHandler
			elem.setContDef((grl.Actor) process(elemZ.getActorDef(), null, false)); // not
																					// certain
			// TODO elem.setParent();
			elem.setWidth(elemZ.getSize().getWidth().intValue());
			urncore.ComponentLabel componentLabel = (urncore.ComponentLabel) ModelCreationFactory.getNewObject(urn, urncore.ComponentLabel.class);
			componentLabel.setDeltaX(elemZ.getLabel().getDeltaX().intValue());
			componentLabel.setDeltaY(elemZ.getLabel().getDeltaY().intValue());
			componentLabel.setContRef(elem);
			elem.setLabel(componentLabel);
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getDiagram();
			// elem.getContDef();
			processList(elemZ.getNodes(), elem.getNodes(), false);
			// elem.getParent();
			// elem.getX();
			// elem.getY();
			// elem.getHeight();
			// elem.getWidth();
			// elem.getLabel();
			// TODO processList(elem.getChildren());
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
