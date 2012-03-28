package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.Evaluation;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Evaluation  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Evaluation">
//  <xsd:sequence>
//    <xsd:element name="evaluation" type="xsd:integer"/>
//    <xsd:element default="None" name="qualitativeEvaluation" type="QualitativeLabel"/>
//    <xsd:element name="intElement" type="xsd:IDREF"/>  <!-- IntentionalElement -->
//  </xsd:sequence>
//</xsd:complexType>

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  QualitativeLabel  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:simpleType name="QualitativeLabel">
//  <xsd:restriction base="xsd:string">
//    <xsd:enumeration value="Denied"/>
//    <xsd:enumeration value="WeaklyDenied"/>
//    <xsd:enumeration value="WeaklySatisfied"/>
//    <xsd:enumeration value="Satisfied"/>
//    <xsd:enumeration value="Conflict"/>
//    <xsd:enumeration value="Unknown"/>
//    <xsd:enumeration value="None"/>
//  </xsd:restriction>
//</xsd:simpleType>

public class EvaluationMHandler extends MHandler {
	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.Evaluation elem = (grl.Evaluation) obj;
		Evaluation elemZ = null;
		if (null == target)
			elemZ = of.createEvaluation();
		else
			elemZ = (Evaluation) target;
		if (isFullConstruction) {
			elemZ.setEvaluation(new BigInteger(Integer.toString(elem.getEvaluation())));
			elemZ.setQualitativeEvaluation(getQualitativeEvaluation(elem.getQualitativeEvaluation()));
			elemZ.setIntElement((IntentionalElement) process(elem.getIntElement(), null, false));
			// Key difference with Z.151: jUCMNav supports evaluation ranges. 
			// Can't add them to Z.151 evaluations as metadata because 
			// Evaluation is not a URNmodelElement 
		}
		return elemZ;
	}
}
