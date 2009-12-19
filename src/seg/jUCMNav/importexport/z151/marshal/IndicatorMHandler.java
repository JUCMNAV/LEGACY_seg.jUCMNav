package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.ConcreteStyle;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.Metadata;

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

public class IndicatorMHandler extends GRLLinkableElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.kpimodel.Indicator elem = (grl.kpimodel.Indicator) o;
		String objId = elem.getId();
		IntentionalElement elemZ = (IntentionalElement) getObject(objId, target, "createIntentionalElement"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (IntentionalElement) super.handle(elem, elemZ, true);
			//type
			elemZ.setType(getIntentionalElementType(elem.getType()));
			elemZ.setDecompositionType(getDecompositionType(elem.getDecompositionType()));
			elemZ.setImportance(getImportanceType(elem.getImportance()));
			elemZ.setImportanceQuantitative(new BigInteger(Integer.toString(elem.getImportanceQuantitative())));
			processList(elem.getRefs(), elemZ.getRefs(), "createIntentionalElementRefs", false); //$NON-NLS-1$

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
			csZ.setFillColor(elem.getFillColor());
			csZ.setLineColor(elem.getLineColor());
			csZ.setFilled(elem.isFilled());
			elemZ.setStyle(csZ);
			// elemZ.setActor() //handled by ActorMHandler. jUCMNav doesn't have this relation.
			
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav Indicator"); //$NON-NLS-1$
			mdZ.setValue("Indicator"); //$NON-NLS-1$
			elemZ.getMetadata().add(mdZ);
		}
		return target = elemZ;
	}
}
