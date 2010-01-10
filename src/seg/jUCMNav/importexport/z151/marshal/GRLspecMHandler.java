package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.GRLspec;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  GRLspec  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="GRLspec">
//  <xsd:sequence>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="intElements" type="IntentionalElement"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="actors" type="Actor"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="links" type="ElementLink"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="groups" type="StrategiesGroup"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="strategies" type="EvaluationStrategy"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="grlGraphs" type="GRLGraph"/>
//    <xsd:element minOccurs="0" name="info" type="ConcreteGRLspec"/>
//  </xsd:sequence>
//</xsd:complexType>

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ConcreteGRLspec  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ConcreteGRLspec">
//  <xsd:sequence>
//    <xsd:element name="showAsMeansEnd" type="xsd:boolean"/>
//  </xsd:sequence>
//</xsd:complexType>

/***
 * Done!** But TODO: jUCMNav missing ConcreteGRLspec
 */

public class GRLspecMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.GRLspec elem = (grl.GRLspec) o;
		GRLspec elemZ = of.createGRLspec();
		processList(elem.getIntElements(), elemZ.getIntElements(), true);
		processList(elem.getActors(), elemZ.getActors(), true);
		processList(elem.getLinks(), elemZ.getLinks(), true);
		processList(elem.getGroups(), elemZ.getGroups(), true);
		processList(elem.getStrategies(), elemZ.getStrategies(), true);
		
		//elemZ.getGrlGraphs() handled by URNspecMHandler
		// jUCMNav missing ConcreteGRLspec
		return elemZ;
	}
}
