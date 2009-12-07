package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GRLGraph  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GRLGraph">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="connections" type="LinkRef"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="nodes" type="GRLNode"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="contRefs" type="ActorRef"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="comments" type="Comment"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class GRLGraphUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		GRLGraph elemZ = (GRLGraph) o;
		String objId = elemZ.getId();
		grl.GRLGraph elem = (grl.GRLGraph) id2object.get(objId);
		if (null == elem) {
			if (null == target) {
				elem = (grl.GRLGraph) ModelCreationFactory.getNewObject(urn, grl.GRLGraph.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId) < Integer.valueOf(objId))
					globelId = objId;
			} else
				elem = (grl.GRLGraph) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (grl.GRLGraph) super.handle(elemZ, elem, true);
			elem.setUrndefinition(urn.getUrndef());
			urncore.Concern concern = (urncore.Concern) process(elemZ.getConcern(), null, false);
			elem.setConcern(concern);
			if (concern != null && concern.getSpecDiagrams().contains(elem)) {
				concern.getSpecDiagrams().add(elem);
			}

			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getContRefs(), elem.getContRefs(), true);
			for (Object tmp : elem.getContRefs()) {
				((grl.ActorRef) tmp).setDiagram(elem);
			}
			processList(elemZ.getNodes(), elem.getNodes(), true);
			for (Object tmp : elem.getNodes()) {
				((grl.GRLNode) tmp).setDiagram(elem);
			}
			// elem.getUrndefinition();
			processList(elemZ.getConnections(), elem.getConnections(), true);
			for (Object tmp : elem.getConnections()) {
				if (tmp instanceof grl.LinkRef) {
					((grl.LinkRef) tmp).setDiagram(elem);
				} else if (tmp instanceof grl.BeliefLink) {
					((grl.BeliefLink) tmp).setDiagram(elem);
				}
			}
			// elem.getConcern();
			processList(elemZ.getComments(), elem.getComments(), true);
			for (Object tmp : elem.getComments()) {
				((urncore.Comment) tmp).setDiagram(elem);
			}
			// elem.getFromLinks();
			// elem.getToLinks();
			// elem.getMetadata();
			// elem.getName();
			// elem.getId();
			// elem.getDescription();
			// elem.getClass();
		}
		return elem;
	}
}
