package seg.jUCMNav.importexport.z151.marshal;

import javax.xml.bind.JAXBElement;

import seg.jUCMNav.importexport.z151.generated.Concern;
import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.URNmodelElement;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Concern  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Concern">
//	<xsd:complexContent>
//		<xsd:extension base="URNmodelElement">
//			<xsd:sequence>
//				<xsd:element minOccurs="0" name="condition" type="xsd:IDREF" />  <!-- Condition -->
//				<xsd:element maxOccurs="unbounded" minOccurs="0" name="elements"
//					type="xsd:IDREF" />  <!-- URNmodelElement -->
//			</xsd:sequence>
//		</xsd:extension>
//	</xsd:complexContent>
//</xsd:complexType>

public class ConcernMHandler extends URNmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.Concern elem = (urncore.Concern) o;
		String objId = elem.getId();
		Concern elemZ = (Concern) getObject(objId, target, "createConcern"); //$NON-NLS-1$
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
			elemZ.setCondition((Condition) process(elem.getCondition(), null, false));
			
            // NOTE: This only cover concerns for GRLGraph and UCMmap. 
			// To update when concerns accept targets other than diagrams 
			for (Object concernElement : elem.getSpecDiagrams()) {
			    URNmodelElement concernElementZ = (URNmodelElement) process(concernElement, null, false);
		        JAXBElement <Object> jaxbElem = of.createConcernElements(concernElementZ);
			    elemZ.getElements().add(jaxbElem);
			}
		}
		return elemZ;
	}
}
