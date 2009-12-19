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

import seg.jUCMNav.importexport.z151.generated.PassiveResource;

public class PassiveResourceUMHandler extends GeneralResourceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		PassiveResource elemZ = (PassiveResource) o;
		String objId = elemZ.getId();
		ucm.performance.PassiveResource elem = (ucm.performance.PassiveResource) getObject(elemZ.getId(), target, ucm.performance.PassiveResource.class);
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