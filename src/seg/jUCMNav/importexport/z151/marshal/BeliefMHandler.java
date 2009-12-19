package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.ActorRef;
import seg.jUCMNav.importexport.z151.generated.ConcreteStyle;
import seg.jUCMNav.importexport.z151.generated.Description;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementRef;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.Position;
import seg.jUCMNav.importexport.z151.generated.Size;

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
		String objId = "Z151_id_"+elem.getClass().getName()+"_"+elem.getId(); //$NON-NLS-1$ //$NON-NLS-2$
		IntentionalElement intentionalElement = (IntentionalElement) id2object.get(objId);
		IntentionalElementRef ieRef = (IntentionalElementRef) id2object.get(elem.getId());
		if (intentionalElement == null) {
			if (null == target) {
				intentionalElement = of.createIntentionalElement();
			} else
				intentionalElement = (IntentionalElement) target;
			id2object.put(objId, intentionalElement);
		}
		if (ieRef == null) {
			ieRef = of.createIntentionalElementRef();
			id2object.put(elem.getId(), ieRef);
		}
		ieRef.setDef(intentionalElement);
		
		if (isFullConstruction) {
			intentionalElement.setId(objId);
			intentionalElement.setName(elem.getName());
			processList(elem.getMetadata(), intentionalElement.getMetadata(), true);
			processList(elem.getToLinks(), intentionalElement.getToLinks(), "createURNmodelElementFromLinks", false); //$NON-NLS-1$
			processList(elem.getFromLinks(), intentionalElement.getFromLinks(), "createURNmodelElementToLinks", false); //$NON-NLS-1$
			if (null != elem.getDescription()) {
				Description desc = of.createDescription();
				desc.setDescription(elem.getDescription());
				intentionalElement.setDesc(desc);
			}
			//elemZ.getConcern() handled by GRLGraphMHandler
			intentionalElement.setType(seg.jUCMNav.importexport.z151.generated.IntentionalElementType.BELIEF);
			intentionalElement.setDecompositionType(seg.jUCMNav.importexport.z151.generated.DecompositionType.AND);
			intentionalElement.setImportance(seg.jUCMNav.importexport.z151.generated.ImportanceType.NONE);
			intentionalElement.setImportanceQuantitative(new BigInteger("0")); //$NON-NLS-1$

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
			csZ.setFillColor("255,255,255"); //$NON-NLS-1$
			csZ.setLineColor("0,0,0"); //$NON-NLS-1$
			csZ.setFilled(false);
			intentionalElement.setStyle(csZ);
			
			ieRef.setId(elem.getId());
			ieRef.setName(elem.getName()); 
			processList(elem.getMetadata(), ieRef.getMetadata(), true);
			processList(elem.getToLinks(), ieRef.getToLinks(), "createURNmodelElementFromLinks", false); //$NON-NLS-1$
			processList(elem.getFromLinks(), ieRef.getFromLinks(), "createURNmodelElementToLinks", false); //$NON-NLS-1$
			if (null != elem.getDescription()) {
				Description desc = of.createDescription();
				desc.setDescription(elem.getDescription());
				ieRef.setDesc(desc);
			}
			//elemZ.getConcern() handled by GRLGraphMHandler
			
			
			processList(elem.getPred(), ieRef.getPred(), "createGRLNodePred", false); //$NON-NLS-1$
			processList(elem.getSucc(), ieRef.getSucc(), "createGRLNodeSucc", false); //$NON-NLS-1$
			ieRef.setContRef((ActorRef) process((grl.ActorRef) elem.getContRef(), null, false));
			Position posZ = new Position();
			posZ.setX(new BigInteger(Integer.toString(elem.getX())));
			posZ.setY(new BigInteger(Integer.toString(elem.getY())));
			ieRef.setPos(posZ);
			Size sizeZ = new Size();
			sizeZ.setHeight(new BigInteger("0")); //$NON-NLS-1$
			sizeZ.setWidth(new BigInteger("0"));  //$NON-NLS-1$
			ieRef.setSize(sizeZ);
			
			// Save Author information to metadata
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav Belief author"); //$NON-NLS-1$
			mdZ.setValue(elem.getAuthor());
			ieRef.getMetadata().add(mdZ);
			
			intentionalElement.getRefs().add(of.createIntentionalElementRefs(ieRef));
			urnZ.getGrlspec().getIntElements().add(intentionalElement);
		}
		return target = ieRef;
	}
}
