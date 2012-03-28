package seg.jUCMNav.importexport.z151.marshal;

import java.util.List;

import javax.xml.bind.JAXBElement;

import seg.jUCMNav.importexport.z151.generated.EvaluationStrategy;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.StrategiesGroup;

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

public class EvaluationStrategyMHandler extends GRLmodelElementMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.EvaluationStrategy elem = (grl.EvaluationStrategy) obj;
		String objId = elem.getId();
		EvaluationStrategy elemZ = (EvaluationStrategy) getObject(objId, target, "createEvaluationStrategy"); //$NON-NLS-1$
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
			processList(elem.getEvaluations(), elemZ.getEvaluations(), true);
			// Key Differences between Z.151 and jUCMNav
			// 7.5.2: EvaluationStrategy in Z.151 may be associated to many
			// StrategiesGroup in Z.151, but to only one in jUCMNav.
			// Also, jUCMNav supports strategy inclusion, but not Z.151
			List<JAXBElement<Object>> gJAXBelementZ = elemZ.getGroup();
			StrategiesGroup gZ;
			gZ = (StrategiesGroup) process(elem.getGroup(), null, false);
			gJAXBelementZ.add(of.createStrategiesGroupStrategies(gZ));

			// Save Author information to metadata
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav EvaluationStrategy author"); //$NON-NLS-1$
			mdZ.setValue(elem.getAuthor());
			elemZ.getMetadata().add(mdZ);
			// jUCMNav has elem.setGrlspec(value)
		}
		return elemZ;
	}
}
