package seg.jUCMNav.importexport.z151.marshal;

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

public class PassiveResourceMHandler extends GeneralResourceMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.PassiveResource elem = (ucm.performance.PassiveResource) o;
		String objId = elem.getId();
		PassiveResource elemZ = (PassiveResource) getObject(objId, target, "createPassiveResource"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (PassiveResource) super.handle(elem, elemZ, true);
			elemZ.setComponent(process(elem.getComponent(), null, true));
			// elemZ.setMultiplicity();
			// elemZ.setSchedPolicy();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			//
			// elemZ.getComponent();
			// elemZ.getMultiplicity();
			// elemZ.getSchedPolicy();
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
		}
		return elemZ;
	}
}
