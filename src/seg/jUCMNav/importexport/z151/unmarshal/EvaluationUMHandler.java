package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Evaluation  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Evaluation">
//    <xsd:sequence>
//      <xsd:element name="evaluation" type="xsd:integer"/>
//      <xsd:element default="None" name="qualitativeEvaluation" type="QualitativeLabel"/>
//      <xsd:element name="intElement" type="xsd:IDREF"/>  <!-- IntentionalElement -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Evaluation;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.model.ModelCreationFactory;

public class EvaluationUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Evaluation elemZ = (Evaluation) o;
		grl.Evaluation elem = null;
		if (null == elem) {
			if (null == target)
				elem = (grl.Evaluation) ModelCreationFactory.getNewObject(urn,
						grl.Evaluation.class);
			else
				elem = (grl.Evaluation) target;
		}
		if (isFullConstruction) {
			elem.setEvaluation(elemZ.getEvaluation().intValue());
			elem.setQualitativeEvaluation(grl.QualitativeLabel.get(elemZ
					.getQualitativeEvaluation().ordinal()));
			elem.setIntElement((grl.IntentionalElement) process(
					(IntentionalElement) elemZ.getIntElement(), null, false));
			//elem.setStrategies(); handled by EvaluationStrategyUMHandler
			// elem.setKpiEvalValueSet();

			// elem.getStrategies();
			// elem.getEvaluation();
			//elem.getQualitativeEvaluation();
			// elem.getIntElement();
			// elem.getKpiEvalValueSet();
			// elem.getClass();
		}
		return elem;
		
	}
}
