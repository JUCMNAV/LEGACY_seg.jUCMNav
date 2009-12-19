package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GRLspec  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GRLspec">
//    <xsd:sequence>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="intElements" type="IntentionalElement"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="actors" type="Actor"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="links" type="ElementLink"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="groups" type="StrategiesGroup"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="strategies" type="EvaluationStrategy"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="grlGraphs" type="GRLGraph"/>
//      <xsd:element minOccurs="0" name="info" type="ConcreteGRLspec"/>
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.GRLGraph;
import seg.jUCMNav.importexport.z151.generated.GRLspec;
import seg.jUCMNav.model.ModelCreationFactory;

public class GRLspecUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GRLspec elemZ = (GRLspec) o;
		grl.GRLspec elem = null;
		if (null == elem) {
			if (null == target)
				elem = (grl.GRLspec) ModelCreationFactory.getNewObject(urn,
						grl.GRLspec.class);
			else
				elem = (grl.GRLspec) target;
		}
		if (isFullConstruction) {
			elem.setUrnspec(urn);			
			// elem.getUrnspec();
			processList(elemZ.getIntElements(), elem.getIntElements(), true);
			for (Object tmp: elem.getIntElements()){
				((grl.IntentionalElement) tmp).setGrlspec(elem);
			}
			processList(elemZ.getActors(), elem.getActors(), true);
			for (Object tmp: elem.getActors()){
				((grl.Actor) tmp).setGrlspec(elem);
			}
			processList(elemZ.getLinks(), elem.getLinks(), true);
			for (Object tmp: elem.getLinks()){
				((grl.ElementLink) tmp).setGrlspec(elem);
			}
			processList(elemZ.getGroups(), elem.getGroups(), true);
			for (Object tmp: elem.getGroups()){
				((grl.StrategiesGroup) tmp).setGrlspec(elem);
			}
			processList(elemZ.getStrategies(), elem.getStrategies(), true);
			for (Object tmp: elem.getStrategies()){
				((grl.EvaluationStrategy) tmp).setGrlspec(elem);
			}
			// elem.getContributionGroups();
			// elem.getContributionContexts();
			// elem.getKpiInformationElements();
			// elem.getKpiModelLinks();
			// elem.getIndicatorGroup();
			// elem.getClass();
			
//			elemZ.getGrlGraphs()
			for (GRLGraph grlGraph :elemZ.getGrlGraphs()){
				urn.getUrndef().getSpecDiagrams().add((grl.GRLGraph) process(grlGraph, null, true));
			}
			
//TODO			elemZ.getInfo()
		}
		return elem;
	}
}