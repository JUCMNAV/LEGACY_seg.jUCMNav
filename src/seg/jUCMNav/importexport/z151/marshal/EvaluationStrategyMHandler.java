package seg.jUCMNav.importexport.z151.marshal;

import java.util.List;
import javax.xml.bind.JAXBElement;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  EvaluationStrategy  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="EvaluationStrategy">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="evaluations" type="Evaluation"/>
//        <xsd:element maxOccurs="unbounded" name="group" type="xsd:IDREF"/> <!-- StrategiesGroup -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

/***
 * Done! //Key Differences between Z.151 and jUCMNav 7.5.2: EvaluationStrategy
 * in Z.151 may be associated to many StrategiesGroup in Z.151, but to only one
 * in jUCMNav.
 ****/

public class EvaluationStrategyMHandler extends GRLmodelElementMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.EvaluationStrategy elem = (grl.EvaluationStrategy) obj;
		String objId = elem.getId();
		EvaluationStrategy elemZ = (EvaluationStrategy) id2object.get(objId);
		if (null == elemZ) {
			if (null == target) {
				elemZ = of.createEvaluationStrategy();
			} else
				elemZ = (EvaluationStrategy) target;
			this.id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
			processList(elem.getEvaluations(), elemZ.getEvaluations(), true);
			// Key Differences between Z.151 and jUCMNav
			// 7.5.2: EvaluationStrategy in Z.151 may be associated to many
			// StrategiesGroup in Z.151, but to only one in jUCMNav.
			List<JAXBElement<Object>> gJAXBelementZ = elemZ.getGroup();
			StrategiesGroup gZ;
			gZ = (StrategiesGroup) process(elem.getGroup(), null, false);
			gJAXBelementZ.add(of.createStrategiesGroupStrategies(gZ));

			// Save Author information to metadata
			Metadata mdZ = of.createMetadata();
			mdZ.setName("author");
			mdZ.setValue(elem.getAuthor());
			elemZ.getMetadata().add(mdZ);
			// jUCMNav has elem.setGrlspec(value)
		}
		return elemZ;
	}
}
