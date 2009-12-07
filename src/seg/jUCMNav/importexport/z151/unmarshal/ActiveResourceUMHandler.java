package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ActiveResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ActiveResource">
//    <xsd:complexContent>
//      <xsd:extension base="GeneralResource">
//        <xsd:sequence>
//          <xsd:element name="opTime" type="xsd:string"/>
//          <xsd:element default="ms" name="unit" type="TimeUnit"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class ActiveResourceUMHandler extends GeneralResourceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ActiveResource elemZ = (ActiveResource) o;
		String objId = elemZ.getId();
		ucm.performance.ActiveResource elem = (ucm.performance.ActiveResource) id2object
				.get(objId);
		if (null == elem) {
			if (null == target){
				elem = (ucm.performance.ActiveResource) ModelCreationFactory
						.getNewObject(urn, ucm.performance.ActiveResource.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}else
				elem = (ucm.performance.ActiveResource) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.performance.ActiveResource) super.handle(elemZ, elem,
					true);
			elem.setOpTime(elemZ.getOpTime());
			// jUCMNav doesn't have TimeUnit, but Z151 have
			// elem.setUcmspec();
			// elem.setMultiplicity();
			// elem.setSchedPolicy();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getOpTime();
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