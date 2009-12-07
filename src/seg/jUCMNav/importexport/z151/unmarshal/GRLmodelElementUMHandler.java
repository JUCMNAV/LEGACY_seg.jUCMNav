package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GRLmodelElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GRLmodelElement">
//    <xsd:complexContent>
//      <xsd:extension base="URNmodelElement"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class GRLmodelElementUMHandler extends URNmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GRLmodelElement elemZ = (GRLmodelElement) o;
		String objId = elemZ.getId();
		urncore.GRLmodelElement elem = (urncore.GRLmodelElement) id2object
				.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (urncore.GRLmodelElement) ModelCreationFactory
						.getNewObject(urn, urncore.GRLmodelElement.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (urncore.GRLmodelElement) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (urncore.GRLmodelElement) super.handle(elemZ, elem, true);
			// elem.setId();
			// elem.setName();
			// elem.setDescription();
			//
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