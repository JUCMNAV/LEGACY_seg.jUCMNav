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
			elem = (grl.Actor) super.handle(elemZ, elem, true);
			//style
			elem.setLineColor(elemZ.getStyle().getLineColor());
			elem.setFillColor(elemZ.getStyle().getFillColor());
			elem.setFilled(elemZ.getStyle().isFilled());
			//elem.setGrlspec(); //Handled in GRLspecUMHandler
//TODO			elem.setIncludingActor();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getFillColor();
			
			//actorRefs
			processList(elemZ.getActorRefs(), elem.getContRefs(), false); //* 100 certain

			// elem.getGrlspec();
//TODO			processList(elem.getIncludedActors());
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
