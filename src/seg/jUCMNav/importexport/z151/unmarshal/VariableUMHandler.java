package seg.jUCMNav.importexport.z151.unmarshal;

//	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//	<!--  Variable  -->
//	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//	<xsd:complexType name="Variable">
//		<xsd:complexContent>
//			<xsd:extension base="UCMmodelElement">
//				<xsd:sequence>
//					<xsd:element default="Boolean" name="type" type="DatatypeKind" /> <!-- DatatypeKind -->
//					<xsd:element minOccurs="0" name="enumerationType" type="xsd:IDREF" /> <!-- EnumerationType -->
//				</xsd:sequence>
//			</xsd:extension>
//		</xsd:complexContent>
//	</xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.EnumerationType;
import seg.jUCMNav.importexport.z151.generated.Variable;

public class VariableUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Variable elemZ = (Variable) o;
		String objId = elemZ.getId();
		ucm.scenario.Variable elem = (ucm.scenario.Variable) getObject(elemZ.getId(), target, ucm.scenario.Variable.class);
		if (isFullConstruction) {
			elem = (ucm.scenario.Variable) super.handle(elemZ, elem, true);
			// elem.setUcmspec(); handled by UCMspecUMHandler
			elem.setEnumerationType((ucm.scenario.EnumerationType) process((EnumerationType) elemZ.getEnumerationType(), null, false));
			String type =elemZ.getType().value().substring(0, 1).toLowerCase()+elemZ.getType().value().substring(1).toLowerCase();
			elem.setType(type);  // not 100%
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUcmspec();
			// elem.getEnumerationType();
			// elem.getType();
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
