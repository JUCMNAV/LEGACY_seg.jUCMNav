package seg.jUCMNav.importexport.z151.marshal;

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

import javax.xml.bind.JAXBElement;

import seg.jUCMNav.importexport.z151.generated.Concern;
import seg.jUCMNav.importexport.z151.generated.UCMmap;

public class UCMmapMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.UCMmap elem = (ucm.map.UCMmap) o;
		String objId = elem.getId();
		UCMmap elemZ = (UCMmap) getObject(objId, target, "createUCMmap"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (UCMmap) super.handle(elem, elemZ, true);
			elemZ.setSingleton(elem.isSingleton());
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getNodes(), elemZ.getNodes(), true);
			processList(elem.getConnections(), elemZ.getConnections(), true);
			processList(elem.getContRefs(), elemZ.getContRefs(), true);
			processList(elem.getComments(), elemZ.getComments(), true);
			processList(elem.getParentStub(), elemZ.getParentStub(), "createUCMmapParentStub", false); //$NON-NLS-1$
			
			Concern concern = (Concern) process(elem.getConcern(), null, false);
			elemZ.setConcern(concern);
			JAXBElement <Object> jaxbElem = of.createConcernElements(elemZ);
			if (concern!=null && concern.getElements().contains(elemZ)){
				concern.getElements().add(jaxbElem);
			}
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
