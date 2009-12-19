package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ProcessingResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ProcessingResource">
//    <xsd:complexContent>
//      <xsd:extension base="ActiveResource">
//        <xsd:sequence>
//          <xsd:element default="Processor" name="kind" type="DeviceKind"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="components" type="xsd:IDREF"/> <!-- Component -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ProcessingResource;

public class ProcessingResourceUMHandler extends ActiveResourceUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ProcessingResource elemZ = (ProcessingResource) o;
		String objId = elemZ.getId();
		ucm.performance.ProcessingResource elem = (ucm.performance.ProcessingResource) getObject(elemZ.getId(), target, ucm.performance.ProcessingResource.class);
		if (isFullConstruction) {
			elem = (ucm.performance.ProcessingResource) super.handle(elemZ, elem, true);
			elem.setKind(getDeviceKind(elemZ.getKind()));
			// elem.setOpTime();
			// elem.setUcmspec();
			// elem.setMultiplicity();
			// elem.setSchedPolicy();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getKind();
			processList(elemZ.getComponents(), elem.getComponents(), false);
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
