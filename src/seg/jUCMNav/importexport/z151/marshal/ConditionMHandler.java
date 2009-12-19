package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.ConcreteCondition;
import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.Label;

public class ConditionMHandler extends MHandler {
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- Condition -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:complexType name="Condition">
	// <xsd:sequence>
	// <xsd:element name="expression" type="xsd:string"/>
	// <xsd:element minOccurs="0" name="desc" type="ConcreteCondition"/>
	// <xsd:element minOccurs="0" name="label" type="Label"/>
	// </xsd:sequence>
	// </xsd:complexType>
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- ConcreteCondition -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:complexType name="ConcreteCondition">
	// <xsd:sequence>
	// <xsd:element name="label" type="xsd:string"/>
	// <xsd:element name="description" type="xsd:string"/>
	// </xsd:sequence>
	// </xsd:complexType>
	
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		urncore.Condition elem = (urncore.Condition) obj;
		Condition elemZ = null;
		if (null == target) {
			elemZ = of.createCondition();
		} else
			elemZ = (Condition) target;
		if (isFullConstruction) {
			elemZ.setExpression(elem.getExpression());
			if (elem.getDescription() != null || elem.getLabel() != null) {
				ConcreteCondition ccZ = of.createConcreteCondition();
				if (elem.getDescription() != null)
					ccZ.setDescription(elem.getDescription());
				else
					ccZ.setDescription(""); //$NON-NLS-1$
				if (elem.getLabel() != null)
					ccZ.setLabel(elem.getLabel());
				else
					ccZ.setLabel(""); //$NON-NLS-1$
				elemZ.setDesc(ccZ);
			}
			Label lZ = of.createLabel();
			lZ.setDeltaX(new BigInteger(Integer.toString(elem.getDeltaX())));
			lZ.setDeltaY(new BigInteger(Integer.toString(elem.getDeltaY())));
			elemZ.setLabel(lZ);
		}
		return elemZ;
	}

}
