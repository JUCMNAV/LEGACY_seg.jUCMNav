package seg.jUCMNav.importexport.z151.marshal;

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

public class InitializationMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.Initialization elem = (ucm.scenario.Initialization) o;
		Initialization elemZ = null;
		if (null == target){
			elemZ = of.createInitialization();
		}else
			elemZ = (Initialization) target;
		if (isFullConstruction) {
			elemZ.setVariable(process(elem.getVariable(), null, false));
			elemZ.setValue(elem.getValue());
		}

		// elemZ.getVariable();
		// elemZ.getValue();
		// elemZ.getClass();
		return elemZ;
	}
}