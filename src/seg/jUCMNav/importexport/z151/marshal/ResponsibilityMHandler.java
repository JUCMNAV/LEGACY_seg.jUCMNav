package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Responsibility  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Responsibility">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element name="expression" type="xsd:string"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="demands" type="Demand"/>
//	  <xsd:element maxOccurs="unbounded" name="respRefs" type="xsd:IDREF"/> <!-- RespRef -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.Responsibility;

public class ResponsibilityMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.Responsibility elem = (urncore.Responsibility) o;
		String objId = elem.getId();
		Responsibility elemZ = (Responsibility) getObject(objId, target, "createResponsibility"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (Responsibility) super.handle(elem, elemZ, true);
			elemZ.setExpression(elem.getExpression());
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			//elemZ.getExpression();
			processList(elem.getDemands(), elemZ.getDemands(), true);
			processList(elem.getRespRefs(), elemZ.getRespRefs(), "createResponsibilityRespRefs", false); //$NON-NLS-1$
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
			
			boolean context = elem.isContext();
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav Responsibility context"); //$NON-NLS-1$
			mdZ.setValue(Boolean.toString(context));
			elemZ.getMetadata().add(mdZ);
			
			boolean empty = elem.isEmpty();
			mdZ = of.createMetadata();
			mdZ.setName("jUCMNav Responsibility empty"); //$NON-NLS-1$
			mdZ.setValue(Boolean.toString(empty));
			elemZ.getMetadata().add(mdZ);
		}
		return elemZ;
	}
}
