package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.*;

//  <xsd:complexType name="IntentionalElement">
//    <xsd:complexContent>
//      <xsd:extension base="GRLLinkableElement">
//        <xsd:sequence>
//          <xsd:element name="type" type="IntentionalElementType"/>
//          <xsd:element default="AND" name="decompositionType" type="DecompositionType"/>
//          <xsd:element default="None" name="importance" type="ImportanceType"/>
//          <xsd:element name="importanceQuantitative" type="xsd:integer"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="refs" type="xsd:IDREF"/>  <!-- IntentionalElementRef -->
//          <xsd:element minOccurs="0" name="style" type="ConcreteStyle"/>
//	  		<xsd:element minOccurs="0" name="actor" type="xsd:IDREF"/> <!-- Actor -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

/***
 * Done! But TODO: DecompositionType: jUCMNav has OR, but Z151 is IOR
 * IntentionalElementType: jNCMNav has Indicator, but Z151 has Belief Actor:
 * jUCMNav doesn't have the relation between IntentionalElement, but Z151 has .
 * 
 */
public class BeliefMHandler extends GRLLinkableElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.Belief elem = (grl.Belief) o;
		String objId = "Z151_id_"+elem.getClass().getName()+"_"+elem.getId();
		IntentionalElement intentionalElement = (IntentionalElement) id2object.get(objId);
		IntentionalElementRef ieRef = (IntentionalElementRef) id2object.get(elem.getId());
		if (intentionalElement == null) {
			if (null == target) {
				intentionalElement = of.createIntentionalElement();
			} else
				intentionalElement = (IntentionalElement) target;
			this.id2object.put(objId, intentionalElement);
		}
		if (ieRef == null) {
			ieRef = of.createIntentionalElementRef();
			this.id2object.put(elem.getId(), ieRef);
		}
		ieRef.setDef(intentionalElement);
		
		if (isFullConstruction) {
			intentionalElement.setId(objId);
			intentionalElement.setName(elem.getName());
			processList(elem.getMetadata(), intentionalElement.getMetadata(), true);
			processList(elem.getToLinks(), intentionalElement.getToLinks(), "createURNmodelElementFromLinks", false);
			processList(elem.getFromLinks(), intentionalElement.getFromLinks(), "createURNmodelElementToLinks", false);
			if (null != elem.getDescription()) {
				Description desc = of.createDescription();
				desc.setDescription(elem.getDescription());
				intentionalElement.setDesc(desc);
			}
			//elemZ.getConcern() handled by GRLGraphMHandler
			intentionalElement.setType(seg.jUCMNav.importexport.z151.generated.IntentionalElementType.BELIEF);
			intentionalElement.setDecompositionType(seg.jUCMNav.importexport.z151.generated.DecompositionType.AND);
			intentionalElement.setImportance(seg.jUCMNav.importexport.z151.generated.ImportanceType.NONE);
			intentionalElement.setImportanceQuantitative(new BigInteger("0"));

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
			csZ.setFillColor("255,255,255");
			csZ.setLineColor("0,0,0");
			csZ.setFilled(false);
			intentionalElement.setStyle(csZ);
			// elemZ.setActor() //handled by ActorMHandler. jUCMNav doesn't have
			// this relation.
			
			ieRef.setId(elem.getId());
			ieRef.setName(elem.getName()); 
			processList(elem.getMetadata(), ieRef.getMetadata(), true);
			processList(elem.getToLinks(), ieRef.getToLinks(), "createURNmodelElementFromLinks", false);
			processList(elem.getFromLinks(), ieRef.getFromLinks(), "createURNmodelElementToLinks", false);
			if (null != elem.getDescription()) {
				Description desc = of.createDescription();
				desc.setDescription(elem.getDescription());
				ieRef.setDesc(desc);
			}
			//elemZ.getConcern() handled by GRLGraphMHandler
			
			
			processList(elem.getPred(), ieRef.getPred(), "createGRLNodePred", false);
			processList(elem.getSucc(), ieRef.getSucc(), "createGRLNodeSucc", false);
			ieRef.setContRef((ActorRef) process((grl.ActorRef) elem.getContRef(), null, false));
			Position posZ = new Position();
			posZ.setX(new BigInteger(Integer.toString(elem.getX())));
			posZ.setY(new BigInteger(Integer.toString(elem.getY())));
			ieRef.setPos(posZ);
			Size sizeZ = new Size();
			sizeZ.setHeight(new BigInteger("0"));
			sizeZ.setWidth(new BigInteger("0")); 
			//sizeZ.setHeight(new BigInteger(Integer.toString(elem.getContRef().getHeight())));
			//sizeZ.setWidth(new BigInteger(Integer.toString(elem.getContRef().getWidth())));
			ieRef.setSize(sizeZ);
			
			intentionalElement.getRefs().add(of.createIntentionalElementRefs(ieRef));
			urnZ.getGrlspec().getIntElements().add(intentionalElement);
		}
		return target = ieRef;
	}
}
