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

import seg.jUCMNav.importexport.z151.generated.ActiveResource;

public class ActiveResourceUMHandler extends GeneralResourceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ActiveResource elemZ = (ActiveResource) o;
		ucm.performance.ActiveResource elem = (ucm.performance.ActiveResource) getObject(elemZ.getId(), target, ucm.performance.ActiveResource.class);
		if (isFullConstruction) {
			elem = (ucm.performance.ActiveResource) super.handle(elemZ, elem,
					true);
			elem.setOpTime(elemZ.getOpTime());
            elem.setUnit(getTimeUnit(elemZ.getUnit()));
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