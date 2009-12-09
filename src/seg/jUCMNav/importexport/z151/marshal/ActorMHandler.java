package seg.jUCMNav.importexport.z151.marshal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import urncore.IURNContainer;
import urncore.URNmodelElement;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Actor  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Actor">
//  <xsd:complexContent>
//    <xsd:extension base="GRLLinkableElement">
//      <xsd:sequence>
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="collapsedRefs" type="xsd:IDREF"/> <!-- CollapsedActorRef -->
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="actorRefs" type="xsd:IDREF"/> <!-- ActorRef --> ***Not found in jUCMNav****
//        <xsd:element minOccurs="0" name="style" type="ConcreteStyle"/>
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="elems" type="xsd:IDREF"/> <!-- IntentionalElement --> ***Not found in jUCMNav****
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

/***
 * Done*** actorRefs AND elems MISSING IN jUCMNav actorRefs: jUCMNav does not
 * have actorRefs, but Z151 has actorRefs -- contRefs in jUCMNav = ActorRef in
 * Z151
 */

public class ActorMHandler extends GRLLinkableElementMHandler {

	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.Actor elem = (grl.Actor) obj;
		String objId = elem.getId();
		Actor elemZ = (Actor) id2object.get(objId);
		if (null == elemZ) {
			if (null == target) {
				elemZ = of.createActor();
			} else
				elemZ = (Actor) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (Actor) super.handle(elem, elemZ, true);
			processList(elem.getCollapsedRefs(), elemZ.getCollapsedRefs(), false);

			// contRefs in jUCMNav = ActorRef in Z151
			processList(elem.getContRefs(), elemZ.getActorRefs(), "createActorActorRefs", false);

			// elems MISSING IN jUCMNav
			// elems
			EList actorRefs = elem.getContRefs();
			for (Object actorRef : elem.getContRefs()) {
				for (Object grlNode : ((grl.ActorRef) actorRef).getNodes()) {
					if (grlNode instanceof grl.IntentionalElementRef) {
						IntentionalElement intElem = (IntentionalElement) process(((grl.IntentionalElementRef) grlNode).getDef(), null, false);
						intElem.setActor(elemZ); // handling
						// IntentionalElementMHandler
						// elemZ.setActor
						JAXBElement<Object> javbIntElem = of.createActorElems(intElem);
						if (!elemZ.getElems().contains(javbIntElem))
							elemZ.getElems().add(javbIntElem);
					}
				}
			}
			// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
			// <!-- ConcreteStyle -->
			// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
			// <xsd:complexType name="ConcreteStyle">
			// <xsd:sequence>
			// <xsd:element name="lineColor" type="xsd:string"/>
			// <xsd:element name="fillColor" type="xsd:string"/>
			// <xsd:element name="filled" type="xsd:boolean"/>
			// </xsd:sequence>
			// </xsd:complexType>

			ConcreteStyle csZ = of.createConcreteStyle();
			if (null == elem.getFillColor())
				csZ.setFillColor("255,255,255"); // set to default "white", when
			// null
			else
				csZ.setFillColor(elem.getFillColor());
			if (null == elem.getLineColor())
				csZ.setLineColor("0,0,0"); // set to default "black" when null
			else
				csZ.setLineColor(elem.getLineColor());
			csZ.setFilled(elem.isFilled());
			elemZ.setStyle(csZ);

			EList list = elem.getIncludedActors();
			if (list != null && list.size() > 0) {
				for (Object item : list) {
					((grl.Actor) item).getId();
					Metadata mdZ = of.createMetadata();
					mdZ.setName("jUCMNav Actor includedActors");
					mdZ.setValue(((grl.Actor) item).getId());
					elemZ.getMetadata().add(mdZ);
				}
			}
			grl.Actor item = elem.getIncludingActor();
			if (item != null) {
				Metadata mdZ = of.createMetadata();
				mdZ.setName("jUCMNav Actor includingActor");
				mdZ.setValue(item.getId());
				elemZ.getMetadata().add(mdZ);
			}

		}
		return elemZ;
	}
}
