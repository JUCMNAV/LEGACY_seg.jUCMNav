package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Actor  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Actor">
//    <xsd:complexContent>
//      <xsd:extension base="GRLLinkableElement">
//        <xsd:sequence>
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="collapsedRefs" type="xsd:IDREF"/> <!-- CollapsedActorRef -->
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="actorRefs" type="xsd:IDREF"/> <!-- ActorRef -->
//          <xsd:element minOccurs="0" name="style" type="ConcreteStyle"/>
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="elems" type="xsd:IDREF"/> <!-- IntentionalElement -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class ActorUMHandler extends GRLLinkableElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Actor elemZ = (Actor) o;
		String objId = elemZ.getId();
		grl.Actor elem = (grl.Actor) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (grl.Actor) ModelCreationFactory.getNewObject(urn,
						grl.Actor.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (grl.Actor) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			
			//Handling jUCMNav includedActors and includingActor
			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav Actor includedActors")){
					grl.Actor actor = (grl.Actor) getObjectFromId(item.getValue(),grl.Actor.class);
					if (!elem.getIncludedActors().contains(actor)){
						elem.getIncludedActors().add(actor);
					}
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav Actor includingActor")){
					grl.Actor actor = (grl.Actor) getObjectFromId(item.getValue(),grl.Actor.class);
					elem.setIncludingActor(actor);
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (grl.Actor) super.handle(elemZ, elem, true);
			//style
			elem.setLineColor(elemZ.getStyle().getLineColor());
			elem.setFillColor(elemZ.getStyle().getFillColor());
			elem.setFilled(elemZ.getStyle().isFilled());
			//elem.setGrlspec(); //Handled in GRLspecUMHandler
			// elem.setId();
			// elem.setName();
			// elem.setDescription();
			// elem.getFillColor();
			
			//actorRefs
			processList(elemZ.getActorRefs(), elem.getContRefs(), false);
			// elem.getGrlspec();
			// elem.getIncludingActor();
			
			//collapsedRefs
			processList(elemZ.getCollapsedRefs(), elem.getCollapsedRefs(),false);
			
			//Handling CollapsedActorRefUMHandler elem.setActor()
			for (Object tmp: elem.getCollapsedRefs()){
				((grl.CollapsedActorRef) tmp).setActor(elem);
			}
			// elem.getLineColor();
			// elem.getLinksDest();
			// elem.getLinksSrc();
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
