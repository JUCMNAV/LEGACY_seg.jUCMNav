package seg.jUCMNav.importexport.z151.marshal;

import javax.xml.bind.JAXBElement;

import seg.jUCMNav.importexport.z151.generated.Concern;
import seg.jUCMNav.importexport.z151.generated.GRLGraph;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  GRLGraph  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="GRLGraph">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="connections" type="LinkRef"/>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="nodes" type="GRLNode"/>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="contRefs" type="ActorRef"/>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="comments" type="Comment"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>


public class GRLGraphMHandler extends GRLmodelElementMHandler {

	public Object handle(Object o, Object target, boolean isFullConstruction) {
		grl.GRLGraph elem = (grl.GRLGraph) o;
		String objId = elem.getId();
		GRLGraph elemZ = (GRLGraph) getObject(objId, target, "createGRLGraph"); //$NON-NLS-1$
		
		if (isFullConstruction) {
			elemZ = (GRLGraph) super.handle(elem, elemZ, true);
			processList(elem.getNodes(), elemZ.getNodes(), true);
			processList(elem.getContRefs(), elemZ.getContRefs(), true);
			processList(elem.getConnections(), elemZ.getConnections(), true);
			processList(elem.getComments(), elemZ.getComments(), true);
			
			Concern concern = (Concern) process(elem.getConcern(), null, false);
			elemZ.setConcern(concern);
			JAXBElement <Object> jaxbElem = of.createConcernElements(elemZ);
			if (concern!=null && concern.getElements().contains(elemZ)){
				concern.getElements().add(jaxbElem);
			}
		}
		return elemZ;
	}
}
