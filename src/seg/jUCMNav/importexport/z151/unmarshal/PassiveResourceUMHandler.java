package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  PassiveResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="PassiveResource">
//    <xsd:complexContent>
//      <xsd:extension base="GeneralResource">
//        <xsd:sequence>
//          <xsd:element minOccurs="0" name="component" type="xsd:IDREF"/> <!-- Component -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class PassiveResourceUMHandler extends GeneralResourceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		PassiveResource elemZ = (PassiveResource) o;
		String objId = elemZ.getId();
		ucm.performance.PassiveResource elem = (ucm.performance.PassiveResource) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.performance.PassiveResource) ModelCreationFactory.getNewObject(urn, ucm.performance.PassiveResource.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.performance.PassiveResource) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.performance.PassiveResource) super.handle(elemZ, elem, true);
			elem.setComponent((urncore.Component) process(elemZ.getComponent(), null, false));
			// elem.setUcmspec();
			// elem.setMultiplicity();
			// elem.setSchedPolicy();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getComponent();
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