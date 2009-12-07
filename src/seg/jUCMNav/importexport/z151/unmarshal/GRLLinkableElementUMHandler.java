package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GRLLinkableElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GRLLinkableElement">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="linksDest" type="xsd:IDREF"/> <!-- ElementLink -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="linksSrc" type="xsd:IDREF"/> <!-- ElementLink -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class GRLLinkableElementUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GRLLinkableElement elemZ = (GRLLinkableElement) o;
		String objId = elemZ.getId();
		grl.GRLLinkableElement elem = (grl.GRLLinkableElement) id2object
				.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (grl.GRLLinkableElement) ModelCreationFactory
						.getNewObject(urn, grl.GRLLinkableElement.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (grl.GRLLinkableElement) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (grl.GRLLinkableElement) super.handle(elemZ, elem, true);
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getLinksDest(), elem.getLinksDest(), false);
			processList(elemZ.getLinksSrc(), elem.getLinksSrc(), false);
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
