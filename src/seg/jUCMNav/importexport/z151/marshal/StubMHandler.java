package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Stub  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Stub">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode">
//        <xsd:sequence>
//          <xsd:element name="dynamic" type="xsd:boolean"/>
//          <xsd:element name="synchronizing" type="xsd:boolean"/>
//          <xsd:element name="blocking" type="xsd:boolean"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="bindings" type="PluginBinding"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>
// DONE

import seg.jUCMNav.importexport.z151.generated.*;

public class StubMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.Stub elem = (ucm.map.Stub) o;
		String objId = elem.getId();
		Stub elemZ = (Stub) id2object.get(objId);
		if (null == elemZ) {
			if (null == target)
				elemZ = of.createStub();
			else
				elemZ = (Stub) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (Stub) super.handle(elem, elemZ, true);
			elemZ.setDynamic(elem.isDynamic());
			elemZ.setSynchronizing(elem.isSynchronization());
			elemZ.setBlocking(elem.isBlocking());
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getBindings(), elemZ.getBindings(), true);
			// elemZ.getPred();
			// elemZ.getSucc();
			// elemZ.getContRef();
			// elemZ.getLabel();
			// elemZ.getPos();
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
		}
		return elemZ;
	}
}
