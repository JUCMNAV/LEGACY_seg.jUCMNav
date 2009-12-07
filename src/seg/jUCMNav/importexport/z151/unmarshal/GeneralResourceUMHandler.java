package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GeneralResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GeneralResource">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element default="1" name="multiplicity" type="xsd:nonNegativeInteger"/>
//          <xsd:element name="schedPolicy" type="xsd:string"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class GeneralResourceUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GeneralResource elemZ = (GeneralResource) o;
		String objId = elemZ.getId();
		ucm.performance.GeneralResource elem = (ucm.performance.GeneralResource) id2object
				.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.performance.GeneralResource) ModelCreationFactory
						.getNewObject(urn,
								ucm.performance.GeneralResource.class);
							elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.performance.GeneralResource) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.performance.GeneralResource) super.handle(elemZ, elem, true);
			//elem.setUcmspec(); handled by UCMspecUMHandler
			elem.setMultiplicity(elemZ.getMultiplicity().toString());
			elem.setSchedPolicy(elemZ.getSchedPolicy());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUcmspec();
			// elem.getMultiplicity();
			// elem.getSchedPolicy();
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