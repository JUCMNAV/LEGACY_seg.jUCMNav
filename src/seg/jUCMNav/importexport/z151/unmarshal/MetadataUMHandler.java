package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Metadata  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Metadata">
//    <xsd:sequence>
//      <xsd:element name="name" type="xsd:string"/>
//      <xsd:element name="value" type="xsd:string"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.model.ModelCreationFactory;

public class MetadataUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Metadata elemZ = (Metadata) o;
		urncore.Metadata elem = null;
		if (null == elem) {
			if (null == target)
				elem = (urncore.Metadata) ModelCreationFactory.getNewObject(
						urn, urncore.Metadata.class);
			else
				elem = (urncore.Metadata) target;
		}
		if (isFullConstruction) {
			elem.setName(elemZ.getName());
			elem.setValue(elemZ.getValue());

			// elem.getName();
			// elem.getValue();
			// elem.getClass();
		}
		return elem;
	}
}
