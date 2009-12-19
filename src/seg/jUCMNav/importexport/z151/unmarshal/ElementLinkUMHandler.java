package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ElementLink  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ElementLink">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="refs" type="xsd:IDREF"/> <!-- LinkRef -->
//          <xsd:element name="dest" type="xsd:IDREF"/> <!-- GRLLinkableElement -->
//          <xsd:element name="src" type="xsd:IDREF"/> <!-- GRLLinkableElement -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ElementLink;
import seg.jUCMNav.importexport.z151.generated.GRLLinkableElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementType;

public class ElementLinkUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ElementLink elemZ = (ElementLink) o;
		if (((IntentionalElement) elemZ.getSrc()).getType().equals(IntentionalElementType.BELIEF)){
			return null;
		}else{
		String objId = elemZ.getId();
		grl.ElementLink elem = (grl.ElementLink) getObject(objId, target, grl.ElementLink.class);
		if (isFullConstruction) {
			elem = (grl.ElementLink) super.handle(elemZ, elem, true);
			//elem.setGrlspec(); //Handled in GRLspecUMHandler
			elem.setSrc((grl.GRLLinkableElement) process(
					(GRLLinkableElement) elemZ.getSrc(), null, false));
			elem.setDest((grl.GRLLinkableElement) process(
					(GRLLinkableElement) elemZ.getDest(), null, false));
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getGrlspec();
			processList(elemZ.getRefs(), elem.getRefs(), false);
			// elem.getSrc();
			// elem.getDest();
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
}
