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

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.ActorRef;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.model.ModelCreationFactory;

public class ActorRefUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ActorRef elemZ = (ActorRef) o;
		String objId = elemZ.getId();
		grl.ActorRef elem = (grl.ActorRef) getObject(objId, target, grl.ActorRef.class);
		if (isFullConstruction) {
			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav ActorRef children")){ //$NON-NLS-1$
					grl.ActorRef actorRef = (grl.ActorRef) getObjectFromId(item.getValue(),grl.ActorRef.class);
					if (!elem.getChildren().contains(actorRef)){
						elem.getChildren().add(actorRef);
					}
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav ActorRef parent")){ //$NON-NLS-1$
					grl.ActorRef actorRef = (grl.ActorRef) getObjectFromId(item.getValue(),grl.ActorRef.class);
					elem.setParent(actorRef);
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (grl.ActorRef) super.handle(elemZ, elem, true);
			elem.setX(elemZ.getPos().getX().intValue());
			elem.setY(elemZ.getPos().getY().intValue());
			elem.setHeight(elemZ.getSize().getHeight().intValue());
			elem.setFixed(false);
			// elem.setDiagram(); //Handled in GRLGraphUMHandler
			elem.setContDef((grl.Actor) process(elemZ.getActorDef(), null, false));
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
