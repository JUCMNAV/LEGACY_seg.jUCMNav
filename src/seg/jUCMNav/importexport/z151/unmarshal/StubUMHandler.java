package seg.jUCMNav.importexport.z151.unmarshal;

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

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class StubUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Stub elemZ = (Stub) o;
		String objId = elemZ.getId();
		ucm.map.Stub elem = (ucm.map.Stub) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.map.Stub) ModelCreationFactory.getNewObject(urn,
						ucm.map.Stub.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.map.Stub) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.map.Stub) super.handle(elemZ, elem, true);
			elem.setDynamic(elemZ.isDynamic());
			elem.setShared(false);
			elem.setRepetitionCount("1");
			elem.setPointcut(false);
			elem.setSynchronization(elemZ.isSynchronizing());
			elem.setBlocking(elemZ.isBlocking());
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getBindings(), elem.getBindings(), true);
			for (Object item : elem.getBindings()){
				((ucm.map.PluginBinding) item).setStub(elem);
			}
			// elem.getDiagram();
			// elem.getContRef();
			// elem.getSucc();
			// elem.getPred();
			// elem.getX();
			// elem.getY();
			// elem.getLabel();
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
