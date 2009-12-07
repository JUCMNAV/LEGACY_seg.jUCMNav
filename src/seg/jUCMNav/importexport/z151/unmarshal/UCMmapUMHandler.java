package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  UCMmap  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="UCMmap">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element name="singleton" type="xsd:boolean"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="parentStub" type="xsd:IDREF"/> <!-- PluginBinding -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="contRefs" type="ComponentRef"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="connections" type="NodeConnection"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="nodes" type="PathNode"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="comments" type="Comment"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class UCMmapUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		UCMmap elemZ = (UCMmap) o;
		String objId = elemZ.getId();
		ucm.map.UCMmap elem = (ucm.map.UCMmap) id2object.get(objId);
		if (null == elem) {
			if (null == target) {
				elem = (ucm.map.UCMmap) ModelCreationFactory.getNewObject(urn, ucm.map.UCMmap.class);
				elem.setId(objId);
				if (Integer.valueOf(globelId) < Integer.valueOf(objId))
					globelId = objId;
			} else
				elem = (ucm.map.UCMmap) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.map.UCMmap) super.handle(elemZ, elem, true);
			elem.setUrndefinition(urn.getUrndef());
			// elem.setConcern();
			elem.setSingleton(elemZ.isSingleton());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getContRefs(), elem.getContRefs(), true);
			processList(elemZ.getNodes(), elem.getNodes(), true);
			// elem.getUrndefinition();
			processList(elemZ.getConnections(), elem.getConnections(), true);
			// elem.getConcern();
			processList(elemZ.getComments(), elem.getComments(), true);
			processList(elemZ.getParentStub(), elem.getParentStub(), false);
			// elem.getFromLinks();
			// elem.getToLinks();
			// elem.getMetadata();
			// elem.getName();
			// elem.getId();
			// elem.getDescription();
			// elem.getClass();
			setDiagram(elem);// added by Yan
		}
		return elem;
	}

	private void setDiagram(ucm.map.UCMmap elem) {
		for (Object item : elem.getConnections()) {
			((ucm.map.NodeConnection) item).setDiagram(elem);
		}
		for (Object item : elem.getContRefs()) {
			((ucm.map.ComponentRef) item).setDiagram(elem);
		}
		for (Object item : elem.getNodes()) {
			((ucm.map.PathNode) item).setDiagram(elem);
		}
		for (Object item : elem.getComments()) {
			((urncore.Comment) item).setDiagram(elem);
		}
	}
}