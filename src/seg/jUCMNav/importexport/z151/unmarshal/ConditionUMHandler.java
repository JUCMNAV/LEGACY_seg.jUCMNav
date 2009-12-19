package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Condition  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Condition">
//    <xsd:sequence>
//      <xsd:element name="expression" type="xsd:string"/>
//      <xsd:element minOccurs="0" name="desc" type="ConcreteCondition"/>
//      <xsd:element minOccurs="0" name="label" type="Label"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.model.ModelCreationFactory;

public class ConditionUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Condition elemZ = (Condition) o;
		urncore.Condition elem = null;
		if (null == elem) {
			if (null == target)
				elem = (urncore.Condition) ModelCreationFactory.getNewObject(urn, urncore.Condition.class);
			else
				elem = (urncore.Condition) target;
		}
		if (isFullConstruction) {
			// elem.setConcern(); handled by ConcernUMHandler
			elem.setExpression(elemZ.getExpression());
			// elem.setStartPoint(); handled by StartPointUMHandler
			// elem.setEndPoint(); handled by EndPointUMHandler
			// elem.setPluginBinding(); handled by PluginBindingUMHandler
			// elem.setNodeConnection(); handled by NodeConnectionUMHandler
			// elem.setScenarioDefPre(); handled by ScenarioDefUMHandler
			// elem.setScenarioDefPost(); handled by ScenarioDefUMHandler

			if (elemZ.getDesc() != null) {
				String description = elemZ.getDesc().getDescription();
				if (!description.equals("")) elem.setDescription(description); //$NON-NLS-1$
				String label = elemZ.getDesc().getLabel();
				if (!label.equals("")) elem.setLabel(label); //$NON-NLS-1$
			}
			if (elemZ.getLabel() != null) {
				elem.setDeltaX(elemZ.getLabel().getDeltaX().intValue());
				elem.setDeltaY(elemZ.getLabel().getDeltaY().intValue());
			}

			// elem.getConcern();
			// elem.getExpression();
			// elem.getPluginBinding();
			// elem.getNodeConnection();
			// elem.getScenarioDefPre();
			// elem.getScenarioDefPost();
			// elem.getLabel();
			// elem.getDescription();
			// elem.getEndPoint();
			// elem.getStartPoint();
			// elem.getDeltaX();
			// elem.getDeltaY();
			// elem.getClass();
		}
		return elem;

	}
}
