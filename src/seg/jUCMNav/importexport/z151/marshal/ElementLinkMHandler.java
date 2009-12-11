package seg.jUCMNav.importexport.z151.marshal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ElementLink  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ElementLink">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="refs" type="xsd:IDREF"/> <!-- LinkRef -->
//        <xsd:element name="dest" type="xsd:IDREF"/> <!-- GRLLinkableElement -->
//        <xsd:element name="src" type="xsd:IDREF"/> <!-- GRLLinkableElement -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

/*** Done! ***/

public class ElementLinkMHandler extends GRLmodelElementMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.ElementLink elem = (grl.ElementLink) obj;
		String objId = elem.getId();
		ElementLink elemZ = (ElementLink) getObject(objId, target,"createElementLink");
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
			processList(elem.getRefs(), elemZ.getRefs(), "createElementLinkRefs", false);
			elemZ.setDest((GRLLinkableElement) process(elem.getDest(), null, false));
			elemZ.setSrc((GRLLinkableElement) process(elem.getSrc(), null, false));
		}
		return elemZ;
	}
}
