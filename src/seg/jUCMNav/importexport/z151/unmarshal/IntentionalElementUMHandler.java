package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  IntentionalElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
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
//	  <xsd:element minOccurs="0" name="actor" type="xsd:IDREF"/> <!-- Actor -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class IntentionalElementUMHandler extends GRLLinkableElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		IntentionalElement elemZ = (IntentionalElement) o;
		if (!elemZ.getType().equals(IntentionalElementType.BELIEF)) {
			String objId = elemZ.getId();
			grl.IntentionalElement elem = (grl.IntentionalElement) id2object.get(objId);
			if (null == elem) {
				if (null == target) {
					elem = (grl.IntentionalElement) ModelCreationFactory.getNewObject(urn, grl.IntentionalElement.class);
					elem.setId(objId);
					if (Integer.valueOf(globelId) < Integer.valueOf(objId))
						globelId = objId;
				} else
					elem = (grl.IntentionalElement) target;
				id2object.put(objId, elem);
			}
			if (isFullConstruction) {
				elem = (grl.IntentionalElement) super.handle(elemZ, elem, true);
				elem.setLineColor(elemZ.getStyle().getLineColor());
				elem.setFillColor(elemZ.getStyle().getFillColor());
				elem.setFilled(elemZ.getStyle().isFilled());
				// elem.setGrlspec(); //Handled in GRLspecUMHandler
				elem.setDecompositionType(getDecompositionType(elemZ.getDecompositionType()));
				elem.setImportance(grl.ImportanceType.get(elemZ.getImportance().ordinal()));
				elem.setImportanceQuantitative(elemZ.getImportanceQuantitative().intValue());
				elem.setType(getIntentionalElementType(elemZ.getType()));
				// elem.setId();
				// elem.setName();
				// elem.setDescription();

				// elem.getFillColor();
				// elem.getGrlspec();
				processList(elemZ.getRefs(), elem.getRefs(), false);
				// elem.getDecompositionType();
				// elem.getImportance();
				// elem.getImportanceQuantitative();
				// elem.getType();
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
				// TODO elemZ.getActor()
			}
			return elem;
		} else {
			return null;
		}

	}
}
