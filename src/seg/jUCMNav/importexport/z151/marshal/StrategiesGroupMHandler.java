package seg.jUCMNav.importexport.z151.marshal;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  StrategiesGroup  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="StrategiesGroup">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="strategies" type="xsd:IDREF"/> <!-- EvaluationStrategy -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

/*** Done! ***/

public class StrategiesGroupMHandler extends GRLmodelElementMHandler {
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.StrategiesGroup elem = (grl.StrategiesGroup) obj;
		String objId = elem.getId();
		StrategiesGroup elemZ = (StrategiesGroup) id2object.get(objId);
		if (null == elemZ) {
			if (null == target) {
				elemZ = of.createStrategiesGroup();
			} else
				elemZ = (StrategiesGroup) target;
			this.id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (StrategiesGroup) super.handle(elem, elemZ, true);
			processList(elem.getStrategies(), elemZ.getStrategies(), "createStrategiesGroupStrategies", false);
		}
		return elemZ;
	}

}
