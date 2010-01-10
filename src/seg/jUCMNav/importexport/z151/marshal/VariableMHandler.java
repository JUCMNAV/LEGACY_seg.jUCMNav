package seg.jUCMNav.importexport.z151.marshal;

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

import seg.jUCMNav.importexport.z151.generated.DatatypeKind;
import seg.jUCMNav.importexport.z151.generated.EnumerationType;
import seg.jUCMNav.importexport.z151.generated.Variable;

public class VariableMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.Variable elem = (ucm.scenario.Variable) o;
		String objId = elem.getId();
		Variable elemZ = (Variable) getObject(objId, target, "createVariable"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (Variable) super.handle(elem, elemZ, true);
			elemZ.setEnumerationType((EnumerationType) process(elem.getEnumerationType(), null, false));
			String type =elem.getType().substring(0, 1).toUpperCase()+elem.getType().substring(1).toLowerCase();
			elemZ.setType(DatatypeKind.fromValue(type)); 
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			//
			// elemZ.getEnumerationType();
			// elemZ.getType();
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
