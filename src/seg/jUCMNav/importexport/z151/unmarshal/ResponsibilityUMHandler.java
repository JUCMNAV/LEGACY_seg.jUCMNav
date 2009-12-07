package seg.jUCMNav.importexport.z151.unmarshal;

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

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class ResponsibilityUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Responsibility elemZ = (Responsibility) o;
		String objId = elemZ.getId();
		urncore.Responsibility elem = (urncore.Responsibility) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (urncore.Responsibility) ModelCreationFactory.getNewObject(urn, urncore.Responsibility.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (urncore.Responsibility) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (urncore.Responsibility) super.handle(elemZ, elem, true);
			elem.setUrndefinition(urn.getUrndef());
			elem.setExpression(elemZ.getExpression());
			elem.setEmpty(false); // set to default
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUrndefinition();
			// elem.getExpression();
			processList(elemZ.getDemands(), elem.getDemands(), true);
			for (Object demand : elem.getDemands()) {
				((ucm.performance.Demand) demand).setResponsibility(elem);
			}
			processList(elemZ.getRespRefs(), elem.getRespRefs(), false);
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
