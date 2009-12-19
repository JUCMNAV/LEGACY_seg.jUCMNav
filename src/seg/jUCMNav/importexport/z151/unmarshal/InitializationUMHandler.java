package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Initialization  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Initialization">
//    <xsd:sequence>
//      <xsd:element name="value" type="xsd:string"/>
//      <xsd:element name="variable" type="xsd:IDREF"/>  <!-- Variable -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Initialization;
import seg.jUCMNav.model.ModelCreationFactory;

public class InitializationUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Initialization elemZ = (Initialization) o;
		ucm.scenario.Initialization elem = null;
		if (null == elem) {
			if (null == target)
				elem = (ucm.scenario.Initialization) ModelCreationFactory
						.getNewObject(urn, ucm.scenario.Initialization.class);
			else
				elem = (ucm.scenario.Initialization) target;
		}
		if (isFullConstruction) {
			//elem.setScenarioDef(); handled by ScenarioDefUMHandler
			elem.setVariable((ucm.scenario.Variable) process(elemZ
					.getVariable(), null, false));
			elem.setValue(elemZ.getValue());

			// elem.getScenarioDef();
			// elem.getVariable();
			// elem.getValue();
			// elem.getClass();
		}
		return elem;
	}
}
